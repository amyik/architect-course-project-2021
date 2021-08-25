package com.sds.devops.backend.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.Base64;
import java.util.Map;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(MockitoExtension.class)
class SampleControllerTest {

    @InjectMocks
    private SampleController subject;

    MockMvc mockMvc;

    @BeforeEach
    void setUp() {
        mockMvc = MockMvcBuilders.standaloneSetup(subject).build();
    }

    @AfterEach
    void tearDown() {
    }

    @Test
    void sample() throws Exception {
        mockMvc.perform(get("/sample"))
                .andExpect(status().isOk())
                .andExpect(content().string("sample api works!"));
    }

    @Test
    void jwt_payload_decode() throws JsonProcessingException {

        String payload = "eyJleHAiOjE2Mjk4OTE0NzksImlhdCI6MTYyOTg1NTQ3OSwiYXV0aF90aW1lIjowLCJqdGkiOiI5YmQ3YTJjMS0wM2RlLTRjYzctYjk1ZC0zOTliYzA3NDAwNjgiLCJpc3MiOiJodHRwOi8vMzQuNjQuMjIwLjE2Mjo4MDgwL2F1dGgvcmVhbG1zL2Rldm9wcyIsImF1ZCI6Im15Y2xpZW50Iiwic3ViIjoiMjZlN2M1ZDEtMTczMS00NDkyLTk0YzUtM2YzZmVlYWE4YWQ4IiwidHlwIjoiSUQiLCJhenAiOiJteWNsaWVudCIsInNlc3Npb25fc3RhdGUiOiJjNTU3YzQ5Yi0wYjVkLTQ3OWYtYjUyNS02MGJkZjk1OTBhMjMiLCJhdF9oYXNoIjoiVjdNYVNPQVdqbUZvd3dISGhiWjJkQSIsImFjciI6IjEiLCJzaWQiOiJjNTU3YzQ5Yi0wYjVkLTQ3OWYtYjUyNS02MGJkZjk1OTBhMjMiLCJlbWFpbF92ZXJpZmllZCI6dHJ1ZSwibmFtZSI6Im1pbnNlb2sgeW9vbiIsInByZWZlcnJlZF91c2VybmFtZSI6Im15dXNlciIsImdpdmVuX25hbWUiOiJtaW5zZW9rIiwiZmFtaWx5X25hbWUiOiJ5b29uIiwiZW1haWwiOiJtbS55b29uQHNhbXN1bmcuY29tIn0";

        byte[] decode = Base64.getDecoder().decode(payload);
        String decodedPayload = new String(decode);

        ObjectMapper objectMapper = new ObjectMapper();
        Map<String, String> userInfoMap = (Map<String, String>)objectMapper.readValue(decodedPayload, Map.class);
        assertThat(userInfoMap.get("email")).isEqualTo("mm.yoon@samsung.com");
    }
}