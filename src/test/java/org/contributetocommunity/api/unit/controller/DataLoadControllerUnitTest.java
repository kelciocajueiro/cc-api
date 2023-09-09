package org.contributetocommunity.api.unit.controller;

import org.contributetocommunity.api.dataload.DataLoadController;
import org.contributetocommunity.api.dataload.DataLoadService;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;

import java.io.IOException;

import static org.mockito.Mockito.doThrow;
import static org.springframework.http.MediaType.APPLICATION_JSON;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(DataLoadController.class)
class DataLoadControllerUnitTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private DataLoadService mockDataLoadService;

    @WithMockUser(value = "spring")
    @Test
    @DisplayName("GET /v1/data-load - Load database")
    void when_data_loads_should_save_successfully() throws Exception {
        mockMvc
                .perform(
                        get("/v1/data-load")
                                .contentType(APPLICATION_JSON)
                                .accept(APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().contentType(APPLICATION_JSON));
    }

    @WithMockUser(value = "spring")
    @Test
    @DisplayName("GET /v1/data-load - Throw exception")
    void when_data_load_fails_should_throw_exception() throws Exception {

        doThrow(new IOException()).when(mockDataLoadService).loadDataFromSpreadsheet();

        mockMvc
                .perform(
                        get("/v1/data-load")
                                .contentType(APPLICATION_JSON)
                                .accept(APPLICATION_JSON))
                .andExpect(status().isInternalServerError());
    }

}
