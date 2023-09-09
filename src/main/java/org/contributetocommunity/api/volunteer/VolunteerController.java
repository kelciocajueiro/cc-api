package org.contributetocommunity.api.volunteer;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/v1/volunteers")
public class VolunteerController implements VolunteerApi {

    private final VolunteerService volunteerService;

    @Autowired
    public VolunteerController(VolunteerService volunteerService) {
        this.volunteerService = volunteerService;
    }

    @GetMapping
    public ResponseEntity<List<VolunteerDTO>> getVolunteers() {
        List<VolunteerDTO> response = volunteerService.findAll();
        return response.isEmpty() ? ResponseEntity.noContent().build() : ResponseEntity.ok(response);
    }

    @GetMapping(value = "/jobs/{jobId}")
    public ResponseEntity<List<VolunteerDTO>> getVolunteersByJobId(@PathVariable long jobId) {
        List<VolunteerDTO> response = volunteerService.findByJobId(jobId);
        return response.isEmpty() ? ResponseEntity.noContent().build() : ResponseEntity.ok(response);
    }

    @GetMapping("/jobs/details")
    public Page<VolunteerJobsDTO> getVolunteersWithJobs(Pageable pageable) {
        return volunteerService.findWithPositionDetails(pageable);
    }

}
