package com.napier.team4groupproject;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;

/**
 * CapitalQueries class provides methods to display each report related to world capital cities
 */
public class CapitalQueries {

    /**
     * Method which retrieves information showing all capital cities in the world
     * <br>
     * <p>Executes an SQL statement which fetches all capital cities, the country they are in and their population
     * <br>
     * Result is formatted using {@link App#FormatOutput(ResultSet)} to ensure consistency between reports</p>
     *
     * @param sql passes in {@link DatabaseConnection} object used to connect to the database
     * @return A formatted string displaying capital city information or an error message if an exception happens
     */
    public static String allInWorld(DatabaseConnection sql) {

        //Prints header to show what report is being shown
        System.out.println("All Capital Cities in World");

        // SQL query to select all capital cities in world
        // includes a where clause placeholder for future queries
        String query = "SELECT city.Name AS Name, country.Name AS Country, city.Population " +
                "FROM city " +
                "JOIN country ON city.ID = country.Capital " + // join to select only capital cities
                "WHERE ? = ? " + // Placeholder condition for future use
                "ORDER BY city.Population DESC"; //orders by population descending

        // Try-with-resources to handle PreparedStatement and ResultSet, closing after use
        try (PreparedStatement preparedStatement = sql.getCon().prepareStatement(query)) {
            //sets placeholders values to 1 = 1 again future proofing so can be used as basis for further queries
            preparedStatement.setInt(1, 1);
            preparedStatement.setInt(2, 1);
            // executes query and formats out to standard using FormatOutput method
            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                return App.FormatOutput(resultSet);
            }

            // catches exception if created and displays error message
        }   catch (Exception e) {
                System.out.println(e.getMessage());
                return "Error fetching Capital Cities Data";
        }

    }
}
