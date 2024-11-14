package com.napier.team4groupproject;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.lang.reflect.InvocationTargetException;

import static org.junit.jupiter.api.Assertions.*;

public class PopDistributionTest {
    private static DatabaseConnection worldDB;
    private static DatabaseConnection nullDB;
    private static ByteArrayOutputStream output;

    // Example inputs for testing
    private static String exampleContinent;
    private static String exampleRegion;
    private static String exampleCountry;
    private static String exampleWhere;

    @BeforeAll
    public static void setUp() {
        worldDB = new DatabaseConnection();
        nullDB = new DatabaseConnection();

        worldDB.connect("localhost:33060", 10000);

        output = new ByteArrayOutputStream();
        System.setOut(new PrintStream(output));

        exampleContinent = "Europe";
        exampleRegion = "Western Europe";
        exampleCountry = "Germany";
    }

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
    @Test
    public void testPopulationDistributionQuery_validContinent() {
        try {
            String result = callPopulationDistributionQuery(worldDB, "continent", exampleContinent);
            assertNotNull(result, "The result should not be null for a valid continent filter.");
        } catch (Exception e) {
            fail("The test should not have thrown an exception: " + e.getMessage());
        }
    }

    @Test
    public void testPopulationDistributionQuery_validRegion() {
        try {
            String result = callPopulationDistributionQuery(worldDB, "region", exampleRegion);
            assertNotNull(result, "The result should not be null for a valid region filter.");
        } catch (Exception e) {
            fail("The test should not have thrown an exception: " + e.getMessage());
        }
    }

    @Test
    public void testPopulationDistributionQuery_validCountry() {
        try {
            String result = callPopulationDistributionQuery(worldDB, "country.Name", exampleCountry);
            assertNotNull(result, "The result should not be null for a valid country filter.");
        } catch (Exception e) {
            fail("The test should not have thrown an exception: " + e.getMessage());
        }
    }

    @Test
    public void testPopulationDistributionQuery_noFilters() {
        try {
            String result = callPopulationDistributionQuery(worldDB, null, null);
            assertNotNull(result, "The result should not be null for no filters.");
        } catch (Exception e) {
            fail("The test should not have thrown an exception: " + e.getMessage());
        }
    }

    // tests for null inputs
    @Test
    public void testPopulationDistributionQuery_nullDatabase() {
        try {
            callPopulationDistributionQuery(null, "continent", exampleContinent);
            fail("Should have thrown an exception due to null database connection.");
        } catch (NullPointerException e) {
            assertTrue(true);
        } catch (Exception e) {
            fail("Unexpected exception type thrown: " + e.getMessage());
        }
    }

    @Test
    public void testPopulationDistributionQuery_nullWhereValidAttribute() {
        try {
            String result = callPopulationDistributionQuery(worldDB, "continent", null);
            assertNotNull(result, "The result should not be null even when the where clause is null.");
        } catch (Exception e) {
            fail("The test should not have thrown an exception: " + e.getMessage());
        }
    }

    // test for invalid attribute or where
    @Test
    public void testPopulationDistributionQuery_invalidAttribute() {
        try {
            String result = callPopulationDistributionQuery(worldDB, "invalidColumn", exampleWhere);
            assertNull(result, "The result should be null for an invalid attribute.");
        } catch (Exception e) {
            fail("Unexpected exception type thrown: " + e.getMessage());
        }
    }

    @Test
    public void testPopulationDistributionQuery_invalidWhere() {
        try {
            String result = callPopulationDistributionQuery(worldDB, "continent", "InvalidValue");
            assertNotNull(result, "The method should return an appropriate output for an invalid where value, not null.");
        } catch (Exception e) {
            fail("Unexpected exception type thrown: " + e.getMessage());
        }
    }

    @Test
    public void testContinentPopulation_notNull() {
        try {
            String result = PopDistributionQueries.ContinentPopulation(worldDB, exampleContinent);
            assertNotNull(result, "ContinentPopulation method returned null. Expected a valid result.");
        } catch (Exception e) {
            fail("The test should not have thrown an exception: " + e.getMessage());
        }
    }

    @Test
    public void testRegionPopulation_notNull() {
        try {
            String result = PopDistributionQueries.RegionPopulation(worldDB, exampleRegion);
            assertNotNull(result, "RegionPopulation method returned null. Expected a valid result.");
        } catch (Exception e) {
            fail("The test should not have thrown an exception: " + e.getMessage());
        }
    }

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
