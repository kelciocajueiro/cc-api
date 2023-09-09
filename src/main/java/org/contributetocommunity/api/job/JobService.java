package org.contributetocommunity.api.job;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class JobService {

    private final JobRepository jobRepository;
    private final JobMapper jobMapper;

    @Autowired
    public JobService(JobRepository jobRepository, JobMapper jobMapper) {
        this.jobRepository = jobRepository;
        this.jobMapper = jobMapper;
    }

    public List<JobDTO> findAll() {
        return jobRepository.findAll().stream()
                .map(jobMapper::toJobDto)
                .collect(Collectors.toList());
    }

}
