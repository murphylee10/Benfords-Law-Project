/*
 * Date: May 5, 2021
 * Names: Naomi Mezheritsky and Murphy Lee
 * Teacher: Mr. Ho
 * Description: A customer sales information that makes use of Benford's Law and Java APIs
 * */

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
import javafx.stage.Stage;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Scanner;

public class CustomerSystem extends Application {
    public static void main(String[] args) throws FileNotFoundException{
        // Launch the JavaFX application
        launch(args);
        System.out.println("Goodbye!");
    }

    /*
     * Description: Combination of reading the sales data, the validation of user data, and the visual representation of digit frequencies
     * 
     * @author - Naomi Mezheritsky & Murphy Lee 
     * @throws FileNotFoundException - Passed by the exportPercentage() and checkSales() methods, thrown to main
     * @param primaryStage - The platform (window) used to draw on for JAVAFX applications
     * */
    @Override
    public void start(Stage primaryStage) throws FileNotFoundException {
        // *MAIN PROGRAM SECTION*

        ArrayList<Integer> salesData = new ArrayList<>();   // Stores the sales data for each postal code
        String digitMark;    // Stores each digit as a string to be used as X-axis labels
        String tableHeader;  // Table header of the CSV 

        // Instantiate Scanner
        Scanner reader = new Scanner(System.in);

        // Print user options and prompt input
        System.out.print("Enter name of the sales data file: ");
        String salesName = reader.nextLine(); 

        // Get the sales data and read the file's contents to the terminal
        salesData = getSalesData(salesName);
        

        // Run the checkSales function and store the frequencies in an array
        double[] firstDigitFreq = checkSales(salesData);

        // Export results to CSV file
        tableHeader = "Digit,Frequency(%)";
        exportPercentage(firstDigitFreq, tableHeader);
        System.out.println();                                      // Adding whitespace
        System.out.println("Results successfully exported to 'results.csv'. Now loading graph...");
        System.out.println();                                      // Adding whitespace

        // *GRAPHICAL INTERFACE SECITON*

        // Set up the new stage
        primaryStage.setTitle("Benford's Law");

        // Create x-axis and y-axis objects
        CategoryAxis xAxis = new CategoryAxis();
        NumberAxis yAxis = new NumberAxis();

        // Create bar chart object
        BarChart<String, Number> barChart = new BarChart<>(xAxis, yAxis);
        barChart.setTitle("Benford's Law Distribution of Leading Digit");

        // Set the axis labels
        xAxis.setLabel("Digit");
        yAxis.setLabel("Percentage Frequency (%)");

        // Create instance of XYChart object to store data
        XYChart.Series<String, Number> series = new XYChart.Series<>();
        series.setName("First Digit Frequencies");

        // Add data to the series object
        for (int i = 0; i < firstDigitFreq.length; i++) {
            digitMark = String.valueOf((i + 1));
            series.getData().add(new XYChart.Data<String, Number>(digitMark, firstDigitFreq[i]));
        }

        // Feed the barChart node to the scene
        Scene scene = new Scene(barChart, 800, 600);

        // Add the series object (data) to the barChart object
        barChart.getData().add(series);

        // Add scene to the stage, and display it to the screen
        primaryStage.setScene(scene);
        primaryStage.show();

        reader.close();
    }

    /*
    * Description: Reads sales data and adds it to the ArrayList 
    * 
    * @author - Naomi Mezheritsky
    * @param fileName - The file name of the file to be read
    * */
    public static ArrayList<Integer> getSalesData(String fileName) {
        String delimeter = ","; // Declares delimeter as a String
        File f = new File(fileName); // Initializes object of class File based on the given file name
        ArrayList<Integer> dataList = new ArrayList<>(); // dataList is declared by a new ArrayList

        // Start of try block
        try { 
            Scanner reader = new Scanner(f);

            // Beginning of while loop	
            while (reader.hasNext()) { // Creates a loop for hasNext
                String line = reader.nextLine();
                String[] fields = line.split(delimeter); // Creates an array of fields separated by "," (a comma)
                if (fields[1].equals("Sales")) {
                    continue; 
                }
                Integer sales = Integer.valueOf(fields[1]);
                System.out.println(sales);
                dataList.add(sales); // Adds sales to dataList   
            } // End of while loop
            
            System.out.println();                                      // Empty print line for aesthetic
            System.out.println("Data has been loaded succesfully..."); // Informs user that data has been loaded
            System.out.println();                                      // Empty print line for aesthetic

            reader.close(); // Closes reader

        } // End of try block

        catch (FileNotFoundException ex) { // Catch block for "FileNotFoundException" exception
            System.out.println("Could not find file - Empty results will be used..."); // Tells user file isn't found
        }
        return dataList; // Returns dataList
    }

    /*
    * Description: Determines the first-digit frequencies of the sales data and checks for fraud
    * 
    * @author - Murphy Lee
    * @param arr - A list containing all the sales numbers 
    * @return digitFrequency - An array containing the percent frequencies of each digit (0 - 9)
    * */
    public static double[] checkSales(ArrayList<Integer> arr) {
        int totalDigits = 0;                      // Accumulator variable to help calculate frequency
        int[] firstDigits = new int[9];           // Stores the number of occurences of each first digit
        double[] digitFrequency = new double[9];  // Stores the frequency of each digit
        int digit;                                // Stores each first digit

        for (int salesNum : arr) {
            // Obtain the first digit of the sales number
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
            System.out.println("Fraud may have occured");
        }

        // Return array
        return digitFrequency;
    }

    /* 
     * Description: Writes data to a CSV file
     * 
     * @author - Naomi Mezheritsky
     * @throws FileNotFoundException - Exception raised when method tries to pass File to PrintWriter, thrown to start method
     * @param arr - Array containing sales values
     * @param header - The categories that go to the top of the CSV
     * */
    public static void exportPercentage(double[] arr, String header) throws FileNotFoundException {
        String fileName = "results.csv";     // Name of the results file
        // Open file
        File file = new File(fileName);
        // Pass file into printwriter
        PrintWriter writeFile = new PrintWriter(file);
        // Create the file header
        writeFile.println(header);

        // Loop through array values, printing results
        for (int i = 0; i < arr.length; i++) {   // Start of for loop
            writeFile.println((i + 1) + "," + arr[i]);     // Index starts at 0, so + 1 needs to be added
        }  // End of for loop

        writeFile.close();  // Closes PrintWriter
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

            // Round to one decimal place
            newArr[i] = Math.round(newArr[i] * 10.0) / 10.0;
        }

        return newArr;
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
}
