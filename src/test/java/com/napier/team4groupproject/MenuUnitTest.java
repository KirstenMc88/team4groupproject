package com.napier.team4groupproject;

import org.junit.jupiter.api.*;

import java.io.*;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.fail;

/**
 * Unit tests for Menu class
 *
 * <p>This class contains unit tests using JUnit to ensure methods in the Menu class work as expected.</p>
 */
public class MenuUnitTest {
    private static DatabaseConnection nullDB;
    private static String output;
    private ByteArrayOutputStream printOutput;
    private ByteArrayInputStream fakeInput;


    /**
     * Setting up environment
     *
     * <p>This method sets up the test environment by setting a System property 'Environment' to 'UnitTest'.
     * This allows certain things in the App class to only be executed in a certain environment.</p>
     */
    @BeforeAll
    public static void setUpEnvironment() {
        System.setProperty("Environment", "UnitTest");
        nullDB = new DatabaseConnection();
    }


    @BeforeEach
    public void setUp() {
        printOutput = new ByteArrayOutputStream();
        System.setOut(new PrintStream(printOutput));
    }

    @AfterEach
    public void tearDown() {
        printOutput.reset();
        System.setIn(System.in);
    }

    private void fakeUserInput(String input) {
        fakeInput = new ByteArrayInputStream(input.getBytes());
        System.setIn(fakeInput);
    }

    @Test
    public void mainMenu_selection0(){
        try{
            fakeUserInput("0");
            Menu.mainMenu(nullDB);

            assertTrue(printOutput.toString().contains("------Main Menu------"));
            System.err.println(printOutput.toString());

        } catch (Exception e){
            fail("Unexpected exception: " + e.getMessage());
        }
    }

    @Test
    public void mainMenu_selection1(){
        try{
            fakeUserInput("1\n0\n0");
            Menu.mainMenu(nullDB);

            assertTrue(printOutput.toString().contains("------Country Reports------"));
            System.err.println(printOutput.toString());

        } catch (Exception e){
            fail("Unexpected exception: " + e.getMessage());
        }
    }

    @Test
    public void mainMenu_selection2(){
        try{
            fakeUserInput("2\n0\n0");
            Menu.mainMenu(nullDB);

            assertTrue(printOutput.toString().contains("------City Reports------"));
            System.err.println(printOutput.toString());

        } catch (Exception e){
            fail("Unexpected exception: " + e.getMessage());
        }
    }

    @Test
    public void mainMenu_selection3(){
        try{
            fakeUserInput("3\n0\n0");
            Menu.mainMenu(nullDB);

            assertTrue(printOutput.toString().contains("------Capital City Reports------"));
            System.err.println(printOutput.toString());

        } catch (Exception e){
            fail("Unexpected exception: " + e.getMessage());
        }
    }

    @Test
    public void mainMenu_selection4(){
        try{
            fakeUserInput("4\n0\n0");
            Menu.mainMenu(nullDB);

            assertTrue(printOutput.toString().contains("------Population Analysis Reports------"));
            System.err.println(printOutput.toString());

        } catch (Exception e){
            fail("Unexpected exception: " + e.getMessage());
        }
    }

    @Test
    public void mainMenu_selection5(){
        try{
            fakeUserInput("5\n0\n0");
            Menu.mainMenu(nullDB);

            assertTrue(printOutput.toString().contains("------General Information------"));
            System.err.println(printOutput.toString());

        } catch (Exception e){
            fail("Unexpected exception: " + e.getMessage());
        }
    }

    @Test
    public void mainMenu_selection6(){
        try{
            fakeUserInput("6\n0\n0");
            Menu.mainMenu(nullDB);

            assertTrue(printOutput.toString().contains("------Language Report------"));
            System.err.println(printOutput.toString());

        } catch (Exception e){
            fail("Unexpected exception: " + e.getMessage());
        }
    }

    @Test
    public void mainMenu_selectionInvalid(){
        try{
            fakeUserInput("7\n0");
            Menu.mainMenu(nullDB);

            assertTrue(printOutput.toString().contains("Invalid choice. Please enter a valid number."));
            System.err.println(printOutput.toString());

        } catch (Exception e){
            fail("Unexpected exception: " + e.getMessage());
        }
    }
}
