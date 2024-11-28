package com.napier.team4groupproject;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.lang.reflect.InvocationTargetException;

import static org.junit.jupiter.api.Assertions.*;

public class CountryQueriesIntTest {
    private static DatabaseConnection worldDB;
    private static String output;
    private static ByteArrayOutputStream printOutput;
    private static String exampleContinent;
    private static String exampleRegion;
    private static Integer exampleTopX;

    /**
     * Setting up environment
     *
     * <p>This method sets up the test environment by setting a System property 'Environment' to 'IntegrationTest'.
     * This allows certain things in the App class to only be executed in a certain environment.
     * It also sets up the database connection and different example variables.</p>
     */
    @BeforeAll
    public static void setUp() {
        System.setProperty("Environment", "IntegrationTest");
        worldDB = new DatabaseConnection();
        worldDB.connect("localhost:33060", 10000);
        printOutput = new ByteArrayOutputStream();
        System.setOut(new PrintStream(printOutput));
        exampleContinent = "Asia";
        exampleRegion = "Caribbean";
        exampleTopX = 3;
    }

    // tests for the AllQueries method

    /**
     * Method to call private AllQueries method
     *
     * <p>This method uses the Utilities class to invoke the private method AllQueries for testing purposes.</p>
     * @param args the arguments to be passed to the AllQueries method
     * @throws InvocationTargetException if there is an issue with invoking the AllQueries method, should not happen
     */
    public void callingAllQueriesMethod(Object...args) throws InvocationTargetException {
        Class<?>[] argTypes = new Class<?>[]{DatabaseConnection.class, String.class, String.class, Integer.class};
        Object outputObj = Utilities.callPrivateMethod(CountryQueries.class, null, "AllQueries", argTypes, args);
        assertNotNull(outputObj);
        output = (String) outputObj;
    }

    /**
     * Testing the AllQueries method, with correct input and a null topX
     *
     * <p>This test checks that no unexpected exception is thrown.</p>
     */
    @Test
    public void AllQueries_successfulNullTopX() {
        try {
            Object[] args = new Object[]{worldDB, "Continent", exampleContinent, null};
            callingAllQueriesMethod(args);
        } catch (Exception e) {
            fail(e.getMessage());
        }
    }

    /**
     * Testing the AllQueries method, with correct input and an example topX
     *
     * <p>This test checks that no unexpected exception is thrown, and that number of rows returned matches the topX.</p>
     */
    @Test
    public void AllQueries_successfulTopX() {
        try {
            Object[] args = new Object[]{worldDB, "Continent", exampleContinent, exampleTopX};
            callingAllQueriesMethod(args);
        } catch (Exception e) {
            fail(e.getMessage());
        }
        String[] lines = output.split("\n");
        int expectedLength = exampleTopX + 2;
        assertEquals(expectedLength, lines.length);
    }

    /**
     * Testing the AllQueries method, with correct input and filtering by continent
     *
     * <p>This test checks that no unexpected exception is thrown, and that each data row is from the continent.</p>
     */
    @Test
    public void AllQueries_successfulContinent() {
        try {
            Object[] args = new Object[]{worldDB, "Continent", exampleContinent,  null};
            callingAllQueriesMethod(args);
        } catch (Exception e) {
            fail(e.getMessage());
        }
        String[] lines = output.split("\n");
        if (lines.length > 2) {
            for (int i = 2; i < lines.length; i++) {
                assertTrue(lines[i].contains(exampleContinent));
            }
        } else {
            fail("Unhandled empty ResultSet.");
        }
    }

    /**
     * Testing the AllQueries method, with correct input and filtering by region
     *
     * <p>This test checks that no unexpected exception is thrown, and that each data row is from the region.</p>
     */
    @Test
    public void AllQueries_successfulRegion() {
        try {
            Object[] args = new Object[]{worldDB, "Region", exampleRegion, null};
            callingAllQueriesMethod(args);
        } catch (Exception e) {
            fail(e.getMessage());
        }
        String[] lines = output.split("\n");
        if (lines.length > 2) {
            for (int i = 2; i < lines.length; i++) {
                assertTrue(lines[i].contains(exampleRegion));
            }
        } else {
            fail("Unhandled empty ResultSet.");
        }
    }


    /**
     * Testing the AllQueries method, with a negative topX
     *
     * <p>This test checks that if a negative topX is passed into the method it is handled appropriately.
     * The string used to assert is from the inputValidation class, as calling a validation method would be
     * the easiest way of handling this.</p>
     */
    @Test
    public void AllQueries_negativeTopX() {
        try {
            Object[] args = new Object[]{worldDB, "Continent", exampleContinent, -exampleTopX};
            callingAllQueriesMethod(args);
        } catch (Exception e) {
            fail(e.getMessage());
        }
        assertTrue(output.contains("Sorry please choose a valid number"));
    }

    /**
     * Testing the AllQueries method, with topX = 0
     *
     * <p>This test checks that if topX = 0 is passed into the method it is handled appropriately.
     * The string used to assert is from the inputValidation class, as calling a validation method would be
     * the easiest way of handling this.</p>
     */
    @Test
    public void AllQueries_zeroTopX() {
        try {
            Object[] args = new Object[]{worldDB, "Continent", exampleContinent, 0};
            callingAllQueriesMethod(args);
        } catch (Exception e) {
            fail(e.getMessage());
        }
        assertTrue(output.contains("Sorry please choose a valid number"));
    }

    /**
     * Testing the AllQueries method, with an empty userInput
     *
     * <p>This test checks that if an empty userInput is passed into the method it is handled appropriately.
     * The string used to assert is from the inputValidation class, as calling a validation method would be
     * the easiest way of handling this.</p>
     */
    @Test
    public void AllQueries_emptyUserInput() {
        try {
            Object[] args = new Object[]{worldDB, "Continent", "", exampleTopX};
            callingAllQueriesMethod(args);
        } catch (Exception e) {
            fail(e.getMessage());
        }
        assertTrue(output.contains("Field cannot be empty"));
    }


    //


    /**
     * Testing the CountriesInTheWorld method with example input
     *
     * <p>This test checks that no unexpected exception is thrown.</p>
     */
    @Test
    public void CountriesInTheWorld_successful() {
        try {
            output = CountryQueries.CountriesInTheWorld(worldDB);
        } catch (Exception e) {
            fail(e.getMessage());
        }
    }

    /**
     * Testing the CountriesInAContinent method with example input
     *
     * <p>This test checks that no unexpected exception is thrown, and that each data row is from the continent.</p>
     */
    @Test
    public void CountriesInAContinent_successful() {
        try {
            output = CountryQueries.CountriesInAContinent(worldDB, exampleContinent);
        } catch (Exception e) {
            fail(e.getMessage());
        }
        String[] lines = output.split("\n");
        if (lines.length > 2) {
            for (int i = 2; i < lines.length; i++) {
                assertTrue(lines[i].contains(exampleContinent));
            }
        } else {
            fail("Unhandled empty ResultSet.");
        }
    }

    /**
     * Testing the CountriesInARegion method with example input
     *
     * <p>This test checks that no unexpected exception is thrown, and that each data row is from the region.</p>
     */
    @Test
    public void CountriesInARegion_successful() {
        try {
            output = CountryQueries.CountriesInARegion(worldDB, exampleRegion);
        } catch (Exception e) {
            fail(e.getMessage());
        }
        String[] lines = output.split("\n");
        if (lines.length > 2) {
            for (int i = 2; i < lines.length; i++) {
                assertTrue(lines[i].contains(exampleRegion));
            }
        } else {
            fail("Unhandled empty ResultSet.");
        }
    }

    /**
     * Testing the TopCountriesInTheWorld method with example input
     *
     * <p>This test checks that no unexpected exception is thrown, and that number of rows returned matches the topX.</p>
     */
    @Test
    public void TopCountriesInTheWorld_successful() {
        try {
            output = CountryQueries.TopCountriesInTheWorld(worldDB, exampleTopX);
        } catch (Exception e) {
            fail(e.getMessage());
        }
        String[] lines = output.split("\n");
        int expectedLength = exampleTopX + 2;
        assertEquals(expectedLength, lines.length);
    }

    /**
     * Testing the TopCountriesInAContinent method with example input
     *
     * <p>This test checks that no unexpected exception is thrown, that each data row is from the continent,
     * and that number of rows returned matches the topX.</p>
     */
    @Test
    public void TopCountriesInAContinent_successful() {
        try {
            output = CountryQueries.TopCountriesInAContinent(worldDB, exampleContinent, exampleTopX);
        } catch (Exception e) {
            fail(e.getMessage());
        }
        String[] lines = output.split("\n");
        int expectedLength = exampleTopX + 2;
        assertEquals(expectedLength, lines.length);
        if (lines.length > 2) {
            for (int i = 2; i < lines.length; i++) {
                assertTrue(lines[i].contains(exampleContinent));
            }
        } else {
            fail("Unhandled empty ResultSet.");
        }
    }

    /**
     * Testing the TopCountriesInARegion method with example input
     *
     * <p>This test checks that no unexpected exception is thrown, that each data row is from the continent,
     * and that number of rows returned matches the topX.</p>
     */
    @Test
    public void TopCountriesInARegion_successful() {
        try {
            output = CountryQueries.TopCountriesInARegion(worldDB, exampleRegion, exampleTopX);
        } catch (Exception e) {
            fail(e.getMessage());
        }
        String[] lines = output.split("\n");
        int expectedLength = exampleTopX + 2;
        assertEquals(expectedLength, lines.length);
        if (lines.length > 2) {
            for (int i = 2; i < lines.length; i++) {
                assertTrue(lines[i].contains(exampleRegion));
            }
        } else {
            fail("Unhandled empty ResultSet.");
        }
    }


}
