package com.keyin.cli;

import java.io.IOException;
import java.util.List;
import java.util.Scanner;

import com.keyin.cli.client.CityApiClient;
import com.keyin.cli.client.PassengerApiClient;
import com.keyin.cli.model.Airport;
import com.keyin.cli.model.City;

public class AirportCliApp {
    private static final Scanner scanner = new Scanner(System.in);
    static CityApiClient cityClient = new CityApiClient();

    public static void main(String[] args) throws IOException, InterruptedException {
        while (true) {
            System.out.println("\nChoose an option:");
            System.out.println("1. List airports located in a city");
            System.out.println("2. ");
            System.out.println("3. ");
            System.out.println("4. Show airports used by a passenger");
            System.out.println("5. Exit");

            String choice = scanner.nextLine();
            switch (choice) {
                case "1" -> {
                    try {
                        List<City> cities = cityClient.getAllCities();

                        for (City city : cities) {
                            System.out.println("City: " + city.getName() + ", " + city.getState());

                            List<Airport> airports = cityClient.getAirportsByCityId(city.getId());

                            if (airports.isEmpty()) {
                                System.out.println("  No airports found.");
                            } else {
                                for (Airport airport : airports) {
                                    System.out.println("  Airport: " + airport.getAirportName() + " (" + airport.getAreaCode() + ")");
                                }
                            }
                            System.out.println();
                        }

                    } catch (Exception e) {
                        System.err.println("An error occurred: " + e.getMessage());
                    }
                }

                case "4" -> {
                    System.out.print("Enter Passenger ID: ");
                    long passengerId = Long.parseLong(scanner.nextLine());

                    try {
                        PassengerApiClient passengerClient = new PassengerApiClient();
                        List<Airport> airports = passengerClient.getAirportsUsedByPassenger(passengerId);

                        if (airports.isEmpty()) {
                            System.out.println("No airports found for passenger ID: " + passengerId);
                        } else {
                            System.out.println("Airports used by passenger ID " + passengerId + ":");
                            for (Airport airport : airports) {
                                System.out.println("- " + airport.getAirportName() + " (" + airport.getAreaCode() + ")");
                            }
                        }
                    } catch (Exception e) {
                        System.err.println("An error occurred: " + e.getMessage());
                    }
                }

                case "2" -> System.exit(0);
                case "3" -> System.exit(0);
                case "5" -> System.exit(0);
                default -> System.out.println("Invalid choice");
            }
        }
    }

    private static String prompt(String message) {
        System.out.print(message);
        return scanner.nextLine();
    }
}
