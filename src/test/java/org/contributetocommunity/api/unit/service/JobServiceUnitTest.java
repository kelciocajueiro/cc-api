package org.contributetocommunity.api.unit.service;

import org.contributetocommunity.api.job.Job;
import org.contributetocommunity.api.job.JobMapper;
import org.contributetocommunity.api.job.JobRepository;
import org.contributetocommunity.api.job.JobService;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mapstruct.factory.Mappers;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Spy;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.BDDMockito.given;

@ExtendWith(MockitoExtension.class)
class JobServiceUnitTest {

    @InjectMocks
    private JobService jobService;

    @Mock
    private JobRepository mockJobRepository;

    @Spy
    private JobMapper jobMapper = Mappers.getMapper(JobMapper.class);

    @Test
    @DisplayName("Test if there are available Jobs")
    void test_if_there_are_available_jobs() {
        given(mockJobRepository.findAll()).willReturn(buildJobs());
        assertNotNull(jobService.findAll());
    }

    private List<Job> buildJobs() {
        Job job = new Job();
        job.setId(1L);
        job.setName("Front Desk");
        job.setDescription("Sign papers");
        return List.of(job);
    }

}
