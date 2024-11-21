package com.napier.team4groupproject;


import java.util.Arrays;
import java.util.List;

/**
 * The {@code InputValidation} class contains methods to validate user inputs both integers and strings
 */
public class InputValidation {
    /**
     * validates user input for queries by carrying out
     * various actions and checks
     * @param userInput takes user defined input
     * @return returns user input or an error message if a validation step fails
     */
    public static String validateStringInput(String userInput) {

        if (userInput != null) {
            //Trims whitespace
            userInput = userInput.trim();


            //Checks if the input is empty
            if (userInput.isEmpty()) {
                userInput = "Field cannot be empty";
                return userInput;
            }

            //Checks if the input is > 50 characters
            else if (userInput.length() > 50) {
                userInput = "Field cannot be over 50 characters.";
                return userInput;
            }
        } else {
            userInput = "Field cannot be empty";
        }

        //Returns user input
        return userInput;
    }

        /**
         * validates user input for queries by carrying out
         * various actions and checks
         * @param userInput takes user defined input
         * @return returns error message if a validation step fails
         */
    public static String validateIntInput(String userInput) {

        int parsedInput;

        if (userInput != null) {
            //Trims whitespace
            userInput = userInput.trim();

            //Checks if the input is empty
            if (userInput.isEmpty()) {
                System.out.println("Please enter a valid number");
                return userInput;
            }

            // attempts to parse the value in the string, if the check fails
            // returns an error message to the user
            try {
                parsedInput = Integer.parseInt(userInput);
            } catch (Exception e) {
                // catches wrong input, i.e. characters and
                userInput = "Please enter a valid number";
                return userInput;
            }

            //Checks if the input is less than 0
            if (parsedInput <= 0) {
                userInput = "Sorry please choose a valid number, numbers cannot be negative or 0";
                return userInput;
            }
        } else {
            userInput = "Please enter a valid number";
        }

        // Returns user input
        return userInput;
    }

    /**
     * validates user input to list of expected column names
     * changes input to lower case for matching purposes
     * @param attribute takes in user defined input
     * throws illegal Argument exception
     */
    public static void validateAttribute(String attribute) {
        List<String> validAttributes = Arrays.asList("continent", "region", "country","district", "city" );
        if (attribute != null && !validAttributes.contains(attribute.toLowerCase())) {
            throw new IllegalArgumentException("Invalid attribute provided: " + attribute);
        }
    }
}
