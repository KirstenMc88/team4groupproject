package com.napier.team4groupproject;

import java.util.Scanner;


/**
 * The {@code Menu} class provides the main menu and submenu structure
 * to allow different reports to be generated
 * such as country, city, region, population etc
 * The user can easily navigate the menu using input to select and view specific reports
 * They can also return to previous menus using 0
 */
public class Menu {
    /**
     A {@code Scanner} object to take in a user input form the cosnole
     */

    private static Scanner input = new Scanner(System.in);

    /**
     * A {@code mainMenu} method which displays the main menu
     * It allows for easy navigation to different reporting menus
     * has built in validation
     */

    public static void mainMenu(DatabaseConnection databaseConnection) {
        boolean exit = false;

        while (!exit) {
            System.out.println("\n------Main Menu------");
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
                    countryReportsMenu(databaseConnection);
                    break;

                case "2":
                    cityReportsMenu();
                    break;

                case "3":
                    capitalCityReportsMenu(databaseConnection);
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

    /**
     * A {@code countryReportsMenu} method which displays the sub menu for all country reports
     * It allows for easy navigation to different reporting menus including back to previous menu
     * has built in validation
     */

    private static void countryReportsMenu(DatabaseConnection databaseConnection) {
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
                    //System.out.println(App.FormatOutput(countryQuery.CountriesInTheWorld()));
                    // Directly call allInWorld and print the formatted output
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

    /**
     * A {@code cityReportsMenu} method which displays the sub menu for all city reports
     * It allows for easy navigation to different reporting menus including back to previous menu
     * has built in validation
     */

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

    /**
     * A {@code capitalCityReportsMenu} method which displays the sub menu for all capital city reports
     * It allows for easy navigation to different reporting menus including back to previous menu
     * has built in validation
     */

    private static void capitalCityReportsMenu(DatabaseConnection databaseConnection) {
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
                    // Directly call allInWorld and print the formatted output
                    System.out.println(CapitalQueries.allInWorld(databaseConnection));
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

    /**
     * A {@code populationAnalysisReportsMenu} method which displays the sub menu for all population reports
     * It allows for easy navigation to different reporting menus including back to previous menu
     * has built in validation
     */

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

    /**
     * A {@code generalInformationMenu} method which displays the sub menu for all population of XXX reports
     * can display reports for populations of world, continent, region etc
     * It allows for easy navigation to different reporting menus including back to previous menu
     * has built in validation
     */

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

    /**
     * A {@code languageReportsMenu} method which displays the sub menu for all language reports
     * shows reports for how many people speak different languages in the world
     * It allows for easy navigation to different reporting menus including back to previous menu
     * has built in validation
     */

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
