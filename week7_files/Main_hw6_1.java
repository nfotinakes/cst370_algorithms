/*
 * INSTRUCTION:
 *     This is a Java starting code for hw6_1.
 *     When you finish the development, download this file.
 *     Note that the current filename is "Main.java".
 *     But rename it to "Main_hw6_1.java".
 *     After that, upload the renamed file on Canvas.
 */

// Finish the head comment with Abstract, Name, and Date.
/*
 * Title: Main_hw6_1.java
 * Abstract: This program takes a board (matrix) as input with coins represented as 1's. The program presents the path 
 * to collect the maximum number of coins as covered in class. 
 * Name: Nicholas Fotinakes
 * Date: 12/13/2022
 */
import java.util.ArrayList;
import java.util.Scanner;
 
class Main 
{
    public static void main(String[] args) {

        // Get row and column size from input
        Scanner input = new Scanner(System.in);
        int rows = input.nextInt();
        int columns = input.nextInt();

        // Create matrix with extra row and column
        int[][] matrix = new int[rows + 1][columns + 1];

        // Leave outside row/column as zero for base case
        // Populate coins in matrix from input
        for (int i = 0; i < rows + 1; i++ ) {
            for (int j = 0; j < columns + 1; j++) {
                if ( i == 0 || j == 0) {
                    matrix[i][j] = 0;
                } else {
                    int num = input.nextInt();
                    matrix[i][j] = num;
                }
            }
        }

        // Iterate through matrix checking the adjacent squares
        // and adding value where appropriate per lecture material
        for (int i = 1; i < rows + 1; i++ ) {
            for (int j = 1; j < columns + 1; j++) {
                if (matrix[i - 1][j] > 0 || matrix[i][j - 1] > 0) {
                    if(matrix[i - 1][j] > matrix[i][j - 1]) {
                        matrix[i][j] = matrix[i][j] + matrix[i - 1][j];
                    } else {
                        matrix[i][j] = matrix[i][j] + matrix[i][j - 1];
                    }
                }
            }
        }

        // Bottom right square of matrix is max value
        int max = matrix[rows][columns];
        System.out.println("Max coins:" + max);


        // Back track through array to find the path
        // if tie, go horizontally not vertical
        // Add to the corresponding square to path 
        ArrayList<Integer> path = new ArrayList<>();
        path.add(columns);
        path.add(rows);

        int i = rows;
        int j = columns;
        while (i != 1 || j != 1) {
            if (matrix[i - 1][j] > matrix[i][j - 1]) {
                i--;
                path.add(j);
                path.add(i);
            } else if (matrix[i - 1][j] < matrix[i][j - 1]) {
                j--;
                path.add(j);
                path.add(i);
            } else if (matrix[i - 1][j] == matrix[i][j - 1]) {
                if (j - 1 != 0) {
                    j--;
                } else {
                    i--;
                }
                path.add(j);
                path.add(i);
            }
        }

        // Iterate backwards through path array to show path from node (1, 1)
        System.out.print("Path:");
        for(int k = path.size() - 1; k > 0; k = k - 2) {
            if (k == 1) {
                System.out.println("(" + path.get(k) + "," + path.get(0) + ")");
            } else {
                System.out.print("(" + path.get(k) + "," + path.get(k -1) + ")->");
            }
        }
        input.close();

    }
}

