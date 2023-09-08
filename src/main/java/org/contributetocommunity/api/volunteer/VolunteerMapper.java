package org.contributetocommunity.api.volunteer;

import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface VolunteerMapper {

    VolunteerDTO toDTO(Volunteer volunteer);
    
    Volunteer toEntity(VolunteerDTO volunteerDTO);

}
