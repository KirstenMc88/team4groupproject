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
        int rowCount = 0;

        // try in case of exceptions with ResultSet or ResultSetMetaData
        try{
            // new instance of ResultSetMetaData to get the data specific to this ResultSet
            // please note that metaData methods use indices starting at 1 instead of 0!
            ResultSetMetaData metaData = resultSet.getMetaData();

            // this gets the number of columns from metadata so we can loop through them
            int columns = metaData.getColumnCount();

            // this will hold display widths for each column
            int[] columnWidths = new int[columns];

            // get column width for each column, with a minimum width of 20 char
            for (int i = 1; i <= columns; i++) {
                columnWidths[i-1] = Math.max(20, metaData.getColumnDisplaySize(i));
            }

            // get column label (not name, so it works with aliases) of all columns
            for (int i = 1; i <= columns; i++) {
                output.append(String.format("%-" + columnWidths[i-1] + "s", metaData.getColumnLabel(i)));
            }
            // add line break
            output.append("\n");

            // get every row
            while(resultSet.next()){
                // add one to row count
                rowCount++;
                // get content of each column
                for (int i = 1; i <= columns; i++) {
                    output.append(String.format("%-" + columnWidths[i-1] + "s", resultSet.getString(i)));
                }
                // add line break
                output.append("\n");
            }
        }
        // catch any exceptions
        catch(Exception e){
            System.out.println(e.getMessage());
        }

        // if the query does not return any results
        if (rowCount == 0){
            output.setLength(0);
            output.append("No matching data found. Please check your spelling and try again.");
        }
        // return formatted output as a string
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


        // Displays the result from the countries in the world query
        System.out.println(CountryQueries.CountriesInTheWorld(sql));

        // displays all cities in the world
        System.out.println(CityQueries.allCitiesInTheWorld(sql));

        //Displays results of All Capital Cities In World Query
        System.out.println(CapitalQueries.allInWorld(sql));

        // Calls menu passes DB connection as parameters
        // Menu.mainMenu(sql);

        // Disconnect from database
        sql.disconnect();

    }
}