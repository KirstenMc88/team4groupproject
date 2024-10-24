package com.napier.team4groupproject;

public class App
{
    /**
     * Main method of the app
     *
     * <p>This is the main method of the app which is called when the app is executed.</p>
     *
     * @param args standard string array for java main class to receive command-line arguments
     */
    public static void main(String[] args)
    {
        // prints "hello world", very basic for now, just to prove that everything is set up as it should be
        System.out.println("Hello World!");

        DatabaseConnection sql = new DatabaseConnection();

        // Connect to database
        sql.connect();

        // Disconnect from database
        sql.disconnect();

    }
}