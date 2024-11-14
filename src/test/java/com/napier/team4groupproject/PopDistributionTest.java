package com.napier.team4groupproject;

import org.junit.jupiter.api.BeforeAll;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.lang.reflect.InvocationTargetException;

import static org.junit.jupiter.api.Assertions.fail;

public class PopDistributionTest {
    private static DatabaseConnection worldDB;
    private static DatabaseConnection nullDB;
    private static ByteArrayOutputStream output;

    // Example inputs for testing
    private static String exampleContinent;
    private static String exampleRegion;
    private static String exampleCountry;
    private static String exampleWhere;

    @BeforeAll
    public static void setUp() {
        worldDB = new DatabaseConnection();
        nullDB = new DatabaseConnection();

        worldDB.connect("localhost:33060", 10000);

        output = new ByteArrayOutputStream();
        System.setOut(new PrintStream(output));

        exampleContinent = "Europe";
        exampleRegion = "Western Europe";
        exampleCountry = "Germany";

        exampleWhere = "Asia";
    }

    public static String callPopulationDistributionQuery(DatabaseConnection sql, String attribute, String where, Integer limit) {
        try {
            return (String) Utilities.callPrivateMethod(
                    CapitalQueries.class,
                    null,
                    "capitalQueries",
                    new Class<?>[]{DatabaseConnection.class, String.class, String.class, Integer.class},
                    sql, attribute, where, limit
            );
        } catch (InvocationTargetException e) {
            throw new RuntimeException("Invocation error: " + e.getTargetException().getMessage(), e);
        }
    }


}
