package com.foodcourt.users_service.infrastructure.input.rest;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.foodcourt.users_service.application.dto.create.CreateEmployeeCommand;
import com.foodcourt.users_service.application.dto.create.CreateEmployeeResponse;
import com.foodcourt.users_service.application.handler.port.IEmployeeHandler;
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

@WebMvcTest(EmployeeController.class)
@Import(SecurityTestConfig.class)
class EmployeeControllerTest {


    @Autowired
    MockMvc mockMvc;

    @MockitoBean
    IEmployeeHandler handler;

    @Autowired
    private ObjectMapper objectMapper;


    @Test
    void shouldCreateEmployee() throws Exception {

        CreateEmployeeCommand command = CreateEmployeeCommand.builder()
                .firstName("John")
                .lastName("Doe")
                .identityDocument("12345678")
                .phoneNumber("+5551234")
                .email("john@example.com")
                .restaurantId(1L)
                .password("password123")
                .build();

        CreateEmployeeResponse mockResponse = CreateEmployeeResponse.builder()
                .id(1L)
                .role("EMPLOYEE")
                .restaurantId(1L)
                .build();

        when(handler.create(command)).thenReturn(mockResponse);

        mockMvc.perform(post("/users-service/employee/")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(command))
        )
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.id").value(1))
                .andExpect(jsonPath("$.role").value("EMPLOYEE"))
                .andExpect(jsonPath("restaurantId").value(1l));

    }

}