package com.napier.team4groupproject;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * PopDistributionQueries class provides methods to display population distribution reports
 */
public class PopDistributionQueries {
    /**
     * Private method to create the appropriate SQL query for population distribution reports.
     * It builds the query for population distribution, with nullable parameters to filter by continent, region, or country.
     * Uses a prepared statement to prevent SQL injection, and passes the resultSet to the FormatOutput method in App.
     *
     * @param sql the DatabaseConnection object used to connect to the world database
     * @param attribute the column used to filter (e.g., continent, region, country) or null
     * @param where the user-inputted value for filtering (e.g., "Europe", "Asia") or null
     * @return formatted string of data containing query results or null if an exception occurs
     */
    private static String populationDistributionQuery(DatabaseConnection sql, String attribute, String where) {
        // Start building the base query with dynamic SELECT fields
        String query = "SELECT ";

        // Determine which fields to include in the SELECT clause based on the attribute
        if ("continent".equals(attribute)) {
            query += "country.Continent AS Continent, ";
        } else if ("region".equals(attribute)) {
            query += "country.Region AS Region, ";
        } else if ("country.Name".equals(attribute)) {
            query += "country.Name AS Country, ";
        }

        // Add the common fields for population data
        query += "SUM(country.Population) AS `Total Pop`, " +
                "SUM(CASE WHEN city.Population IS NOT NULL THEN city.Population ELSE 0 END) AS `City Pop`, " +
                "(SUM(country.Population) - SUM(CASE WHEN city.Population IS NOT NULL THEN city.Population ELSE 0 END)) AS `Rural Pop`, " +
                "(SUM(CASE WHEN city.Population IS NOT NULL THEN city.Population ELSE 0 END) / SUM(country.Population)) * 100 AS `City %`, " +
                "((SUM(country.Population) - SUM(CASE WHEN city.Population IS NOT NULL THEN city.Population ELSE 0 END)) / SUM(country.Population)) * 100 AS `Rural %` " +
                "FROM country " +
                "LEFT JOIN city ON country.Code = city.CountryCode ";

        // Add filtering condition if needed
        if (attribute != null) {
            query += " WHERE " + attribute + " = ? ";
        }

        // Build the GROUP BY clause dynamically based on the attribute
        if ("continent".equals(attribute)) {
            query += "GROUP BY country.Continent ";
        } else if ("region".equals(attribute)) {
            query += "GROUP BY country.Region ";
        } else if ("country.Name".equals(attribute)) {
            query += "GROUP BY country.Name ";
        }

        // Add an ORDER BY clause for consistent sorting
        query += "ORDER BY " + (attribute != null ? attribute : "country.Continent");

        try (PreparedStatement preparedStatement = sql.getCon().prepareStatement(query)) {
            if (attribute != null && where != null) {
                preparedStatement.setString(1, where);
            }

            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                return App.FormatOutput(resultSet);
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return null;
        }
    }

    /**
     * Retrieves population distribution for a specific continent.
     *
     * @param sql the DatabaseConnection object used to connect to the world database
     * @param continent the name of the continent to filter by
     * @return formatted string of population distribution for the specified continent or null if an error occurs
     */
    public static String ContinentPopulation(DatabaseConnection sql, String continent) {
        return populationDistributionQuery(sql, "continent", continent);
    }

    /**
     * Retrieves population distribution for a specific region.
     *
     * @param sql the DatabaseConnection object used to connect to the world database
     * @param region the name of the region to filter by
     * @return formatted string of population distribution for the specified region or null if an error occurs
     */
    public static String RegionPopulation(DatabaseConnection sql, String region) {
        return populationDistributionQuery(sql, "region", region);
    }

    /**
     * Retrieves population distribution for a specific country.
     *
     * @param sql the DatabaseConnection object used to connect to the world database
     * @param country the name of the country to filter by
     * @return formatted string of population distribution for the specified country or null if an error occurs
     */
    public static String CountryPopulation(DatabaseConnection sql, String country) {
        return populationDistributionQuery(sql, "country.Name", country);
    }
}
