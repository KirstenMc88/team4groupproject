package com.napier.team4groupproject;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class GeneralInfoQueries {

    public static String populationOfTheWorld(DatabaseConnection databaseConnection) throws SQLException {

        System.out.println("Population of the world");

        String query =  "SELECT SUM(Population) FROM country";

        try(PreparedStatement preparedStatement = databaseConnection.getCon().prepareStatement(query)){

            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                // return formatted result set
                return App.FormatOutput(resultSet);
            }
        }
        catch (Exception e){
            System.out.println(e.getMessage());
            return null;
        }

    }

}
