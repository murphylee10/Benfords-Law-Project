/*
 * Date: May 5, 2021
 * Names: Naomi Mezheritsky and Murphy Lee
 * Teacher: Mr. Ho
 * Description: A customer sales information that makes use of Benford's Law and Java APIs
 * */

import javafx.application.Application;
import javafx.stage.Stage;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

class CustomerSystem {
    public static void main(String[] args) throws FileNotFoundException{
        String userChoice;              // Which menu option the user has chosen
        boolean keepGoing = true;       // Ensures that the while loop runs at least once
        String getSalesOption = "1";    // If the user wants the data from CSV file
        String checkSalesOption = "2";  // If the user wants to check the sales data for digit frequency
        String quitOption = "3";        // If the user wants to exit the program 

        // Instantiate Scanner
        Scanner reader = new Scanner(System.in);

        checkSales(reader);

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
    public static double[] checkSales(Scanner scanner) throws FileNotFoundException {
        int totalDigits = 0;            // Accumulator variable to help calculate frequency
        int[] firstDigits = new int[9];   // Stores the number of occurences of each first  
        double[] digitFrequency = new double[9];
        String fileName = "sales.csv";    // The file name of the CSV
        String line;                      // Stores each line of the file
        Scanner lineReader;               // Scanner takes in each line of the CSV
        int salesNum;                     // Stores each sales number
        int digit;                        // Stores each first digit

        // Open the CSV file into the Scanner
        File file = new File(fileName);
        scanner = new Scanner(file);

        scanner.nextLine();    // Skip the first line of the CSV (spreadsheet headers)

        // Read the sales column of the CSV file, parsing the first digit and storing in an array  
        while (scanner.hasNextLine() == true) {
            // Read the next line of the file
            line = scanner.nextLine();

            // Store each line into the Scanner - use the useDelimiter() functon
            lineReader = new Scanner(line);
            lineReader.useDelimiter(",");

            lineReader.next(); // Skip to the next token of the line (the sales number)

            // Pass the sales value into nextInt
            salesNum = lineReader.nextInt();

            digit = getFirstDigit(salesNum);

            // Increase the corresponding value of the array by 1
            firstDigits[digit - 1]++;

            totalDigits++;           // Add 1 to the accumulator vairable
        }
        // Calculate the frequency of each first digit
        digitFrequency = itemFrequency(firstDigits, totalDigits);

        // Display to terminal whether fraud occured - Depends on the frequency of the digit 1
        System.out.println("The first digit frequency of the number 1 is " + digitFrequency[0] + "%");
        if (digitFrequency[0] >= 29 && digitFrequency[0] <= 32) {
            System.out.println("Fraud has most likely not occured :)");
        }
        else {
            System.out.println("Fraud has occured");
        }

        // Export results to a seperate CSV file

        // Return array
        return digitFrequency;
    }

    /*
     * Description: Finds the first digit of any integer
     * 
     * @author - Murphy Lee
     * @param num - The number whose first digit needs to be found
     * @return num - The first digit
     * */
    public static int getFirstDigit(int num) {
        // Loop while the number is greater than 10
        while (num >= 10) {
            num /= 10;
        }
        // Return the digit
        return num;
    }

    /*
     * Description: Finds the percentage of each item in an array, and returns them as a seperate array
     * 
     * @author - Murphy Lee
     * @param arr - Array containing each items numbers
     * @param total - The sum of all numbers in the array
     * @return newArr - Array containing frequencies of each item (doubles)
     * */
    public static double[] itemFrequency(int[] arr, int total) {
        double[] newArr = new double[arr.length];    // Create new array with the same length as the param array

        for (int i = 0; i < newArr.length; i++) {
            newArr[i] = ((1.0 * arr[i]) / total) * 100;     // Divide num / total and multiply by 100%
        }
        return newArr;
    }

    /*
     * Description: Reads through the file, finds the first digit of the sales value, and finds the occurence of each digit
     * 
     * @author - Murphy Lee
     * @throws FileNotFoundException - Exception raised when the program tries to read the CSV, thrown to checkSales()
     * */
    // public static void countFirstDigit(int arr[], Scanner scanner) {

    // }

    /*
        * Description:
        * 
        * @author - Murphy Lee
        * 
        * */
    // @Override
    // public void start(Stage arg0) throws Exception {
        
    // }

    
}