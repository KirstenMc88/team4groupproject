package com.napier.team4groupproject;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.sql.SQLException;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;



public class GeneralInfoQueriesTestIntegrated {

    static DatabaseConnection connection;

    // integration tests
    @BeforeAll
    public static void connect() {
        connection = new DatabaseConnection();
        connection.connect("localhost:33060", 60000);
    }

    @AfterAll
    public static void disconnect() {
        connection.disconnect();
    }

    @Test
    public void testGetPopulationOfTheWorldNotNull() throws SQLException {
        String worldPopReport = GeneralInfoQueries.populationOfTheWorld(connection);

        assertNotNull(worldPopReport);
    }

    @Test
    public void testGetPopulationOfTheWorldContainPopulation() throws SQLException {
        String worldPopReport = GeneralInfoQueries.populationOfTheWorld(connection);

        System.out.println(worldPopReport);

        assertTrue(worldPopReport.contains("6078749450"));
    }




}
