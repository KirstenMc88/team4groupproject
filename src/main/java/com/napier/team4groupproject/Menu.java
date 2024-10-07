package com.napier.team4groupproject;

import java.util.Scanner;

public class Menu {
    private static Scanner input = new Scanner(System.in);

    public static void mainMenu() {
        boolean exit = false;

        while (!exit) {
            System.out.println("------Main Menu------");
            System.out.println("1 - Country reports.");
            System.out.println("2 - City reports.");
            System.out.println("3 - Capital city reports.");
            System.out.println("4 - Population analysis reports.");
            System.out.println("5 - General information.");
            System.out.println("6 - Language reports.");
            System.out.println("0 - Exit");

            String choice = input.nextLine();

            switch (choice) {
                case "1":
                    countryReportsMenu();
                    break;

                case "2":
                    cityReportsMenu();
                    break;

                case "3":
                    capitalCityReportsMenu();
                    break;

                case "4":
                    populationAnalysisReportsMenu();
                    break;

                case "5":
                    generalInformationMenu();
                    break;

                case "6":
                    languageReportsMenu();
                    break;

                case "0":
                    exit = true;
                    break;

                default:
                    System.out.println("Invalid choice. Please enter a valid number.");
            }
        }
    }

    private static void countryReportsMenu() {
        boolean exit = false;

        while (!exit) {
            System.out.println("\n------Country Reports------");

            System.out.println("1 - All the countries in the world organised by largest population to smallest.");
            System.out.println("2 - All the countries in a continent organised by largest population to smallest.");
            System.out.println("3 - All the countries in a region organised by largest population to smallest.");
            System.out.println("4 - The top N populated countries in the world where N is provided by the user.");
            System.out.println("5 - The top N populated countries in a continent where N is provided by the user.");
            System.out.println("6 - The top N populated countries in a region where N is provided by the user.");
            System.out.println("0 - Back to main menu.");

            String choice = input.nextLine();

            switch (choice) {
                case "1":
                    System.out.println("Placeholder");
                    break;

                case "2":
                    System.out.println("Placeholder");
                    break;

                case "3":
                    System.out.println("Placeholder");
                    break;

                case "4":
                    System.out.println("Placeholder");
                    break;

                case "5":
                    System.out.println("Placeholder");
                    break;

                case "6":
                    System.out.println("Placeholder");
                    break;

                case "0":
                    exit = true;
                    break;

                default:
                    System.out.println("Invalid choice. Please enter a valid number.");
            }
        }
    }

    private static void cityReportsMenu() {
        boolean exit = false;

        while (!exit) {
            System.out.println("\n------City Reports------");

            System.out.println("1 - All the cities in the world organised by largest population to smallest.");
            System.out.println("2 - All the cities in a continent organised by largest population to smallest.");
            System.out.println("3 - All the cities in a region organised by largest population to smallest.");
            System.out.println("4 - All the cities in a country organised by largest population to smallest.");
            System.out.println("5 - All the cities in a district organised by largest population to smallest.");
            System.out.println("6 - The top N populated cities in the world where N is provided by the user.");
            System.out.println("7 - The top N populated cities in a continent where N is provided by the user.");
            System.out.println("8 - The top N populated cities in a region where N is provided by the user.");
            System.out.println("9 - The top N populated cities in a country where N is provided by the user.");
            System.out.println("10 - The top N populated cities in a district where N is provided by the user.");
            System.out.println("0 - Back to main menu.");

            String choice = input.nextLine();

            switch (choice) {
                case "1":
                    System.out.println("Placeholder");
                    break;

                case "2":
                    System.out.println("Placeholder");
                    break;

                case "3":
                    System.out.println("Placeholder");
                    break;

                case "4":
                    System.out.println("Placeholder");
                    break;

                case "5":
                    System.out.println("Placeholder");
                    break;

                case "6":
                    System.out.println("Placeholder");
                    break;

                case "7":
                    System.out.println("Placeholder");
                    break;

                case "8":
                    System.out.println("Placeholder");
                    break;

                case "9":
                    System.out.println("Placeholder");
                    break;

                case "10":
                    System.out.println("Placeholder");
                    break;

                case "0":
                    exit = true;
                    break;

                default:
                    System.out.println("Invalid choice. Please enter a valid number.");
            }
        }
    }

    private static void capitalCityReportsMenu() {
        boolean exit = false;

        while (!exit) {
            System.out.println("\n------Capital City Reports------");

            System.out.println("1 - All the capital cities in the world organised by largest population to smallest.");
            System.out.println("2 - All the capital cities in a continent organised by largest population to smallest.");
            System.out.println("3 - All the capital cities in a region organised by largest to smallest.");
            System.out.println("4 - The top N populated capital cities in the world where N is provided by the user.");
            System.out.println("5 - The top N populated capital cities in a continent where N is provided by the user.");
            System.out.println("6 - The top N populated capital cities in a region where N is provided by the user.");
            System.out.println("0 - Back to main menu.");

            String choice = input.nextLine();

            switch (choice) {
                case "1":
                    System.out.println("Placeholder");
                    break;

                case "2":
                    System.out.println("Placeholder");
                    break;

                case "3":
                    System.out.println("Placeholder");
                    break;

                case "4":
                    System.out.println("Placeholder");
                    break;

                case "5":
                    System.out.println("Placeholder");
                    break;

                case "6":
                    System.out.println("Placeholder");
                    break;

                case "0":
                    exit = true;
                    break;

                default:
                    System.out.println("Invalid choice. Please enter a valid number.");
            }
        }
    }

    private static void populationAnalysisReportsMenu() {
        boolean exit = false;

        while (!exit) {
            System.out.println("\n------Population Analysis Reports------");

            System.out.println("1 - The population of people, people living in cities, and people not living in cities in each continent.");
            System.out.println("2 - The population of people, people living in cities, and people not living in cities in each region.");
            System.out.println("3 - The population of people, people living in cities, and people not living in cities in each country.");
            System.out.println("0 - Back to main menu.");

            String choice = input.nextLine();

            switch (choice) {
                case "1":
                    System.out.println("Placeholder");
                    break;

                case "2":
                    System.out.println("Placeholder");
                    break;

                case "3":
                    System.out.println("Placeholder");
                    break;

                case "0":
                    exit = true;
                    break;

                default:
                    System.out.println("Invalid choice. Please enter a valid number.");
            }
        }
    }

    private static void generalInformationMenu() {
        boolean exit = false;

        while (!exit) {
            System.out.println("\n------General Information------");

            System.out.println("1 - The population of the world.");
            System.out.println("2 - The population of a continent.");
            System.out.println("3 - The population of a region.");
            System.out.println("4 - The population of a country.");
            System.out.println("5 - The population of a district.");
            System.out.println("6 - The population of a city.");
            System.out.println("0 - Back to main menu.");

            String choice = input.nextLine();

            switch (choice) {
                case "1":
                    System.out.println("Placeholder");
                    break;

                case "2":
                    System.out.println("Placeholder");
                    break;

                case "3":
                    System.out.println("Placeholder");
                    break;

                case "4":
                    System.out.println("Placeholder");
                    break;

                case "5":
                    System.out.println("Placeholder");
                    break;

                case "6":
                    System.out.println("Placeholder");
                    break;

                case "0":
                    exit = true;
                    break;

                default:
                    System.out.println("Invalid choice. Please enter a valid number.");
            }
        }
    }

    private static void languageReportsMenu() {
        boolean exit = false;

        while (!exit) {
            System.out.println("\n------Language Reports------");

            System.out.println("1 - Chinese.");
            System.out.println("2 - English.");
            System.out.println("3 - Hindi.");
            System.out.println("4 - Spanish.");
            System.out.println("5 - Arabic.");
            System.out.println("0 - Back to main menu.");

            String choice = input.nextLine();

            switch (choice) {
                case "1":
                    System.out.println("Placeholder");
                    break;

                case "2":
                    System.out.println("Placeholder");
                    break;

                case "3":
                    System.out.println("Placeholder");
                    break;

                case "4":
                    System.out.println("Placeholder");
                    break;

                case "5":
                    System.out.println("Placeholder");
                    break;

                case "0":
                    exit = true;
                    break;

                default:
                    System.out.println("Invalid choice. Please enter a valid number.");
            }
        }
    }
}
