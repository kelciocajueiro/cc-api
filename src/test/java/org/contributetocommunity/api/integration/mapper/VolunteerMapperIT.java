package org.contributetocommunity.api.integration.mapper;

import org.contributetocommunity.api.job.Job;
import org.contributetocommunity.api.volunteer.Volunteer;
import org.contributetocommunity.api.volunteer.VolunteerDTO;
import org.contributetocommunity.api.volunteer.VolunteerJobsDTO;
import org.contributetocommunity.api.volunteer.VolunteerMapper;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest(properties = "spring.main.lazy-initialization=true")
@ActiveProfiles("dev")
class VolunteerMapperIT {

    @Autowired
    private VolunteerMapper volunteerMapper;

    @Test
    @DisplayName("Test if the Volunteer Mapper returns null when param is null")
    void check_if_job_mapper_returns_null() {
        assertThat(volunteerMapper.toVolunteerDto(null)).isNull();
        assertThat(volunteerMapper.toVolunteerJobsDto(null)).isNull();
        assertThat(volunteerMapper.toEntity(null)).isNull();
    }

    @Test
    @DisplayName("Test if the Volunteer Mapper converts an Entity to VolunteerDTO")
    void test_if_volunteer_mapper_converts_entity_to_dto() {

        Volunteer volunteer = buildVolunteer();

        VolunteerDTO dto = volunteerMapper.toVolunteerDto(volunteer);

        assertThat(volunteer.getId()).isEqualTo(dto.getId());
        assertThat(volunteer.getFirstName()).isEqualTo(dto.getFirstName());
        assertThat(volunteer.getLastName()).isEqualTo(dto.getLastName());
    }

    @Test
    @DisplayName("Test if the Volunteer Mapper converts a VolunteerDTO to an Entity")
    void test_if_volunteer_mapper_converts_dto_to_entity() {

        VolunteerDTO dto = VolunteerDTO.builder().id(1L).firstName("John").lastName("Doe").build();

        Volunteer volunteer = volunteerMapper.toEntity(dto);

        assertThat(dto.getId()).isEqualTo(volunteer.getId());
        assertThat(dto.getFirstName()).isEqualTo(volunteer.getFirstName());
        assertThat(dto.getLastName()).isEqualTo(volunteer.getLastName());
    }

    @Test
    @DisplayName("Test if the Volunteer Mapper converts an Entity to a VolunteerJobsDTO")
    void test_if_volunteer_mapper_converts_entity_to_volunteerjobs_dto() {

        Volunteer volunteer = buildVolunteer();

        VolunteerJobsDTO dto = volunteerMapper.toVolunteerJobsDto(volunteer);

        assertThat(dto.getVolunteerDto().getId()).isEqualTo(volunteer.getId());
        assertThat(dto.getVolunteerDto().getFirstName()).isEqualTo(volunteer.getFirstName());
        assertThat(dto.getVolunteerDto().getLastName()).isEqualTo(volunteer.getLastName());

        List<Job> jobs = new ArrayList<>(volunteer.getJobs());
        jobs.sort(Comparator.comparing(Job::getId));

        assertThat(dto.getJobDtoList().get(0).getId()).isEqualTo(jobs.get(0).getId());
        assertThat(dto.getJobDtoList().get(0).getName()).isEqualTo(jobs.get(0).getName());
        assertThat(dto.getJobDtoList().get(0).getDescription()).isEqualTo(jobs.get(0).getDescription());
    }

    private Volunteer buildVolunteer() {
        Volunteer volunteer = new Volunteer();
        volunteer.setId(1L);
        volunteer.setFirstName("John");
        volunteer.setLastName("Doe");

        Job job = new Job();
        job.setId(1L);
        job.setName("Front Desk");
        job.setDescription("Description of a Front Desk job");

        volunteer.addJob(job);

        return volunteer;
    }

}

