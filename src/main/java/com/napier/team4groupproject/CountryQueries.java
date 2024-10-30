package com.napier.team4groupproject;

import java.sql.*;

public class CountryQueries {

    private static String AllQueries(DatabaseConnection databaseConnection, String attribute, String whereParameter, Integer limitParameter) {
        String query =  "SELECT Code, Name, Continent, Region, Population, " +
                "(SELECT Name " +
                "FROM city " +
                "WHERE ID = country.Capital) " +
                "AS `Capital`" +
                "FROM country ";

        if (attribute != null) {
            query += "WHERE " + attribute + " = ? ";
        }

        query +=    "ORDER BY Population " +
                "DESC ";

        if (limitParameter != null) {
            query += "LIMIT ?";
        }

        System.out.println(query);

        try(PreparedStatement preparedStatement = databaseConnection.getCon().prepareStatement(query)){

            if (whereParameter != null) {
                preparedStatement.setString(1, whereParameter);
                if (limitParameter != null) {
                    preparedStatement.setInt(2, limitParameter);
                }
            } else if (limitParameter != null) {
                preparedStatement.setInt(1, limitParameter);
            }

            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                return App.FormatOutput(resultSet);
            }
        }
        catch (Exception e){
            System.out.println(e.getMessage());
            return null;
        }
    }

    public static String CountriesInTheWorld(DatabaseConnection databaseConnection){
        return AllQueries(databaseConnection, null, null, null);
    }

    public static String CountriesInAContinent(DatabaseConnection databaseConnection, String continent){
        return AllQueries(databaseConnection, "Continent", continent, null);
    }

    public static String CountriesInARegion(DatabaseConnection databaseConnection, String region){
        return AllQueries(databaseConnection, "Region", region, null);
    }

    public static String TopCountriesInTheWorld(DatabaseConnection databaseConnection, int limitParameter){
        return AllQueries(databaseConnection, null, null, limitParameter);
    }

    public static String TopCountriesInAContinent(DatabaseConnection databaseConnection, String continent, int limitParameter){
        return AllQueries(databaseConnection, "Continent", continent, limitParameter);
    }

    public static String TopCountriesInARegion(DatabaseConnection databaseConnection, String region, int limitParameter){
        return AllQueries(databaseConnection, "Region", region, limitParameter);
    }
}

