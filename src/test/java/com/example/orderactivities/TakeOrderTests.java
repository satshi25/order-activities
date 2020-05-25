package com.example.orderactivities;

import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest
public class TakeOrderTests {

    private static final Logger LOG = LoggerFactory.getLogger(TakeOrderTests.class);

    @Autowired
    private MockMvc mockMvc;

    @Test
    public void takeOrderTest_shouldReturnOk() throws Exception {
        String requestBody = "{\"status\": \"TAKEN\"}";
        mockMvc.perform(MockMvcRequestBuilders.patch("/orders/123456")
                .contentType(MediaType.APPLICATION_JSON_VALUE).content(requestBody))
                .andExpect(status().isOk());

    }

}
