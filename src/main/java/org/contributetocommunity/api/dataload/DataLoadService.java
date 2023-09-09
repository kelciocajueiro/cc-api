package org.contributetocommunity.api.dataload;

import lombok.extern.slf4j.Slf4j;
import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.contributetocommunity.api.job.Job;
import org.contributetocommunity.api.job.JobRepository;
import org.contributetocommunity.api.volunteer.Volunteer;
import org.contributetocommunity.api.volunteer.VolunteerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Slf4j
@Service
public class DataLoadService {

    private final JobRepository jobRepository;
    private final VolunteerRepository volunteerRepository;

    @Autowired
    public DataLoadService(JobRepository jobRepository, VolunteerRepository volunteerRepository) {
        this.jobRepository = jobRepository;
        this.volunteerRepository = volunteerRepository;
    }

    @Transactional
    public void loadDataFromSpreadsheet() throws IOException {

        log.info("Importing the Spreadsheet");

        InputStream inputStream = new ClassPathResource("spreadsheets/JobVolunteers.xlsx").getInputStream();

        Workbook workbook = new XSSFWorkbook(inputStream);

        log.info("Workbook has {} sheets", workbook.getNumberOfSheets());

        List<DataLoadDTO> jobVolunteerDtoList = importRelationship(workbook.getSheet("jobs_volunteers"));

        List<Job> savedJobs = importJobs(workbook.getSheet("jobs"));
        importVolunteers(workbook.getSheet("volunteers"), savedJobs, jobVolunteerDtoList);

        workbook.close();
    }

    private List<DataLoadDTO> importRelationship(Sheet jobsVolunteersSheet) {
        log.info("Preparing to save new relationship between Jobs and Volunteers...");

        DataFormatter formatter = new DataFormatter();

        List<DataLoadDTO> jobVolunteerDtos = new ArrayList<>();

        for (Row row : jobsVolunteersSheet) {
            if (row.getRowNum() != 0 && row.getPhysicalNumberOfCells() != 0) {
                Long jobId = Long.parseLong(formatter.formatCellValue(row.getCell(0)));
                Long volunteerId = Long.parseLong(formatter.formatCellValue(row.getCell(1)));
                jobVolunteerDtos.add(new DataLoadDTO(jobId, volunteerId));
            }
        }

        return jobVolunteerDtos;
    }

    private List<Job> importJobs(Sheet jobsSheet) {
        log.info("Preparing to save new Jobs...");

        DataFormatter formatter = new DataFormatter();

        List<Job> jobsToSave = new ArrayList<>();

        for (Row row : jobsSheet) {
            /* Skip the first line of field names and consumes only filled cells */
            if (row.getRowNum() != 0 && row.getPhysicalNumberOfCells() != 0) {
                Job job = new Job();
                job.setId(Long.parseLong(formatter.formatCellValue(row.getCell(0))));
                job.setName(formatter.formatCellValue(row.getCell(1)));
                job.setDescription(formatter.formatCellValue(row.getCell(2)));
                jobsToSave.add(job);
                log.debug("A new Job has been selected to be saved [{}]", job);
            }
        }

        if (!jobsToSave.isEmpty()) {
            List<Job> savedJobs = jobRepository.saveAll(jobsToSave);
            log.info("{} Jobs have been saved successfully!", savedJobs.size());
            return savedJobs;
        } else {
            return Collections.emptyList();
        }
    }

    private void importVolunteers(Sheet volunteersSheet, List<Job> jobs, List<DataLoadDTO> jobVolunteerDtoList) {
        log.info("Preparing to save new Volunteers...");

        DataFormatter formatter = new DataFormatter();

        List<Volunteer> volunteersToSave = new ArrayList<>();

        for (Row row : volunteersSheet) {
            if (row.getRowNum() != 0 && row.getPhysicalNumberOfCells() != 0) {
                Volunteer volunteer = new Volunteer();
                volunteer.setId(Long.parseLong(formatter.formatCellValue(row.getCell(0))));
                volunteer.setFirstName(formatter.formatCellValue(row.getCell(1)));
                volunteer.setLastName(formatter.formatCellValue(row.getCell(2)));

                /* Filter the Id of Jobs a Volunteer has applied for */
                List<Long> jobIdsOfVolunteers = jobVolunteerDtoList.stream()
                        .filter(dto -> volunteer.getId().equals(dto.getVolunteerId()))
                        .map(DataLoadDTO::getJobId)
                        .collect(Collectors.toList());

                /* Filter the persisted Jobs that match with Volunteers in DTO list */
                Set<Job> filteredJobs = jobs.stream()
                        .filter(job -> jobIdsOfVolunteers.stream()
                                .anyMatch(jobId -> job.getId().equals(jobId)))
                        .collect(Collectors.toSet());

                volunteer.setJobs(filteredJobs);
                volunteersToSave.add(volunteer);

                log.debug("A new Volunteer has been selected to be saved [{}]", volunteer);
            }
        }

        if (!volunteersToSave.isEmpty()) {
            List<Volunteer> savedVolunteers = volunteerRepository.saveAll(volunteersToSave);
            log.info("{} new Volunteers have been saved successfully!", savedVolunteers.size());
        }
    }

}
