package com.keyin.cli;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.keyin.cli.client.CityApiClient;
import com.keyin.cli.model.Airport;
import com.keyin.cli.model.City;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

public class CityClientTest {

    @Test
    public void testDeserializeCityList() throws Exception {
        // sample city
        String json = "[{\"id\":1,\"name\":\"St. John's\",\"state\":\"NL\",\"population\":100000}]";
        InputStream stream = new ByteArrayInputStream(json.getBytes());

        // Mock ObjectMapper
        ObjectMapper mockMapper = mock(ObjectMapper.class);
        List<City> mockResult = List.of(new City());
        when(mockMapper.readValue(any(InputStream.class), any(TypeReference.class))).thenReturn(mockResult);

        // Inject ObjectMapper into client
        CityApiClient client = new CityApiClient(mockMapper);

        // simulate deserialization
        List<City> result = mockMapper.readValue(stream, new TypeReference<List<City>>() {});

        assertEquals(mockResult, result);
        verify(mockMapper).readValue(any(InputStream.class), any(TypeReference.class));
    }

    @Test
    public void testDeserializeAirportListByCityId() throws Exception {
        // sample airport
        String json = "[{\"airportId\":1,\"airportName\":\"YYT\",\"areaCode\":\"709\"}]";
        InputStream stream = new ByteArrayInputStream(json.getBytes());

        ObjectMapper mockMapper = mock(ObjectMapper.class);
        List<Airport> mockResult = List.of(new Airport());
        when(mockMapper.readValue(any(InputStream.class), any(TypeReference.class))).thenReturn(mockResult);

        CityApiClient client = new CityApiClient(mockMapper);

        List<Airport> result = mockMapper.readValue(stream, new TypeReference<List<Airport>>() {});

        assertEquals(mockResult, result);
        verify(mockMapper).readValue(any(InputStream.class), any(TypeReference.class));
    }
}

