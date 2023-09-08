package org.contributetocommunity.api.unit.controller;

import org.contributetocommunity.api.job.JobController;
import org.contributetocommunity.api.job.JobDTO;
import org.contributetocommunity.api.job.JobService;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;

import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.Matchers.is;
import static org.mockito.BDDMockito.given;
import static org.springframework.http.MediaType.APPLICATION_JSON;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(JobController.class)
class JobControllerUnitTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private JobService mockJobService;

    @WithMockUser(value = "spring")
    @Test
    @DisplayName("GET /v1/jobs - Return all available Jobs")
    void when_find_jobs_should_return_available_jobs() throws Exception {

        given(mockJobService.findAll()).willReturn(buildJobs());

        mockMvc
                .perform(
                        get("/v1/jobs")
                                .contentType(APPLICATION_JSON)
                                .accept(APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(1)))
                .andExpect(jsonPath("$.[0].id", is(1)));
    }

    @WithMockUser(value = "spring")
    @Test
    @DisplayName("GET /v1/jobs - No Jobs found (204)")
    void when_find_no_jobs_should_return_204() throws Exception {

        given(mockJobService.findAll()).willReturn(new ArrayList<>());

        mockMvc
                .perform(
                        get("/v1/jobs")
                                .contentType(APPLICATION_JSON)
                                .accept(APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isNoContent());
    }

    private List<JobDTO> buildJobs() {
        return List.of(JobDTO.builder().id(1L).name("Front Desk").description("This is a Front Desk job").build());
    }

}
