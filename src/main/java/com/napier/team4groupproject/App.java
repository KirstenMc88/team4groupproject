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

        // Creates instance of DatabaseConnetion
        DatabaseConnection sql = new DatabaseConnection();

        // Connect to database
        if (args.length < 1) {
            sql.connect("localhost:33060", 30000);
        } else {
            sql.connect(args[0], Integer.parseInt(args[1]));
        }
        //sql.connect("world-db:3306" ,30000);

        // Calls menu
        // Menu.mainMenu();

        // Disconnect from database
        sql.disconnect();


    }
}