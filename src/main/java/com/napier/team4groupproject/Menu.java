package com.napier.team4groupproject;

import java.sql.SQLException;
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
     * It allows for easy navigation to different reporting menus and reports
     * has built in validation
     * @param databaseConnection passes in object to connect to database
     */

    public static void mainMenu(DatabaseConnection databaseConnection) throws SQLException {

        // Check for non-interactive environment
        if (System.getenv("NON_INTERACTIVE") != null || "false".equalsIgnoreCase(System.getProperty("NON_INTERACTIVE"))) {
            System.out.println("Non-interactive mode detected. Skipping menu.");
            return; // Exit the menu immediately
        }

        boolean exit = false;

        while (!exit) {
            System.out.println("\n------Main Menu------");
            System.out.println("1 - Country reports.");
            System.out.println("2 - City reports.");
            System.out.println("3 - Capital city reports.");
            System.out.println("4 - Population analysis reports.");
            System.out.println("5 - General information.");
            System.out.println("6 - Language report.");
            System.out.println("0 - Exit");

            String choice = input.nextLine();

            switch (choice) {
                case "1":
                    countryReportsMenu(databaseConnection);
                    break;

                case "2":
                    cityReportsMenu(databaseConnection);
                    break;

                case "3":
                    capitalCityReportsMenu(databaseConnection);
                    break;

                case "4":
                    populationAnalysisReportsMenu(databaseConnection);
                    break;

                case "5":
                    generalInformationMenu(databaseConnection);
                    break;

                case "6":
                    System.out.println(LanguageQuery.LanguageDistributionInThWorld(databaseConnection));
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
            String continent, region;
            int limit;

            switch (choice) {
                case "1":
                    System.out.println(CountryQueries.CountriesInTheWorld(databaseConnection));
                    break;

                case "2":
                    System.out.println("Please enter continent");

                    continent = input.nextLine();
                    System.out.println(CountryQueries.CountriesInAContinent(databaseConnection, continent));
                    break;

                case "3":
                    System.out.println("Please enter region");

                    region = input.nextLine();
                    System.out.println(CountryQueries.CountriesInARegion(databaseConnection, region));
                    break;

                case "4":
                    System.out.println("Please enter N");
                    limit = input.nextInt();
                    input.nextLine();

                    System.out.println(CountryQueries.TopCountriesInTheWorld(databaseConnection, limit));
                    break;

                case "5":
                    System.out.println("Please enter continent");
                    continent = input.nextLine();

                    System.out.println("Please enter N");
                    limit = input.nextInt();
                    input.nextLine();
                    System.out.println(CountryQueries.TopCountriesInAContinent(databaseConnection, continent, limit));
                    break;

                case "6":
                    System.out.println("Please enter region");
                    region = input.nextLine();

                    System.out.println("Please enter N");
                    limit = input.nextInt();
                    input.nextLine();
                    System.out.println(CountryQueries.TopCountriesInARegion(databaseConnection, region, limit));
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

    private static void cityReportsMenu(DatabaseConnection databaseConnection) throws SQLException {
        boolean exit = false;
        String userInput;
        int topX;

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
                    System.out.println(CityQueries.allCitiesInTheWorld(databaseConnection, null));
                    break;

                case "2":
                    System.out.println("Which continent would you like to view? ");
                    userInput = input.nextLine();

                    System.out.println(CityQueries.allCitiesInAContinent(databaseConnection, userInput));
                    break;

                case "3":
                    System.out.println("Which region would you like to view? ");
                    userInput = input.nextLine();

                    System.out.println(CityQueries.allCitiesInARegion(databaseConnection, userInput));
                    break;

                case "4":
                    System.out.println("Which country would you like to view? ");
                    userInput = input.nextLine();

                    System.out.println(CityQueries.allCitiesInACountry(databaseConnection, userInput));
                    break;

                case "5":
                    System.out.println("Which district would you like to view? ");
                    userInput = input.nextLine();

                    System.out.println(CityQueries.allCitiesInADistrict(databaseConnection, userInput));
                    break;

                case "6":
                    System.out.println("How many cities would you like to view? ");
                    topX = input.nextInt();
                    input.nextLine();

                    System.out.println(CityQueries.allCitiesInTheWorld(databaseConnection, topX));
                    break;

                case "7":
                    System.out.println("Which continent would you like to view? ");
                    userInput = input.nextLine();

                    System.out.println("How many cities would you like to view? ");
                    topX = input.nextInt();
                    input.nextLine();

                    System.out.println(CityQueries.topXCitiesInAContinent(databaseConnection, userInput, topX));
                    break;

                case "8":
                    System.out.println("Which region would you like to view? ");
                    userInput = input.nextLine();

                    System.out.println("How many cities would you like to view? ");
                    topX = input.nextInt();
                    input.nextLine();

                    System.out.println(CityQueries.topXCitiesInARegion(databaseConnection, userInput, topX));
                    break;

                case "9":
                    System.out.println("Which country would you like to view? ");
                    userInput = input.nextLine();

                    System.out.println("How many cities would you like to view? ");
                    topX = input.nextInt();
                    input.nextLine();

                    System.out.println(CityQueries.topXCitiesInACountry(databaseConnection, userInput, topX));
                    break;

                case "10":
                    System.out.println("Which district would you like to view? ");
                    userInput = input.nextLine();

                    System.out.println("How many cities would you like to view? ");
                    topX = input.nextInt();
                    input.nextLine();

                    System.out.println(CityQueries.topXCitiesInADistrict(databaseConnection, userInput, topX));
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
     * Each menu option retrieves and displays capital city reports for a variety of condiotons
     * can be filtered by continent and region as well as limiting the amount of data returned
     * @param databaseConnection passes in object to connect to database so that queries can be executed
     *
     */

    private static void capitalCityReportsMenu(DatabaseConnection databaseConnection) {
        boolean exit = false; //control for the menu loop

        //menu loop
        while (!exit) {
            System.out.println("\n------Capital City Reports------");

            System.out.println("1 - All the capital cities in the world organised by largest population to smallest.");
            System.out.println("2 - All the capital cities in a continent organised by largest population to smallest.");
            System.out.println("3 - All the capital cities in a region organised by largest to smallest.");
            System.out.println("4 - The top N populated capital cities in the world where N is provided by the user.");
            System.out.println("5 - The top N populated capital cities in a continent where N is provided by the user.");
            System.out.println("6 - The top N populated capital cities in a region where N is provided by the user.");
            System.out.println("0 - Back to main menu.");

            //captures users menu choice
            String choice = input.nextLine();

            //local variables to pass parameters into CapitalQueries class
            String continent = null; // variable to store continent input from user
            String region = null; // varaiable to store region input from  user
            int limit ; //variable to store user input to limit entries to view


            switch (choice) {
                case "1":
                    // Displays Report Header & retrieves all capital cities in world report
                    System.out.println("All Capital cities in world");
                    System.out.println("Organised by largest population to smallest");
                    System.out.println(CapitalQueries.AllCapitals(databaseConnection));
                    break;

                case "2":
                    // prompts user for continent
                    System.out.println("Please enter which Continent to view");
                    continent = input.nextLine();
                    try {
                        // Displays report header and capitals report for chosen continent
                        System.out.println("All Capital Cities in " + continent + " continent");
                        System.out.println("Organised by largest population to smallest");
                        System.out.println(CapitalQueries.AllCapitalsContinent(databaseConnection, continent));
                    } catch (Exception e) {
                        // catches exception if there are any runitme errors
                        throw new RuntimeException(e);
                    }
                    break;

                case "3":
                    // Prompts user for region
                    System.out.println("Please enter which Region to view");
                    region = input.nextLine();
                    try {
                        // Displays report header and capitals report for chosen region
                        System.out.println("All Capital Cities in " + region + " region");
                        System.out.println("Organised by largest population to smallest");
                        System.out.println(CapitalQueries.AllCapitalsRegion(databaseConnection, region));
                    } catch (Exception e) {
                        // catches any runtime exceptions
                        throw new RuntimeException(e);
                    }
                    break;

                case "4":
                    // prompts users for number of capital cities to view
                    System.out.println("Please enter how many capital cities to view");
                    limit = input.nextInt();
                    input.nextLine(); //clears the nextline from the buffer - causes invalid choice bug if not present
                    try {
                        // Displays report header and capitals report for user chosen number
                        System.out.println("Top " + limit + " Capital Cities in World ");
                        System.out.println("Organised by largest population to smallest");
                        System.out.println(CapitalQueries.XCapitalsWorld(databaseConnection, limit));
                    } catch (Exception e) {
                        throw new RuntimeException(e);
                    }
                    break;

                case "5":
                    // prompts user for continent choice and number of entries to view
                    System.out.println("Please enter which Continent to view");
                    continent = input.nextLine();
                    System.out.println("Please enter how many capital cities to view");
                    limit = input.nextInt();
                    input.nextLine();  //clears the nextline from the buffer - causes invalid choice bug if not present
                    try {
                        //Prints report header & capitals report for user chosen continent and number of entries
                        System.out.println("Top " + limit + " Capital Cities in " + continent + " Continent ");
                        System.out.println("Organised by largest population to smallest");
                        System.out.println(CapitalQueries.XCapitalsContinent(databaseConnection, continent, limit));
                    } catch (Exception e) {
                        throw new RuntimeException(e);
                    }
                    break;

                case "6":
                    //prompts user for region and number of entries input
                    System.out.println("Please enter which Region to view");
                    region = input.nextLine();
                    System.out.println("Please enter how many capital cities to view");
                    limit = input.nextInt();
                    input.nextLine();  //clears the nextline from the buffer - causes invalid choice bug if not present
                    try {
                        // displays header and capital reports for chose region and number of entries
                        System.out.println("Top " + limit + " Capital Cities in " + region + " Region ");
                        System.out.println("Organised by largest population to smallest");
                        System.out.println(CapitalQueries.XCapitalsRegion(databaseConnection, region, limit));
                    } catch (Exception e) {
                        throw new RuntimeException(e);
                    }
                    break;

                case "0": // changes control to 0 to exit menu
                    exit = true;
                    break;

                default:
                    // handles menu choices out of range
                    System.out.println("Invalid choice. Please enter a valid number.");
            }
        }
    }

    /**
     * A {@code populationAnalysisReportsMenu} method which displays the sub menu for all population reports
     * It allows for easy navigation to different reporting menus including back to previous menu
     * has built in validation
     */

    private static void populationAnalysisReportsMenu(DatabaseConnection databaseConnection) {
        boolean exit = false;

        while (!exit) {
            System.out.println("\n------Population Analysis Reports------");

            System.out.println("1 - The population of people, people living in cities, and people not living in cities in each continent.");
            System.out.println("2 - The population of people, people living in cities, and people not living in cities in each region.");
            System.out.println("3 - The population of people, people living in cities, and people not living in cities in each country.");
            System.out.println("0 - Back to main menu.");

            //captures users menu choice
            String choice = input.nextLine();

            //local variables to pass parameters into CapitalQueries class
            String continent = null; // variable to store continent input from user
            String region = null; // variable to store region input from  user
            String country = null; // variable to store country input from user

            switch (choice) {
                case "1":
                    // prompts user for continent
                    System.out.println("Please enter which Continent to view population distribution");
                    continent = input.nextLine();

                    try {
                        // Displays report header and capitals report for chosen continent
                        System.out.println("Population Distribution in " + continent + " continent");
                        //System.out.println("Organised by largest population to smallest");
                        System.out.println(PopDistributionQueries.ContinentPopulation(databaseConnection, continent));
                    } catch (Exception e) {
                        // catches exception if there are any runtime errors
                        throw new RuntimeException(e);
                    }
                    break;

                case "2":
                    // prompts user for continent
                    System.out.println("Please enter which region to view population distribution");
                    region = input.nextLine();

                    try {
                        // Displays report header and capitals report for chosen continent
                        System.out.println("Population Distribution in " + region + " region");
                        //System.out.println("Organised by largest population to smallest");
                        System.out.println(PopDistributionQueries.RegionPopulation(databaseConnection, region));
                    } catch (Exception e) {
                        // catches exception if there are any runtime errors
                        throw new RuntimeException(e);
                    }
                    break;

                case "3":
                    // prompts user for continent
                    System.out.println("Please enter which country to view population distribution");
                    country = input.nextLine();

                    try {
                        // Displays report header and capitals report for chosen continent
                        System.out.println("Population Distribution in " + country + " country");
                        //System.out.println("Organised by largest population to smallest");
                        System.out.println(PopDistributionQueries.CountryPopulation(databaseConnection, country));
                    } catch (Exception e) {
                        // catches exception if there are any runtime errors
                        throw new RuntimeException(e);
                    }
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

    private static void generalInformationMenu(DatabaseConnection databaseConnection) {
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

            // local variables to pass parameters into CapitalQueries class
            String continent = null; // variable to store continent input from user
            String region = null; // variable to store region input from  user
            String country = null; // variable to store country input from user
            String district = null; // variable to store district input from user
            String city = null; // variable to store city input from user

            switch (choice) {
                case "1":
                    try {
                        System.out.println(GeneralInfoQueries.populationOfTheWorld(databaseConnection));
                    } catch (SQLException e) {
                        throw new RuntimeException(e);
                    }
                    break;

                case "2":
                    // prompts user for continent
                    System.out.println("Please enter a continent");
                    continent = input.nextLine();
                    try {
                        System.out.println(GeneralInfoQueries.populationOfAContinent(databaseConnection, continent));
                    } catch (SQLException e) {
                        throw new RuntimeException(e);
                    }
                    break;

                case "3":
                    // prompts user for region
                    System.out.println("Please enter a region");
                    region = input.nextLine();
                    try {
                        System.out.println(GeneralInfoQueries.populationOfARegion(databaseConnection, region));
                    } catch (SQLException e) {
                        throw new RuntimeException(e);
                    }
                    break;

                case "4":
                    // prompts user for country
                    System.out.println("Please enter a country");
                    country = input.nextLine();
                    try {
                        System.out.println(GeneralInfoQueries.populationOfACountry(databaseConnection, country));
                    } catch (SQLException e) {
                        throw new RuntimeException(e);
                    }
                    break;

                case "5":
                    // prompts user for district
                    System.out.println("Please enter a district");
                    district = input.nextLine();
                    try {
                        System.out.println(GeneralInfoQueries.populationOfADistrict(databaseConnection, district));
                    } catch (SQLException e) {
                        throw new RuntimeException(e);
                    }
                    break;

                case "6":
                    // prompts user for city
                    System.out.println("Please enter a city");
                    city = input.nextLine();
                    try {
                        System.out.println(GeneralInfoQueries.populationOfACity(databaseConnection, city));
                    } catch (SQLException e) {
                        throw new RuntimeException(e);
                    }
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
