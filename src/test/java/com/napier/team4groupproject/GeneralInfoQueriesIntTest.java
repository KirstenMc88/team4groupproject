package com.napier.team4groupproject;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.sql.SQLException;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;



public class GeneralInfoQueriesIntTest {

    private static DatabaseConnection connection;
    private static DatabaseConnection noConnection;
    private static String exampleContinent;
    private static String exampleCountry;
    private static String exampleRegion;
    private static String exampleDistrict;
    private static String exampleCity;
    private static String inputThatWontBeFound;

    // integration tests
    @BeforeAll
    public static void connect() {
        connection = new DatabaseConnection();
        connection.connect("localhost:33060", 60000);

        noConnection = new DatabaseConnection();

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

    @Test
    public void testGetPopulationOfTheWorldWithNoDatabaseConnection() throws SQLException {
        String result = GeneralInfoQueries.populationOfTheWorld(noConnection);


    }


    @Test
    public void testGetPopulationOfTheWorldReturnsValue() throws SQLException {
        String result = GeneralInfoQueries.populationOfTheWorld(connection);

        assertNotNull(result);
    }

    @Test
    public void testGetPopulationOfTheWorldContainPopulationColumnHeader() throws SQLException {
        String result = GeneralInfoQueries.populationOfTheWorld(connection);

        assertTrue(result.contains("Population"));
    }


    // population of a continent tests
    @Test
    public void testGetPopulationOfAContinentReturnsValue() throws SQLException {
        String result = GeneralInfoQueries.populationOfAContinent(connection, exampleContinent);

        assertNotNull(result);
    }

    @Test
    public void testGetPopulationOfAContinentReturnsCorrectColumnHeader() throws SQLException {
        String result = GeneralInfoQueries.populationOfAContinent(connection, exampleContinent);

        assertTrue(result.contains("Population"));
    }

    @Test
    public void testGetPopulationOfAContinentWhenContinentNotFound() throws SQLException {
        String result = GeneralInfoQueries.populationOfAContinent(connection, inputThatWontBeFound);

        assertTrue(result.contains("No matching data found. Please check your spelling and try again."));
    }

    @Test
    public void testGetPopulationOfAContinentWhenContinentNull() throws SQLException {
        String result = GeneralInfoQueries.populationOfAContinent(connection, null);

        assertTrue(result.contains("No matching data found. Please check your spelling and try again."));
    }

    // population of a country tests
    @Test
    public void testGetPopulationOfACountryReturnsValue() throws SQLException {
        String result = GeneralInfoQueries.populationOfACountry(connection, exampleCountry);

        assertNotNull(result);
    }

    @Test
    public void testGetPopulationOfACountryReturnsCorrectColumnHeader() throws SQLException {
        String result = GeneralInfoQueries.populationOfACountry(connection, exampleCountry);

        assertTrue(result.contains("Population"));
    }

    @Test
    public void testGetPopulationOfACountryWhenCountryNotFound() throws SQLException {
        String result = GeneralInfoQueries.populationOfACountry(connection, inputThatWontBeFound);

        assertTrue(result.contains("No matching data found. Please check your spelling and try again."));
    }

    @Test
    public void testGetPopulationOfACountryWhenCountryNull() throws SQLException {
        String result = GeneralInfoQueries.populationOfACountry(connection, null);

        assertTrue(result.contains("No matching data found. Please check your spelling and try again."));
    }


    // population of a region tests
    @Test
    public void testGetPopulationOfARegionReturnsValue() throws SQLException {
        String result = GeneralInfoQueries.populationOfARegion(connection, exampleRegion);

        assertNotNull(result);
    }

    @Test
    public void testGetPopulationOfARegionReturnsCorrectColumnHeader() throws SQLException {
        String result = GeneralInfoQueries.populationOfARegion(connection, exampleRegion);

        assertTrue(result.contains("Population"));
    }

    @Test
    public void testGetPopulationOfARegionWhenRegionNotFound() throws SQLException {
        String result = GeneralInfoQueries.populationOfARegion(connection, inputThatWontBeFound);

        assertTrue(result.contains("No matching data found. Please check your spelling and try again."));
    }

    @Test
    public void testGetPopulationOfARegionWhenRegionNull() throws SQLException {
        String result = GeneralInfoQueries.populationOfARegion(connection, null);

        assertTrue(result.contains("No matching data found. Please check your spelling and try again."));
    }

    // population of a district tests
    @Test
    public void testGetPopulationOfDistrictReturnsValue() throws SQLException {
        String result = GeneralInfoQueries.populationOfADistrict(connection, exampleDistrict);

        assertNotNull(result);
    }

    @Test
    public void testGetPopulationOfADistrictReturnsCorrectColumnHeader() throws SQLException {
        String result = GeneralInfoQueries.populationOfADistrict(connection, exampleDistrict);

        assertTrue(result.contains("Population"));
    }


    @Test
    public void testGetPopulationOfADistrictWhenDistrictNotFound() throws SQLException {
        String result = GeneralInfoQueries.populationOfADistrict(connection, inputThatWontBeFound);

        assertTrue(result.contains("No matching data found. Please check your spelling and try again."));
    }

    @Test
    public void testGetPopulationOfADistrictWhenDistrictNull() throws SQLException {
        String result = GeneralInfoQueries.populationOfADistrict(connection, null);

        assertTrue(result.contains("No matching data found. Please check your spelling and try again."));
    }


    // population of a city tests
    @Test
    public void testGetPopulationOfCityReturnsValue() throws SQLException {
        String result = GeneralInfoQueries.populationOfACity(connection, exampleCity);

        assertNotNull(result);
    }

    @Test
    public void testGetPopulationOfACityReturnsCorrectColumnHeader() throws SQLException {
        String result = GeneralInfoQueries.populationOfACity(connection, exampleCity);

        assertTrue(result.contains("Population"));
    }

    @Test
    public void testGetPopulationOfACityWhenCityNotFound() throws SQLException {
        String result = GeneralInfoQueries.populationOfACity(connection, inputThatWontBeFound);

        assertTrue(result.contains("No matching data found. Please check your spelling and try again."));
    }

    @Test
    public void testGetPopulationOfACityWhenCityNull() throws SQLException {
        String result = GeneralInfoQueries.populationOfACity(connection, null);

        assertTrue(result.contains("No matching data found. Please check your spelling and try again."));
    }




}
