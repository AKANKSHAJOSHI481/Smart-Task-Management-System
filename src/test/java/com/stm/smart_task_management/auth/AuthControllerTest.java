package com.stm.smart_task_management.auth;

import com.stm.smart_task_management.auth.dto.RegisterRequest;
import com.stm.smart_task_management.integrationTest.AbstractIntegrationTest;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


public class AuthControllerTest extends AbstractIntegrationTest {
    @Autowired
    MockMvc mockMvc;

    @Autowired
    ObjectMapper objectMapper;

    @Test
    void shouldRegisterUser() throws Exception{
        RegisterRequest request = new RegisterRequest(
                "Akanksha Joshi",
                "joshiakanksha@email.com",
                "password"
        );
        mockMvc.perform(
                post("/api/auth/register")
                        .header(
                                "X-Tenant-ID",
                                "tenant_1"
                        )
                        .contentType(
                                MediaType.APPLICATION_JSON
                        )
                        .content(
                                objectMapper.writeValueAsString(request)
                        )
        )
                .andExpect(status().isOk());
    }
}
