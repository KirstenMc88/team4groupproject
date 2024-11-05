package com.napier.team4groupproject;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DatabaseConnection {
    /**
     * Connection to MySQL database.
     *
     * <p>This field is used by the connect() and disconnect() methods to create and close the connection to the MySQL database.</p>
     */
    private Connection con = null;
    private String databaseDriver = "com.mysql.cj.jdbc.Driver";

    public Connection getCon() {
        return con;
    }

    /**
     * Connect to the MySQL database.
     *
     * <p>This method trys to connect to the MySQL database, it allows for 10 attempts and has a delay set up to give the database time to start. It deals with exceptions internally.</p>
     *
     * @param location is the name of the sql file that holds the database, which is inserted in the url
     * @param delay is the number of milliseconds which the connect method will wait before retrying to connect
     */
    public void connect(String location, int delay)
    {
        try
        {
            // Load Database driver
            int retries;
            Class.forName(databaseDriver);
            if (!"UnitTest".equals(System.getProperty("Environment"))) {
                retries = 10;
            } else {
                retries = 3;
            }

            boolean shouldWait = false;
            for (int i = 0; i < retries; ++i)
            {
                System.out.println("Connecting to database...");
                try
                {
                    if (!"UnitTest".equals(System.getProperty("Environment"))) {
                        if (shouldWait){
                            // Wait a bit for db to start
                            Thread.sleep(delay);
                        }
                        // Connect to database
                        con = DriverManager.getConnection("jdbc:mysql://" + location + "/world?allowPublicKeyRetrieval=true&useSSL=false", "root", "example");
                        System.out.println("Successfully connected");
                        break;
                    } else {
                        Thread.sleep(delay);
                    }
                }
                catch (SQLException sqle)
                {
                    System.out.println("Failed to connect to database attempt " + Integer.toString(i));
                    System.err.println("SQLException: " + sqle.getMessage());

                    // Let's wait before attempting to reconnect
                    shouldWait = true;
                }
                catch (InterruptedException ie)
                {
                    System.err.println("InterruptedException: " + ie.getMessage());
                }
            }
        }
        catch (ClassNotFoundException e)
        {
            System.err.println("ClassNotFoundException: " + e.getMessage());
        }
    }

    /**
     * Disconnect from the MySQL database.
     *
     * <p>This method closes the connection to the MySQL database. It deals with exceptions internally.</p>
     *
     */
    public void disconnect()
    {
        if (con != null)
        {
            try
            {
                // Close connection
                con.close();
            }
            catch (SQLException e)
            {
                System.err.println("SQLException: " + e.getMessage());
            }
            finally {
                con = null;
            }
        }
    }

}
