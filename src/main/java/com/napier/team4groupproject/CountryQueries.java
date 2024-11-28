package com.napier.team4groupproject;

import java.sql.*;
import java.util.Objects;

/**
 * The {@code CountryQueries} class provides methods to retrieve and display country reports
 */
public class CountryQueries {

    /**
     * Method to create the appropriate SQL query and fetch the data from the world database
     *
     * <p>This method builds the query for different country reports, all with the same columns and ordered by
     * population in descending order. It uses nullable parameters to determine if a where and/or limit is needed.
     * It then uses the built query in a prepared statement to protect from sql injections, and passes the resultSet
     * to the FormatOutput method in App which returns it as a formatted string.<p/>
     *
     * @param databaseConnection connects to the world database
     * @param attribute is a column name from the world database used to filter results in the where statement
     * @param whereParameter is the user input which will be used for filtering in the where statement
     * @param limitParameter is the user input which will determine the limit
     * @return a formatted string containing the data fetched from the query
     */
    private static String AllQueries(DatabaseConnection databaseConnection, String attribute, String whereParameter, Integer limitParameter) {
        // first part of the query which is always the same
        String query =  "SELECT Code, Name, Continent, Region, Population, " +
                            "(SELECT Name " +
                            "FROM city " +
                            "WHERE ID = country.Capital) " +
                            "AS `Capital`" +
                        "FROM country ";

        // second part, which is optional and only used if there is an attribute to filter with
        if (attribute != null) {
            query += "WHERE " + attribute + " = ? ";
        }

        // third part of the query which is also always the same
        query +=    "ORDER BY Population " +
                    "DESC ";

        // final part, which is also optional and only used if a limit is set
        if (limitParameter != null) {
            query += "LIMIT ?";
        }

        // creates a prepared statement using the query
        try(PreparedStatement preparedStatement = databaseConnection.getCon().prepareStatement(query)){
            // validates that the combination of not null arguments is valid
            if ((attribute == null && whereParameter != null) || (attribute != null && whereParameter == null)) {
                throw new IllegalArgumentException("attribute and whereParameter must either both be null or both not be null.");
            }

            if (limitParameter != null) {
                String validateLimitParameter = "" + limitParameter;
                validateLimitParameter = InputValidation.validateIntInput(validateLimitParameter);

                if (!Objects.equals(validateLimitParameter, "" + limitParameter)) {
                    return validateLimitParameter;
                }
            }


            // if there is a where it is set
            if (whereParameter != null) {
                String validateWhereParameter = InputValidation.validateStringInput(whereParameter);

                if (!Objects.equals(validateWhereParameter, whereParameter)) {
                    return validateWhereParameter;
                }

                preparedStatement.setString(1, whereParameter);
                // if there is a limit it is set
                if (limitParameter != null) {
                    preparedStatement.setInt(2, limitParameter);
                }
            // if there is not where but there is a limit the limit is set
            } else if (limitParameter != null) {
                preparedStatement.setInt(1, limitParameter);
            }

            // creates the result set
            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                // returns the formatted result set
                return App.FormatOutput(resultSet);
            }
        }
        // catch any exceptions
        catch (Exception e){
            return e.getMessage();
        }
    }

    /**
     * Method to create a report showing all countries in the world
     *
     * <p>This method calls the AllQueries method with the needed parameters, which in this case is only the database
     * connection, to return the requested report in the form of a string output.<p/>
     *
     * @param databaseConnection connects to the world database
     * @return a report showing all countries in the world
     */
    public static String CountriesInTheWorld(DatabaseConnection databaseConnection){
        return AllQueries(databaseConnection, null, null, null);
    }

    /**
     * Method to create a report showing all countries in a requested continent
     *
     * <p>This method calls the AllQueries method with the needed parameters, in this case the database
     * connection, the attribute being Continent and the requested continent, to return the requested report in the
     * form of a string output.<p/>
     *
     * @param databaseConnection connects to the world database
     * @param continent is the name of the requested continent
     * @return a report showing all countries in the requested continent
     */
    public static String CountriesInAContinent(DatabaseConnection databaseConnection, String continent){
        return AllQueries(databaseConnection, "Continent", continent, null);
    }

    /**
     * Method to create a report showing all countries in a requested region
     *
     * <p>This method calls the AllQueries method with the needed parameters, in this case the database
     * connection, the attribute being Region and the requested region, to return the requested report in the
     * form of a string output.<p/>
     *
     * @param databaseConnection connects to the world database
     * @param region is the name of the requested region
     * @return a report showing all countries in the requested region
     */
    public static String CountriesInARegion(DatabaseConnection databaseConnection, String region){
        return AllQueries(databaseConnection, "Region", region, null);
    }

    /**
     * Method to create a report showing a requested amount of most populated countries in the world
     *
     * <p>This method calls the AllQueries method with the needed parameters, in this case the database
     * connection and the requested amount (limit), to return the requested report in the form of a string output.<p/>
     *
     * @param databaseConnection connects to the world database
     * @param limitParameter is the requested amount
     * @return a report showing the requested amount of most populated countries in the world
     */
    public static String TopCountriesInTheWorld(DatabaseConnection databaseConnection, int limitParameter){
        return AllQueries(databaseConnection, null, null, limitParameter);
    }

    /**
     * Method to create a report showing a requested amount of most populated countries in a requested continent
     *
     * <p>This method calls the AllQueries method with the needed parameters, in this case the database
     * connection, the attribute being Continent, the requested continent and the requested amount (limit), to return
     * the requested report in the form of a string output.<p/>
     *
     * @param databaseConnection connects to the world database
     * @param continent is the name of the requested continent
     * @param limitParameter is the requested amount
     * @return a report showing the requested amount of most populated countries in the requested continent
     */
    public static String TopCountriesInAContinent(DatabaseConnection databaseConnection, String continent, int limitParameter){
        return AllQueries(databaseConnection, "Continent", continent, limitParameter);
    }

    /**
     * Method to create a report showing a requested amount of most populated countries in a requested region
     *
     * <p>This method calls the AllQueries method with the needed parameters, in this case the database
     * connection, the attribute being Region, the requested region and the requested amount (limit), to return the
     * requested report in the form of a string output.<p/>
     *
     * @param databaseConnection connects to the world database
     * @param region is the name of the requested region
     * @param limitParameter is the requested amount
     * @return a report showing the requested amount of most populated countries in the requested region
     */
    public static String TopCountriesInARegion(DatabaseConnection databaseConnection, String region, int limitParameter){
        return AllQueries(databaseConnection, "Region", region, limitParameter);
    }
}

