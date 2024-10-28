package com.napier.team4groupproject;

import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;

public class App
{
    /**
     * Formatting method for SQL query results
     *
     * <p>This method formats the SQL query result, using metadata to find the number of columns, the needed width for
     * each column (with a minimum of 20 char), and the names of each column. <p/>
     *
     * @param resultSet is the result of the SQL query
     */
    public static String FormatOutput(ResultSet resultSet){
        StringBuilder output = new StringBuilder();

        try{
            ResultSetMetaData metaData = resultSet.getMetaData(); // metaData methods use indices starting at 1 instead of 0!
            int columns = metaData.getColumnCount();
            int[] columnWidths = new int[columns];

            for (int i = 1; i <= columns; i++) {
                columnWidths[i-1] = Math.max(20, metaData.getColumnDisplaySize(i));
            }

            for (int i = 1; i <= columns; i++) {
                output.append(String.format("%-" + columnWidths[i-1] + "s", metaData.getColumnLabel(i)));
            }
            output.append("\n");

            while(resultSet.next()){
                for (int i = 1; i <= columns; i++) {
                    output.append(String.format("%-" + columnWidths[i-1] + "s", resultSet.getString(i)));
                }
                output.append("\n");
            }
        }
        catch(Exception e){
            System.out.println(e.getMessage());
        }

        return output.toString();
    }

    /**
     * Main method of the app
     *
     * <p>This is the main method of the app which is called when the app is executed.</p>
     *
     * @param args standard string array for java main class to receive command-line arguments
     */
    public static void main(String[] args) throws SQLException {
        // prints "hello world", very basic for now, just to prove that everything is set up as it should be
        System.out.println("Population Information System");

        // Creates instance of DatabaseConnetion
        DatabaseConnection sql = new DatabaseConnection();

        // Connect to database
        if (args.length < 1) {
            sql.connect("localhost:33060", 30000);
        } else {
            sql.connect(args[0], Integer.parseInt(args[1]));
        }

        // displays all cities in the world
        System.out.println(CityQueries.allCitiesInTheWorld(sql));

        // Displays the result from the countries in the world query
        System.out.println(CountryQueries.CountriesInTheWorld(sql));

        //Displays results of All Capital Cities In World Query
        System.out.println(CapitalQueries.allInWorld(sql));



        // Calls menu passes DB connection as parameters
        //Menu.mainMenu(sql);

        // Disconnect from database
        sql.disconnect();


    }
}