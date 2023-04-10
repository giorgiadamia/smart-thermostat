package com.example.smartthermostat;

import com.example.smartthermostat.model.domain.Thermostat;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.*;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;
import java.util.*;

public class TemperatureSimulator {


    private static final int DELAY_MS = 1000;

    public static void main(String[] args) throws InterruptedException {
        String url = "http://localhost:8080/api/thermostat/{id}";
        String urlGet = "http://localhost:8080/api/thermostat";

        RestTemplate restTemplate = new RestTemplate();
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));

        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter username:");
        String username = scanner.nextLine();
        System.out.println("Enter password:");
        String password = scanner.nextLine();
        headers.setBasicAuth(username, password);

        HttpEntity<String> entityGet = new HttpEntity<>("parameters", headers);
        ResponseEntity<List<Thermostat>> responseGet = restTemplate.exchange(urlGet, HttpMethod.GET, entityGet, new ParameterizedTypeReference<List<Thermostat>>(){});
        List<Thermostat> thermostats = responseGet.getBody();

        for (Thermostat thermostat: thermostats) {
            Map<String, Object> requestBody = new HashMap<>();
            requestBody.put("temperature", generateTemperature());

            HttpEntity<Map<String, Object>> entity = new HttpEntity<>(requestBody, headers);

            URI uri = UriComponentsBuilder.fromUriString(url)
                    .buildAndExpand(thermostat.getId())
                    .toUri();

            restTemplate.exchange(uri, HttpMethod.PUT, entity, String.class);
        }
    }

    private static int generateTemperature() {
        return (int) (Math.random() * 40) + 50;
    }
}
