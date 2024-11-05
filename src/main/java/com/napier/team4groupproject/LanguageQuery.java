package com.napier.team4groupproject;

import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class LanguageQuery {
    /**
     * Method to create language report
     *
     * <p>The language report created contains the number and percentage of people in the world speaking Chinese,
     * English, Hindi, Spanish and Arabic, sorted in descending order.</p>
     *
     * @param databaseConnection connection to the world database
     * @return the language report
     */
    public static String LanguageDistributionInThWorld(DatabaseConnection databaseConnection) {
        // sql query
        String query =  "SELECT  countrylanguage.Language, " +
                                "ROUND(SUM(country.Population*countrylanguage.Percentage/100)) AS `Number of Speakers`, " +
                                "CONCAT(ROUND(SUM(country.Population*countrylanguage.Percentage/100)*100/(SELECT SUM(Population) FROM country)), '%') AS `Percentage of Speakers` " +
                        "FROM countrylanguage JOIN country ON countrylanguage.CountryCode = country.Code " +
                        "WHERE Language IN ('Chinese', 'English', 'Hindi', 'Spanish', 'Arabic') " +
                        "GROUP BY Language " +
                        "ORDER BY `Number of Speakers` " +
                        "DESC";

        // creates a prepared statement using the query
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
