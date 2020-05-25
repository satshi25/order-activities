package com.example.orderactivities.service;

import com.example.orderactivities.config.AppConfig;
import com.example.orderactivities.entity.DistanceMatrixResponse;
import com.example.orderactivities.entity.OrderRequest;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

@Service
public class DistanceMatrixService {
    static final String STR_COMA = ",";
    static final Integer START_LATITUDE = 0;
    static final Integer START_LONGITUDE = 1;
    static final Integer END_LATITUDE = 0;
    static final Integer END_LONGITUDE = 1;

    static final Logger LOG = LoggerFactory.getLogger(DistanceMatrixService.class);

    @Autowired
    private AppConfig config;

    RestTemplate restTemplate = new RestTemplate();

    public DistanceMatrixResponse getDistance(OrderRequest coordinates) {
        String origin = coordinates.getOrigin().get(START_LATITUDE) + STR_COMA +
                coordinates.getOrigin().get(START_LONGITUDE);
        String destination = coordinates.getDestination().get(END_LATITUDE) + STR_COMA +
                coordinates.getDestination().get(END_LONGITUDE);
        LOG.info("Formatted origin parameter: {}", origin);
        LOG.info("Formatted destination parameter: {}" , destination);
        LOG.info("API key from configuration: {}", config.getKey());

        HttpHeaders headers = new HttpHeaders();
        headers.set("Accept", MediaType.APPLICATION_JSON_VALUE);

        UriComponentsBuilder uriBuilder = UriComponentsBuilder.fromHttpUrl(config.getUrl())
                .queryParam("origins", origin)
                .queryParam("destinations", destination)
                .queryParam("key", config.getKey());

        HttpEntity<?> entity = new HttpEntity<>(headers);

        ResponseEntity<DistanceMatrixResponse> response = restTemplate
                .exchange(uriBuilder.toUriString(), HttpMethod.GET,entity,DistanceMatrixResponse.class);

        return response.getBody();
    }
}
