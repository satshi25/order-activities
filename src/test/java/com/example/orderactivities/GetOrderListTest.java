package com.example.orderactivities;

import com.example.orderactivities.service.OrderService;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.web.util.UriComponentsBuilder;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest
public class GetOrderListTest {
    private static final Logger LOG = LoggerFactory.getLogger(GetOrderListTest.class);

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private OrderService orderService;

    @Test
    public void getOrderListTest_shouldReturnOK() throws Exception {

        mockMvc.perform(MockMvcRequestBuilders.get("/orders")
                            .param("page", "1")
                            .param("limit", "10").contentType(MediaType.APPLICATION_JSON_VALUE))
                .andExpect(status().isOk());
    }

    @Test
    public void getOrderListTest_withPageDoesNotStartWithOne_shouldReturnOK() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/orders")
                .param("page", "0")
                .param("limit", "10").contentType(MediaType.APPLICATION_JSON_VALUE))
                .andExpect(status().isBadRequest());
    }

    @Test
    public void getOrderListTest_withPageLimitNotNumeric_shouldReturnOK() throws Exception {

        mockMvc.perform(MockMvcRequestBuilders.get("/orders")
                .param("page", "one")
                .param("limit", "ten").contentType(MediaType.APPLICATION_JSON_VALUE))
                .andExpect(status().isBadRequest());
    }

    @Test
    public void getOrderListTest_withPageInvalidValue_shouldReturnOK() throws Exception {

        mockMvc.perform(MockMvcRequestBuilders.get("/orders")
                .param("page", "-1")
                .param("limit", "10").contentType(MediaType.APPLICATION_JSON_VALUE))
                .andExpect(status().isBadRequest());
    }
}
