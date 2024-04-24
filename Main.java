import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class Main {
    //Maria Palacios
    public static void main(String[] args) throws FileNotFoundException {
        File in = new File("maze.dat");
        Scanner scan = new Scanner(in);
        int row = scan.nextInt(); // Scans number of rows
        int column = scan.nextInt(); // Scans number of columns
        scan.nextLine();

        // Array of characters that holds the value of row and column
        char arr[][] = new char[row][column];

        // For loop to scan the number of rows and columns from the maze file
        for (int i = 0; i < row; i++) {
            String line = scan.nextLine(); // Reads each line of maze.dat and stores it in line
            for (int j = 0; j < column; j++) {
                arr[i][j] = line.charAt(j);
            }
        }

        //Find start position
        int[]startPosition=findPlus(arr);
        System.out.println("Start position: " + startPosition[0] + ", " + startPosition[1]);
        solve(startPosition[0], startPosition[1], arr);

        // Print the maze array
        for (int i = 0; i < row; i++) {
            for (int j = 0; j < column; j++) {
                System.out.print(arr[i][j]);
            }
            System.out.println();
        }
    }

    // Recursive method to solve the maze
    public static boolean solve(int row, int column, char[][] arr) {
        // Check if the current position is out of bounds
        if (row < 0 || column < 0 || row >= arr.length || column >= arr[0].length) {
            return false;
        }

        // Check if the current position is a wall
        if (arr[row][column] == 'X' || arr[row][column] == '+'|| arr[row][column]=='.') {
            return false;
        }

        // Checks if the '-' is the end of the maze
        if (arr[row][column] == '-') {
            System.out.println("The Maze is solved!");
            return true;
        }

        // Mark the position with the plus sign as visited
        arr[ row ][ column ] = '+';

        // walking through 4 Directions
        if (solve(row, column +1, arr)) { //Right
            return true;
        }
        if (solve(row - 1, column, arr)) { // Up
            return true;
        }
        if (solve(row, column - 1, arr)) { // Left
            return true;
        }
        if (solve(row + 1, column, arr)) { // Down
            return true;
        }

        // Dead end
        arr[row][column] = '.';
        return false;
    }

    //findPlus method, identifies the + sign
    public static int[] findPlus(char[][] arr) {
        int[] startPoint = {0, 0};
        for (int row = 0; row < arr.length; row++) {
            for (int col = 0; col < arr[ 0 ].length; col++) {
                if (arr[ row ][ col ] == '+') {
                    arr[ row ][ col ] = ' ';
                    startPoint[ 0 ] = row;
                    startPoint[ 1 ] = col;
                    return startPoint;
                }
            }
        }
        return startPoint;
    }
}