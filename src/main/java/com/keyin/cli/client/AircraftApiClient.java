package com.keyin.cli.client;

import com.keyin.cli.model.Aircraft;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.core.type.TypeReference;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.List;

public class AircraftApiClient {
    private static final String API_URL = "http://localhost:8080/airport";
    private final HttpClient client = HttpClient.newHttpClient();
    private final ObjectMapper mapper;

    public AircraftApiClient() {
        this.mapper = new ObjectMapper();
    }

    public AircraftApiClient(ObjectMapper mapper) {
        this.mapper = mapper;
    }

    public List<Aircraft> fetchAllAircraft() throws Exception {
        HttpRequest request = HttpRequest.newBuilder(URI.create(API_URL))
                .GET()
                .build();

        HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());

        if (response.statusCode() == 200) {
            return mapper.readValue(response.body(), new TypeReference<List<Aircraft>>() {
            });
        } else {
            throw new Exception("Failed to fetch aircraft. HTTP Status: " + response.statusCode());
        }
    }

    public Aircraft fetchAircraftById(Long aircraftId) throws Exception {
        HttpRequest request = HttpRequest.newBuilder(URI.create(API_URL + "/" + aircraftId))
                .GET()
                .build();

        HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());

        if (response.statusCode() == 200) {
            return mapper.readValue(response.body(), Aircraft.class);
        } else {
            throw new Exception("Failed to fetch aircraft. HTTP Status: " + response.statusCode());
        }
    }

    public Aircraft createAircraft(Aircraft aircraft) throws Exception {
        String aircraftJson = mapper.writeValueAsString(aircraft);

        HttpRequest request = HttpRequest.newBuilder()
                .uri(new URI(API_URL))
                .header("Content-Type", "application/json")
                .POST(HttpRequest.BodyPublishers.ofString(aircraftJson))
                .build();

        HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());

        if (response.statusCode() == 200 || response.statusCode() == 201) {
            return mapper.readValue(response.body(), Aircraft.class);
        } else {
            throw new Exception("Failed to create aircraft. HTTP Status: " + response.statusCode());
        }
    }

    public Aircraft updateAircraft(Long aircraftId, Aircraft aircraft) throws Exception {
        String aircraftJson = mapper.writeValueAsString(aircraft);

        HttpRequest request = HttpRequest.newBuilder()
                .uri(new URI(API_URL + "/" + aircraftId))
                .header("Content-Type", "application/json")
                .PUT(HttpRequest.BodyPublishers.ofString(aircraftJson))
                .build();

        HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());

        if (response.statusCode() == 200) {
            return mapper.readValue(response.body(), Aircraft.class);
        } else {
            throw new Exception("Failed to update aircraft. HTTP Status: " + response.statusCode());
        }
    }

    public void deleteAircraft(Long aircraftId) throws Exception {
        HttpRequest request = HttpRequest.newBuilder()
                .uri(new URI(API_URL + "/" + aircraftId))
                .header("Content-Type", "application/json")
                .DELETE()
                .build();

        HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());

        if (response.statusCode() != 200 && response.statusCode() != 204) {
            throw new Exception("Failed to delete aircraft. HTTP Status: " + response.statusCode());
        }
    }

    public List<Aircraft> getAircraftByPassengerId(long passengerId) throws Exception {
        HttpRequest request = HttpRequest.newBuilder(
                        URI.create(API_URL + "/passenger/" + passengerId))
                .GET()
                .build();

        HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());

        if (response.statusCode() == 200) {
            return mapper.readValue(response.body(), new TypeReference<List<Aircraft>>() {
            });
        } else {
            throw new Exception("Failed to fetch aircraft for passenger. HTTP Status: " + response.statusCode());
        }
    }
}