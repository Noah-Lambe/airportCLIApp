//package com.keyin.cli;
//
//import java.io.IOException;
//import java.util.Scanner;
//
//public class AirportCliApp {
//    private static final Scanner scanner = new Scanner(System.in);
//
//    public static void main(String[] args) throws IOException, InterruptedException {
//        while (true) {
//            System.out.println("\nChoose an option:");
//            System.out.println("1. List all cities");
//            System.out.println("2. Get city by ID");
//            System.out.println("3. Search cities by airport name");
//            System.out.println("4. Search cities by passenger phone");
//            System.out.println("5. Exit");
//
//            String choice = scanner.nextLine();
//            switch (choice) {
//                case "1" -> CityApiClient.getAllCities();
//                case "2" -> CityApiClient.getCityById(prompt("Enter City ID: "));
//                case "3" -> CityApiClient.searchByAirport(prompt("Enter airport name: "));
//                case "4" -> CityApiClient.searchByPhone(prompt("Enter phone number: "));
//                case "5" -> System.exit(0);
//                default -> System.out.println("Invalid choice");
//            }
//        }
//    }
//
//    private static String prompt(String message) {
//        System.out.print(message);
//        return scanner.nextLine();
//    }
//}
