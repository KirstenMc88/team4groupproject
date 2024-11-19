package com.napier.team4groupproject;

import org.junit.jupiter.api.*;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import static org.junit.jupiter.api.Assertions.*;

public class CityQueriesUnitTest {
    private static DatabaseConnection nullDB;
    private static ByteArrayOutputStream output;
    private static String exampleContinent;
    private static Integer exampleTopX;

    @BeforeAll
    public static void setUp() {
        System.setProperty("Environment", "UnitTest");
        nullDB = new DatabaseConnection();
        output = new ByteArrayOutputStream();
        System.setOut(new PrintStream(output));
        exampleContinent = "Asia";
        exampleTopX = 3;
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

}
