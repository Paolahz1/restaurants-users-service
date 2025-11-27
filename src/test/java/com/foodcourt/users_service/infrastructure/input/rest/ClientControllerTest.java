package com.foodcourt.users_service.infrastructure.input.rest;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.foodcourt.users_service.application.dto.create.CreateClientCommand;
import com.foodcourt.users_service.application.dto.create.CreateClientResponse;
import com.foodcourt.users_service.application.handler.port.IClientHandler;
import com.foodcourt.users_service.infrastructure.security.SecurityTestConfig;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.context.annotation.Import;
import org.springframework.http.MediaType;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(ClientController.class)
@Import(SecurityTestConfig.class)
class ClientControllerTest {

    @Autowired
    MockMvc mockMvc;

    @MockitoBean
    IClientHandler handler;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    void shouldCreateClient() throws Exception {

        CreateClientCommand command = CreateClientCommand.builder()
                .firstName("John")
                .lastName("Doe")
                .identityDocument("12345678")
                .phoneNumber("555-1234")
                .email("john@example.com")
                .password("password123")
                .build();


        CreateClientResponse mockResponse = CreateClientResponse.builder()
                .id(10L)
                .role("CLIENT")
                .build();

        when(handler.create(command)).thenReturn(mockResponse);

        mockMvc.perform(post("/users-service/client/")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(command))
            )
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.id").value(10))
                .andExpect(jsonPath("$.role").value("CLIENT"));
    }

}