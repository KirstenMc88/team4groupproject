package com.napier.team4groupproject;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import static org.junit.jupiter.api.Assertions.*;

public class CityQueriesIntTest {
    private static DatabaseConnection worldDB;
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
    public void statementBuilder_successfulNullTopX() {
        try {
            CityQueries.statementBuilder(worldDB, exampleContinent, "Continent", null);
        } catch (Exception e) {
            fail(e.getMessage());
        }
    }

    @Test
    public void statementBuilder_successfulTopX() {
        try {
            CityQueries.statementBuilder(worldDB, exampleContinent, "Continent", exampleTopX);
        } catch (Exception e) {
            fail(e.getMessage());
        }
    }

    @Test
    public void statementBuilder_successfulContinent() {
        try {
            CityQueries.statementBuilder(worldDB, exampleContinent, "Continent", null);
        } catch (Exception e) {
            fail(e.getMessage());
        }
    }

    @Test
    public void statementBuilder_successfulRegion() {
        try {
            CityQueries.statementBuilder(worldDB, exampleRegion, "Region", null);
        } catch (Exception e) {
            fail(e.getMessage());
        }
    }

    @Test
    public void statementBuilder_successfulCountry() {
        try {
            CityQueries.statementBuilder(worldDB, exampleCountry, "Country", null);
        } catch (Exception e) {
            fail(e.getMessage());
        }
    }

    @Test
    public void statementBuilder_successfulDistrict() {
        try {
            CityQueries.statementBuilder(worldDB, exampleDistrict, "District", null);
        } catch (Exception e) {
            fail(e.getMessage());
        }
    }

    @Test
    public void statementBuilder_wrongDatabase() {
        // TODO set up a second, wrong, database
    }

    @Test
    public void statementBuilder_withException() {
        // TODO fake exception - is this needed?
    }

    @Test
    public void statementBuilder_tooHighTopX() {
        try {
            CityQueries.statementBuilder(worldDB, exampleDistrict, "District", exampleTooHighTopX);
            fail("Should have thrown an exception.");
        } catch (Exception e) {
            assertTrue(output.toString().contains("Top X cannot be higher than total rows returned by where filter.")); // or similar string :)
        }
    }

    @Test
    public void statementBuilder_invalidUserInput() {
        try {
            CityQueries.statementBuilder(worldDB, exampleInvalid, "District", null);
            fail("Should have thrown an exception.");
        } catch (Exception e) {
            assertTrue(output.toString().contains("User input does not return anything with where filter.")); // or similar string :)
        }
    }

    // allCitiesInTheWorld

    @Test
    public void allCitiesInTheWorld_successfulNullTopX() {
        try {
            String report = CityQueries.allCitiesInTheWorld(worldDB, null);
        } catch (Exception e) {
            fail(e.getMessage());
        }
    }

    @Test
    public void allCitiesInTheWorld_successfulTopX() {
        try {
            String report = CityQueries.allCitiesInTheWorld(worldDB, exampleTopX);
        } catch (Exception e) {
            fail(e.getMessage());
        }
    }

    @Test
    public void allCitiesInTheWorld_wrongDatabase() {
        // TODO set up a second, wrong, database
    }

    @Test
    public void allCitiesInTheWorld_withException() {
        // TODO fake exception - is this needed?
    }

    @Test
    public void allCitiesInTheWorld_tooHighTopX() {
        System.setOut(new PrintStream(output));
        // total cities in world is 4079
        try {
            String report = CityQueries.allCitiesInTheWorld(worldDB, 4080);
        } catch (Exception e) {
            fail(e.getMessage());
        }
        // change output!
        assertFalse(output.toString().contains("Top 4080 Cities in the World"));
    }

    // allCitiesInAContinent

    @Test
    public void allCitiesInAContinent_successful() {
        try {
            CityQueries.allCitiesInAContinent(worldDB, exampleContinent);
        } catch (Exception e) {
            fail(e.getMessage());
        }
    }

    // topXCitiesInAContinent

    @Test
    public void topXCitiesInAContinent_successful() {
        try {
            CityQueries.topXCitiesInAContinent(worldDB, exampleContinent, exampleTopX);
        } catch (Exception e) {
            fail(e.getMessage());
        }
    }

    // allCitiesInARegion

    @Test
    public void allCitiesInARegion_successful() {
        try {
            CityQueries.allCitiesInARegion(worldDB, exampleRegion);
        } catch (Exception e) {
            fail(e.getMessage());
        }
    }

    // topXCitiesInARegion

    @Test
    public void topXCitiesInARegion_successful() {
        try {
            CityQueries.topXCitiesInARegion(worldDB, exampleRegion, exampleTopX);
        } catch (Exception e) {
            fail(e.getMessage());
        }
    }

    // allCitiesInACountry

    @Test
    public void allCitiesInACountry_successful() {
        try {
            CityQueries.allCitiesInACountry(worldDB, exampleCountry);
        } catch (Exception e) {
            fail(e.getMessage());
        }
    }

    // topXCitiesInACountry

    @Test
    public void topXCitiesInACountry_successful() {
        try {
            CityQueries.topXCitiesInACountry(worldDB, exampleCountry, exampleTopX);
        } catch (Exception e) {
            fail(e.getMessage());
        }
    }

    // allCitiesInADistrict

    @Test
    public void allCitiesInADistrict_successful() {
        try {
            CityQueries.allCitiesInADistrict(worldDB, exampleDistrict);
        } catch (Exception e) {
            fail(e.getMessage());
        }
    }

    // topXCitiesInADistrict

    @Test
    public void topXCitiesInADistrict_successful() {
        try {
            CityQueries.topXCitiesInADistrict(worldDB, exampleDistrict, exampleTopX);
        } catch (Exception e) {
            fail(e.getMessage());
        }
    }

}
