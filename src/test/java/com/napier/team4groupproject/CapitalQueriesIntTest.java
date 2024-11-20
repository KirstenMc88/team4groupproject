package com.napier.team4groupproject;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Integration tests for CapitalQueries class
 *
 * <p>This class contains integration tests using JUnit to test that all methods in CapitalQueries behave
 * as expected when interacting with several other classes and methods.</p>
 */
public class CapitalQueriesIntTest {
    private static DatabaseConnection worldDB; // db connection with actual data
    private static DatabaseConnection nullDB;
    private static ByteArrayOutputStream output; // for capturing system output

    // example inputs
    private static String exampleContinent;
    private static String exampleRegion;
    private static String exampleWhere;
    private static Integer exampleLimit;
    private static Integer tooHighLimit;
    private static String exampleInvalid;

    // reference to the private method
    private static Method capitalQueriesMethod;

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
        nullDB = new DatabaseConnection();

        worldDB.connect("localhost:33060", 10000);

        output = new ByteArrayOutputStream();
        System.setOut(new PrintStream(output));

        exampleContinent = "Europe";
        exampleRegion = "Western Europe";
        exampleLimit = 5;
        tooHighLimit = 1000;
        exampleInvalid = "Invalid";
    }

    /**
     * Method to call the capitalQueries method for different tests
     *
     * <p>This method calls the capitalQueries method with different parameters and returns the string
     * which is returned by the capitalQueries method. </p>
     *
     * @param sql the DatabaseConnection object used to connect to the world database
     * @param attribute the column from world db used to filter. examples: continent, region or null
     * @param where the user inputted value used to filter query. examples: Europe, North America, Nordic Countries or null
     * @param limit the user inputted value to limit the amount of entries shown. examples: 5, 25 or null (returns every entry)
     * @return formatted string of data containing query results or null if exception occurs
     */
    public static String callCapitalQueries(DatabaseConnection sql, String attribute, String where, Integer limit) {
        try {
            return (String) Utilities.callPrivateMethod(
                    CapitalQueries.class,
                    null,
                    "capitalQueries",
                    new Class<?>[]{DatabaseConnection.class, String.class, String.class, Integer.class},
                    sql, attribute, where, limit
            );
        } catch (InvocationTargetException e) {
            throw new RuntimeException("Invocation error: " + e.getTargetException().getMessage(), e);
        }
    }

    /**
     * Testing the populationDistributionQuery method, with correct null input
     *
     * <p>This test checks that no unexpected exception is thrown, and that the result is not null as it should return
     * all in the world.</p>
     */
    @Test
    public void testCapitalQueries_noFiltersNoLimits() {
        try {
            String result = callCapitalQueries(worldDB, null, null, null);
            assertNotNull(result, "The result should not be null for a valid connection with no filters or limits.");
        } catch (Exception e) {
            fail("The test should not have thrown an exception" + e.getMessage());
        }
    }

    /**
     * Testing the populationDistributionQuery method, with correct continent input
     *
     * <p>This test checks that no unexpected exception is thrown, and that the result is not null.</p>
     */
    @Test
    public void testCapitalQueries_withContinentFilter() {
        try {
            String result = callCapitalQueries(worldDB, "continent", exampleContinent, null);
            assertNotNull(result, "The result should not be null for a valid connection with no filters or limits.");
            assertTrue(result.contains("Paris"));
            assertTrue(result.contains("Berlin"));
        } catch (Exception e) {
            fail("The test should not have thrown an exception" + e.getMessage());
        }
    }

    /**
     * Testing the populationDistributionQuery method, with correct region input
     *
     * <p>This test checks that no unexpected exception is thrown, and that the result is not null.</p>
     */
    @Test
    public void testCapitalQueries_withRegionFilter() {
        try {
            String result = callCapitalQueries(worldDB, "region", exampleRegion, null);
            assertNotNull(result, "The result should not be null when filtering by region.");
            assertTrue(result.contains("Paris"));
            assertTrue(result.contains("Berlin"));
        } catch (Exception e) {
            fail("The test should not have thrown an exception: " + e.getMessage());
        }
    }

    /**
     * Testing the populationDistributionQuery method, with correct limit (top x)
     *
     * <p>This test checks that no unexpected exception is thrown, and that the result is not null.</p>
     */
    @Test
    public void testCapitalQueries_withLimit() {
        try {
            String result = callCapitalQueries(worldDB, null, null, exampleLimit);
            assertNotNull(result, "The result should not be null when using a valid limit.");
            assertTrue(result.contains("Tokyo"));
            assertTrue(result.contains("Seoul"));
        } catch (Exception e) {
            fail("The test should not have thrown an exception: " + e.getMessage());
        }
    }
    @Disabled("disabled: github issue #99, zube card #113")
    @Test
    public void testCapitalQueries_nullDatabase() {
        try {
            callCapitalQueries(null, null, null, null);
            fail("Should have thrown an exception.");
        } catch (IllegalArgumentException e) {
            assertTrue(output.toString().contains("DatabaseConnection cannot be null"), "Expected an error indicating that the DatabaseConnection is null.");
        }
    }

    @Disabled("disabled: github issue #100, zube card #132")
    @Test
    public void testCapitalQueries_nullDatabaseConnection() {
        try {
            callCapitalQueries(nullDB, null, null, null);
            fail("Should have thrown an exception.");
        } catch (Exception e) {
            assertTrue(output.toString().contains("The connection of the DatabaseConnection object is null"), "Expected an error indicating that the connection of the DatabaseConnection is null.");
        }
    }

    @Disabled("disabled: github issue #98, zube card #114")
    @Test
    public void testCapitalQueries_attributeWhereMismatch() {
        try {
            String result = callCapitalQueries(worldDB, exampleContinent, null, null);
            if (result == null) {
                fail("The method returned null instead of throwing an IllegalArgumentException.");
            }
        } catch (IllegalArgumentException e) {
            assertTrue(e.getMessage().contains("attribute & Where must match"), "Expected IllegalArgumentException with a message about attribute and where mismatch.");
        } catch (Exception e) {
            fail("Unexpected exception type thrown:" + e.getMessage());
        }
    }

    /**
     * Testing the AllCapitals method, with valid input
     *
     * <p>This test checks that no unexpected exception is thrown, and that the result is not null.</p>
     */
    @Test
    public void testAllCapitals_notNull() {
        try {
            String result = CapitalQueries.AllCapitals(worldDB);
            assertNotNull(result, "AllCapitals method returned null. Expected a valid result.");
        } catch (Exception e) {
            fail("Exception occurred: " + e.getMessage());
        }
    }

    /**
     * Testing the AllCapitalsContinent method, with valid input
     *
     * <p>This test checks that no unexpected exception is thrown, and that the result is not null.</p>
     */
    @Test
    public void testAllCapitalsContinent_notNull() {
        try {
            String result = CapitalQueries.AllCapitalsContinent(worldDB, exampleContinent);
            assertNotNull(result, "AllCapitalsContinent method returned null. Expected a valid result.");
        } catch (Exception e) {
            fail("Exception occurred: " + e.getMessage());
        }
    }

    /**
     * Testing the AllCapitalsRegion method, with valid input
     *
     * <p>This test checks that no unexpected exception is thrown, and that the result is not null.</p>
     */
    @Test
    public void testAllCapitalsRegion_notNull() {
        try {
            String result = CapitalQueries.AllCapitalsRegion(worldDB, exampleRegion);
            assertNotNull(result, "AllCapitalsRegion method returned null. Expected a valid result.");
        } catch (Exception e) {
            fail("Exception occurred: " + e.getMessage());
        }
    }

    /**
     * Testing the XCapitals method, with valid input
     *
     * <p>This test checks that no unexpected exception is thrown, and that the result is not null.</p>
     */
    @Test
    public void testXCapitalsWorld_notNull() {
        try {
            String result = CapitalQueries.XCapitalsWorld(worldDB, exampleLimit);
            assertNotNull(result, "XCapitalsWorld method returned null. Expected a valid result.");
        } catch (Exception e) {
            fail("Exception occurred: " + e.getMessage());
        }
    }

    /**
     * Testing the XCapitalsContinent method, with valid input
     *
     * <p>This test checks that no unexpected exception is thrown, and that the result is not null.</p>
     */
    @Test
    public void testXCapitalsContinent_notNull() {
        try {
            String result = CapitalQueries.XCapitalsContinent(worldDB, exampleContinent, exampleLimit);
            assertNotNull(result, "XCapitalsContinent method returned null. Expected a valid result.");
        } catch (Exception e) {
            fail("Exception occurred: " + e.getMessage());
        }
    }

    /**
     * Testing the XCapitalsRegion method, with valid input
     *
     * <p>This test checks that no unexpected exception is thrown, and that the result is not null.</p>
     */
    @Test
    public void testXCapitalsRegion_notNull() {
        try {
            String result = CapitalQueries.XCapitalsRegion(worldDB, exampleRegion, exampleLimit);
            assertNotNull(result, "XCapitalsRegion method returned null. Expected a valid result.");
        } catch (Exception e) {
            fail("Exception occurred: " + e.getMessage());
        }
    }

}
