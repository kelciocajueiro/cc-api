package org.contributetocommunity.api.integration.repository;

import org.contributetocommunity.api.volunteer.VolunteerRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
class VolunteerRepositoryIT extends PostgresDockerConfiguration {

    @Autowired
    private VolunteerRepository volunteerRepository;

    @Test
    @DisplayName("Test if can find Volunteers by Job Id")
    void when_search_by_job_id_should_return_volunteers_list() {
        assertThat(volunteerRepository.findByJobs_Id(1L)).isNotNull().isNotEmpty().hasSize(9);
    }

    @Test
    @DisplayName("Test if there are no Volunteers by Job Id")
    void when_search_by_job_id_should_return_empty_result() {
        assertThat(volunteerRepository.findByJobs_Id(294L)).isNotNull().isEmpty();
    }

}
