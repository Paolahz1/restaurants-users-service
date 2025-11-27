package com.foodcourt.users_service.infrastructure.input.rest;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.foodcourt.users_service.application.dto.create.CreateOwnerCommand;
import com.foodcourt.users_service.application.dto.create.CreateOwnerResponse;
import com.foodcourt.users_service.application.handler.port.IOwnerHandler;
import com.foodcourt.users_service.infrastructure.security.SecurityTestConfig;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.context.annotation.Import;
import org.springframework.http.MediaType;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;

import java.time.LocalDate;


import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(OwnerController.class)
@Import(SecurityTestConfig.class)
class OwnerControllerTest {

    @Autowired
    MockMvc mockMvc;

    @MockitoBean
    IOwnerHandler handler;

    @Autowired
    private ObjectMapper objectMapper;


    @Test
    void shouldCreateOwner() throws Exception {
        CreateOwnerCommand command = CreateOwnerCommand.builder()
                .firstName("John")
                .lastName("Doe")
                .identityDocument("12345678")
                .phoneNumber("5551234")
                .email("john@example.com")
                .password("password123")
                .birthDate(LocalDate.of(1990, 02, 03))
                .build();
        CreateOwnerResponse mockResponse = CreateOwnerResponse.builder()
                .success(true)
                .message("Ok")
                .build();

        when(handler.createOwner(command)).thenReturn(mockResponse);

        mockMvc.perform(post("/users-service/owner/")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(command))
                )
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.success").value(true))
                .andExpect(jsonPath("$.message").value("Ok"));
    }
}