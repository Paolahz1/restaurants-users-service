package com.foodcourt.users_service.infrastructure.input.rest;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.foodcourt.users_service.application.dto.login.AuthResponse;
import com.foodcourt.users_service.application.dto.login.LoginCommand;
import com.foodcourt.users_service.application.handler.port.IAuthHandler;
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

@WebMvcTest(AuthController.class)
@Import(SecurityTestConfig.class)
class AuthControllerTest {

    @Autowired
    MockMvc mockMvc;

    @MockitoBean
    IAuthHandler authHandler;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    void shouldReturnJwtOnSuccessfulLogin() throws Exception {
        LoginCommand command = LoginCommand.builder()
                .email("user@gmail.con")
                .password("123")
                .build();

        AuthResponse expectedRes = AuthResponse.builder()
                .token("jwt-token-example")
                .build();

        when(authHandler.login((command))).thenReturn(expectedRes);

        mockMvc.perform(post("/users-service/users/auth/login")
                        .contentType(String.valueOf(MediaType.APPLICATION_JSON))
                        .content(objectMapper.writeValueAsString(command)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.token").value("jwt-token-example"));
    }


    }