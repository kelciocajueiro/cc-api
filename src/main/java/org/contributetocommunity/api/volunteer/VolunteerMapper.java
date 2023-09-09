package org.contributetocommunity.api.volunteer;

import org.contributetocommunity.api.job.JobDTO;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface VolunteerMapper {

    VolunteerDTO toVolunteerDto(Volunteer volunteer);

    Volunteer toEntity(VolunteerDTO volunteerDTO);

    default VolunteerJobsDTO toVolunteerJobsDto(Volunteer volunteer) {

        if (volunteer == null) {
            return null;
        }

        VolunteerJobsDTO dto = new VolunteerJobsDTO();

        dto.setVolunteerDto(VolunteerDTO.builder()
                .id(volunteer.getId())
                .firstName(volunteer.getFirstName())
                .lastName(volunteer.getLastName()).build());

        volunteer.getJobs().forEach(job -> dto.addJobDto(
                JobDTO.builder()
                        .id(job.getId())
                        .name(job.getName())
                        .description(job.getDescription())
                        .build()));
        return dto;
    }

}
