package com.napier.team4groupproject;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.lang.reflect.Method;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.fail;

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

    // example null inputs
    private static String nullAttribute = null;
    private static String nullWhere = null;
    private static Integer nullLimit = null;

    // reference to the private method
    private static Method capitalQueriesMethod;

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

        try {
            capitalQueriesMethod = Utilities.getAccessibleMethod(
                    CapitalQueries.class,
                    "capitalQueries",
                    DatabaseConnection.class,
                    String.class,
                    String.class,
                    Integer.class
            );
        } catch (NoSuchMethodException e) {
            fail("Failed to access the capitalQueries method: " + e.getMessage());
        }
    }

    @Test
    public void testCapitalQueries_noFiltersNoLimits() {
        try {
            String result = (String) capitalQueriesMethod.invoke(null, worldDB, nullAttribute, nullWhere, nullLimit);
            assertNotNull(result, "The result should not be null for a valid connection with no filters or limits.");
        } catch (Exception e) {
            fail("The test should not have thrown an exception" + e.getMessage());
        }
    }
}
