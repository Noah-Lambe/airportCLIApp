package com.keyin.cli.client;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.keyin.cli.model.Passenger;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class PassengerApiClient {

    private static final String API_URL = "http://localhost:8080/passengers";

    public Passenger getPassengerById(long id) throws Exception {
        URL url = new URL(API_URL + "/" + id);
        HttpURLConnection conn = (HttpURLConnection) url.openConnection();
        conn.setRequestMethod("GET");

        BufferedReader reader = new BufferedReader(new InputStreamReader(conn.getInputStream()));
        ObjectMapper mapper = new ObjectMapper();
        Passenger passenger = mapper.readValue(reader, Passenger.class);
        reader.close();

        return passenger;
    }

}
