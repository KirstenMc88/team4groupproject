package com.napier.team4groupproject;

import org.junit.jupiter.api.*;
import static org.junit.jupiter.api.Assertions.*;

/**
 * The {@test InputValidation} contains java unit tests to test that it handles outputs correctly
 */

public class InputValidationTest {

    InputValidation inputValidation = new InputValidation();

    // Tests for validateStringInput testing strings
    @Test //Test valid string
    public void testValidateStringInput_ValidInput() {
        String userInput = "Valid Input";
        String result = inputValidation.validateStringInput(userInput);
        assertEquals("Valid Input", result);
    }

    @Test //Tests spaces string inpiut
    public void testValidateStringInput_EmptyInput() {
        String userInput = " ";
        String result = inputValidation.validateStringInput(userInput);
        assertEquals("", result);  // expecting empty string due to validation
    }

    @Test // Tests string
    public void testValidateStringInput_TooLongInput() {
        String userInput = "This input is definitely more than fifty characters, exceeding the limit.";
        String result = inputValidation.validateStringInput(userInput);
        assertEquals("", result);  // expecting empty string due to length validation
    }

    @Test
    public void testValidateStringInput_WhitespaceInput() {
        String userInput = "    Valid Input   ";
        String result = inputValidation.validateStringInput(userInput);
        assertEquals("Valid Input", result);  // leading and trailing whitespace should be trimmed
    }

    // Tests for validateIntInput testing integers
    @Test //Tests valid int input
    public void testValidateIntInput_ValidInput() {
        String userInput = "150";
        String result = inputValidation.validateIntInput(userInput);
        assertEquals("150", result);  // valid input
    }

    @Test //Tests whitespace input
    public void testValidateIntInput_EmptyInput() {
        String userInput = " ";
        String result = inputValidation.validateIntInput(userInput);
        assertEquals("", result);  // expecting empty string due to validation
    }

    @Test // Tests characters input
    public void testValidateIntInput_NonNumericInput() {
        String userInput = "abc";
        String result = inputValidation.validateIntInput(userInput);
        assertEquals("", result);  // expecting empty string due to non-numeric input
    }

    @Test //Test negative integer
    public void testValidateIntInput_NegativeNumber() {
        String userInput = "-3";
        String result = inputValidation.validateIntInput(userInput);
        assertEquals("", result);  // Expecting empty string due to negative number
    }


}


