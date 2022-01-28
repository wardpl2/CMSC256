package cmsc256;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Arrays;
import java.util.Scanner;

/**
 *  CMSC 256
 *  Project 1
 *  Ward, Preston
 *
 *  Project 1 reads a csv file from the CL and creates a 2D String array which can be manipulated to calculate data
 */
// place any import statements here
public class Project1 {
    public static void main(String[] args) {
        // Test your program thoroughly before submitting.
        // For example,
        // Display appropriately labeled information for the following:
        // What is tallest height?
        // Which row has the lowest weight?
        // Calculate average height of 20-30 year age range in the data.
        Project1 p1 = new Project1();
        File testFile;
        String[][] testArray = new String[500][3];
        try {
            testFile = p1.getFile(p1.checkArgs(args));
        } catch (FileNotFoundException e) {
            testFile = new File(p1.promptForFileName());
        }
        try {
            testArray = p1.readFile(testFile,1);
        } catch (IOException e) {
            e.printStackTrace();
        }
        System.out.println("Tallest Height: " + p1.findTallest(testArray));
        System.out.println("Row with lowest weight: " + Arrays.toString(p1.findLightestRecord(testArray)));
        System.out.println("Average height for 20-30 year olds: " + p1.findAvgHeightByAgeRange(testArray,20,30));
    }

    /**
     * Gets the file name from command line argument;
     * If parameter is empty, call promptForFileName() method
     *
     * @param argv String array from command line argument
     * @return the name of the data file
     */
    public String checkArgs(String[] argv) {
        if (argv.length < 1) {
            return promptForFileName();
        } else {
            return argv[0];
        }
    }

    /**
     * Prompt user to enter a file name
     *
     * @return user entered file name
     */
    public String promptForFileName() {
        Scanner in = new Scanner(System.in);
        System.out.println("Enter the file name (with extension) of the file containing your data");
        return in.nextLine();
    }

    /**
     * Retrieve file with the given file name.
     * Prompts user if file cannot be opened or does not exist.
     *
     * @param fileName The name of the data file
     * @return File object
     * @throws java.io.FileNotFoundException
     */
    public File getFile(String fileName) throws FileNotFoundException {
        return new File(fileName);
    }

    /**
     * Reads the comma delimited file to extract the number data elements
     * provided in the second argument.
     *
     * @param file       The File object
     * @param numRecords The number of values to read from the input file
     * @return 2D array of data from the File
     * @throws IOException if any lines are missing data
     */
    public String[][] readFile(File file, int numRecords) throws IOException {
        Scanner in = new Scanner(file);
        String[][] returnArray = new String[numRecords][3];
        int row = 0;
        in.nextLine();
        while (in.hasNextLine() && numRecords > 0) {
            String[] temp = in.nextLine().split(",");
            for (int col = 0; col < temp.length; col++) {
                returnArray[row][col] = temp[col];
            }
            row++;
            numRecords--;
        }
        in.close();
//        for (int i = 0; i < numRecords + 1; i++) {
//            String[] temp = in.nextLine().split(",");
//            returnArray[i][0] = temp[0];
//            returnArray[i][1] = temp[1];
//            returnArray[i][2] = temp[2];
//        }
        return returnArray;
    }

    /**
     * Determines the tallest height in the data set
     * Height is the second field in each row
     *
     * @param db 2D array of data containing [age] [height] [weight]
     * @return Maximum height value
     */
    public int findTallest(String[][] db) {
        final int heightIndex = 1;
        int max = 0;
        for (int i = 0; i < db.length; i++) {
            if (Integer.parseInt(db[i][heightIndex]) > max) {
                max = Integer.parseInt(db[i][heightIndex]);
            }
        }
        return max;
    }

    /**
     * Returns the values in the record that have the lowest weight
     *
     * @param db 2D array of data containing [age] [height] [weight]
     * @return Smallest weight value
     */
    public String[] findLightestRecord(String[][] db) {
        final int weightIndex = 2;
        int rowIndex = 1;
        String min = db[0][2];

        if (db.length == 1) {
            return db[0];
        } else {
            for (int i = 0; i < db.length; i++) {
                if (Integer.parseInt(db[i][weightIndex]) < Integer.parseInt(min)) {
                    min = db[i][weightIndex];
                    rowIndex = i;
                }
            }
        }
        return db[rowIndex];
    }

    /**
     * Calculates the average height for all records with the given age range.
     *
     * @param db         2D array of data containing [age] [height] [weight]
     * @param lowerBound youngest age to include in the average
     * @param upperBound oldest age to include in the average
     * @return The average height for the given range or 0 if no
     * records match the filter criteria
     */
    public double findAvgHeightByAgeRange(String[][] db, int lowerBound, int upperBound) {
        double sum = 0;
        double numElements = 0;
        final int heightIndex = 1;
        for (int i = 0; i < db.length; i++) {
            if (Integer.parseInt(db[i][0]) >= lowerBound && Integer.parseInt(db[i][0]) <= upperBound) {
                sum += Double.parseDouble(db[i][heightIndex]);
                numElements++;
            }
        }
        if (sum == 0 || numElements == 0) {
            return 0;
        } else {
            return sum / numElements;
        }
    }
}
