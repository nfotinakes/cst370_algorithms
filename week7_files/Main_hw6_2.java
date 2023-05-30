/*
 * INSTRUCTION:
 *     This is a Java starting code for hw6_2.
 *     When you finish the development, download this file.
 *     Note that the current filename is "Main.java".
 *     But rename it to "Main_hw6_2.java".
 *     After that, upload the renamed file on Canvas.
 */

// Finish the head comment with Abstract, Name, and Date.
/*
 * Title: Main_hw6_2.java
 * Abstract: This program implements Floyd's Algorithm to display all-pairs shortest path of a graph
 * Name: Nicholas Fotinakes
 * Date: 12/13/2022
 */
import java.util.Scanner;
 
class Main 
{
    public static void main(String[] args) {

        // Get number of vertices from input and create adjacency matrix
        Scanner input = new Scanner(System.in);
        int vertices = input.nextInt();
        int[][] matrix = new int[vertices][vertices];

        // Input value to get graph values
        for (int i = 0; i < vertices; i++) {
            for (int j = 0; j < vertices; j++) {
                int num = input.nextInt();
                matrix[i][j] = num;
            }
        }

        // Follow psuedocode algorithm 
        for(int k = 0; k < vertices; k++) {
            for (int i = 0; i < vertices; i++) {
                for (int j = 0; j < vertices; j++) {
                    // Check if infinity value can be updated
                    if (matrix[i][j] == -1) {
                        if (matrix[i][k] != -1 && matrix[k][j] != -1 && matrix[i][k] != 0 && matrix[k][j] != 0) {
                            matrix[i][j] = matrix[i][k] + matrix[k][j];
                        }
                    }
                    // Check if an existing node can be updated
                    if (matrix[i][j] != 0) {
                        if (matrix[i][k] != -1 && matrix[k][j] != -1) {
                            matrix[i][j] = Math.min(matrix[i][j], matrix[i][k] + matrix[k][j]);
                        }
                    }
                }
            }
        }

        // Print updated matrix 
        for (int i = 0; i < vertices; i++) {
            for (int j = 0; j < vertices; j++) {
                if (j == vertices - 1) {
                    System.out.print(matrix[i][j]);  
                } else {
                    System.out.print(matrix[i][j] + " ");
                }
            }
            System.out.println();
        }

      input.close();

    }
}

