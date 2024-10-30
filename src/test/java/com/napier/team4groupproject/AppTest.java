package com.napier.team4groupproject;

import org.junit.jupiter.api.*;
import static org.junit.jupiter.api.Assertions.*;

import java.sql.SQLException;

public class AppTest {
    @BeforeAll
    public static void init(){
        System.setProperty("Environment", "UnitTest");
    }

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
}
