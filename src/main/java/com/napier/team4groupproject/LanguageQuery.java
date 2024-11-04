package com.napier.team4groupproject;

import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class LanguageQuery {
    public static String LanguageDistributionInThWorld(DatabaseConnection databaseConnection) {
        String query =  "SELECT  countrylanguage.Language, " +
                                "ROUND(SUM(country.Population*countrylanguage.Percentage/100)) AS `Number of Speakers`, " +
                                "CONCAT(ROUND(SUM(country.Population*countrylanguage.Percentage/100)*100/(SELECT SUM(Population) FROM country)), '%') AS `Percentage of Speakers` " +
                        "FROM countrylanguage JOIN country ON countrylanguage.CountryCode = country.Code " +
                        "WHERE Language IN ('Chinese', 'English', 'Hindi', 'Spanish', 'Arabic') " +
                        "GROUP BY Language " +
                        "ORDER BY `Number of Speakers` " +
                        "DESC";

        try(PreparedStatement preparedStatement = databaseConnection.getCon().prepareStatement(query)){
            // creates the result set
            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                // returns the formatted result set
                return App.FormatOutput(resultSet);
            }
        }  // catch any exceptions
        catch (Exception e){
            System.out.println(e.getMessage());
            return null;
        }
    }
}
