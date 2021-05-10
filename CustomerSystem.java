/*
 * Date: May 5, 2021
 * Names: Naomi Mezheritsky and Murphy Lee
 * Teacher: Mr. Ho
 * Description: A customer sales information that makes use of Benford's Law and Java APIs
 * */

import javafx.application.Application;
import javafx.stage.Stage;

import java.io.FileNotFoundException;
import java.util.Scanner;

class CustomerSystem extends Application {
    public static void main(String[] args) throws FileNotFoundException{
        String userChoice;              // Which menu option the user has chosen
        boolean keepGoing = true;       // Ensures that the while loop runs at least once
        String getSalesOption = "1";    // If the user wants the data from CSV file
        String checkSalesOption = "2";  // If the user wants to check the sales data for digit frequency
        String quitOption = "3";        // If the user wants to exit the program 

        // Instantiate Scanner
        Scanner reader = new Scanner(System.in);

        // Loop through user options as long as the user wants to keep going
        while (keepGoing == true) {
            // Print user options
            printMenu();
            userChoice = reader.nextLine();
            
            // Prompt the user for re-input as long as their choice is invalid (not a number from 1 - 3)
            while (!(userChoice.equals(getSalesOption) || userChoice.equals(checkSalesOption) || userChoice.equals(quitOption))) {
                System.out.println("Invalid input. Please enter a number from the options menu: ");
                userChoice = reader.nextLine();
            }

            // If the user entered 1, read the sales data

            // If the user entered 2, 

            // If the user entered 3, set the keepGoing variable to false

        }

        reader.close();
    }

    /* 
     * Description: 
     * 
     * @author - 
     * 
     * */
    public static void printMenu() {

    }

    /*
     * Description:
     * 
     * @author - Naomi Mezheritsky
     * */
    public static void getSalesData() {

    }

    /*
     * Description:
     * 
     * @author - Murphy Lee
     * @param scanner - The Scanner used to read from the file
     * @throws FileNotFoundException - Exception raised when the program tries to read the CSV, thrown to main
     * */
    public static void checkSales(Scanner scanner) throws FileNotFoundException {

        int totalDigits = 0;            // Accumulator variable to help calculate frequency
        int[] firstDigits = new int[9];   // Stores the number of occurences of each first digit

        // Read the sales column of the CSV file, parsing the first digit and storing in an array
        

        // Calculate the frequency of each first digit

        // Store frequencies in an array

        // Display to terminal whether fraud occured

        // Return array
    }

    /*
     * Description:
     * 
     * @author - Murphy Lee
     * @throws FileNotFoundException - Exception raised when the program tries to read the CSV, thrown to checkSales()
     * */
    public static void countFirstDigit(int arr[] ) {

    }

    /*
        * Description:
        * 
        * @author - Murphy Lee
        * 
        * */
    @Override
    public void start(Stage arg0) throws Exception {
        
    }

    
}