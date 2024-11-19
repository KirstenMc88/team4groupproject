package com.napier.team4groupproject;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import static org.junit.jupiter.api.Assertions.*;

public class CityQueriesIntTest {
    private static DatabaseConnection worldDB;
    private static String output;
    private static ByteArrayOutputStream printOutput;
    private static String exampleContinent;
    private static String exampleCountry;
    private static String exampleRegion;
    private static String exampleDistrict;
    private static Integer exampleTopX;
    private static Integer exampleTooHighTopX;
    private static String exampleInvalid;

    @BeforeAll
    public static void setUp() {
        System.setProperty("Environment", "IntegrationTest");
        worldDB = new DatabaseConnection();
        worldDB.connect("localhost:33060", 10000);
        printOutput = new ByteArrayOutputStream();
        System.setOut(new PrintStream(printOutput));
        exampleContinent = "Asia";
        exampleCountry = "Spain";
        exampleRegion = "Caribbean";
        exampleDistrict = "Buenos Aires";
        exampleTopX = 3;
        exampleTooHighTopX = 1000;
        exampleInvalid = "Invalid";
    }

    // statement builder & database & format output

    @Test
    public void statementBuilder_successfulNullTopX() {
        try {
            output = CityQueries.statementBuilder(worldDB, exampleContinent, "Continent", null);
        } catch (Exception e) {
            fail(e.getMessage());
        }
        //System.err.println(output.replace("\n", "\\n\n"));
        String[] lines = output.split("\n");
        if (lines.length > 2) {
            assertTrue(lines[2].contains("Mumbai (Bombay)"));
            assertTrue(lines[lines.length-1].contains("Bandar Seri Begawan"));
        }
    }

    @Test
    public void statementBuilder_successfulTopX() {
        try {
            output = CityQueries.statementBuilder(worldDB, exampleContinent, "Continent", exampleTopX);
        } catch (Exception e) {
            fail(e.getMessage());
        }
        String[] lines = output.split("\n");
        if (lines.length > 2) {
            assertTrue(lines[2].contains("Mumbai"));
            assertTrue(lines[lines.length-1].contains("Shanghai"));
        }
    }

    @Test
    public void statementBuilder_successfulContinent() {
        try {
            output = CityQueries.statementBuilder(worldDB, exampleContinent, "Continent", null);
        } catch (Exception e) {
            fail(e.getMessage());
        }
        String[] lines = output.split("\n");
        if (lines.length > 2) {
            assertTrue(lines[2].contains("Mumbai (Bombay)"));
            assertTrue(lines[lines.length-1].contains("Bandar Seri Begawan"));
        }
    }

    @Test
    public void statementBuilder_successfulRegion() {
        try {
            output = CityQueries.statementBuilder(worldDB, exampleRegion, "Region", null);
        } catch (Exception e) {
            fail(e.getMessage());
        }
        String[] lines = output.split("\n");
        if (lines.length > 2) {
            assertTrue(lines[2].contains("La Habana"));
            assertTrue(lines[lines.length-1].contains("The Valley"));
        }
    }

    @Test
    public void statementBuilder_successfulCountry() {
        try {
            output = CityQueries.statementBuilder(worldDB, exampleCountry, "Country", null);
        } catch (Exception e) {
            fail(e.getMessage());
        }
        String[] lines = output.split("\n");
        if (lines.length > 2) {
            assertTrue(lines[2].contains("Madrid"));
            assertTrue(lines[lines.length-1].contains("Torrejón de Ardoz"));
        }
    }

    @Test
    public void statementBuilder_successfulDistrict() {
        try {
            output = CityQueries.statementBuilder(worldDB, exampleDistrict, "District", null);
        } catch (Exception e) {
            fail(e.getMessage());
        }
        String[] lines = output.split("\n");
        if (lines.length > 2) {
            assertTrue(lines[2].contains("La Matanza"));
            assertTrue(lines[lines.length-1].contains("Tandil"));
        }
    }

    @Test
    public void statementBuilder_invalidWhere() {
        try {
            CityQueries.statementBuilder(worldDB, exampleContinent, exampleInvalid, exampleTopX);
            fail("Should have thrown an exception.");
        } catch (Exception e) {
            assertTrue(printOutput.toString().contains("Invalid where filter.")); // or similar string :)
        }
    }

    @Test
    public void statementBuilder_tooHighTopX() {
        try {
            CityQueries.statementBuilder(worldDB, exampleDistrict, "District", exampleTooHighTopX);
            fail("Should have thrown an exception.");
        } catch (Exception e) {
            assertTrue(printOutput.toString().contains("Top X cannot be higher than total rows returned by where filter.")); // or similar string :)
        }
    }

    // statement builder & input validation
    // strings here used from input validation

    @Test
    public void statementBuilder_negativeTopX() {
        try {
            CityQueries.statementBuilder(worldDB, exampleContinent, "Continent", -exampleTopX);
            fail("Should have thrown an exception.");
        } catch (Exception e) {
            assertTrue(printOutput.toString().contains("Sorry please choose a valid number"));
        }
    }

    @Test
    public void statementBuilder_zeroTopX() {
        try {
            CityQueries.statementBuilder(worldDB, exampleContinent, "Continent", 0);
            fail("Should have thrown an exception.");
        } catch (Exception e) {
            // string used here is for below 0, exactly 0 can be a different string just needs to be amended here as well
            assertTrue(printOutput.toString().contains("Sorry please choose a valid number"));
        }
    }

    @Test
    public void statementBuilder_emptyUserInput() {
        try {
            CityQueries.statementBuilder(worldDB, "", "Continent", exampleTopX);
            fail("Should have thrown an exception.");
        } catch (Exception e) {
            assertTrue(printOutput.toString().contains("Field cannot be empty"));
        }
    }

    @Test
    public void statementBuilder_emptyWhere() {
        try {
            CityQueries.statementBuilder(worldDB, exampleContinent, "", exampleTopX);
            fail("Should have thrown an exception.");
        } catch (Exception e) {
            assertTrue(printOutput.toString().contains("Field cannot be empty")); // or similar string :)
        }
    }

    // allCitiesInTheWorld & database & formatoutput

    @Test
    public void allCitiesInTheWorld_successfulNullTopX() {
        try {
            output = CityQueries.allCitiesInTheWorld(worldDB, null);
        } catch (Exception e) {
            fail(e.getMessage());
        }
        String[] lines = output.split("\n");
        if (lines.length > 2) {
            assertTrue(lines[2].contains("Mumbai (Bombay)"));
            assertTrue(lines[lines.length-1].contains("Adamstown"));
        }
    }

    @Test
    public void allCitiesInTheWorld_successfulTopX() {
        try {
            output = CityQueries.allCitiesInTheWorld(worldDB, exampleTopX);
        } catch (Exception e) {
            fail(e.getMessage());
        }
        String[] lines = output.split("\n");
        if (lines.length > 2) {
            assertTrue(lines[2].contains("Mumbai (Bombay)"));
            assertTrue(lines[lines.length-1].contains("São Paulo"));
        }
    }

    @Test
    public void allCitiesInTheWorld_tooHighTopX() {
        // total cities in world is 4079
        try {
            output = CityQueries.allCitiesInTheWorld(worldDB, 4080);
        } catch (Exception e) {
            fail(e.getMessage());
        }
        assertFalse(printOutput.toString().contains("Top 4080 Cities in the World"));
    }


    // allCitiesInTheWorld & input validation

    @Test
    public void allCitiesInTheWorld_negativeTopX() {
        try {
            CityQueries.allCitiesInTheWorld(worldDB, -exampleTopX);
            fail("Should have thrown an exception.");
        } catch (Exception e) {
            assertTrue(printOutput.toString().contains("Sorry please choose a valid number"));
        }
    }

    @Test
    public void allCitiesInTheWorld_zeroTopX() {
        try {
            CityQueries.allCitiesInTheWorld(worldDB, 0);
            fail("Should have thrown an exception.");
        } catch (Exception e) {
            // string used here is for below 0, exactly 0 can be a different string just needs to be amended here as well
            assertTrue(printOutput.toString().contains("Sorry please choose a valid number"));
        }
    }


    // allCitiesInAContinent & database & format output

    @Test
    public void allCitiesInAContinent_successful() {
        try {
            output = CityQueries.allCitiesInAContinent(worldDB, exampleContinent);
        } catch (Exception e) {
            fail(e.getMessage());
        }
        String[] lines = output.split("\n");
        if (lines.length > 2) {
            assertTrue(lines[2].contains("Mumbai (Bombay)"));
            assertTrue(lines[lines.length-1].contains("Bandar Seri Begawan"));
        }
    }

    // topXCitiesInAContinent

    @Test
    public void topXCitiesInAContinent_successful() {
        try {
            output = CityQueries.topXCitiesInAContinent(worldDB, exampleContinent, exampleTopX);
        } catch (Exception e) {
            fail(e.getMessage());
        }
        String[] lines = output.split("\n");
        if (lines.length > 2) {
            assertTrue(lines[2].contains("Mumbai (Bombay)"));
            assertTrue(lines[lines.length-1].contains("Shanghai"));
        }
    }

    // allCitiesInARegion

    @Test
    public void allCitiesInARegion_successful() {
        try {
            output = CityQueries.allCitiesInARegion(worldDB, exampleRegion);
        } catch (Exception e) {
            fail(e.getMessage());
        }
        String[] lines = output.split("\n");
        if (lines.length > 2) {
            assertTrue(lines[2].contains("La Habana"));
            assertTrue(lines[lines.length-1].contains("The Valley"));
        }
    }

    // topXCitiesInARegion

    @Test
    public void topXCitiesInARegion_successful() {
        try {
            output = CityQueries.topXCitiesInARegion(worldDB, exampleRegion, exampleTopX);
        } catch (Exception e) {
            fail(e.getMessage());
        }
        String[] lines = output.split("\n");
        if (lines.length > 2) {
            assertTrue(lines[2].contains("La Habana"));
            assertTrue(lines[lines.length-1].contains("Port-au-Prince"));
        }
    }

    // allCitiesInACountry

    @Test
    public void allCitiesInACountry_successful() {
        try {
            output = CityQueries.allCitiesInACountry(worldDB, exampleCountry);
        } catch (Exception e) {
            fail(e.getMessage());
        }
        String[] lines = output.split("\n");
        if (lines.length > 2) {
            assertTrue(lines[2].contains("Madrid"));
            assertTrue(lines[lines.length-1].contains("Torrejón de Ardoz"));
        }
    }

    // topXCitiesInACountry

    @Test
    public void topXCitiesInACountry_successful() {
        try {
            output = CityQueries.topXCitiesInACountry(worldDB, exampleCountry, exampleTopX);
        } catch (Exception e) {
            fail(e.getMessage());
        }
        String[] lines = output.split("\n");
        if (lines.length > 2) {
            assertTrue(lines[2].contains("Madrid"));
            assertTrue(lines[lines.length-1].contains("Valencia"));
        }
    }

    // allCitiesInADistrict

    @Test
    public void allCitiesInADistrict_successful() {
        try {
            output = CityQueries.allCitiesInADistrict(worldDB, exampleDistrict);
        } catch (Exception e) {
            fail(e.getMessage());
        }
        String[] lines = output.split("\n");
        if (lines.length > 2) {
            assertTrue(lines[2].contains("La Matanza"));
            assertTrue(lines[lines.length-1].contains("Tandil"));
        }
    }

    // topXCitiesInADistrict

    @Test
    public void topXCitiesInADistrict_successful() {
        try {
            output = CityQueries.topXCitiesInADistrict(worldDB, exampleDistrict, exampleTopX);
        } catch (Exception e) {
            fail(e.getMessage());
        }
        String[] lines = output.split("\n");
        if (lines.length > 2) {
            assertTrue(lines[2].contains("La Matanza"));
            assertTrue(lines[lines.length-1].contains("Quilmes"));
        }
    }

}
