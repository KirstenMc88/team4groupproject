package com.napier.team4groupproject;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class CityQueries {

   /**
    * Function to query database, returns specified field data on all cities.
    *
    * @param databaseConnection passes in database connection as parameter.
    *
    * @return returns formatted data by passing the retrieved data through the formatOutput function
    *
    * @throws SQLException
    */
   public static String allCitiesInTheWorld(DatabaseConnection databaseConnection) throws SQLException {

      System.out.println("All Cities in the World");
      Statement stmt = databaseConnection.getCon().createStatement();

      // query to retrieve the data from the database
      String cityQuery = "Select city.Name AS City, country.Name AS Country, city.District, city.Population "
              + "from city as city "
              + " inner join country as country on city.CountryCode = country.Code "
              + " order by city.Population desc";

      //  runs the query and assigns the result to resultSet
      ResultSet resultSet = stmt.executeQuery(cityQuery);

      // checks if a result has been returned from the query
      if (resultSet.next()) {
         return App.FormatOutput(resultSet);
      }// error message, if data hasn't been retrieved
      return "System Error, couldn't retrieve the data.";
   }


   /**
    *
    * @param databaseConnection
    *
    * @param userInput
    *
    * @param queryWhere
    *
    * @return
    *
    * @throws SQLException
    */
   public static String statementBuilder(DatabaseConnection databaseConnection, String userInput, String queryWhere) throws SQLException {


      //userInput = InputValidation.validateStringInput(userInput);

      PreparedStatement pstmt = null;
      try {
         String cityQuery = "Select city.Name AS City, country.Name AS Country, city.District, city.Population "
                 + "from city as city "
                 + " inner join country as country on city.CountryCode = country.Code "
                 + "WHERE " + queryWhere + " = ?"
                 + " order by city.Population desc";



         pstmt = databaseConnection.getCon().prepareStatement(cityQuery);

         pstmt.setString(1, userInput);

         //  runs the query and assigns the result to resultSet
         ResultSet resultSet = pstmt.executeQuery();

         // checks if a result has been returned from the query
         if (resultSet.next()) {
            return App.FormatOutput(resultSet);
         }



      }
      catch (SQLException e) {
         System.out.println(e.getMessage());
      }
      finally {
         pstmt.close();
      }

      // error message, if data hasn't been retrieved
      return "System Error, couldn't retrieve data.";
   }

   /**
    *
    * @param databaseConnection
    *
    * @param userInput
    *
    * @return
    *
    * @throws SQLException
    */
   public static String allCitiesInAContinent(DatabaseConnection databaseConnection, String userInput) throws SQLException {

      //userInput = InputValidation.validateStringInput(userInput);
      return statementBuilder(databaseConnection, userInput, "country.Continent");

   }

   /**
    *
    * @param databaseConnection
    *
    * @param userInput
    *
    * @return
    *
    * @throws SQLException
    */
   public static String allCitiesInARegion(DatabaseConnection databaseConnection, String userInput) throws SQLException {

      //userInput = InputValidation.validateStringInput(userInput);
      return statementBuilder(databaseConnection, userInput, "country.Region");

   }

   /**
    *
    * @param databaseConnection
    *
    * @param userInput
    *
    * @return
    *
    * @throws SQLException
    */
   public static String allCitiesInACountry(DatabaseConnection databaseConnection, String userInput) throws SQLException {

      //userInput = InputValidation.validateStringInput(userInput);
      return statementBuilder(databaseConnection, userInput, "country.Name");

   }

   /**
    *
    * @param databaseConnection
    *
    * @param userInput
    *
    * @return
    *
    * @throws SQLException
    */
   public static String allCitiesInADistrict(DatabaseConnection databaseConnection, String userInput) throws SQLException {

      //userInput = InputValidation.validateStringInput(userInput);
      return statementBuilder(databaseConnection, userInput, "city.District");

   }


}

