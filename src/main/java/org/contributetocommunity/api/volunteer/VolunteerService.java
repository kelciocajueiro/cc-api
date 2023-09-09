package org.contributetocommunity.api.volunteer;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class VolunteerService {

    private final VolunteerRepository volunteerRepository;
    private final VolunteerMapper volunteerMapper;

    @Autowired
    public VolunteerService(VolunteerRepository volunteerRepository, VolunteerMapper volunteerMapper) {
        this.volunteerRepository = volunteerRepository;
        this.volunteerMapper = volunteerMapper;
    }

    public List<VolunteerDTO> findAll() {
        return volunteerRepository.findAll()
                .stream()
                .map(volunteerMapper::toVolunteerDto)
                .collect(Collectors.toList());
    }

    public List<VolunteerDTO> findByJobId(Long jobId) {
        return volunteerRepository.findByJobs_Id(jobId)
                .stream()
                .map(volunteerMapper::toVolunteerDto)
                .collect(Collectors.toList());
    }

    public Page<VolunteerJobsDTO> findWithPositionDetails(Pageable pageable) {
        return volunteerRepository.findAll(pageable)
                .map(volunteerMapper::toVolunteerJobsDto);
    }

}
