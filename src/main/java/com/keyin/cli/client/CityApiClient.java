package com.keyin.cli.client;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.keyin.cli.model.Airport;
import com.keyin.cli.model.City;

import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.List;

public class CityApiClient {
    private static final String BASE_URL = "http://localhost:8080/city";

    private static ObjectMapper objectMapper = new ObjectMapper();

    public List<City> getAllCities() throws Exception {
        URL url = new URL(BASE_URL);
        HttpURLConnection conn = (HttpURLConnection) url.openConnection();
        conn.setRequestMethod("GET");

        if (conn.getResponseCode() != 200) {
            throw new RuntimeException("Failed to fetch cities: " + conn.getResponseCode());
        }

        InputStream inputStream = conn.getInputStream();
        return objectMapper.readValue(inputStream, new TypeReference<List<City>>() {});
    }

    public List<Airport> getAirportsByCityId(Long cityId) throws Exception {
        URL url = new URL(BASE_URL + "/" + cityId + "/airports");
        HttpURLConnection conn = (HttpURLConnection) url.openConnection();
        conn.setRequestMethod("GET");

        if (conn.getResponseCode() != 200) {
            throw new RuntimeException("Failed to fetch airports for city " + cityId + ": " + conn.getResponseCode());
        }

        InputStream inputStream = conn.getInputStream();
        return objectMapper.readValue(inputStream, new TypeReference<List<Airport>>() {
        });
    }
}

