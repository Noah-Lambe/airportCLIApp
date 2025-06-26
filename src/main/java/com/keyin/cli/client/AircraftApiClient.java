package com.keyin.cli.client;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.keyin.cli.model.Aircraft;

import java.io.InputStream;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.List;

public class AircraftApiClient {
    private static final String BASE_URL = "http://localhost:8080/aircraft";
    private final ObjectMapper objectMapper;

    public AircraftApiClient() {
        this.objectMapper = new ObjectMapper();
    }

    public AircraftApiClient(ObjectMapper objectMapper) {
        this.objectMapper = objectMapper;
    }

    public List<Aircraft> fetchAllAircraft() throws Exception {
        URL url = new URL(BASE_URL);
        HttpURLConnection conn = (HttpURLConnection) url.openConnection();
        conn.setRequestMethod("GET");

        if (conn.getResponseCode() != 200) {
            throw new RuntimeException("Failed to fetch aircraft: " + conn.getResponseCode());
        }

        try (InputStream inputStream = conn.getInputStream()) {
            return objectMapper.readValue(inputStream, new TypeReference<List<Aircraft>>() {});
        }
    }

    public Aircraft fetchAircraftById(Long aircraftId) throws Exception {
        URL url = new URL(BASE_URL + "/" + aircraftId);
        HttpURLConnection conn = (HttpURLConnection) url.openConnection();
        conn.setRequestMethod("GET");

        if (conn.getResponseCode() != 200) {
            throw new RuntimeException("Failed to fetch aircraft: " + conn.getResponseCode());
        }

        try (InputStream inputStream = conn.getInputStream()) {
            return objectMapper.readValue(inputStream, Aircraft.class);
        }
    }

    public Aircraft createAircraft(Aircraft aircraft) throws Exception {
        URL url = new URL(BASE_URL);
        HttpURLConnection conn = (HttpURLConnection) url.openConnection();
        conn.setRequestMethod("POST");
        conn.setRequestProperty("Content-Type", "application/json");
        conn.setDoOutput(true);

        try (OutputStream os = conn.getOutputStream()) {
            objectMapper.writeValue(os, aircraft);
        }

        if (conn.getResponseCode() != 200 && conn.getResponseCode() != 201) {
            throw new RuntimeException("Failed to create aircraft: " + conn.getResponseCode());
        }

        try (InputStream inputStream = conn.getInputStream()) {
            return objectMapper.readValue(inputStream, Aircraft.class);
        }
    }

    public Aircraft updateAircraft(Long aircraftId, Aircraft aircraft) throws Exception {
        URL url = new URL(BASE_URL + "/" + aircraftId);
        HttpURLConnection conn = (HttpURLConnection) url.openConnection();
        conn.setRequestMethod("PUT");
        conn.setRequestProperty("Content-Type", "application/json");
        conn.setDoOutput(true);

        try (OutputStream os = conn.getOutputStream()) {
            objectMapper.writeValue(os, aircraft);
        }

        if (conn.getResponseCode() != 200) {
            throw new RuntimeException("Failed to update aircraft: " + conn.getResponseCode());
        }

        try (InputStream inputStream = conn.getInputStream()) {
            return objectMapper.readValue(inputStream, Aircraft.class);
        }
    }

    public void deleteAircraft(Long aircraftId) throws Exception {
        URL url = new URL(BASE_URL + "/" + aircraftId);
        HttpURLConnection conn = (HttpURLConnection) url.openConnection();
        conn.setRequestMethod("DELETE");

        int status = conn.getResponseCode();
        if (status != 200 && status != 204) {
            throw new RuntimeException("Failed to delete aircraft: " + status);
        }
    }

    public List<Aircraft> getAircraftByPassengerId(long passengerId) throws Exception {
        URL url = new URL(BASE_URL + "/" + passengerId + "/airports");
        HttpURLConnection conn = (HttpURLConnection) url.openConnection();
        conn.setRequestMethod("GET");

        if (conn.getResponseCode() != 200) {
            throw new RuntimeException("Failed to fetch aircraft by passenger ID: " + conn.getResponseCode());
        }

        try (InputStream inputStream = conn.getInputStream()) {
            return objectMapper.readValue(inputStream, new TypeReference<List<Aircraft>>() {});
        }
    }
}
