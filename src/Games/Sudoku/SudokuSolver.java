package Games.Sudoku;

import java.util.Arrays;
import java.util.Scanner;

/**
 * Asks the user to input a sudoku board to be solved and then the program solves it
 * @author Preston Ward
 */
public class SudokuSolver {
    public static void main(String[] args) {
        int[][] board = fillBoardToSolve();

        System.out.println("\nBEFORE SOLVING:");
        for (int[] i : board) {//print the board before being solved
            System.out.println(Arrays.toString(i));
        }

        solver(board);

        System.out.println("\nAFTER SOLVING:");
        for (int[] i : board) {//print the board after being solved
            System.out.println(Arrays.toString(i));
        }
    }

    /**
     * Fills a sudoku board by asking the user to input the numbers on the board using 0s for empty spaces
     * @return int[][]
     */
    public static int[][] fillBoardToSolve() {
        int[][] returnBoard = new int[9][9];
        for (int row = 0; row < 9; row++) {
            for (int col = 0; col < 9; col++) {
                System.out.print("Enter a number for a 9x9 sudoku grid at row " + (row + 1) + " and column " + (col + 1) + "\nEnter a 0 to indicate an empty space: ");
                returnBoard[row][col] = new Scanner(System.in).nextInt();
            }
        }
        return returnBoard;
    }

    /**
     * Searches the sudoku board to be solved and returns the coordinates of the first empty space it finds (Searches from top left to bottom right)
     * @param board int[][] of the sudoku board to be solved
     * @return coordinates of an empty space in the sudoku board as an int array
     */
    public static int[] blankPosition(int[][] board) {
        int[] returnArray = new int[2];

        returnArray[0] = -1;
        returnArray[1] = -1;

        for (int row = 0; row < board.length; row++) {
            for (int col = 0; col < board[row].length; col++) {
                if (board[row][col] == 0) {
                    returnArray[0] = row;
                    returnArray[1] = col;
                    return returnArray;
                }
            }
        }
        return returnArray;
    }

    /**
     * @param board int[][] The sudoku board to be solved
     * @param row int The row to search through to see if num is present
     * @param num int The num to search for
     * @return boolean Whether or not num is in the row in board
     */
    public static boolean usedInRow(int[][] board, int row, int num) {
        for (int i = 0; i < board.length; i++) {
            if (board[row][i] == num) {
                return true;
            }
        }
        return false;
    }

    /**
     * @param board int[][] The sudoku board to be solved
     * @param col int The column to search through to se if num is present
     * @param num int The num to search for
     * @return boolean Whether or not num is in the col in board
     */
    public static boolean usedInCol(int[][] board, int col, int num) {
        for (int i = 0; i < board.length; i++) {
            if (board[i][col] == num) {
                return true;
            }
        }
        return false;
    }

    /**
     * @param board int[][] The sudoku board to be solved
     * @param row1Start int The startRow that will search it and the 2 rows associated with the grid
     * @param col1Start int The startCol that will search it and the 2 columns associated with the grid
     * @param num int The num to search for in the current 3x3 grid
     * @return boolean Whether or not num is in the 3x3 grid in board
     */
    public static boolean usedInBox(int[][] board, int row1Start, int col1Start, int num) {
        for (int row = 0; row < 3; row++) {
            for (int col = 0; col < 3; col++) {
                if (board[row + row1Start][col + col1Start] == num) {
                    return true;
                }
            }
        }
        return false;
    }

    /**
     * @param board int[][] The sudoku board to be solved
     * @param row int The row to be searched
     * @param col int The column to be searched
     * @param num int The num that is being searched for
     * @return boolean Whether or not num is in the current row, the current column, and the current 3x3 grid
     */
    public static boolean isSafe(int[][] board, int row, int col, int num) {
        return (!usedInRow(board,row,num) && !usedInCol(board,col,num) && !usedInBox(board, row - row % 3, col - col % 3, num));
    }

    /**
     * @param board int[][] The sudoku board to be solved
     * @return boolean Whether or not the board has been solved
     */
    public static boolean solver(int[][] board) {
        int[] returnArray = blankPosition(board);
        if (returnArray[0] == -1) {
            return true;
        }

        int row = returnArray[0];
        int col = returnArray[1];

        for (int num = 1; num <= 9; num++) {
            if (isSafe(board,row,col,num)) {
                board[row][col] = num;
                boolean check = solver(board);
                if (check) {
                    return true;
                }
                board[row][col] = 0;
            }
        }
        return false;
    }
}

