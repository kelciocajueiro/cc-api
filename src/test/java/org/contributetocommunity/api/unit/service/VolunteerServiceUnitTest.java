package org.contributetocommunity.api.unit.service;

import org.contributetocommunity.api.volunteer.*;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mapstruct.factory.Mappers;
import org.mockito.ArgumentMatchers;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Spy;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.BDDMockito.given;

@ExtendWith(MockitoExtension.class)
class VolunteerServiceUnitTest {

    @InjectMocks
    private VolunteerService volunteerService;

    @Mock
    private VolunteerRepository mockVolunteerRepository;

    @Mock
    Page<VolunteerJobDetailsDTO> result;

    @Spy
    private VolunteerMapper volunteerMapper = Mappers.getMapper(VolunteerMapper.class);

    @Test
    @DisplayName("Test if there are Volunteers available in the database")
    void test_if_there_are_volunteers_available_db() {
        given(mockVolunteerRepository.findAll()).willReturn(buildVolunteers());
        assertNotNull(volunteerService.findAll());
    }

    @Test
    @DisplayName("Test if there are Volunteers in a specific Job application")
    void test_if_there_are_volunteers_in_specific_job_application() {
        given(mockVolunteerRepository.findByJobId(anyLong())).willReturn(buildVolunteers());
        assertNotNull(volunteerService.findByJobId(1L));
    }

    @Test
    @DisplayName("Test if there are Volunteers with Job details")
    void test_if_there_are_volunteers_with_job_details() {
        given(mockVolunteerRepository.findWithPositionDetails(ArgumentMatchers.isA(Pageable.class))).willReturn(result);
        assertNotNull(volunteerService.findWithPositionDetails(Pageable.ofSize(1)));
    }

    private List<Volunteer> buildVolunteers() {
        Volunteer volunteer = new Volunteer();
        volunteer.setId(1L);
        volunteer.setFirstName("John");
        volunteer.setLastName("Jones");
        return List.of(volunteer);
    }

}
