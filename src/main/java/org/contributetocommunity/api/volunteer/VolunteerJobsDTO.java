package org.contributetocommunity.api.volunteer;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonUnwrapped;
import lombok.*;
import org.contributetocommunity.api.job.JobDTO;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class VolunteerJobsDTO {

    @JsonUnwrapped
    private VolunteerDTO volunteerDto;

    @JsonProperty("jobs")
    private List<JobDTO> jobDtoList;

    public void addJobDto(JobDTO jobDto) {
        if (jobDtoList == null) {
            jobDtoList = new ArrayList<>();
        }

        jobDtoList.add(jobDto);
    }

}
