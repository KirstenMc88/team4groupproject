package com.napier.team4groupproject;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

public class CapitalQueriesTest {
    private static DatabaseConnection worldDB; // db connection with actual data
    private static DatabaseConnection nullDB; // empty db connection
    private static ByteArrayOutputStream output; // for capturing system output

    // example inputs
    private static String exampleContinent;
    private static String exampleRegion;
    private static String exampleWhere;
    private static Integer exampleLimit;
    private static Integer tooHighLimit;
    private static String exampleInvalid;

    @BeforeAll
    public static void setUp() {
        worldDB = new DatabaseConnection();
        nullDB = new DatabaseConnection();

        worldDB.connect("localhost:33060", 10000);

        output = new ByteArrayOutputStream();
        System.setOut(new PrintStream(output));

        exampleContinent = "Europe";
        exampleRegion = "Western Europe";
        exampleLimit = 5;
        tooHighLimit = 1000;
        exampleInvalid = "Invalid";
    }
}
