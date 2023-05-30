/*
 * INSTRUCTION:
 *     This is a Java starting code for hw4_3.
 *     When you finish the development, download this file.
 *     Note that the current filename is "Main.java".
 *     But rename it to "Main_hw4_3.java".
 *     After that, upload the renamed file on Canvas.
 */

// Finish the head comment with Abstract, Name, and Date.
/*
 * Title: Main_hw4_3.java
 * Abstract: This program implements Kahns Algorithm to conduct a topological sort of a graph (DAG)
 * Name: Nicholas Fotinakes
 * Date: 11/29/2022
 */
import java.util.Scanner;
import java.util.ArrayList;

class Main 
{
    public static void main(String[] args) {

      Scanner input = new Scanner(System.in);
        int vertices = input.nextInt();
        int edges = input.nextInt();
        int[][] graph = new int[vertices][vertices];
        int[] inDegree = new int[vertices];

        // Populate adjacency matrix with 0's
        for (int i = 0; i < vertices; i++) {
            for(int j = 0; j < vertices; j++) {
                graph[i][j] = 0;
            }
        }

        // Get edges from user and update adjacency matrix
        // Update vertex in-edge count at same time via inDegree array
        int count = 0;
        while (count < edges) {
            int edge1 = input.nextInt();
            int edge2 = input.nextInt();
            inDegree[edge2] = inDegree[edge2] + 1; // Add in-degree from edge input
            graph[edge1][edge2] = 1; // Update adjacency matrix with edge
            count++;
        }


        // Print initial In-Degree array with in-degree values
        // Also check that graph is a DAG and update flag if not DAG
        boolean degreeCheck = false;
        for(int i = 0; i < inDegree.length; i++) {
            System.out.println("In-degree[" + i + "]:" + inDegree[i]);
            if (inDegree[i] == 0) {
                degreeCheck = true;
            }
        }

        // If graph has a cycle and not a DAG, exit program with no order
        if (!degreeCheck) {
            System.out.println("No Order:");
            System.exit(0);
        }

        // Calculate and update in-degrees
        // Check for initial vertices with zero in-degree and add to check array for order
        // Also add to the queue to remove edges
        // While vertices in check array, remove edge and vertex from array then loop back
        // Keep checking until order is determined and all vertices added to queue
        int total = 0;
        ArrayList<Integer> queue = new ArrayList<>();
        ArrayList<Integer> check = new ArrayList<>();
        while (queue.size() != vertices) {
            // For loop to check in-degree total of vertices
            for (int column = 0; column < graph[0].length; column++) {
                total = 0;
                for (int row = 0; row < graph.length; row++) {
                    if (graph[row][column] == 1) {
                        total++;
                    }
                }
                // If in-degree is zero and not in the queue, add to queue array and to check array for order
                if (total == 0 && !queue.contains(column)) {
                    queue.add(column);
                    check.add(column);
                }

            }
            
            // Check for cycles, if found not a DAG and exit 
            if (check.size() == 0 && queue.size() != vertices) {
              System.out.println("No Order:");
              System.exit(0);
            }

            // For next vertex in check queue, remove its edges from graph and then dequeue it
            if (check.size() != 0) {
                for (int j = 0; j < vertices; j++) {
                    if (graph[check.get(0)][j] == 1) {
                        graph[check.get(0)][j] = 0;
                    }
                }
                check.remove(0);
            }
        }

        // Print order results from Array queue
        System.out.print("Order:");
        for (int k = 0; k < queue.size(); k++) {
            if (k != queue.size() - 1) {
                System.out.print(queue.get(k) + "->");
            } else {
                System.out.println(queue.get(k));
            }
        }
      input.close();
        
    }
}

