package com.napier.team4groupproject;

public class InputValidation {
    /**
     * validates user input for queries by carrying out
     * various actions and checks
     * @param userInput takes user defined input
     * @return returns user input after validation steps
     */
    public String validateStringInput(String userInput) {

         //Trims whitespace
        userInput = userInput.trim();


         //Checks if the input is empty
        if (userInput.isEmpty()) {
            System.out.println("Field cannot be empty");
            return "";
        }

         //Checks if the input is > 50 characters
        else if (userInput.length() > 50) {
            System.out.println("Field cannot be over 50 characters.");
            return "";
        }

        //Returns user input
        return userInput;
    }


    /**
     * validates user input for queries by carrying out
     * various actions and checks
     * @param userInput takes user defined input
     * @return returns user input after validation steps
     */
    public String validateIntInput(String userInput) {


        //Trims whitespace
        userInput = userInput.trim();
        int parsedInput;


        //Checks if the input is empty
        if (userInput.isEmpty()) {
            System.out.println("Field cannot be empty");
            return "";
        }

        // attempts to parse the value in the string, if the check fails
        // returns an error message to the user
        try {
            parsedInput = Integer.parseInt(userInput);
        } catch (Exception e) {
            // catches wrong input, i.e. characters and
            System.out.println("Please enter a valid number");
            return "";
        }


         //Checks if the input is > 3 characters or if parsed input is over 200
        if (parsedInput > 200 || userInput.length() > 3) {
            System.out.println("Sorry please choose a number between 1 - 200");
            return "";
        }

        // Returns user input
        return userInput;
    }


    /*
    // string
    // need to add scanner name in the function call in the menu
    // in Kane's menu it is called 'input'
    query.AllCountriesInAContinent(input);

    // function
    public void AllCountriesInAContinent(Scanner input) {

		InputValidation validate = new InputValidation();
		String userInput = "";

		while(userInput == "") {
		System.out.println("Which continent population data would you like to view?");
		userInput = validate.validateStringInput(input.nextLine());
		}

		// test to confirm data validation
		// replace with prepared statement
		System.out.println("Validated Input " + userInput);

	}
     */

    /*
    // int
    // need to add scanner name in the function call in the menu
    // in Kane's menu it is called 'input'
    query.topXCountries(input);

    // function
    public void topXCountries(Scanner input) {

		InputValidation validate = new InputValidation();
		String userInput = "";
		int queryValue;

		while(userInput == "") {
			System.out.println("How many countries would you like to view?");
			userInput = validate.validateIntInput(input.nextLine());
		}
		// converts userInput to an int after validation
		queryValue = Integer.parseInt(userInput);

		// test to confirm data validation
		// replace with prepared statement
		System.out.println("Validated Input " + queryValue);

	}
     */




}
