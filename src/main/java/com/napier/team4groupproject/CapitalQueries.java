package com.napier.team4groupproject;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.time.LocalDate;


public class CapitalQueries {

    public static void allInWorld(DatabaseConnection sql) {

        System.out.println("All Capital Cities in World");
        LocalDate today = LocalDate.now();
        System.out.println("Now is: " + today);

// SQL query with a placeholder that doesn’t alter the results
        String query = "SELECT city.Name AS Name, country.Name AS Country, city.Population " +
                "FROM city JOIN country ON city.CountryCode = country.Code " +
                "WHERE ? = ? " + // Placeholder condition that always evaluates to true
                "ORDER BY city.Population DESC";

        try (PreparedStatement preparedStatement = sql.getConnection().prepareStatement(query)) {

            preparedStatement.setInt(1, 1);
            preparedStatement.setInt(2, 1);

            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                String cityName = resultSet.getString("Name");
                String countryName = resultSet.getString("Country");
                int population = resultSet.getInt("Population");

                System.out.printf("City: %s, Country: %s, Population: %d%n", cityName, countryName, population);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


}
