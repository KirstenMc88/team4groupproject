package com.napier.team4groupproject;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class CityQueries {

   /**
    * Function to query database, returns specified field data on all cities.
    * @param databaseConnection passes in database connection as parameter.
    * @return returns formatted data by passing the retrieved data through the formatOutput function
    * @throws SQLException
    */
   public static String allCitiesInTheWorld(DatabaseConnection databaseConnection) throws SQLException {

      Statement stmt = databaseConnection.getCon().createStatement();

      // query to retrieve the data from the database
      String cityQuery = "Select city.Name AS City, country.Name AS Country, city.District, city.Population "
              + "from city as city "
              + " inner join country as country on city.CountryCode = country.Code "
              + " order by city.Population desc";

      ResultSet resultSet = stmt.executeQuery(cityQuery);

      if (resultSet.next()) {
         return App.FormatOutput(resultSet);
      }// error message, if data hasn't been retrieved
      return "System Error, couldn't retrieve the data.";
   }





}
