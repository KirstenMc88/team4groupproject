package com.napier.team4groupproject;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * The {@code GeneralInfoQueries} class provides methods to retrieve and display each report listed under the general info submenu.
 */

public class GeneralInfoQueries {

    /**
     * Method to query the database, returns the sum of the countries' populations.
     *
     * @param databaseConnection passes in database connection as parameter.
     * @return returns formatted data by passing the retrieved data through the formatOutput function
     */

    public static String populationOfTheWorld(DatabaseConnection databaseConnection) throws SQLException {

        // header for clarity
        System.out.println("Population of the world");

        // sql query to add all the countries' populations
        String query = "SELECT SUM(Population) FROM country";

        // prepare the query for execution
        try (PreparedStatement preparedStatement = databaseConnection.getCon().prepareStatement(query)) {

            // executes the query and stores the result in resultSet
            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                // returns formatted result set
                return App.FormatOutput(resultSet);
            }

        }

        // catches any exceptions which may occur during the query, execution or formatting
        // then prints the exceptions
        catch (Exception e) {
            System.out.println(e.getMessage());
            return null;
        }

    }

    /**
     * Method to query the database, returns the sum of the selected continent's population.
     *
     * @param databaseConnection passes in database connection as parameter.
     * @param continent          passes in a continent as a parameter.
     * @return returns formatted data by passing the retrieved data through the formatOutput function
     */

    public static String populationOfAContinent(DatabaseConnection databaseConnection, String continent) throws SQLException {

        // validates the user input
        String validatedContinent = InputValidation.validateStringInput(continent);

        if (validatedContinent.equals("Field cannot be empty") || validatedContinent.equals("Field cannot be over 50 characters.")) {
            return validatedContinent;
        } else {
            // sql query to add all the countries' populations which are part of a specific continent
            String query = "SELECT SUM(Population) AS Population FROM country WHERE Continent = ?";

            // prepare the query for execution
            try (PreparedStatement preparedStatement = databaseConnection.getCon().prepareStatement(query)) {

                // sets the validated continent parameter in the query
                preparedStatement.setString(1, validatedContinent);

                // executes the query and stores the result in resultSet
                try (ResultSet resultSet = preparedStatement.executeQuery()) {
                    if (resultSet.next()) {
                        // retrieve the population value
                        Long population = resultSet.getLong("Population");

                        // check for null or zero population
                        if (resultSet.wasNull() || population == 0) {
                            return "No population data available for the given continent.";
                        }
                        // header for clarity
                        System.out.println("Population of a continent");
                        // return formatted population
                        return "SUM(Population) \n" + population;
                    } else {
                        // no rows found for the continent
                        return "No matching data found. Please check your spelling and try again.";
                    }
                }

            }
            // catches any exceptions which may occur during the query, execution or formatting
            // then prints the exceptions
            catch (Exception e) {
                System.out.println("Error: " + e.getMessage());
                return "An error occurred while retrieving data.";
            }
        }
    }



    /**
     * Method to query the database, returns the sum of the selected region's population.
     *
     * @param databaseConnection passes in database connection as parameter.
     * @param region             passes in a region as a parameter.
     * @return returns formatted data by passing the retrieved data through the formatOutput function
     */

    public static String populationOfARegion(DatabaseConnection databaseConnection, String region) throws SQLException {

        // validates the user input
        String validatedRegion = InputValidation.validateStringInput(region);

        if (validatedRegion.equals("Field cannot be empty") || validatedRegion.equals("Field cannot be over 50 characters.")) {
            return validatedRegion;
        } else {
            // sql query to add all the countries' populations which are part of a specific region
            String query = "SELECT SUM(Population) AS Population FROM country WHERE Region = ?";

            // prepare the query for execution
            try (PreparedStatement preparedStatement = databaseConnection.getCon().prepareStatement(query)) {

                // sets the validated region parameter in the query
                preparedStatement.setString(1, validatedRegion);

                // executes the query and stores the result in resultSet
                try (ResultSet resultSet = preparedStatement.executeQuery()) {
                    if (resultSet.next()) {
                        // retrieve the population value
                        Long population = resultSet.getLong("Population");

                        // check for null or zero population
                        if (resultSet.wasNull() || population == 0) {
                            return "No population data available for the given region.";
                        }
                        // header for clarity
                        System.out.println("Population of a region");
                        // return formatted population
                        return "SUM(Population) \n" + population;
                    } else {
                        // no rows found for the region
                        return "No matching data found. Please check your spelling and try again.";
                    }
                }

            }
            // catches any exceptions which may occur during the query, execution or formatting
            // then prints the exceptions
            catch (Exception e) {
                System.out.println(e.getMessage());
                return null;
            }
        }
    }


    /**
     * Method to query the database, returns the selected country's population.
     *
     * @param databaseConnection passes in database connection as parameter.
     * @param country            passes in a country as a parameter.
     * @return returns formatted data by passing the retrieved data through the formatOutput function
     */

    public static String populationOfACountry(DatabaseConnection databaseConnection, String country) throws SQLException {

        // validates the user input
        String validatedCountry = InputValidation.validateStringInput(country);

        if (validatedCountry.equals("Field cannot be empty") || validatedCountry.equals("Field cannot be over 50 characters.")) {
            return validatedCountry;
        } else {
            // sql query to select the country's population
            String query = "SELECT Population FROM country WHERE Name = ?";

            // prepare the query for execution
            try (PreparedStatement preparedStatement = databaseConnection.getCon().prepareStatement(query)) {

                // sets the validated country parameter in the query
                preparedStatement.setString(1, validatedCountry);

                // executes the query and stores the result in resultSet
                try (ResultSet resultSet = preparedStatement.executeQuery()) {
                    if (resultSet.next()) {
                        // retrieve the population value
                        Long population = resultSet.getLong("Population");

                        // check for null or zero population
                        if (resultSet.wasNull() || population == 0) {
                            return "No population data available for the given country.";
                        }
                        // header for clarity
                        System.out.println("Population of a country");
                        // return formatted population
                        return "SUM(Population) \n" + population;
                    } else {
                        // no rows found for the country
                        return "No matching data found. Please check your spelling and try again.";
                    }
                }
            }
            // catches any exceptions which may occur during the query, execution or formatting
            // then prints the exceptions
            catch (Exception e) {
                System.out.println(e.getMessage());
                return null;
            }
        }
    }


    /**
     * Method to query the database, returns the sum of the selected district's population.
     *
     * @param databaseConnection passes in database connection as parameter.
     * @param district           passes in a district as a parameter.
     * @return returns formatted data by passing the retrieved data through the formatOutput function
     */

    public static String populationOfADistrict(DatabaseConnection databaseConnection, String district) throws SQLException {

        // validates the user input
        String validatedDistrict = InputValidation.validateStringInput(district);

        if (validatedDistrict.equals("Field cannot be empty") || validatedDistrict.equals("Field cannot be over 50 characters.")) {
            return validatedDistrict;
        } else {
            // sql query to add all the city's populations which are part of a specific district
            String query = "SELECT SUM(Population) AS Population FROM city WHERE District = ?";

            // prepare the query for execution
            try (PreparedStatement preparedStatement = databaseConnection.getCon().prepareStatement(query)) {

                // sets the validated district parameter in the query
                preparedStatement.setString(1, validatedDistrict);

                // executes the query and stores the result in resultSet
                try (ResultSet resultSet = preparedStatement.executeQuery()) {
                    if (resultSet.next()) {
                        // retrieve the population value
                        Long population = resultSet.getLong("Population");

                        // check for null or zero population
                        if (resultSet.wasNull() || population == 0) {
                            return "No population data available for the given district.";
                        }
                        // header for clarity
                        System.out.println("Population of a district");
                        // return formatted population
                        return "SUM(Population) \n" + population;
                    } else {
                        // no rows found for the district
                        return "No matching data found. Please check your spelling and try again.";
                    }
                }

            }
            // catches any exceptions which may occur during the query, execution or formatting
            // then prints the exceptions
            catch (Exception e) {
                System.out.println(e.getMessage());
                return null;
            }
        }
    }


    /**
     * Method to query the database, returns the selected city's population.
     *
     * @param databaseConnection passes in database connection as parameter.
     * @param city               passes in a city as a parameter.
     * @return returns formatted data by passing the retrieved data through the formatOutput function
     */

    public static String populationOfACity(DatabaseConnection databaseConnection, String city) throws SQLException {

        // validates the user input
        String validatedCity = InputValidation.validateStringInput(city);

        if (validatedCity.equals("Field cannot be empty") || validatedCity.equals("Field cannot be over 50 characters.")) {
            return validatedCity;
        } else {
            // sql query to get the population of a specific city
            String query = "SELECT Population FROM city WHERE Name = ?";

            // prepare the query for execution
            try (PreparedStatement preparedStatement = databaseConnection.getCon().prepareStatement(query)) {

                // sets the validated city parameter in the query
                preparedStatement.setString(1, validatedCity);

                // executes the query and stores the result in resultSet
                try (ResultSet resultSet = preparedStatement.executeQuery()) {
                    if (resultSet.next()) {
                        // retrieve the population value
                        Long population = resultSet.getLong("Population");

                        // check for null or zero population
                        if (resultSet.wasNull() || population == 0) {
                            return "No population data available for the given city.";
                        }
                        // header for clarity
                        System.out.println("Population of a city");
                        // return formatted population
                        return "SUM(Population) \n" + population;
                    } else {
                        // no rows found for the city
                        return "No matching data found. Please check your spelling and try again.";
                    }
                }

            }
            // catches any exceptions which may occur during the query, execution or formatting
            // then prints the exceptions
            catch (Exception e) {
                System.out.println(e.getMessage());
                return null;
            }
        }
    }
}

