package com.keyin.cli;

import com.keyin.cli.client.PassengerApiClient;
import com.keyin.cli.model.Passenger;
import org.junit.jupiter.api.Test;

import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class PassengerApiClientTest {
    @Test
    public void testParsePassenger_returnsValidPassenger() throws Exception {
        String json = """
                {
                    "id": 1,
                    "firstName": "Hunter",
                    "lastName": "Saunders",
                    "phoneNumber": "7095551234"
                }
                """;

        ByteArrayInputStream inputStream = new ByteArrayInputStream(json.getBytes());
        BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream));

        PassengerApiClient apiClient = new PassengerApiClient();
        Passenger result = apiClient.parsePassenger(reader);

        assertNotNull(result);
        assertEquals(1, result.getId());
        assertEquals("Hunter", result.getFirstName());
        assertEquals("Saunders", result.getLastName());
        assertEquals("7095551234", result.getPhoneNumber());
    }
}
