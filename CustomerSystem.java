/*
 * Date: May 5, 2021
 * Names: Naomi Mezheritsky and Murphy Lee
 * Teacher: Mr. Ho
 * Description: A customer sales information that makes use of Benford's Law and Java APIs
 * */

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

class CustomerSystem {
    public static void main(String[] args) {
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

            /* If the user entered 1:
             *     - read sales data from sales.csv file
             *     - based on the input from sales.csv analyze the sales with Benford's law
             *     - generate results.csv
             */
            
            List<Integer> salesData = getSalesData("sales.csv");
            Boolean valid = checkSales(salesData);
            if (valid) {
            	System.out.println("....");
            	reportPercentage(salesData, "results.csv");
            }
            else {
            	System.out.println("....");
            }
            

            // If the user entered 2, set the keepGoing variable to false

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
     * */
    public static Boolean checkSales(List<Integer> salesData) {
    	return true;
    }
}