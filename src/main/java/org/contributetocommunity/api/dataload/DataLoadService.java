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
import java.util.List;

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
        DataFormatter formatter = new DataFormatter();

        log.info("Workbook has {} sheets", workbook.getNumberOfSheets());

        importJobs(workbook.getSheet("jobs"), formatter);
        importVolunteers(workbook.getSheet("volunteers"), formatter);
        importRelationship(workbook.getSheet("jobs_volunteers"), formatter);

        workbook.close();
    }

    private void importJobs(Sheet jobsSheet, DataFormatter formatter) {
        log.info("Preparing to save new Jobs...");

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
            log.info("{} new Jobs have been saved successfully!", savedJobs.size());
        }
    }

    private void importVolunteers(Sheet volunteersSheet, DataFormatter formatter) {
        log.info("Preparing to save new Volunteers...");

        List<Volunteer> volunteersToSave = new ArrayList<>();

        for (Row row : volunteersSheet) {
            if (row.getRowNum() != 0 && row.getPhysicalNumberOfCells() != 0) {
                Volunteer volunteer = new Volunteer();
                volunteer.setId(Long.parseLong(formatter.formatCellValue(row.getCell(0))));
                volunteer.setFirstName(formatter.formatCellValue(row.getCell(1)));
                volunteer.setLastName(formatter.formatCellValue(row.getCell(2)));
                volunteersToSave.add(volunteer);
                log.debug("A new Volunteer has been selected to be saved [{}]", volunteer);
            }
        }

        if (!volunteersToSave.isEmpty()) {
            List<Volunteer> savedVolunteers = volunteerRepository.saveAll(volunteersToSave);
            log.info("{} new Volunteers have been saved successfully!", savedVolunteers.size());
        }
    }

    private void importRelationship(Sheet jobsVolunteersSheet, DataFormatter formatter) {
        log.info("Preparing to save new relationship between Jobs and Volunteers...");

        for (Row row : jobsVolunteersSheet) {
            if (row.getRowNum() != 0 && row.getPhysicalNumberOfCells() != 0) {
                Long jobId = Long.parseLong(formatter.formatCellValue(row.getCell(0)));
                Long volunteerId = Long.parseLong(formatter.formatCellValue(row.getCell(1)));
                jobRepository.insertJobVolunteerRelationship(jobId, volunteerId);
                log.info("A new relationship between Job {} and Volunteer {} has been saved successfully!", jobId, volunteerId);
            }
        }
    }

}
