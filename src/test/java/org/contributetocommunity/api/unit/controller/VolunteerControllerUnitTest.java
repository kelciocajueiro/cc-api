package org.contributetocommunity.api.unit.controller;

import org.contributetocommunity.api.volunteer.VolunteerController;
import org.contributetocommunity.api.volunteer.VolunteerDTO;
import org.contributetocommunity.api.volunteer.VolunteerJobsDTO;
import org.contributetocommunity.api.volunteer.VolunteerService;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentMatchers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;

import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.Matchers.is;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.mock;
import static org.springframework.http.MediaType.APPLICATION_JSON;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(VolunteerController.class)
class VolunteerControllerUnitTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private VolunteerService mockVolunteerService;

    @WithMockUser(value = "spring")
    @Test
    @DisplayName("GET /v1/volunteers - Return all volunteers")
    void when_find_volunteers_should_return_result() throws Exception {

        given(mockVolunteerService.findAll()).willReturn(buildVolunteers());

        mockMvc
                .perform(
                        get("/v1/volunteers")
                                .contentType(APPLICATION_JSON)
                                .accept(APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(2)))
                .andExpect(jsonPath("$.[0].id", is(1)))
                .andExpect(jsonPath("$.[0].firstName", is("Cassius")));
    }

    @WithMockUser(value = "spring")
    @Test
    @DisplayName("GET /v1/volunteers - No Volunteers found (204)")
    void when_no_volunteers_found_should_return_result() throws Exception {

        given(mockVolunteerService.findAll()).willReturn(new ArrayList<>());

        mockMvc
                .perform(
                        get("/v1/volunteers")
                                .contentType(APPLICATION_JSON)
                                .accept(APPLICATION_JSON))
                .andExpect(status().isNoContent());
    }

    @WithMockUser(value = "spring")
    @Test
    @DisplayName("GET /v1/volunteers/jobs/{jobId} - Return all volunteers for a specific job")
    void when_find_volunteers_for_specific_job_should_return_result() throws Exception {

        given(mockVolunteerService.findByJobId(anyLong())).willReturn(buildVolunteers());

        mockMvc
                .perform(
                        get("/v1/volunteers/jobs/{jobId}", "1")
                                .contentType(APPLICATION_JSON)
                                .accept(APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(2)))
                .andExpect(jsonPath("$.[0].id", is(1)))
                .andExpect(jsonPath("$.[0].firstName", is("Cassius")));
    }

    @WithMockUser(value = "spring")
    @Test
    @DisplayName("GET /v1/volunteers/jobs/{jobId} - No Volunteers found (204)")
    void when_no_volunteers_found_should_return_no_content() throws Exception {

        given(mockVolunteerService.findByJobId(anyLong())).willReturn(new ArrayList<>());

        mockMvc
                .perform(
                        get("/v1/volunteers/jobs/{jobId}", "1")
                                .contentType(APPLICATION_JSON)
                                .accept(APPLICATION_JSON))
                .andExpect(status().isNoContent());
    }

    @WithMockUser(value = "spring")
    @Test
    @DisplayName("GET /v1/volunteers/jobs/details - Return all volunteers with job application details")
    void when_find_volunteers_with_details_should_return_result() throws Exception {

        Page<VolunteerJobsDTO> result = mock(Page.class);

        given(mockVolunteerService.findWithPositionDetails(ArgumentMatchers.isA(Pageable.class))).willReturn(result);

        mockMvc
                .perform(
                        get("/v1/volunteers/jobs/details")
                                .contentType(APPLICATION_JSON)
                                .accept(APPLICATION_JSON))
                .andExpect(status().isOk());
    }

    private List<VolunteerDTO> buildVolunteers() {
        return List.of(
                VolunteerDTO.builder().id(1L).firstName("Cassius").lastName("Johnson").build(),
                VolunteerDTO.builder().id(2L).firstName("Norah").lastName("McAllister").build()
        );
    }

}
