package com.napier.team4groupproject;

import org.junit.jupiter.api.*;

import java.sql.*;
import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class DatabaseConnectionTest {
    private static DatabaseConnection testDB;
    private static Connection connection;
    private static ByteArrayOutputStream errorOutput;

    @BeforeAll
    public static void init(){
        System.setProperty("Environment", "UnitTest");
    }

    @BeforeEach
    public void setUp() {
        testDB = new DatabaseConnection();
        connection = mock(Connection.class);
        errorOutput = new ByteArrayOutputStream();
        System.setErr(new PrintStream(errorOutput));
    }

    // connect method tests
    private String connect(String databaseDriver, int delay, boolean interrupt) {
        Utilities.setPrivateField(DatabaseConnection.class,testDB,"databaseDriver", databaseDriver);
        Thread testThread = new Thread(() -> testDB.connect("irrelevant", delay));

        testThread.start();

        try{
            Thread.sleep(100);
        } catch (InterruptedException e){
            System.out.println(e.getMessage());
        }

        if (interrupt){
            testThread.interrupt();
        }

        try {
            testThread.join();
        } catch (InterruptedException e) {
            System.out.println(e.getMessage());
        }

        return errorOutput.toString();
    }

    @Test
    public void connect_Successful(){
        assertFalse(connect("com.mysql.cj.jdbc.Driver", 1000, false).contains("Exception"));
    }

    @Test
    public void connect_invalidDriver(){
        assertTrue(connect("invalid.driver", 1000, false).contains("ClassNotFoundException: "));
    }

    @Test
    public void connect_threadInterrupted(){
        assertTrue(connect("com.mysql.cj.jdbc.Driver", 1000, true).contains("InterruptedException: "));
    }


    // disconnect method tests

    private String disconnect(Connection con, boolean shouldThrow){

        if (shouldThrow){
            try{
                doThrow(new SQLException("Error closing connection to database")).when(con).close();
            } catch (SQLException e) {
                // SQLException is not thrown here as this is just setting up the mock
            }
        }

        Utilities.setPrivateField(DatabaseConnection.class, testDB,"con", con);

        testDB.disconnect();

        return errorOutput.toString();

    }

    @Test
    public void disconnect_closeExistingConnection() {
        assertFalse(disconnect(connection, false).contains("SQLException: "));
    }

    @Test
    public void disconnect_closeNullConnection() {
        assertFalse(disconnect(null, false).contains("SQLException: "));
    }

    @Test
    public void disconnect_closeWithException(){
        assertTrue(disconnect(connection, true).contains("SQLException: "));
    }

}
