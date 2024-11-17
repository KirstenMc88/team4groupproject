package com.napier.team4groupproject;

import org.junit.jupiter.api.*;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import static org.junit.jupiter.api.Assertions.*;

public class CityQueriesUnitTest {
    private static DatabaseConnection worldDB;
    private static DatabaseConnection nullDB;
    private static ByteArrayOutputStream output;
    private static String exampleContinent;
    private static String exampleCountry;
    private static String exampleRegion;
    private static String exampleDistrict;
    private static Integer exampleTopX;
    private static Integer exampleTooHighTopX;
    private static String exampleInvalid;

    @BeforeAll
    public static void setUp() {
        worldDB = new DatabaseConnection();
        nullDB = new DatabaseConnection();
        worldDB.connect("localhost:33060", 10000);
        output = new ByteArrayOutputStream();
        System.setOut(new PrintStream(output));
        exampleContinent = "Asia";
        exampleCountry = "Spain";
        exampleRegion = "Caribbean";
        exampleDistrict = "Buenos Aires";
        exampleTopX = 3;
        exampleTooHighTopX = 1000;
        exampleInvalid = "Invalid";
    }

    // statement builder

    @Test
    public void statementBuilder_nullDatabase() {
        try {
            CityQueries.statementBuilder(null, exampleContinent, "Continent", exampleTopX);
            fail("Should have thrown an exception.");
        } catch (Exception e) {
            assertTrue(output.toString().contains("DatabaseConnection is null")); // or similar string :)
        }
    }

    @Test
    public void statementBuilder_nullDatabaseConnection() {
        try {
            CityQueries.statementBuilder(nullDB, exampleContinent, "Continent", exampleTopX);
            fail("Should have thrown an exception.");
        } catch (Exception e) {
            assertTrue(output.toString().contains("The connection of the DatabaseConnection object is null")); // or similar string :)
        }
    }

    @Test
    public void statementBuilder_negativeTopX() {
        try {
            CityQueries.statementBuilder(worldDB, exampleContinent, "Continent", -exampleTopX);
            fail("Should have thrown an exception.");
        } catch (Exception e) {
            assertTrue(output.toString().contains("Top X cannot be negative")); // or similar string :)
        }
    }

    @Test
    public void statementBuilder_zeroTopX() {
        try {
            CityQueries.statementBuilder(worldDB, exampleContinent, "Continent", 0);
            fail("Should have thrown an exception.");
        } catch (Exception e) {
            assertTrue(output.toString().contains("Top X cannot be 0")); // or similar string :)
        }
    }

    @Test
    public void statementBuilder_emptyUserInput() {
        try {
            CityQueries.statementBuilder(worldDB, "", "Continent", exampleTopX);
            fail("Should have thrown an exception.");
        } catch (Exception e) {
            assertTrue(output.toString().contains("User input cannot be empty")); // or similar string :)
        }
    }

    @Test
    public void statementBuilder_invalidWhere() {
        try {
            CityQueries.statementBuilder(worldDB, exampleContinent, exampleInvalid, exampleTopX);
            fail("Should have thrown an exception.");
        } catch (Exception e) {
            assertTrue(output.toString().contains("Invalid where filter.")); // or similar string :)
        }
    }

    @Test
    public void statementBuilder_emptyWhere() {
        try {
            CityQueries.statementBuilder(worldDB, exampleContinent, "", exampleTopX);
            fail("Should have thrown an exception.");
        } catch (Exception e) {
            assertTrue(output.toString().contains("Where filter cannot be empty.")); // or similar string :)
        }
    }

    // allCitiesInTheWorld

    @Test
    public void allCitiesInTheWorld_nullDatabase() {
        System.setOut(new PrintStream(output));
        try {
            String report = CityQueries.allCitiesInTheWorld(null, null);
            fail("Should have thrown an exception.");
        } catch (Exception e) {
            assertTrue(output.toString().contains("Database Connection is null")); // or similar string :)
        }
    }

    @Test
    public void allCitiesInTheWorld_nullDatabaseConnection() {
        System.setOut(new PrintStream(output));
        try {
            String report = CityQueries.allCitiesInTheWorld(nullDB, null);
            fail("Should have thrown an exception.");
        } catch (Exception e) {
            assertTrue(output.toString().contains("The connection of the DatabaseConnection object is null")); // or similar string :)
        }
    }

    @Test
    public void allCitiesInTheWorld_negativeTopX() {
        try {
            CityQueries.allCitiesInTheWorld(worldDB, -exampleTopX);
            fail("Should have thrown an exception.");
        } catch (Exception e) {
            assertTrue(output.toString().contains("Top X cannot be negative")); // or similar string :)
        }
    }

    @Test
    public void allCitiesInTheWorld_zeroTopX() {
        try {
            CityQueries.allCitiesInTheWorld(worldDB, 0);
            fail("Should have thrown an exception.");
        } catch (Exception e) {
            assertTrue(output.toString().contains("Top X cannot be 0")); // or similar string :)
        }
    }


}
