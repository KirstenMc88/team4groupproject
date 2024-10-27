package com.napier.team4groupproject;

import java.sql.*;

public class CountryQueries {
    DatabaseConnection databaseConnection;

    public CountryQueries(DatabaseConnection databaseConnection) {
        this.databaseConnection = databaseConnection;
    }

    public ResultSet CountriesInTheWorld(){
        try{
            Statement statement = databaseConnection.getCon().createStatement();

            String query =  "SELECT Code, Name, Continent, Region, Population, " +
                                "(SELECT Name " +
                                "FROM city " +
                                "WHERE ID = country.Capital) " +
                                "AS `Capital`" +
                            "FROM country " +
                            "ORDER BY Population " +
                            "DESC";

            ResultSet resultSet = statement.executeQuery(query);

            if(resultSet.next()){
                return resultSet;
            }
            else{
                return null;
            }
        }
        catch (Exception e){
            System.out.println(e.getMessage());
            return null;
        }
    }
}
