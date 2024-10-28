package com.napier.team4groupproject;

import java.sql.*;

public class CountryQueries {

    public static String CountriesInTheWorld(DatabaseConnection databaseConnection){
        String query =  "SELECT Code, Name, Continent, Region, Population, " +
                            "(SELECT Name " +
                            "FROM city " +
                            "WHERE ID = country.Capital) " +
                            "AS `Capital`" +
                        "FROM country " +
                        "ORDER BY Population " +
                        "DESC";

        try(PreparedStatement preparedStatement = databaseConnection.getCon().prepareStatement(query)){

            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                if(resultSet.next()){
                    return App.FormatOutput(resultSet);
                }
                else{
                    return null;
                }
            }
        }
        catch (Exception e){
            System.out.println(e.getMessage());
            return null;
        }
    }
}
