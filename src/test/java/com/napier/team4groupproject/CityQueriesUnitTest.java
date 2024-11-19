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
    private static ByteArrayOutputStream output;
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
        output = new ByteArrayOutputStream();
        System.setOut(new PrintStream(output));
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
            CityQueries.queryResults(null, exampleContinent, "Continent", exampleTopX);
            fail("Should have thrown an exception.");
        } catch (Exception e) {
            assertTrue(output.toString().contains("DatabaseConnection is null")); // or similar string :)
        }
    }

    /**
     * Testing the queryResults method, using a databaseConnection object which has a null connection
     *
     * <p>This test checks that if the connection is null the method handles it appropriately.</p>
     */
    @Test
    public void queryResults_nullDatabaseConnection() {
        try {
            CityQueries.queryResults(nullDB, exampleContinent, "Continent", exampleTopX);
            fail("Should have thrown an exception.");
        } catch (Exception e) {
            assertTrue(output.toString().contains("The connection of the DatabaseConnection object is null")); // or similar string :)
        }
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
        System.setOut(new PrintStream(output));
        try {
            CityQueries.allCitiesInTheWorld(null, null);
            fail("Should have thrown an exception.");
        } catch (Exception e) {
            assertTrue(output.toString().contains("Database Connection is null")); // or similar string :)
        }
    }

    /**
     * Testing the allCitiesInTheWorld method, using a databaseConnection object which has a null connection
     *
     * <p>This test checks that if the connection is null the method handles it appropriately.</p>
     */
    @Test
    public void allCitiesInTheWorld_nullDatabaseConnection() {
        System.setOut(new PrintStream(output));
        try {
            CityQueries.allCitiesInTheWorld(nullDB, null);
            fail("Should have thrown an exception.");
        } catch (Exception e) {
            assertTrue(output.toString().contains("The connection of the DatabaseConnection object is null")); // or similar string :)
        }
    }
}
