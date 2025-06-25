package com.keyin.cli;

import com.keyin.cli.client.PassengerApiClient;
import com.keyin.cli.model.Passenger;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayInputStream;
import java.net.HttpURLConnection;
import java.net.URL;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class PassengerApiClientTest {

    @Test
    public void testGetPassengerById_returnsValidPassenger() throws Exception {
        // Mock response JSON
        String json = "{\"id\":1,\"firstName\":\"Hunter\"}";
        ByteArrayInputStream fakeStream = new ByteArrayInputStream(json.getBytes());

        // Mock connection + URL
        HttpURLConnection mockConnection = mock(HttpURLConnection.class);
        when(mockConnection.getInputStream()).thenReturn(fakeStream);

        URL mockUrl = mock(URL.class);
        when(mockUrl.openConnection()).thenReturn(mockConnection);

        // Mock URL constructor using mockito-inline
        try (var mockedUrl = mockStatic(URL.class)) {
            mockedUrl.when(() -> new URL("http://localhost:8080/passengers/1"))
                    .thenReturn(mockUrl);

            // Call the method
            PassengerApiClient apiClient = new PassengerApiClient();
            Passenger result = apiClient.getPassengerById(1);

            // Assertions
            assertNotNull(result);
            assertEquals(1, result.getId());
            assertEquals("Hunter", result.getFirstName());
        }
    }
}
