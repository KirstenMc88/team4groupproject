package com.napier.team4groupproject;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

/**
 * Integration tests for LanguageQuery class
 *
 * <p>This class contains integration tests using JUnit to test that all methods in LanguageQuery behave
 * as expected when interacting with several other classes and methods.</p>
 */
public class LanguageQueryIntTest {
    private static DatabaseConnection worldDB;
    private static String output;

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
    }

    /**
     * Testing if LanguageDistributionInThWorld returns a value.
     */
    @Test
    public void LanguageDistributionInThWorld_returnsValue(){
        output = LanguageQuery.LanguageDistributionInThWorld(worldDB);

        assertNotNull(output);
    }

    /**
     * Testing if LanguageDistributionInThWorld returns a value containing the required languages.
     */
    @Test
    public void LanguageDistributionInThWorld_returnsCorrectLanguages(){
        output = LanguageQuery.LanguageDistributionInThWorld(worldDB);

        assertTrue(output.contains("Chinese"));
        assertTrue(output.contains("English"));
        assertTrue(output.contains("Hindi"));
        assertTrue(output.contains("Spanish"));
        assertTrue(output.contains("Arabic"));
    }
}
