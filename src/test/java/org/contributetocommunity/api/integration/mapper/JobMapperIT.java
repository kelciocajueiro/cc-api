package org.contributetocommunity.api.integration.mapper;

import org.contributetocommunity.api.job.Job;
import org.contributetocommunity.api.job.JobDTO;
import org.contributetocommunity.api.job.JobMapper;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest(properties = "spring.main.lazy-initialization=true")
@ActiveProfiles("dev")
class JobMapperIT {

    @Autowired
    private JobMapper jobMapper;

    @Test
    @DisplayName("Test if the Job Mapper returns null when param is null")
    void check_if_job_mapper_returns_null() {
        assertThat(jobMapper.toJobDto(null)).isNull();
        assertThat(jobMapper.toEntity(null)).isNull();
    }

    @Test
    @DisplayName("Test if the Job Mapper converts an Entity to DTO")
    void check_if_job_mapper_converts_entity_to_dto() {

        Job job = new Job();
        job.setId(1L);
        job.setName("Front Desk");
        job.setDescription("Manage the front desk");

        JobDTO dto = jobMapper.toJobDto(job);

        assertThat(job.getId()).isEqualTo(dto.getId());
        assertThat(job.getName()).isEqualTo(dto.getName());
        assertThat(job.getDescription()).isEqualTo(dto.getDescription());
    }

    @Test
    @DisplayName("Test if the Job Mapper converts a DTO to an Entity")
    void check_if_job_mapper_converts_dto_to_entity() {

        JobDTO dto = JobDTO.builder()
                .id(4L)
                .name("Food Service")
                .description("Handle food logistics, preparation & serving")
                .build();

        Job job = jobMapper.toEntity(dto);

        assertThat(dto.getId()).isEqualTo(job.getId());
        assertThat(dto.getName()).isEqualTo(job.getName());
        assertThat(dto.getDescription()).isEqualTo(job.getDescription());
    }

}
