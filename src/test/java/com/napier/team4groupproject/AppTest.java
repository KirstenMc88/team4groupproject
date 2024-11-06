package com.napier.team4groupproject;

import org.junit.jupiter.api.*;

import java.sql.*;

import static java.lang.Integer.parseInt;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class AppTest {
    private static ResultSet resultSet;
    private static final Object defaultDatabaseLocation = Utilities.getPrivateField(App.class, null,"databaseLocation");
    private static final Object defaultDatabaseDelay = Utilities.getPrivateField(App.class, null,"databaseDelay");


    /**
     * Initialising environment
     *
     * <p>This method sets up the test environment by setting a System property 'Environment' to 'UnitTest'.
     * This allows certain things in the App class to only be executed in a certain environment.</p>
     */
    @BeforeAll
    public static void init(){
        System.setProperty("Environment", "UnitTest");
    }

    /**
     * Setting up mocks
     *
     * <p>This method sets up mocks for ResultSet and ResultSetMetaData to test with.</p>
     */
    @BeforeEach
    public void setUpMocks(){
        resultSet = mock(ResultSet.class);
        ResultSetMetaData metaData = mock(ResultSetMetaData.class);

        try{
            when(resultSet.getMetaData()).thenReturn(metaData);

            when(resultSet.next()).thenReturn(true).thenReturn(false);

            when(metaData.getColumnCount()).thenReturn(2);

            when(metaData.getColumnDisplaySize(1)).thenReturn(18);
            when(metaData.getColumnDisplaySize(2)).thenReturn(23);

            when(metaData.getColumnLabel(1)).thenReturn("Test Column 1");
            when(metaData.getColumnLabel(2)).thenReturn("Test Column 2");

            when(resultSet.getString(1)).thenReturn("Test Row 1 Content");
            when(resultSet.getString(2)).thenReturn("Long Test Row 1 Content");

        } catch (SQLException e) {
            // SQLException is unrelated to this test
        }
    }

    /**
     * Reset method
     *
     * <p>This method resets the values for databaseLocation and databaseDelay between each test.</p>
     */
    @BeforeEach
    public void reset(){
        Utilities.setPrivateField(App.class, null,"databaseLocation", defaultDatabaseLocation);
        Utilities.setPrivateField(App.class, null,"databaseDelay", defaultDatabaseDelay);
    }


    /**
     * Main method tests
     *
     * <p>This method is used to test the logic of the main method of App.
     * It ensures that the passed arguments are correctly used. It is used by multiple unit tests which pass a
     * different number of arguments for each test.</p>
     *
     * @param args arguments which are passed onto the App main, containing test data
     */
    private void main(String [] args){
        try{
            App.main(args);
        } catch (SQLException e) {
            // SQLException is unrelated to this test
        }

        // assert if the right values were assigned
        if (args.length <= 1) {
            assertEquals(defaultDatabaseLocation, Utilities.getPrivateField(App.class, null,"databaseLocation"));
            assertEquals(defaultDatabaseDelay, Utilities.getPrivateField(App.class, null,"databaseDelay"));

        } else {
            assertEquals(args[0], Utilities.getPrivateField(App.class, null,"databaseLocation"));
            assertEquals(parseInt(args[1]), Utilities.getPrivateField(App.class, null,"databaseDelay"));
        }
    }

    /**
     * Main method with zero arguments being passed
     *
     * <p>Checks if databaseLocation and databaseDelay are still the default.</p>
     */
    @Test
    public void main_zeroStringArgs() {
        String[] args = new String[0];

        main(args);
    }

    /**
     * Main method with one argument being passed
     *
     * <p>Checks if databaseLocation and databaseDelay are still the default.</p>
     */
    @Test
    public void main_oneStringArgs() {
        String[] args = new String[1];
        args[0] = "test";

        main(args);
    }

    /**
     * Main method with two arguments being passed
     *
     * <p>Checks if databaseLocation and databaseDelay have been set to the passed values.</p>
     */
    @Test
    public void main_twoStringArgs() {
        String[] args = new String[2];
        args[0] = "world-db:3306";
        args[1] = "10000";

        main(args);
    }


    // FormatOutput method tests

    /**
     * Format zero rows correctly
     *
     * <p>Checks if defined message is displayed.</p>
     */
    @Test
    public void FormatOutput_rowCountZero(){
        try{
            when(resultSet.next()).thenReturn(false);
        } catch (SQLException e) {
            // SQLException is unrelated to this test
        }

        String result = App.FormatOutput(resultSet);

        assertEquals("No matching data found. Please check your spelling and try again.", result);
    }

    /**
     * Format one row correctly
     *
     * <p>Checks if header and row have been formatted correctly.</p>
     */
    @Test
    public void FormatOutput_oneRow(){
        String result = App.FormatOutput(resultSet);

        assertEquals(String.format("\n%-20s%-23s\n%-20s%-23s\n", "Test Column 1", "Test Column 2", "Test Row 1 Content", "Long Test Row 1 Content"), result);
    }

    /**
     * Format two rows correctly
     *
     * <p>Checks if header and rows have been formatted correctly.</p>
     */
    @Test
    public void FormatOutput_twoRows(){
        try {
            when(resultSet.next()).thenReturn(true).thenReturn(true).thenReturn(false);

            when(resultSet.getString(1)).thenReturn("Test Row 1 Content").thenReturn("Test Row 2 Content");
            when(resultSet.getString(2)).thenReturn("Long Test Row 1 Content").thenReturn("Long Test Row 2 Content");
        } catch (SQLException e) {
            // SQLException is unrelated to this test
        }

        String result = App.FormatOutput(resultSet);

        assertEquals(String.format("\n%-20s%-23s\n%-20s%-23s\n%-20s%-23s\n", "Test Column 1", "Test Column 2", "Test Row 1 Content", "Long Test Row 1 Content", "Test Row 2 Content", "Long Test Row 2 Content"), result);
    }

    /**
     * Format narrow column header
     *
     * <p>Checks if narrow column header is formatted correctly as 20 char wide.</p>
     */
    @Test
    public void FormatOutput_columnWidthUnder20Header(){
        String result = App.FormatOutput(resultSet);

        assertTrue(result.contains(String.format("%-20s", "Test Column 1")));
    }

    /**
     * Format narrow column row
     *
     * <p>Checks if narrow column header is formatted correctly as 20 char wide.</p>
     */
    @Test
    public void FormatOutput_columnWidthUnder20Row(){
        String result = App.FormatOutput(resultSet);

        assertTrue(result.contains(String.format("%-20s", "Test Row 1 Content")));
    }

    /**
     * Format wide column header
     *
     * <p>Checks if wide column header is formatted correctly as its number of char long.</p>
     */
    @Test
    public void FormatOutput_columnWidthOver20Header(){
        String result = App.FormatOutput(resultSet);

        assertTrue(result.contains(String.format("%-23s", "Test Column 2")));
    }

    /**
     * Format wide column row
     *
     * <p>Checks if wide column row is formatted correctly as its number of char long.</p>
     */
    @Test
    public void FormatOutput_columnWidthOver20Row(){
        String result = App.FormatOutput(resultSet);

        assertTrue(result.contains(String.format("%-23s", "Long Test Row 1 Content")));
    }
}
