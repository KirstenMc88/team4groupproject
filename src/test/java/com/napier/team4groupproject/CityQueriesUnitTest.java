package com.napier.team4groupproject;

import org.junit.jupiter.api.*;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Unit tests for CityQueries class
 *
 * <p>This class contains unit tests using JUnit to ensure methods in the CityQueries class work as expected.</p>
 */
public class CityQueriesUnitTest {
    private static DatabaseConnection nullDB;
    private static String output;
    private static ByteArrayOutputStream printOutput;
    private static String exampleContinent;
    private static Integer exampleTopX;

    /**
     * Setting up environment
     *
     * <p>This method sets up the test environment by setting a System property 'Environment' to 'UnitTest'.
     * This allows certain things in the App class to only be executed in a certain environment.
     * It also sets up a null database ad different variables to test with.</p>
     */
    @BeforeAll
    public static void setUp() {
        System.setProperty("Environment", "UnitTest");
        nullDB = new DatabaseConnection();
        printOutput = new ByteArrayOutputStream();
        System.setOut(new PrintStream(printOutput));
        exampleContinent = "Asia";
        exampleTopX = 3;
    }

    // tests for the queryResults method

    /**
     * Testing the queryResults method, passing null instead of a databaseConnection object
     *
     * <p>This test checks that if null is passed into the method in place of a databaseConnection object
     * the method handles it appropriately.</p>
     */
    @Test
    public void queryResults_nullDatabase() {
        try {
            output = CityQueries.queryResults(null, exampleContinent, "Continent", exampleTopX);
        } catch (Exception e) {
            fail(e.getMessage());
        }
        assertTrue(output.contains("DatabaseConnection is null")); // or similar string :)
    }

    /**
     * Testing the queryResults method, using a databaseConnection object which has a null connection
     *
     * <p>This test checks that if the connection is null the method handles it appropriately.</p>
     */
    @Test
    public void queryResults_nullDatabaseConnection() {
        try {
            output = CityQueries.queryResults(nullDB, exampleContinent, "Continent", exampleTopX);
        } catch (Exception e) {
            fail(e.getMessage());
        }
        assertTrue(output.contains("Sorry database doesn't have a connection"));

    }


    // tests for the allCitiesInTheWorld method

    /**
     * Testing the allCitiesInTheWorld method, passing null instead of a databaseConnection object
     *
     * <p>This test checks that if null is passed into the method in place of a databaseConnection object
     * the method handles it appropriately.</p>
     */
    @Test
    public void allCitiesInTheWorld_nullDatabase() {
        try {
            output = CityQueries.allCitiesInTheWorld(null, null);
        } catch (Exception e) {
           fail(e.getMessage());
        }
        assertTrue(output.contains("Database Connection is null")); // or similar string :)
    }

    /**
     * Testing the allCitiesInTheWorld method, using a databaseConnection object which has a null connection
     *
     * <p>This test checks that if the connection is null the method handles it appropriately.</p>
     */
    @Test
    public void allCitiesInTheWorld_nullDatabaseConnection() {
        try {
            output = CityQueries.allCitiesInTheWorld(nullDB, null);
        } catch (Exception e) {
            fail(e.getMessage());
        }
        assertTrue(output.contains("Sorry database doesn't have a connection"));

    }
}
