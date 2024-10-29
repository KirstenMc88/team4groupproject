package com.napier.team4groupproject;

import java.sql.*;

public class CountryQueries {

    /**
     * Method to get all countries in the world from a database
     *
     * <p>This method uses a prepared statement from java.sql to retrieve the information of all countries in the world
     * from the world database which is connected to the database connection passed into it.<p/>
     *
     * @param databaseConnection for access to the world database
     * @return a string containing the result of the retrieved data, formatted with FormatOutput method from App
     */
    public static String CountriesInTheWorld(DatabaseConnection databaseConnection){
        // header to make it clear what report this is
        System.out.println("All Countries In The World");

        // sql query to retrieve all countries in the world
        // displays their country code, name, continent, region, population and capital city
        // and sorts from biggest population to smallest
        String query =  "SELECT Code, Name, Continent, Region, Population, " +
                "(SELECT Name " +
                "FROM city " +
                "WHERE ID = country.Capital) " +
                "AS `Capital`" +
                "FROM country " +
                "ORDER BY Population " +
                "DESC";
        // try-with-resource for prepared statement
        try(PreparedStatement preparedStatement = databaseConnection.getCon().prepareStatement(query)){

            // try-with-resource for result set
            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                // return formatted result set
                return App.FormatOutput(resultSet);
            }
        }
        // catch exceptions
        catch (Exception e){
            System.out.println(e.getMessage());
            return null;
        }
    }
}