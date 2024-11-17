package com.napier.team4groupproject;

import org.junit.jupiter.api.*;

import java.sql.*;
import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class DatabaseConnectionUnitTest {
    private static DatabaseConnection testDB;
    private static Connection connection;
    private static ByteArrayOutputStream errorOutput;

    /**
     * Initialising environment
     *
     * <p>This method sets up the test environment by setting a System property 'Environment' to 'UnitTest'.
     * This allows certain things in the DatabaseConnection class to only be executed in a certain environment.</p>
     */
    @BeforeAll
    public static void init(){
        System.setProperty("Environment", "UnitTest");
    }

    /**
     * (Re)setting variables for each test
     *
     * <p>This method sets up the testDB, a mock connection and errorOutput (which intercepts System.err messages)
     * for each test.</p>
     */
    @BeforeEach
    public void setUp() {
        testDB = new DatabaseConnection();
        connection = mock(Connection.class);
        errorOutput = new ByteArrayOutputStream();
        System.setErr(new PrintStream(errorOutput));
    }

    /**
     * Connect method tests
     *
     * <p>This method is used for all tests for the connect method.</p>
     *
     * @param databaseDriver the name of the database driver used
     * @param delay the number of milliseconds the thread sleeps for (to delay retries)
     * @param interrupt if the specific test requires a thread interruption
     * @return a string containing the error messages printed during execution
     */
    private String connect(String databaseDriver, int delay, boolean interrupt) {
        // set DatabaseConnection field databaseDriver to test value
        Utilities.setPrivateField(DatabaseConnection.class,testDB,"databaseDriver", databaseDriver);
        // create separate thread to run connect in
        Thread testThread = new Thread(() -> testDB.connect("irrelevant", delay));

        // start test thread
        testThread.start();

        // pause this thread to allow test thread to fully start
        try{
            Thread.sleep(100);
        } catch (InterruptedException e){
            System.out.println(e.getMessage());
        }

        // interrupt test thread if interrupt boolean is true
        if (interrupt){
            testThread.interrupt();
        }

        // let main thread wait for test thread to finish
        try {
            testThread.join();
        } catch (InterruptedException e) {
            System.out.println(e.getMessage());
        }

        // return error messages
        return errorOutput.toString();
    }

    /**
     * Successful Connection test
     *
     * <p>This test checks that no exception messages were printed while running with working values.</p>
     */
    @Test
    public void connect_Successful(){
        assertFalse(connect("com.mysql.cj.jdbc.Driver", 1000, false).contains("Exception"));
    }

    /**
     * Invalid driver test
     *
     * <p>This test checks that a ClassNotFoundException messages was printed, as this is what should be thrown
     * due to an incorrect driver name.</p>
     */
    @Test
    public void connect_invalidDriver(){
        assertTrue(connect("invalid.driver", 1000, false).contains("ClassNotFoundException: "));
    }

    /**
     * Thread interrupted test
     *
     * <p>This test checks that a InterruptedException messages was printed, as this is what should be thrown
     * due to the thread being interrupted.</p>
     */
    @Test
    public void connect_threadInterrupted(){
        assertTrue(connect("com.mysql.cj.jdbc.Driver", 1000, true).contains("InterruptedException: "));
    }


    /**
     * Disconnect method tests
     *
     * <p>This method is used for all tests for the disconnect method.</p>
     * @param con is what will be assigned to DatabaseConnection for different tests
     * @param shouldThrow if the specific test should throw an exception
     * @return a string containing the error messages printed during execution
     */
    private String disconnect(Connection con, boolean shouldThrow){
        // mock an exception if required for the test
        if (shouldThrow){
            try{
                doThrow(new SQLException("Error closing connection to database")).when(con).close();
            } catch (SQLException e) {
                // SQLException is not thrown here as this is just setting up the mock
            }
        }

        // set DatabaseConnection field con to test value
        Utilities.setPrivateField(DatabaseConnection.class, testDB,"con", con);

        // call disconnect method
        testDB.disconnect();

        // return error messages
        return errorOutput.toString();
    }

    /**
     * Closing existing connection test
     *
     * <p>This test checks that no unexpected SQLException is thrown when closing an open connection. </p>
     */
    @Test
    public void disconnect_closeExistingConnection() {
        assertFalse(disconnect(connection, false).contains("SQLException: "));
    }

    /**
     * Closing non-existing connection test
     *
     * <p>This test checks that no unexpected SQLException is thrown when closing a null connection. </p>
     */
    @Test
    public void disconnect_closeNullConnection() {
        assertFalse(disconnect(null, false).contains("SQLException: "));
    }

    /**
     * Closing with exception
     *
     * <p>This test checks that a thrown SQLException is correctly handled. </p>
     */
    @Test
    public void disconnect_closeWithException(){
        assertTrue(disconnect(connection, true).contains("SQLException: "));
    }
}
