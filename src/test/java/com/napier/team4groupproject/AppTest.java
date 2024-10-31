package com.napier.team4groupproject;

import org.junit.jupiter.api.*;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import java.sql.SQLException;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;

public class AppTest {
    private static ResultSet resultSet;

    @BeforeAll
    public static void init(){
        System.setProperty("Environment", "UnitTest");
    }

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

    // main method tests

    @Test
    public void main_zeroStringArgs() {
        String[] args = new String[0];

        try{
            App.main(args);
        } catch (SQLException e) {
            // SQLException is unrelated to this test
        }

        assertEquals("localhost:33060", App.databaseLocation);
        assertEquals(30000, App.databaseDelay);
    }

    @Test
    public void main_oneStringArgs() {
        String[] args = new String[1];
        args[0] = "test";

        try{
            App.main(args);
        } catch (SQLException e) {
            // SQLException is unrelated to this test
        }

        assertEquals("localhost:33060", App.databaseLocation);
        assertEquals(30000, App.databaseDelay);
    }

    @Test
    public void main_twoStringArgs() {
        String[] args = new String[2];
        args[0] = "world-db:3306";
        args[1] = "10000";

        try{
            App.main(args);
        } catch (SQLException e) {
            // SQLException is unrelated to this test
        }

        assertEquals("world-db:3306", App.databaseLocation);
        assertEquals(10000, App.databaseDelay);
    }

    // FormatOutput method tests

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

    @Test
    public void FormatOutput_oneRow(){
        String result = App.FormatOutput(resultSet);

        assertEquals(String.format("%-20s%-23s\n%-20s%-23s\n", "Test Column 1", "Test Column 2", "Test Row 1 Content", "Long Test Row 1 Content"), result);
    }

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

        assertEquals(String.format("%-20s%-23s\n%-20s%-23s\n%-20s%-23s\n", "Test Column 1", "Test Column 2", "Test Row 1 Content", "Long Test Row 1 Content", "Test Row 2 Content", "Long Test Row 2 Content"), result);
    }

    @Test
    public void FormatOutput_columnWidthUnder20Header(){
        String result = App.FormatOutput(resultSet);

        assertTrue(result.contains(String.format("%-20s", "Test Column 1")));
    }

    @Test
    public void FormatOutput_columnWidthUnder20Row(){
        String result = App.FormatOutput(resultSet);

        assertTrue(result.contains(String.format("%-20s", "Test Row 1 Content")));
    }

    @Test
    public void FormatOutput_columnWidthOver20Header(){
        String result = App.FormatOutput(resultSet);

        assertTrue(result.contains(String.format("%-23s", "Test Column 2")));
    }

    @Test
    public void FormatOutput_columnWidthOver20Row(){
        String result = App.FormatOutput(resultSet);

        assertTrue(result.contains(String.format("%-23s", "Long Test Row 1 Content")));
    }
}
