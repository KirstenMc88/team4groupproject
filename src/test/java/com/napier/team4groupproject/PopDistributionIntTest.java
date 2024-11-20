package com.napier.team4groupproject;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.lang.reflect.InvocationTargetException;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Integration tests for PopDistributionQueries class
 *
 * <p>This class contains integration tests using JUnit to test that all methods in PopDistributionQueries behave
 * as expected when interacting with several other classes and methods.</p>
 */
public class PopDistributionIntTest {
    private static DatabaseConnection worldDB;
    private static DatabaseConnection nullDB;
    private static ByteArrayOutputStream output;

    // Example inputs for testing
    private static String exampleContinent;
    private static String exampleRegion;
    private static String exampleCountry;
    private static String exampleWhere;

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
        exampleCountry = "Germany";
    }

    /**
     * Method to call the populationDistributionQuery method for different tests
     * <p>This method calls the populationDistributionQuery method with different parameters and returns the string
     * which is returned by the populationDistributionQuery method. </p>
     *
     * @param sql the DatabaseConnection object used to connect to the world database
     * @param attribute the column used to filter (e.g., continent, region, country) or null
     * @param where the user-inputted value for filtering (e.g., "Europe", "Asia") or null
     * @return formatted string of data containing query results or null if an exception occurs
     */
    public static String callPopulationDistributionQuery(DatabaseConnection sql, String attribute, String where) {
        try {
            return (String) Utilities.callPrivateMethod(
                    PopDistributionQueries.class,
                    null,
                    "populationDistributionQuery",
                    new Class<?>[]{DatabaseConnection.class, String.class, String.class},
                    sql, attribute, where
            );
        } catch (InvocationTargetException e) {
            throw new RuntimeException("Invocation error: " + e.getTargetException().getMessage(), e);
        }
    }

    // tests with valid parameters

    /**
     * Testing the populationDistributionQuery method, with correct continent input
     *
     * <p>This test checks that no unexpected exception is thrown, and that the result is not null.</p>
     */
    @Test
    public void testPopulationDistributionQuery_validContinent() {
        try {
            String result = callPopulationDistributionQuery(worldDB, "continent", exampleContinent);
            assertNotNull(result, "The result should not be null for a valid continent filter.");
        } catch (Exception e) {
            fail("The test should not have thrown an exception: " + e.getMessage());
        }
    }

    /**
     * Testing the populationDistributionQuery method, with correct region input
     *
     * <p>This test checks that no unexpected exception is thrown, and that the result is not null.</p>
     */
    @Test
    public void testPopulationDistributionQuery_validRegion() {
        try {
            String result = callPopulationDistributionQuery(worldDB, "region", exampleRegion);
            assertNotNull(result, "The result should not be null for a valid region filter.");
        } catch (Exception e) {
            fail("The test should not have thrown an exception: " + e.getMessage());
        }
    }

    /**
     * Testing the populationDistributionQuery method, with correct country input
     *
     * <p>This test checks that no unexpected exception is thrown, and that the result is not null.</p>
     */
    @Test
    public void testPopulationDistributionQuery_validCountry() {
        try {
            String result = callPopulationDistributionQuery(worldDB, "country.Name", exampleCountry);
            assertNotNull(result, "The result should not be null for a valid country filter.");
        } catch (Exception e) {
            fail("The test should not have thrown an exception: " + e.getMessage());
        }
    }

    /**
     * Testing the populationDistributionQuery method, with null input
     *
     * <p>This test checks that no unexpected exception is thrown, and that the result is not null, as it
     * should return the population of the whole world.</p>
     */
    @Test
    public void testPopulationDistributionQuery_noFilters() {
        try {
            String result = callPopulationDistributionQuery(worldDB, null, null);
            assertNotNull(result, "The result should not be null for no filters.");
        } catch (Exception e) {
            fail("The test should not have thrown an exception: " + e.getMessage());
        }
    }


    // test for invalid attribute or where
    /**
     * Testing the populationDistributionQuery method, with invalid column to filter by
     *
     * <p>This test checks that no unexpected exception is thrown, and that the result is null.</p>
     */
    @Test
    public void testPopulationDistributionQuery_invalidAttribute() {
        try {
            String result = callPopulationDistributionQuery(worldDB, "invalidColumn", exampleWhere);
            assertNull(result, "The result should be null for an invalid attribute.");
        } catch (Exception e) {
            fail("Unexpected exception type thrown: " + e.getMessage());
        }
    }

    /**
     * Testing the populationDistributionQuery method, with invalid where to filter with
     *
     * <p>This test checks that no unexpected exception is thrown, and that the result is null.</p>
     */
    @Test
    public void testPopulationDistributionQuery_invalidWhere() {
        try {
            String result = callPopulationDistributionQuery(worldDB, "continent", "InvalidValue");
            assertNotNull(result, "The method should return an appropriate output for an invalid where value, not null.");
        } catch (Exception e) {
            fail("Unexpected exception type thrown: " + e.getMessage());
        }
    }

    /**
     * Testing the ContinentPopulation method, with valid input
     *
     * <p>This test checks that no unexpected exception is thrown, and that the result is not null.</p>
     */
    @Test
    public void testContinentPopulation_notNull() {
        try {
            String result = PopDistributionQueries.ContinentPopulation(worldDB, exampleContinent);
            assertNotNull(result, "ContinentPopulation method returned null. Expected a valid result.");
        } catch (Exception e) {
            fail("The test should not have thrown an exception: " + e.getMessage());
        }
    }

    /**
     * Testing the RegionPopulation method, with valid input
     *
     * <p>This test checks that no unexpected exception is thrown, and that the result is not null.</p>
     */
    @Test
    public void testRegionPopulation_notNull() {
        try {
            String result = PopDistributionQueries.RegionPopulation(worldDB, exampleRegion);
            assertNotNull(result, "RegionPopulation method returned null. Expected a valid result.");
        } catch (Exception e) {
            fail("The test should not have thrown an exception: " + e.getMessage());
        }
    }

    /**
     * Testing the CountryPopulation method, with valid input
     *
     * <p>This test checks that no unexpected exception is thrown, and that the result is not null.</p>
     */
    @Test
    public void testCountryPopulation_notNull() {
        try {
            String result = PopDistributionQueries.CountryPopulation(worldDB, exampleCountry);
            assertNotNull(result, "CountryPopulation method returned null. Expected a valid result.");
        } catch (Exception e) {
            fail("The test should not have thrown an exception: " + e.getMessage());
        }
    }
}
