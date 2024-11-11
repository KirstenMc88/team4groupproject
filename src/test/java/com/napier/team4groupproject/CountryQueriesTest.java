package com.napier.team4groupproject;

import com.napier.team4groupproject.CountryQueries;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.contains;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.*;

/**
 * The {@test CountryQueriesTest} contains java unit tests and integration tests along with the mock environment needed to run them
 * Junit tests for format output on various data scenarios
 */
public class CountryQueriesTest {

    // mocks objects for simulationg database interactions
    @Mock
    private DatabaseConnection mockDatabaseConnection;

    @Mock
    private Connection mockConnection;

    @Mock
    private PreparedStatement mockPreparedStatement;

    @Mock
    private ResultSet mockResultSet;

    /** initialises mock objects before each test
     * setup of mock database connection and query
    **/
    @BeforeEach
    void setup() throws SQLException {

        // initialises mock objects
        MockitoAnnotations.openMocks(this);

        // behaviours for mock db connection and prepared statement
        when(mockDatabaseConnection.getCon()).thenReturn(mockConnection);
        when(mockConnection.prepareStatement(anyString())).thenReturn(mockPreparedStatement);
        when(mockPreparedStatement.executeQuery()).thenReturn(mockResultSet);
    }
    @Test
    void testCountriesInTheWorld() throws SQLException {
        // Mock ResultSet behavior for test verification
        when(mockResultSet.next()).thenReturn(true).thenReturn(false);
        when(mockResultSet.getString("Name")).thenReturn("CountryName");

        // Call the method to test
        String result = CountryQueries.CountriesInTheWorld(mockDatabaseConnection);

        // Verify that a query containing essential parts of the expected SQL was executed
        verify(mockConnection).prepareStatement(contains("SELECT Code, Name, Continent, Region, Population, (SELECT Name FROM city WHERE ID = country.Capital) AS `Capital` FROM country ORDER BY Population DESC "));
        verify(mockPreparedStatement).executeQuery();

        // Assert the expected output format, assuming "Expected formatted output" represents correct formatting
        assertEquals("Expected formatted output", result);
    }

}
