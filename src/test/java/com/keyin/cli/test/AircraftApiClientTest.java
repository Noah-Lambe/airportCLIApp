package com.keyin.cli.test;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.keyin.cli.client.AircraftApiClient;
import com.keyin.cli.model.Aircraft;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

public class AircraftApiClientTest {

    @Test
    public void testDeserializeAircraftList() throws Exception {
        String json = "[{\"aircraftId\":1,\"type\":\"Boeing 737\",\"airlineName\":\"WestJet\",\"numberOfPassengers\":180}]";
        InputStream stream = new ByteArrayInputStream(json.getBytes());

        ObjectMapper mockMapper = mock(ObjectMapper.class);
        List<Aircraft> mockResult = List.of(new Aircraft());
        when(mockMapper.readValue(any(InputStream.class), any(TypeReference.class))).thenReturn(mockResult);

        AircraftApiClient client = new AircraftApiClient(mockMapper);

        List<Aircraft> result = mockMapper.readValue(stream, new TypeReference<List<Aircraft>>() {});

        assertEquals(mockResult, result);
        verify(mockMapper).readValue(any(InputStream.class), any(TypeReference.class));
    }

    @Test
    public void testDeserializeAircraftById() throws Exception {
        String json = "{\"aircraftId\":1,\"type\":\"Airbus A320\",\"airlineName\":\"Air Canada\",\"numberOfPassengers\":160}";
        InputStream stream = new ByteArrayInputStream(json.getBytes());

        ObjectMapper mockMapper = mock(ObjectMapper.class);
        Aircraft mockAircraft = new Aircraft();
        when(mockMapper.readValue(any(InputStream.class), eq(Aircraft.class))).thenReturn(mockAircraft);

        AircraftApiClient client = new AircraftApiClient(mockMapper);

        Aircraft result = mockMapper.readValue(stream, Aircraft.class);

        assertEquals(mockAircraft, result);
        verify(mockMapper).readValue(any(InputStream.class), eq(Aircraft.class));
    }
}
