package com.foodcourt.users_service.infrastructure.input.rest;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.foodcourt.users_service.application.dto.CreateOwnerCommand;
import com.foodcourt.users_service.application.dto.OwnerResponse;
import com.foodcourt.users_service.application.handler.IOwnerHandler;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;

import java.time.LocalDate;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(controllers = OwnerController.class, excludeAutoConfiguration = SecurityAutoConfiguration.class)class OwnerControllerTest {

    @Autowired
    private MockMvc mockMvc; // <- MockMvc se inyecta automáticamente

    @MockitoBean
    private IOwnerHandler ownerHandler; // <- lo mockeas para que no llame al UseCase real

    // Método helper para convertir objetos a JSON, con soporte para LocalDate
    private static String asJsonString(final Object obj) {
        try {
            ObjectMapper mapper = new ObjectMapper();
            mapper.registerModule(new JavaTimeModule()); // <--- registro del módulo para fechas
            return mapper.writeValueAsString(obj);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
    @Test
    void shouldReturn201WhenOwnerCreated() throws Exception {
        CreateOwnerCommand command = CreateOwnerCommand.builder()
                .firstName("Ana")
                .lastName("Gómez")
                .identityDocument("123456789")
                .phoneNumber("+573005678910")
                .email("ana@mail.com")
                .password("1234")
                .birthDate(LocalDate.of(1990, 5, 10))
                .build();

        OwnerResponse response = OwnerResponse.builder()
                .success(true)
                .message(null)
                .build();

        when(ownerHandler.createOwner(any())).thenReturn(response);

        mockMvc.perform(post("/owners/")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(asJsonString(command)))
                .andExpect(status().isCreated());
    }
}