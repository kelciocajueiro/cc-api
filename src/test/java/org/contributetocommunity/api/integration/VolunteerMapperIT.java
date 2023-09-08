package org.contributetocommunity.api.integration;

import org.contributetocommunity.api.volunteer.Volunteer;
import org.contributetocommunity.api.volunteer.VolunteerDTO;
import org.contributetocommunity.api.volunteer.VolunteerMapper;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest(properties = "spring.main.lazy-initialization=true")
@ActiveProfiles("dev")
class VolunteerMapperIT {

    @Autowired
    private VolunteerMapper volunteerMapper;

    @Test
    @DisplayName("Test if the Volunteer Mapper converts an Entity to DTO")
    void check_if_volunteer_mapper_converts_entity_to_dto() {

        Volunteer volunteer = new Volunteer();
        volunteer.setId(1L);
        volunteer.setFirstName("John");
        volunteer.setLastName("Doe");

        VolunteerDTO dto = volunteerMapper.toDTO(volunteer);

        assertThat(volunteer.getId()).isEqualTo(dto.getId());
        assertThat(volunteer.getFirstName()).isEqualTo(dto.getFirstName());
        assertThat(volunteer.getLastName()).isEqualTo(dto.getLastName());
    }

    @Test
    @DisplayName("Test if the Volunteer Mapper converts a DTO to an Entity")
    void check_if_volunteer_mapper_converts_dto_to_entity() {

        VolunteerDTO dto = VolunteerDTO.builder()
                .id(1L)
                .firstName("John")
                .lastName("Doe")
                .build();

        Volunteer volunteer = volunteerMapper.toEntity(dto);

        assertThat(dto.getId()).isEqualTo(volunteer.getId());
        assertThat(dto.getFirstName()).isEqualTo(volunteer.getFirstName());
        assertThat(dto.getLastName()).isEqualTo(volunteer.getLastName());
    }

}

