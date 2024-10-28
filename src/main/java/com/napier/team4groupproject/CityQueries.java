package com.napier.team4groupproject;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class CityQueries {

   public String allCitiesInTheWorld(DatabaseConnection databaseConnection) throws SQLException {

      Statement stmt = databaseConnection.getCon().createStatement();

      String cityQuery = "Select city.Name AS City, country.Name AS Country, city.District, city.Population "
              + "from city as city "
              + " inner join country as country on city.CountryCode = country.Code "
              + " order by city.Population desc";

      ResultSet resultSet = stmt.executeQuery(cityQuery);

      if (resultSet.next()) {
         return App.FormatOutput(resultSet);
      }
      return "System Error";
   }





}
