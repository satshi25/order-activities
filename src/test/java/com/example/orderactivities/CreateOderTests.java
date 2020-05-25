package com.example.orderactivities;

import com.example.orderactivities.config.AppConfig;
import com.example.orderactivities.entity.DistanceMatrixResponse;
import com.example.orderactivities.entity.OrderRequest;
import com.example.orderactivities.service.DistanceMatrixService;
import com.example.orderactivities.service.OrderService;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.Spy;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.util.ReflectionTestUtils;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.util.ArrayList;
import java.util.Arrays;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@WebMvcTest
@ExtendWith(SpringExtension.class)
@EnableConfigurationProperties(value = AppConfig.class)
public class CreateOderTests {

	private static final Logger LOG = LoggerFactory.getLogger(CreateOderTests.class);

	@Autowired
	MockMvc mockMvc;

	@Autowired
	AppConfig config;

	@MockBean
	OrderService orderService;

	@Spy
	DistanceMatrixService distanceService;
	
	@Test
	public void testCreateOrders_shouldReturnStatusOK() throws Exception {
		String requestBody = "{\n" +
				"\"origin\":[\"14.56\",\"121.07\"],\n" +
				"\"destination\":[\"14.55\",\"121.02\"]\n" +
				"}";
		mockMvc.perform(MockMvcRequestBuilders.post("/orders")
				.content(requestBody).contentType(MediaType.APPLICATION_JSON_VALUE))
			.andExpect(status().isOk());
	}

	@Test
	public void testCreateOrders_withOutOfRangeCoordinates_shouldReturnStatusBadRequest() throws Exception {
		String requestBody = "{\n" +
				"\"origin\":[\"14.56\",\"181.00\"],\n" +
				"\"destination\":[\"14.55\",\"221.02\"]\n" +
				"}";
		mockMvc.perform(MockMvcRequestBuilders.post("/orders")
				.content(requestBody).contentType(MediaType.APPLICATION_JSON_VALUE))
				.andExpect(status().isBadRequest());
	}

	@Test
	public void testCreateOrders_withNonStringCoordinates_shouldReturnStatusBadRequest() throws  Exception{
		String requestBody = "{\n" +
				"    \"origin\": [12, 2]\n" +
				"    \"destination\": [10, 12]\n" +
				"}";
		mockMvc.perform(MockMvcRequestBuilders.post("/orders")
			.contentType(MediaType.APPLICATION_JSON_VALUE).content(requestBody))
				.andExpect(status().isBadRequest());
	}

	@Test
	public void testCreateOrders_withNonNumericString_shouldReturnStatusBadRequest() throws  Exception{
		String requestBody = "{\n" +
				"    \"origin\": [\"START_LATITUDE\", \"START_LONGITUDE\"],\n" +
				"    \"destination\": [\"END_LATITUDE\", \"END_LONGITUDE\"]\n" +
				"}";
		mockMvc.perform(MockMvcRequestBuilders.post("/orders")
			.content(requestBody).contentType(MediaType.APPLICATION_JSON_VALUE))
				.andExpect(status().isBadRequest());
	}

	@Test
	public void testCreateOrders_withNullCoordinates_shouldReturnStatusBadRequest() throws  Exception{
		String requestBody = "{\"origin\": null,\"destination\": null}";
		mockMvc.perform(MockMvcRequestBuilders.post("/orders")
				.content(requestBody).contentType(MediaType.APPLICATION_JSON_VALUE))
				.andExpect(status().isBadRequest());
	}

	@Test
	public void testCreateOrders_withCoordinatesinvalidSize_shouldReturnStatusBadRequest() throws  Exception{
		String requestBody = "{\"origin\": [\"12\"],\"destination\": [\"13\",\"12\",\"14\"]}";
		mockMvc.perform(MockMvcRequestBuilders.post("/orders")
				.content(requestBody).contentType(MediaType.APPLICATION_JSON_VALUE))
				.andExpect(status().isBadRequest());
	}

	@Test
	public void testDistanceMetricsService_withValidValues_shouldReturnCorrectResponse() throws Exception {
		OrderRequest sampleCoordinates = new OrderRequest();
		sampleCoordinates.setOrigin(new ArrayList<String>(Arrays.asList("14.568399", "121.076602")));
		sampleCoordinates.setDestination(new ArrayList<String>(Arrays.asList("14.55","121.05")));

		AppConfig mockConfig = Mockito.mock(AppConfig.class);
		Mockito.when(mockConfig.getUrl()).thenReturn("https://maps.googleapis.com/maps/api/distancematrix/json?");
		Mockito.when(mockConfig.getKey()).thenReturn("");
		ReflectionTestUtils.setField(distanceService, "config", mockConfig);

		DistanceMatrixResponse result = distanceService.getDistance(sampleCoordinates);

		Assertions.assertThat(result.getRows().get(0).getElements().get(0).getDistance().getText()).isEqualTo("4.5 km");
		Assertions.assertThat(result.getRows().get(0).getElements().get(0).getDistance().getValue()).isEqualTo(4464);
		Assertions.assertThat(result.getStatus()).isEqualTo("OK");
		Assertions.assertThat(result).isNotNull();
	}
}
