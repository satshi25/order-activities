package com.example.orderactivities.integration;

import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import com.example.orderactivities.dto.OrderRequest;
import com.example.orderactivities.dto.OrderResponse;
import com.example.orderactivities.entity.OrderEntity;
import com.example.orderactivities.repository.OrderRepository;
import com.example.orderactivities.service.OrderService;
import com.google.gson.Gson;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.*;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Optional;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class IntegrationTests {

	@Autowired
	TestRestTemplate restTemplate;

	@MockBean
	private OrderRepository orderRepository;

	@MockBean
	private OrderService orderService;


	@Test
	void testOrderAllLayers_shouldReturnValidResults() throws  Exception{
		String requestBody = "{\n" +
				"\"origin\":[\"14.568399\",\"121.076602\"],\n" +
				"\"destination\":[\"14.556548\",\"121.054194\"]\n" +
				"}";
		HttpHeaders headers = new HttpHeaders();
		headers.set("Content-Type", MediaType.APPLICATION_JSON_VALUE);
		HttpEntity<String> entity = new HttpEntity<String>(requestBody, headers);
		ResponseEntity<String> result = restTemplate.exchange("/orders",HttpMethod.POST,entity, String.class);

		Assertions.assertThat(result.getStatusCode()).isEqualTo(HttpStatus.OK);
	}

}
