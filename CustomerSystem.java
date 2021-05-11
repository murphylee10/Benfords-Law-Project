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
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

class CustomerSystem {
    public static void main(String[] args) throws FileNotFoundException{
        String userChoice;           // Which menu option the user has chosen
        boolean keepGoing = true;    // Ensures that the while loop runs at least once

        // Instantiate Scanner
        Scanner reader = new Scanner(System.in);

        // Loop through user options as long as the user wants to keep going
        while (keepGoing == true) {
            // Print user options
            printMenu();
            userChoice = reader.nextLine();
            
            // Prompt the user for re-input as long as their choice is invalid (not a number from 1 - 3)
            while (!(userChoice.equals("1") || userChoice.equals("2") || userChoice.equals("3"))) {
                System.out.println("User input is invalid. Enter one of the choices (1 - 3): ");
                userChoice = reader.nextLine();
            }

            /* If the user entered 1:
             *     - read sales data from sales.csv file
             *     - based on the input from sales.csv analyze the sales with Benford's law
             *     - generate results.csv
             */
            
            if (userChoice.equals("1")) {
                getSalesData("sales.csv");
            }


            // If the user entered 2, set the keepGoing variable to false
            else if (userChoice.equals("2")) {
                // Run the checkSales function and store the frequencies in an array
                double[] firstDigitFreq = checkSales(reader);


            }

            // Otherwise, the user entered 3 and wants to quit the program
            else {
                keepGoing = false;
            }
        }

        reader.close();
    }
    
    /* 
     * Description: 
     * 
     * @author - Naomi mezheritsky
     * 
     * */
    public static void reportPercentage(List<Integer> salesData, String fileName) {

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
    public static List<Integer> getSalesData(String fileName) {
    	String delimeter = ","; //declares delimeter as a String
    	File f = new File(fileName); // initializes object of class File based on the given file name
    	List<Integer> dataList = new ArrayList<>(); //dataList is declared by a new ArrayList

    	try { //start of try block
    		Scanner reader = new Scanner(f);

    		//beginning of while loop	
    		while (reader.hasNext()) { //creates a loop for hasNext
    			String line = reader.nextLine();
    			String[] fields = line.split(delimeter); //creates an array of fields separated by "," (a comma)
    			if (fields[1].equals("Sales")) {
    				continue; // 
    			}
    			Integer sales = Integer.valueOf(fields[1]);
    			//System.out.println(sales);   MAYBE
    			dataList.add(sales); //adds sales to dataList
    		} //end of while loop
    		
    		reader.close(); //closes reader

    	} //end of try block

    	catch (FileNotFoundException ex) { //catch block for "FileNotFoundException" exception
    		ex.printStackTrace();
    	}

    	return dataList; //returns dataList

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
        int salesNum;
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
            System.out.println(digit);
            // System.out.println("Line " + (totalDigits + 1) + ": " + digit);

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

    
}
