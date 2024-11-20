package com.napier.team4groupproject;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.sql.SQLException;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;


/**
 * GeneralInfoQueriesIntTest Class provides tests for the GeneralInfoQueries to ensure they are working as expected.
 */
public class GeneralInfoQueriesIntTest {

    private static DatabaseConnection connection;
    private static String exampleContinent;
    private static String exampleCountry;
    private static String exampleRegion;
    private static String exampleDistrict;
    private static String exampleCity;
    private static String inputThatWontBeFound;

    // integration tests
    @BeforeAll
    public static void setUp() {

        System.setProperty("Environment", "IntegrationTest");

        connection = new DatabaseConnection();
        connection.connect("localhost:33060", 60000);



        exampleContinent = "North America";
        exampleCountry = "Australia";
        exampleRegion = "Eastern Europe";
        exampleDistrict = "Carabobo";
        exampleCity = "Tigre";
        inputThatWontBeFound = "aaaaaaaaaa";
    }

    @AfterAll
    public static void disconnect() {
        connection.disconnect();
    }

    // population of the world tests

    /**
     * Tests whether a value is returned when querying the database.
     * Test passes if a value was returned from the database.
     * @throws SQLException
     */
    @Test
    public void testGetPopulationOfTheWorldReturnsValue() throws SQLException {
        String result = GeneralInfoQueries.populationOfTheWorld(connection);

        assertNotNull(result);
    }

    /**
     * Tests whether the correct column header is returned during the query.
     * Test passes if the Population header is present in the results.
     * @throws SQLException
     */
    @Test
    public void testGetPopulationOfTheWorldContainPopulationColumnHeader() throws SQLException {
        String result = GeneralInfoQueries.populationOfTheWorld(connection);

        assertTrue(result.contains("Population"));
    }


    // population of a continent tests

    /**
     * Tests whether a value is returned when querying the database.
     * Test passes if a value was returned from the database.
     * @throws SQLException
     */
    @Test
    public void testGetPopulationOfAContinentReturnsValue() throws SQLException {
        String result = GeneralInfoQueries.populationOfAContinent(connection, exampleContinent);

        assertNotNull(result);
    }

    /**
     * Tests whether the correct column header is returned during the query.
     * Test passes if the Population header is present in the results.
     * @throws SQLException
     */
    @Test
    public void testGetPopulationOfAContinentReturnsCorrectColumnHeader() throws SQLException {
        String result = GeneralInfoQueries.populationOfAContinent(connection, exampleContinent);

        assertTrue(result.contains("Population"));
    }

    /**
     * Test checks the error message out is output to the user when their search doesn't return a result.
     * Test passes if the correct error message is output to the user.
     * @throws SQLException
     */
    @Test
    public void testGetPopulationOfAContinentWhenContinentNotFound() throws SQLException {
        String result = GeneralInfoQueries.populationOfAContinent(connection, inputThatWontBeFound);

        assertTrue(result.contains("No population data available for the given continent."));
    }

    /**
     * Tests what happens when an empty input is entered.
     * Tests passes if the correct error message is output to the user.
     * @throws SQLException
     */
    @Test
    public void testGetPopulationOfAContinentWhenContinentNull() throws SQLException {
        String result = GeneralInfoQueries.populationOfAContinent(connection, null);

        assertTrue(result.contains("Field cannot be empty"));
    }

    // population of a country tests

    /**
     * Tests whether a value is returned when querying the database.
     * Test passes if a value was returned from the database.
     * @throws SQLException
     */
    @Test
    public void testGetPopulationOfACountryReturnsValue() throws SQLException {
        String result = GeneralInfoQueries.populationOfACountry(connection, exampleCountry);

        assertNotNull(result);
    }

    /**
     * Tests whether the correct column header is returned during the query.
     * Test passes if the Population header is present in the results.
     * @throws SQLException
     */
    @Test
    public void testGetPopulationOfACountryReturnsCorrectColumnHeader() throws SQLException {
        String result = GeneralInfoQueries.populationOfACountry(connection, exampleCountry);

        assertTrue(result.contains("Population"));
    }

    /**
     * Test checks the error message out is output to the user when their search doesn't return a result.
     * Test passes if the correct error message is output to the user.
     * @throws SQLException
     */
    @Test
    public void testGetPopulationOfACountryWhenCountryNotFound() throws SQLException {
        String result = GeneralInfoQueries.populationOfACountry(connection, inputThatWontBeFound);

        assertTrue(result.contains("No matching data found. Please check your spelling and try again."));
    }

    /**
     * Tests what happens when an empty input is entered.
     * Tests passes if the correct error message is output to the user.
     * @throws SQLException
     */
    @Test
    public void testGetPopulationOfACountryWhenCountryNull() throws SQLException {
        String result = GeneralInfoQueries.populationOfACountry(connection, null);

        assertTrue(result.contains("Field cannot be empty"));
    }


    // population of a region tests

    /**
     * Tests whether a value is returned when querying the database.
     * Test passes if a value was returned from the database.
     * @throws SQLException
     */
    @Test
    public void testGetPopulationOfARegionReturnsValue() throws SQLException {
        String result = GeneralInfoQueries.populationOfARegion(connection, exampleRegion);

        assertNotNull(result);
    }

    /**
     * Tests whether the correct column header is returned during the query.
     * Test passes if the Population header is present in the results.
     * @throws SQLException
     */
    @Test
    public void testGetPopulationOfARegionReturnsCorrectColumnHeader() throws SQLException {
        String result = GeneralInfoQueries.populationOfARegion(connection, exampleRegion);

        assertTrue(result.contains("Population"));
    }

    /**
     * Test checks the error message out is output to the user when their search doesn't return a result.
     * Test passes if the correct error message is output to the user.
     * @throws SQLException
     */
    @Test
    public void testGetPopulationOfARegionWhenRegionNotFound() throws SQLException {
        String result = GeneralInfoQueries.populationOfARegion(connection, inputThatWontBeFound);

        assertTrue(result.contains("No population data available for the given region."));
    }

    /**
     * Tests what happens when an empty input is entered.
     * Tests passes if the correct error message is output to the user.
     * @throws SQLException
     */
    @Test
    public void testGetPopulationOfARegionWhenRegionNull() throws SQLException {
        String result = GeneralInfoQueries.populationOfARegion(connection, null);

        assertTrue(result.contains("Field cannot be empty"));
    }

    // population of a district tests

    /**
     * Tests whether a value is returned when querying the database.
     * Test passes if a value was returned from the database.
     * @throws SQLException
     */
    @Test
    public void testGetPopulationOfDistrictReturnsValue() throws SQLException {
        String result = GeneralInfoQueries.populationOfADistrict(connection, exampleDistrict);

        assertNotNull(result);
    }

    /**
     * Tests whether the correct column header is returned during the query.
     * Test passes if the Population header is present in the results.
     * @throws SQLException
     */
    @Test
    public void testGetPopulationOfADistrictReturnsCorrectColumnHeader() throws SQLException {
        String result = GeneralInfoQueries.populationOfADistrict(connection, exampleDistrict);

        assertTrue(result.contains("Population"));
    }


    /**
     * Test checks the error message out is output to the user when their search doesn't return a result.
     * Test passes if the correct error message is output to the user.
     * @throws SQLException
     */
    @Test
    public void testGetPopulationOfADistrictWhenDistrictNotFound() throws SQLException {
        String result = GeneralInfoQueries.populationOfADistrict(connection, inputThatWontBeFound);

        assertTrue(result.contains("No population data available for the given district."));
    }

    /**
     * Tests what happens when an empty input is entered.
     * Tests passes if the correct error message is output to the user.
     * @throws SQLException
     */
    @Test
    public void testGetPopulationOfADistrictWhenDistrictNull() throws SQLException {
        String result = GeneralInfoQueries.populationOfADistrict(connection, null);

        assertTrue(result.contains("Field cannot be empty"));
    }


    // population of a city tests

    /**
     * Tests whether a value is returned when querying the database.
     * Test passes if a value was returned from the database.
     * @throws SQLException
     */
    @Test
    public void testGetPopulationOfCityReturnsValue() throws SQLException {
        String result = GeneralInfoQueries.populationOfACity(connection, exampleCity);

        assertNotNull(result);
    }

    /**
     * Tests whether the correct column header is returned during the query.
     * Test passes if the Population header is present in the results.
     * @throws SQLException
     */
    @Test
    public void testGetPopulationOfACityReturnsCorrectColumnHeader() throws SQLException {
        String result = GeneralInfoQueries.populationOfACity(connection, exampleCity);

        assertTrue(result.contains("Population"));
    }

    /**
     * Test checks the error message out is output to the user when their search doesn't return a result.
     * Test passes if the correct error message is output to the user.
     * @throws SQLException
     */
    @Test
    public void testGetPopulationOfACityWhenCityNotFound() throws SQLException {
        String result = GeneralInfoQueries.populationOfACity(connection, inputThatWontBeFound);

        assertTrue(result.contains("No matching data found. Please check your spelling and try again."));
    }

    /**
     * Tests what happens when an empty input is entered.
     * Tests passes if the correct error message is output to the user.
     * @throws SQLException
     */
    @Test
    public void testGetPopulationOfACityWhenCityNull() throws SQLException {
        String result = GeneralInfoQueries.populationOfACity(connection, null);

        assertTrue(result.contains("Field cannot be empty"));
    }




}
