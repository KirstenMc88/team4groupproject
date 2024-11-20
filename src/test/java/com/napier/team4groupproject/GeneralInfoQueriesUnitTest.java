package com.napier.team4groupproject;

import org.junit.jupiter.api.*;
import java.sql.*;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.*;

/**
 * GeneralInfoQueriesUnitTest class provides unit tests for the GeneralInfoQueries to ensure they work as intended.
 */
public class GeneralInfoQueriesUnitTest {


    private static DatabaseConnection nullDatabaseConnection;
    private static String exampleData;



    @BeforeAll
    public static void initialize() {
        System.setProperty("Environment", "UnitTest");
       nullDatabaseConnection = new DatabaseConnection();
        exampleData = "";

    }

    // population of the world

    /**
     * Test checks that the correct error message is output to the user if there is no database connection.
     * @throws SQLException
     */
    @Test
    public void testGetPopulationOfTheWorldWithNullDatabaseConnection() throws SQLException {
        String result = GeneralInfoQueries.populationOfTheWorld(nullDatabaseConnection);

        assertTrue(result.contains("Sorry database doesn't have a connection"));
    }

    /**
     * Test checks if a valid database has been passed to the method.
     * Test passes if the correct error message is output to the user.
     * @throws SQLException
     */
    @Test
    public void testGetPopulationOfTheWorldWithNull() throws SQLException {
        String result = GeneralInfoQueries.populationOfTheWorld(null);

        assertTrue(result.contains("Sorry database doesn't have a connection"));
    }

    // population of a continent

    /**
     * Test checks that the correct error message is output to the user if there is no database connection.
     * @throws SQLException
     */
    @Test
    public void testGetPopulationOfAContinentWithNullDatabaseConnection() throws SQLException {
        String result = GeneralInfoQueries.populationOfAContinent(nullDatabaseConnection, exampleData);

        assertTrue(result.contains("Sorry database doesn't have a connection"));
    }

    /**
     * Test checks if a valid database has been passed to the method.
     * Test passes if the correct error message is output to the user.
     * @throws SQLException
     */
    @Test
    public void testGetPopulationOfAContinentWithNull() throws SQLException {
        String result = GeneralInfoQueries.populationOfAContinent(null, exampleData);

        assertTrue(result.contains("Sorry database doesn't have a connection"));
    }

    // population of the Region

    /**
     * Test checks that the correct error message is output to the user if there is no database connection.
     * @throws SQLException
     */
    @Test
    public void testGetPopulationOfARegionWithNullDatabaseConnection() throws SQLException {
        String result = GeneralInfoQueries.populationOfARegion(nullDatabaseConnection, exampleData);

        assertTrue(result.contains("Sorry database doesn't have a connection"));
    }

    /**
     * Test checks if a valid database has been passed to the method.
     * Test passes if the correct error message is output to the user.
     * @throws SQLException
     */
    @Test
    public void testGetPopulationOfARegionWithNull() throws SQLException {
        String result = GeneralInfoQueries.populationOfARegion(null, exampleData);

        assertTrue(result.contains("Sorry database doesn't have a connection"));
    }

    // population of a Country

    /**
     * Test checks that the correct error message is output to the user if there is no database connection.
     * @throws SQLException
     */
    @Test
    public void testGetPopulationOfACountryWithNullDatabaseConnection() throws SQLException {
        String result = GeneralInfoQueries.populationOfACountry(nullDatabaseConnection, exampleData);

        assertTrue(result.contains("Sorry database doesn't have a connection"));
    }

    /**
     * Test checks if a valid database has been passed to the method.
     * Test passes if the correct error message is output to the user.
     * @throws SQLException
     */
    @Test
    public void testGetPopulationOfaCountryWithNull() throws SQLException {
        String result = GeneralInfoQueries.populationOfACountry(null, exampleData);

        assertTrue(result.contains("Sorry database doesn't have a connection"));
    }

    // population of a District

    /**
     * Test checks that the correct error message is output to the user if there is no database connection.
     * @throws SQLException
     */
    @Test
    public void testGetPopulationOfOfaDistrictWithNullDatabase() throws SQLException {
        String result = GeneralInfoQueries.populationOfADistrict(nullDatabaseConnection, exampleData);

        assertTrue(result.contains("Sorry database doesn't have a connection"));
    }

    /**
     * Test checks if a valid database has been passed to the method.
     * Test passes if the correct error message is output to the user.
     * @throws SQLException
     */
    @Test
    public void testGetPopulationOfaDistrictWithNull() throws SQLException {
        String result = GeneralInfoQueries.populationOfADistrict(null, exampleData);

        assertTrue(result.contains("Sorry database doesn't have a connection"));
    }

    // population of a city

    /**
     * Test checks that the correct error message is output to the user if there is no database connection.
     * @throws SQLException
     */
    @Test
    public void testGetPopulationOfaCityWithNullDatabase() throws SQLException {
        String result = GeneralInfoQueries.populationOfACity(nullDatabaseConnection, exampleData);

        assertTrue(result.contains("Sorry database doesn't have a connection"));
    }

    /**
     * Test checks if a valid database has been passed to the method.
     * Test passes if the correct error message is output to the user.
     * @throws SQLException
     */
    @Test
    public void testGetPopulationOfaCityWithNull() throws SQLException {
        String result = GeneralInfoQueries.populationOfACity(null, exampleData);

        assertTrue(result.contains("Sorry database doesn't have a connection"));
    }

}
