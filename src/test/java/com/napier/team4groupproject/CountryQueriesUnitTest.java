package com.napier.team4groupproject;

import org.junit.jupiter.api.*;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.lang.reflect.InvocationTargetException;

import static org.junit.jupiter.api.Assertions.*;


/**
 * CountryQueriesTest contains java unit tests along with the environment needed to run them
 *
 */
public class CountryQueriesUnitTest {
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
     * Testing the AllQueries method, passing null instead of a databaseConnection object
     *
     * <p>This test checks that if null is passed into the method in place of a databaseConnection object
     * the method handles it appropriately.</p>
     */
    @Test
    public void AllQueries_nullDatabase() {
        try {
            Object[] args = new Object[]{null, "Continent", exampleContinent, exampleTopX};
            callingAllQueriesMethod(args);
        } catch (Exception e) {
            fail(e.getMessage());
        }
        assertTrue(output.contains("\"databaseConnection\" is null"));
    }

    /**
     * Testing the AllQueries method, using a databaseConnection object which has a null connection
     *
     * <p>This test checks that if the connection is null the method handles it appropriately.</p>
     */
    @Test
    public void AllQueries_nullDatabaseConnection() {
        try {
            Object[] args = new Object[]{nullDB, "Continent", exampleContinent, exampleTopX};
            callingAllQueriesMethod(args);
        } catch (Exception e) {
            fail(e.getMessage());
        }
        assertTrue(output.contains("DatabaseConnection.getCon()\" is null"));
    }




}
