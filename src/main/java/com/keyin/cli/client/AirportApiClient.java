package com.keyin.cli.client;

import com.keyin.cli.model.Airport;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.core.type.TypeReference;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.List;

public class AirportApiClient {

    private static final String API_URL = "http://localhost:8080/airport";
    private final HttpClient client;
    private final ObjectMapper mapper = new ObjectMapper();

    public AirportApiClient() {
        this(HttpClient.newHttpClient());
    }

    public AirportApiClient(HttpClient client) {
        this.client = client;
    }

    public List<Airport> fetchAllAirports() throws Exception {
        HttpRequest request = HttpRequest.newBuilder(URI.create(API_URL))
                .GET()
                .build();

        HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());

        if (response.statusCode() == 200) {
            return mapper.readValue(response.body(), new TypeReference<List<Airport>>() {});
        } else {
            throw new Exception("Failed to fetch airports. HTTP Status: " + response.statusCode());
        }
    }

    public Airport fetchAirportById(String airportId) throws Exception {
        HttpRequest request = HttpRequest.newBuilder(URI.create(API_URL + "/" + airportId))
                .GET()
                .build();

        HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());

        if (response.statusCode() == 200) {
            return mapper.readValue(response.body(), Airport.class);
        } else {
            throw new Exception("Failed to fetch airport. HTTP Status: " + response.statusCode());
        }
    }

    public Airport createAirport(Airport airport, Long cityId) throws Exception {
        String airportJson = mapper.writeValueAsString(airport);

        HttpRequest request = HttpRequest.newBuilder()
                .uri(new URI(API_URL + "?cityId=" + cityId))
                .header("Content-Type", "application/json")
                .POST(HttpRequest.BodyPublishers.ofString(airportJson))
                .build();

        HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());

        if (response.statusCode() == 201) {
            return mapper.readValue(response.body(), Airport.class);
        } else {
            throw new Exception("Failed to create airport. HTTP Status: " + response.statusCode());
        }
    }

    public Airport updateAirport(Long airportId, Airport airport) throws Exception {
        String airportJson = mapper.writeValueAsString(airport);

        HttpRequest request = HttpRequest.newBuilder()
                .uri(new URI(API_URL + "/" + airportId))
                .header("Content-Type", "application/json")
                .PUT(HttpRequest.BodyPublishers.ofString(airportJson))
                .build();

        HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());

        if (response.statusCode() == 200) {
            return mapper.readValue(response.body(), Airport.class);
        } else {
            throw new Exception("Failed to update airport. HTTP Status: " + response.statusCode());
        }
    }

    public void deleteAirport(Long airportId) throws Exception {
        HttpRequest request = HttpRequest.newBuilder()
                .uri(new URI(API_URL + "/" + airportId))
                .header("Content-Type", "application/json")
                .DELETE()
                .build();

        HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());

        if (response.statusCode() != 200) {
            throw new Exception("Failed to delete airport. HTTP Status: " + response.statusCode());
        }
    }
}
