package com.napier.team4groupproject;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * The {@code CapitalQueries} class provides methods to retrieve and display each report related to worlds capital cities
 */
public class CapitalQueries {


    /**
     * Private method to create the appropriate SQL query and fetch the data from the world database
     * <p>
     *     <p>This method builds the query for different Capital city reports, all with the same columns and ordered by
     *      * population in descending order. It uses nullable parameters to determine if a where and/or limit is needed.
     *      * It then uses the built query in a prepared statement to protect from sql injections, and passes the resultSet
     *      * to the FormatOutput method in App which returns it as a formatted string
     * </p>
     * @param sql the DatabaseConnection object used to connect to the world database
     * @param attribute the column from world db used to filter. examples: continent, region or null
     * @param where the user inputted value used to filter query. examples: Europe, North America, Nordic Countries or null
     * @param limit the user inputted value to limit the amount of entries shown. examples: 5, 25 or null (returns every entry)
     * @return formatted string of data containing query results or null if exception occurs
     */
    private static String capitalQueries(DatabaseConnection sql, String attribute, String where, Integer limit) {
        // builds the base of the query selecting city name, country name and population
        String query = "SELECT city.Name AS Name, country.Name AS Country, city.Population " +
                "FROM city " +
                "JOIN country ON city.ID = country.Capital " ;

        // appends where onto query if attribute and where parameters are provided
        if (attribute != null) {
            query += " WHERE " + attribute + " = ? ";
        }

        // always appends order by Population descending clause onto query
        query += " ORDER BY city.Population DESC";

        // appends user specified limit onto clause if parameter is provided
        if (limit != null) {
            query += " LIMIT ? ";
        }

        // tries to build prepared statement which is closed when query is retrieved
        try (PreparedStatement preparedStatement = sql.getCon().prepareStatement(query)) {

            // validates that either both attribute and where are provided or neither is avoids mismatch
            if ((attribute == null && where != null) || (attribute != null && where == null)) {
                throw new IllegalArgumentException("attribute & Where must match (either both null or both not null");
            }
            // if where is provided it is set in prepared statement
            if (where != null) {
                preparedStatement.setString(1, where);
                // if limit is provided it is set in prepared statement
                if (limit != null) {
                    preparedStatement.setInt(2, limit);
                }
            // if limit is set but not where, limit is set in prepared statement
            } else if (limit != null) {
                preparedStatement.setInt(1, limit);
            }

            //exceute query against db and format resultSet into correct format with FormatOutput method
            try (ResultSet resultSet = preparedStatement.executeQuery()) {
            return App.FormatOutput(resultSet);
        }
        } catch (Exception e) {
            //displays error message for any exceptions and returns null if this is the case
            System.out.println(e.getMessage());
            return null;
        }
    }

    /**
     * Report which retrieves all Capital cities in the world
     * ordered by population largest to smallest
     *
     * @param sql the DatabaseConnection object used to connect to world db
     * @return formatted string of all capital cities in world ordered by population
     *         or null if an error occurs
     */
    public static String AllCapitals(DatabaseConnection sql) {
        return capitalQueries(sql, null, null, null); //attribute, where and limit not needed
    }

    /**
     * All the capital cities in a continent organised by largest population to smallest.
     * report which retrieves all capital cities in user inputted continent
     *
     * @param sql the DatabaseConnection object used to connect to world db
     * @param continent user inputted name of continent to filter by. example "Europe", "Asia", "North America"
     * @return Formatted string of the capital cities in user inputted continent ordered by population or null if error occurs
     */
    public static String AllCapitalsContinent(DatabaseConnection sql, String continent) {
        return capitalQueries(sql, "continent", continent, null);
    }

    /**
     * All the capital cities in a region organised by largest to smallest.
     * report which retrieves all capital cities in user specified region
     *
     * @param sql the DatabaseConnection object used to connect to db
     * @param region user inputted name of region to filter by. example "Middle East", "Polynesia", "Western Europe"
     * @return Formatted string of the capital cities in user inputted region ordered by population or null if error occurs
     */
    public static String AllCapitalsRegion(DatabaseConnection sql, String region) {
        return capitalQueries(sql, "region", region, null);
    }

    /**
     * The top N populated capital cities in the world where N is provided by the user.
     * Report which retrieves user inputted number of capital cities in the world
     *
     * @param sql the DatabaseConnection object used to connect to db
     * @param limit user inputted limit of how many records to view examples 10, 25
     * @return Formatted string of the capital cities limit by user inputted number ordered by population or null if error occurs
     */
    public static String XCapitalsWorld(DatabaseConnection sql, int limit) {
        return capitalQueries(sql, null, null, limit);
    }

    /**
     * The top N populated capital cities in a continent where N is provided by the user.
     * Report which retrieves user inputted number of capital cities from user inputted continent
     *
     * @param sql the DatabaseConnection object used to connect to db
     * @param continent user inputted name of continent to filter by. example "Europe", "Asia", "North America"
     * @param limit user inputted limit of how many records to view examples 10, 25
     * @return Formatted string of the capital cities in specified continent, limited by user inputted number,ordered by population or null
     */
    public static String XCapitalsContinent(DatabaseConnection sql, String continent, int limit) {
        return capitalQueries(sql, "continent", continent, limit);
    }

    /**
     * The top N populated capital cities in a region where N is provided by the user.
     * report which retrieves user inputted number of capital cities from user specified region
     *
     * @param sql the DatabaseConnection object used to connect to db
     * @param region user inputted name of region to filter by. example "Middle East", "Polynesia", "Western Europe"
     * @param limit user inputted limit of how many records to view examples 10, 25
     * @return Formatted string of the capital cities in specified continent, limited by user inputted number,ordered by population or null
     */
    public static String XCapitalsRegion(DatabaseConnection sql, String region, int limit) {
        return capitalQueries(sql, "region", region, limit);
    }
}
