package com.napier.team4groupproject;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/**
 * The {@code CityQueries} class provides methods to retrieve and display each of the city reports
 */

public class CityQueries {

   /**
    * Method to query the database, returns specified field data on all cities.
    * Can be used to show all cities or a specified number
    *
    * @param databaseConnection passes in database connection as parameter to allow access.
    *
    * @param topX topX is used to limit the number of database entries output to the user when provided.
    *
    * @return method returns formatted data by passing the retrieved data through the formatOutput function
    *
    * @throws SQLException throws SQL exception.
    */
   public static String allCitiesInTheWorld(DatabaseConnection databaseConnection, Integer topX) throws SQLException {

      // check that there is a valid database connection
      if(databaseConnection == null || databaseConnection.getCon() == null) {
         return "Sorry database doesn't have a connection";
      }

      String intError = "";

      // checks if topX has a value
      if(topX != null) {
         intError = String.valueOf(topX);
         intError = InputValidation.validateIntInput(intError);
      }

      // checks what error messages have been returned and outputs them to the user
       if(intError.equals("Please enter a valid number") || intError.equals("Sorry please choose a valid number, numbers cannot be negative or 0")) {
         return "Input Errors:\n" + intError;
      } else {

          if (topX != null) {
             System.out.println("Top Cities in the World");
          } else {
             System.out.println("All Cities in the World");

          }

          Statement stmt = databaseConnection.getCon().createStatement();

          // query to retrieve the data from the database
          String cityQuery = "Select city.Name AS City, country.Name AS Country, city.District, city.Population "
                  + "from city as city "
                  + " inner join country as country on city.CountryCode = country.Code "
                  + " order by city.Population desc";

          // checks to ensure a value has been passed before setting a limit
          if (topX != null) {
             cityQuery += " LIMIT " + topX;
          }

          //  runs the query and assigns the result to resultSet
          ResultSet resultSet = stmt.executeQuery(cityQuery);

          // checks if a result has been returned from the query

          return App.FormatOutput(resultSet);
       }
   }


   /**
    *Method to build a query statement, to be used within other functions which act as filters.
    *
    * @param databaseConnection passes in the database connection class as parameter to allow access.
    *
    * @param userInput Takes user input to use in filtering the database
    *
    * @param queryWhere is passed in through the filtering functions to ensure the correct data is being queried.
    *
    * @param topX topX is used to limit the number of database entries output to the user when provided.
    *
    * @return method returns formatted data by passing the retrieved data through the formatOutput function.
    *
    * @throws SQLException
    */
   public static String queryResults(DatabaseConnection databaseConnection, String userInput, String queryWhere, Integer topX) throws SQLException {

      // check that there is a valid database connection
      if(databaseConnection == null || databaseConnection.getCon() == null) {
         return "Sorry database doesn't have a connection";
      }

      // validates user input
      userInput = InputValidation.validateStringInput(userInput);
      String intError = "";

      // checks if topX has a value
      if (topX != null) {
         intError = String.valueOf(topX);
         intError = InputValidation.validateIntInput(intError);
      }

      // checks what error messages have been returned and outputs them to the user
      if (userInput.equals("Field cannot be empty") && topX == null || userInput.equals("Field cannot be over 50 characters.") && topX == null) { // if user input returns an error
         return userInput;
      } else if (userInput.equals("Field cannot be empty") && intError != null || userInput.equals("Field cannot be over 50 characters.") && intError == null) { // if user input and top x returns an error
         return "Input Errors:\nQuery Input: " + userInput + "\nTop X: " + intError;
      } else if(intError.equals("Please enter a valid number") || intError.equals("Sorry please choose a valid number, numbers cannot be negative or 0")) {
         return "Input Errors:\n" + intError;
      } else {

         if (topX != null) {
            System.out.println("Top Cities in " + userInput + ".");
         } else {
            System.out.println("All Cities in " + userInput + ".");

         }




         PreparedStatement pstmt = null;
      try {
         String cityQuery = "Select city.Name AS City, country.Name AS Country, city.District, city.Population "
                 + "from city as city "
                 + "inner join country as country on city.CountryCode = country.Code "
                 + "WHERE " + queryWhere + " = ?"
                 + " order by city.Population desc";//.replace()

         // checks if topX has a value, if it does it is added to the query.
         if (topX != null) {
            cityQuery += " LIMIT ? ";
         }


         // creates a connection to the database and a statement to be sent
         pstmt = databaseConnection.getCon().prepareStatement(cityQuery);

         // sets the ? parameters for the query to corresponding input
         pstmt.setString(1, userInput);

         if (topX != null) {
            pstmt.setInt(2, topX);
         }

         //  runs the query and assigns the result to resultSet
         ResultSet resultSet = pstmt.executeQuery();

         // returns formatted query output
         return App.FormatOutput(resultSet);


      } catch (SQLException e) {
         System.out.println(e.getMessage());
      } finally {
         pstmt.close();
      }

      // error message, if data hasn't been retrieved
      return "Error, couldn't retrieve data.";

   }
   }

   /**
    *
    * @param databaseConnection passes in the database connection class as parameter to allow access.
    *
    * @param userInput Takes user input to use in filtering the database.
    *
    * @return returns the statementBuilder method output based on provided data.
    *
    * @throws SQLException throws SQL exception.
    */
   public static String allCitiesInAContinent(DatabaseConnection databaseConnection, String userInput) throws SQLException {

      //userInput = InputValidation.validateStringInput(userInput);
      return queryResults(databaseConnection, userInput, "country.Continent", null);

   }

   /**
    *
    * @param databaseConnection passes in the database connection class as parameter to allow access.
    *
    * @param userInput Takes user input to use in filtering the database
    *
    * @return returns the statementBuilder method output(Query Output) based on provided data.
    *
    * @throws SQLException throws SQL exception.
    */
   public static String allCitiesInARegion(DatabaseConnection databaseConnection, String userInput) throws SQLException {

      //userInput = InputValidation.validateStringInput(userInput);
      return queryResults(databaseConnection, userInput, "country.Region", null);

   }

   /**
    *
    * @param databaseConnection passes in the database connection class as parameter to allow access.
    *
    * @param userInput Takes user input to use in filtering the database
    *
    * @return returns the statementBuilder method output(Query Output) based on provided data.
    *
    * @throws SQLException throws SQL exception.
    */
   public static String allCitiesInACountry(DatabaseConnection databaseConnection, String userInput) throws SQLException {

      //userInput = InputValidation.validateStringInput(userInput);
      return queryResults(databaseConnection, userInput, "country.Name", null);

   }

   /**
    *
    * @param databaseConnection passes in the database connection class as parameter to allow access.
    *
    * @param userInput Takes user input to use in filtering the database
    *
    * @return returns the statementBuilder method output(Query Output) based on provided data.
    *
    * @throws SQLException throws SQL exception.
    */
   public static String allCitiesInADistrict(DatabaseConnection databaseConnection, String userInput) throws SQLException {

      //userInput = InputValidation.validateStringInput(userInput);
      return queryResults(databaseConnection, userInput, "city.District", null);

   }



   /**
    *
    * @param databaseConnection passes in the database connection class as parameter to allow access.
    *
    * @param userInput Takes user input to use in filtering the database
    *
    * @param userTopX userTopX is used to limit the number of database entries output to the user.
    *
    * @return returns the statementBuilder method output(Query Output) based on provided data.
    *
    * @throws SQLException throws SQL exception.
    */
   public static String topXCitiesInAContinent(DatabaseConnection databaseConnection, String userInput, int userTopX) throws SQLException {

      //userInput = InputValidation.validateStringInput(userInput);
      return queryResults(databaseConnection, userInput, "country.Continent", userTopX);

   }

   /**
    *
    * @param databaseConnection passes in the database connection class as parameter to allow access.
    *
    * @param userInput Takes user input to use in filtering the database
    *
    * @param userTopX userTopX is used to limit the number of database entries output to the user.
    *
    * @return returns the statementBuilder method output(Query Output) based on provided data.
    *
    * @throws SQLException throws SQL exception.
    */
   public static String topXCitiesInARegion(DatabaseConnection databaseConnection, String userInput, int userTopX) throws SQLException {

      //userInput = InputValidation.validateStringInput(userInput);
      return queryResults(databaseConnection, userInput, "country.Region", userTopX);

   }

   /**
    *
    * @param databaseConnection passes in the database connection class as parameter to allow access.
    *
    * @param userInput Takes user input to use in filtering the database
    *
    * @param userTopX userTopX is used to limit the number of database entries output to the user.
    *
    * @return returns the statementBuilder method output(Query Output) based on provided data.
    *
    * @throws SQLException throws SQL exception.
    */
   public static String topXCitiesInACountry(DatabaseConnection databaseConnection, String userInput, int userTopX) throws SQLException {

      //userInput = InputValidation.validateStringInput(userInput);
      return queryResults(databaseConnection, userInput, "country.Name", userTopX);

   }

   /**
    *
    * @param databaseConnection passes in the database connection class as parameter to allow access.
    *
    * @param userInput Takes user input to use in filtering the database
    *
    * @param userTopX userTopX is used to limit the number of database entries output to the user.
    *
    * @return returns the statementBuilder method output(Query Output) based on provided data.
    *
    * @throws SQLException throws SQL exception.
    */
   public static String topXCitiesInADistrict(DatabaseConnection databaseConnection, String userInput, int userTopX) throws SQLException {

      //userInput = InputValidation.validateStringInput(userInput);
      return queryResults(databaseConnection, userInput, "city.District", userTopX);
   }



}

