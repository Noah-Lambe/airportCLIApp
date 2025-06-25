package com.keyin.cli.model;

public class Airport {
    private String airportName;
    private String areaCode;
    private City city;

    // Default constructor
    public Airport() {
        // Empty constructor for testing
    }

    // Full constructor
    public Airport(String airportName, String areaCode, City city) {
        this.airportName = airportName;
        this.areaCode = areaCode;
        this.city = city;
    }

    // Getters
    public String getAirportName() {
        return airportName;
    }

    public String getAreaCode() {
        return areaCode;
    }

    public City getCity() {
        return city;
    }

    // Setters
    public void setAirportName(String airportName) {
        this.airportName = airportName;
    }

    public void setAreaCode(String areaCode) {
        this.areaCode = areaCode;
    }

    public void setCity(City city) {
        this.city = city;
    }

    @Override
    public String toString() {
        return "Name: " + airportName + ", Area Code: " + areaCode + ", City: " + (city != null ? city.getName() : "N/A");
    }
}
