/*
 * Date: May 5, 2021
 * Names: Naomi Mezheritsky and Murphy Lee
 * Teacher: Mr. Ho
 * Description: A customer sales information that makes use of Benford's Law and Java APIs
 * */

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
     * */
    public static void checkSales() {

    }
}