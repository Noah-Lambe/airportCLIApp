package com.keyin.cli.model;

import java.util.ArrayList;
import java.util.List;

public class Aircraft {
    private Long aircraftId;
    private String type;
    private String airlineName;
    private int numberOfPassengers;
    private List<Airport> airports;
    private List<Passenger> passengers;


    public Aircraft() {}

    public Aircraft(Long aircraftId, String type, String airlineName, int numberOfPassengers) {
        this.aircraftId = aircraftId;
        this.type = type;
        this.airlineName = airlineName;
        this.numberOfPassengers = numberOfPassengers;
    }

    public Long getAircraftId() {
        return aircraftId;
    }

    public String getType() {
        return type;
    }

    public String getAirlineName() {
        return airlineName;
    }

    public int getNumberOfPassengers() {
        return numberOfPassengers;
    }

    public List<Airport> getAirports() {
        return airports;
    }

    public List<Passenger> getPassengers() {
        return passengers;
    }

    public void setAircraftId(Long aircraftId) {
        this.aircraftId = aircraftId;
    }

    public void setType(String type) {
        this.type = type;
    }

    public void setAirlineName(String airlineName) {
        this.airlineName = airlineName;
    }

    public void setNumberOfPassengers(int numberOfPassengers) {
        this.numberOfPassengers = numberOfPassengers;
    }

    public void setAirports(List<Airport> airports) {
        this.airports = airports;
    }

    public void setPassengers(List<Passenger> passengers) {
        this.passengers = passengers;
    }

    @Override
    public String toString() {
        return "Aircraft ID: " + aircraftId +
                ", Type: " + type +
                ", Airline: " + airlineName +
                ", Passengers: " + numberOfPassengers;
    }
}
