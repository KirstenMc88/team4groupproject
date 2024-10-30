package com.napier.team4groupproject;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * the GeneralInfoQueries class provides methods to display each report listed under the general info submenu.
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
        String query =  "SELECT SUM(Population) FROM country";

        // prepare the query for execution
        try(PreparedStatement preparedStatement = databaseConnection.getCon().prepareStatement(query)){

            // executes the query and stores the result in resultSet
            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                // returns formatted result set
                return App.FormatOutput(resultSet);
            }

        }

        // catches any exceptions which may occur during the query, execution or formatting
        // then prints the exceptions
        catch (Exception e){
            System.out.println(e.getMessage());
            return null;
        }

    }

}