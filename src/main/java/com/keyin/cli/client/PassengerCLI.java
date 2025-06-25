package com.keyin.cli.client;

import com.keyin.cli.model.Passenger;

import java.util.Scanner;

public class PassengerCLI {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        PassengerApiClient apiClient = new PassengerApiClient();

        System.out.print("Enter Passenger ID: ");
        long id = scanner.nextLong();

        try {
            Passenger passenger = apiClient.getPassengerById(id);
            System.out.println("\nPassenger Details:");
            System.out.println("Name: " + passenger.getFirstName());
        } catch (Exception e) {
            System.out.println("Error fetching passenger: " + e.getMessage());
        }
    }
}
