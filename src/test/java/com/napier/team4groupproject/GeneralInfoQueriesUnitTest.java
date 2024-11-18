package com.napier.team4groupproject;

import org.junit.jupiter.api.*;
import org.mockito.MockedStatic;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.sql.*;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class GeneralInfoQueriesUnitTest {

    private final PrintStream printStream = System.out;
    private final ByteArrayOutputStream outputStream = new ByteArrayOutputStream();

    // mocks
    static DatabaseConnection databaseConnection;
    static ResultSet resultSet;
    static ResultSetMetaData metaData;
    static PreparedStatement preparedStatement;
    static App app;
    static Connection mockConnection;

    @BeforeAll
    public static void initialize() {
        databaseConnection = mock(DatabaseConnection.class);
        resultSet = mock(ResultSet.class);
        metaData = mock(ResultSetMetaData.class);
        preparedStatement = mock(PreparedStatement.class);
        app = mock(App.class);
        mockConnection = mock(Connection.class);

    }

    @BeforeEach
    public void setUp() {
        //System.setOut(new PrintStream(outputStream));
    }

    @AfterEach
    public void restoreSystemOut() {
      //  System.setOut(printStream);  // Restore the original System.out
    }

    @Test
    public void testGetPopulationOfTheWorlds() throws SQLException {
        String query =  "SELECT SUM(Population) FROM country";

        // mocks the connection to the database
        when(databaseConnection.getCon()).thenReturn(mockConnection);
        // mocks the prepared statement
        when(mockConnection.prepareStatement(query)).thenReturn(preparedStatement);
        when(preparedStatement.executeQuery()).thenReturn(resultSet);
        when(resultSet.getMetaData()).thenReturn(metaData);
        when(metaData.getColumnLabel(1)).thenReturn("Population");


        String result = GeneralInfoQueries.populationOfTheWorld(databaseConnection);

        assertTrue(result.contains("Population"));

        // checks if the output contains the population
        //System.out.println(GeneralInfoQueries.populationOfTheWorld(databaseConnection));
        //assertTrue(outputStream.toString().contains("6078749450"));

    }



    // unit tests
    @Test
    public void testGetPopulationOfTheWorld() throws SQLException {
        String query =  "SELECT SUM(Population) FROM country";

        // mocks the connection to the database
        when(databaseConnection.getCon()).thenReturn(mockConnection);
        // mocks the prepared statement
        when(mockConnection.prepareStatement(query)).thenReturn(preparedStatement);
        // mocks the execution of the query and returning of result set
        when(preparedStatement.executeQuery()).thenReturn(resultSet);

        //
        when(resultSet.getMetaData()).thenReturn(metaData);
        when(metaData.getColumnLabel(1)).thenReturn("Population");

        when(resultSet.next()).thenReturn(true).thenReturn(false);
        // sets result set to contain the world population
        when(resultSet.getString(1)).thenReturn("6078749450");
        //System.out.println(resultSet.getString(1));

        // mocks static method
        try (MockedStatic<App> mockedApp = mockStatic(App.class)) {
            mockedApp.when(() -> App.FormatOutput(resultSet)).thenReturn("6078749450");
        }

        String result = GeneralInfoQueries.populationOfTheWorld(databaseConnection);

        assertTrue(result != null && result.contains("6078749450"));

        // checks if the output contains the population
        //System.out.println(GeneralInfoQueries.populationOfTheWorld(databaseConnection));
        //assertTrue(outputStream.toString().contains("6078749450"));

    }

    /*
    @Test
    public void testGetPopulationOfTheWorldDatabaseNull() throws SQLException {
        GeneralInfoQueries.populationOfTheWorld(null);

    }

    @Test
    public void testGetPopulationOfAContinent() throws SQLException {
        String query =  "SELECT SUM(Population) AS Population FROM country WHERE Continent = 'Asia'";

        // mocks the connection to the database
        when(databaseConnection.getCon()).thenReturn(mockConnection);
        // mocks the prepared statement
        when(mockConnection.prepareStatement(query)).thenReturn(preparedStatement);

        // mocks the execution of the query and returning of result set
        when(preparedStatement.executeQuery()).thenReturn(resultSet);


        when(resultSet.getMetaData()).thenReturn(metaData);
        when(metaData.getColumnLabel(1)).thenReturn("Population");

        when(resultSet.next()).thenReturn(true).thenReturn(false);
        // sets result set to contain the continent population
        when(resultSet.getString(1)).thenReturn("3705025700");


        // mocks static method
        try (MockedStatic<App> mockedApp = mockStatic(App.class)) {
            mockedApp.when(() -> App.FormatOutput(resultSet)).thenReturn("3705025700");
        }


        // checks if the output contains the population
        System.out.println(GeneralInfoQueries.populationOfAContinent(databaseConnection, "Asia"));


        assertTrue(outputStream.toString().contains("3705025700"));


    }

    @Test
    public void testGetPopulationOfARegion() throws SQLException {
        String query =  "SELECT SUM(Population) AS Population FROM country WHERE Region = 'Caribbean'";

        // mocks the connection to the database
        when(databaseConnection.getCon()).thenReturn(mockConnection);
        // mocks the prepared statement
        when(mockConnection.prepareStatement(query)).thenReturn(preparedStatement);
        // mocks the execution of the query and returning of result set
        when(preparedStatement.executeQuery()).thenReturn(resultSet);


        when(resultSet.getMetaData()).thenReturn(metaData);
        when(metaData.getColumnLabel(1)).thenReturn("Population");

        when(resultSet.next()).thenReturn(true).thenReturn(false);
        // sets result set to contain the region population
        when(resultSet.getString(1)).thenReturn("38140000");
        System.out.println(resultSet.getString(1));

        // mocks static method
        try (MockedStatic<App> mockedApp = mockStatic(App.class)) {
            mockedApp.when(() -> App.FormatOutput(resultSet)).thenReturn("38140000n");
        }


        // checks if the output contains the population
        System.out.println(GeneralInfoQueries.populationOfARegion(databaseConnection, "Caribbean"));


        assertTrue(outputStream.toString().contains("38140000"));


    }


    @Test
    public void testGetPopulationOfACountry() throws SQLException {
        String query =  "SELECT Population FROM country WHERE Name = 'Afghanistan'";

        // mocks the connection to the database
        when(databaseConnection.getCon()).thenReturn(mockConnection);
        // mocks the prepared statement
        when(mockConnection.prepareStatement(query)).thenReturn(preparedStatement);
        // mocks the execution of the query and returning of result set
        when(preparedStatement.executeQuery()).thenReturn(resultSet);


        when(resultSet.getMetaData()).thenReturn(metaData);
        when(metaData.getColumnLabel(1)).thenReturn("Population");

        when(resultSet.next()).thenReturn(true).thenReturn(false);
        // sets result set to contain the country population
        when(resultSet.getString(1)).thenReturn("22720000");


        // mocks static method
        try (MockedStatic<App> mockedApp = mockStatic(App.class)) {
            mockedApp.when(() -> App.FormatOutput(resultSet)).thenReturn("22720000");
        }


        // checks if the output contains the population
        System.out.println(GeneralInfoQueries.populationOfACountry(databaseConnection, "Afghanistan"));


        assertTrue(outputStream.toString().contains("22720000"));


    }


    @Test
    public void testGetPopulationOfADistrict() throws SQLException {
        String query =  "SELECT SUM(Population) AS Population FROM city WHERE District = 'Kabol'";

        // mocks the connection to the database
        when(databaseConnection.getCon()).thenReturn(mockConnection);
        // mocks the prepared statement
        when(mockConnection.prepareStatement(query)).thenReturn(preparedStatement);
        // mocks the execution of the query and returning of result set
        when(preparedStatement.executeQuery()).thenReturn(resultSet);


        when(resultSet.getMetaData()).thenReturn(metaData);
        when(metaData.getColumnLabel(1)).thenReturn("Population");

        when(resultSet.next()).thenReturn(true).thenReturn(false);
        // sets result set to contain the district population
        when(resultSet.getString(1)).thenReturn("1780000");


        // mocks static method
        try (MockedStatic<App> mockedApp = mockStatic(App.class)) {
            mockedApp.when(() -> App.FormatOutput(resultSet)).thenReturn("1780000");
        }


        // checks if the output contains the population
        System.out.println(GeneralInfoQueries.populationOfADistrict(databaseConnection, "Kabol"));


        assertTrue(outputStream.toString().contains("1780000"));


    }


    @Test
    public void testGetPopulationOfACity() throws SQLException {
        String query =  "SELECT Population FROM city WHERE Name = 'Escobar'";

        // mocks the connection to the database
        when(databaseConnection.getCon()).thenReturn(mockConnection);
        // mocks the prepared statement
        when(mockConnection.prepareStatement(query)).thenReturn(preparedStatement);
        // mocks the execution of the query and returning of result set
        when(preparedStatement.executeQuery()).thenReturn(resultSet);


        when(resultSet.getMetaData()).thenReturn(metaData);
        when(metaData.getColumnLabel(1)).thenReturn("Population");

        when(resultSet.next()).thenReturn(true).thenReturn(false);
        // sets result set to contain the city population
        when(resultSet.getString(1)).thenReturn("116675");

        // mocks static method
        try (MockedStatic<App> mockedApp = mockStatic(App.class)) {
            mockedApp.when(() -> App.FormatOutput(resultSet)).thenReturn("116675");
        }


        // checks if the output contains the population
        System.out.println(GeneralInfoQueries.populationOfACity(databaseConnection, "Escobar"));


        assertTrue(outputStream.toString().contains("116675"));

    }

*/
}
