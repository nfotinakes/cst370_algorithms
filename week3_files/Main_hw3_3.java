/*
 * INSTRUCTION:
 *     This is a Java starting code for hw3_3.
 *     When you finish the development, download this file.
 *     Note that the current filename is "Main.java".
 *     But rename it to "Main_hw3_3.java".
 *     After that, upload the renamed file on Canvas.
 */

// Finish the head comment with Abstract, Name, and Date.
/*
 * Title: Main_hw3_3.java
 * Abstract: This program reads graph data as input, implements the Depth-First Search, and displays the Mark results
 * (Mark is the order the nodes are visited during the algorithm)
 * Name: Nicholas Fotinakes 
 * Date: 11/15/2022
 */
import java.util.ArrayList;
import java.util.Scanner;
 
class Main 
{
    // Global variables
    static ArrayList<Integer> stack = new ArrayList<>(); // Represent stack (not needed as recursive function is used)
    static int[] markArray; // Mark array to store vertices by index, value for order vertex was visited
    static int count = 0;
    static int vertices;
    static int[][] graph;
  
    public static void main(String[] args) {
        // Get amount of vertices and edges
        Scanner input = new Scanner(System.in);
        vertices = input.nextInt();
        int edges = input.nextInt();

        // Create adjacency matrix
        graph = new int[vertices][vertices];

        // Fill matrix with 0's
        for (int i = 0; i < vertices; i++) {
            for (int j = 0; j < vertices; j++) {
                graph[i][j] = 0;
            }
        }

        // Populate matrix with edges
        input.nextLine();
        int count = 0;
        while (count < edges) {
            String edgeInput = input.nextLine();
            String[] edgeStringArray = edgeInput.split(" ");
            int[] edgeIntArray = new int[2];
            for (int i = 0; i < edgeStringArray.length; i++) {
                edgeIntArray[i] = Integer.parseInt(edgeStringArray[i]);
            }
            graph[edgeIntArray[0]][edgeIntArray[1]] = 1;
            count++;
        }

        // Create the Mark array for each node/vertex in graph
        // Mark each as 0 for initial value 
        // index value represents the node
        markArray = new int[vertices];
        for (int i = 0; i < vertices; i++) {
            markArray[i] = 0;
        }

        // For each vertex call the depthFirst method to begin recursive search
        for (int v = 0; v < vertices; v++) {
            if (markArray[v] == 0) {
                depthFirst(v);
            }
        }

        // Print mark array values to show order of Depth-First search traversal of graph
        for (int i = 0; i < markArray.length; i++) {
            System.out.println("Mark[" + i + "]:" + markArray[i]);
        }
        input.close();
        
    }

  // Recursive depthFirst method for all unvisited connected vertices of v
  // marks in order they are encountered 
  public static void depthFirst(int vertex) {
        stack.add(vertex); // Stack (unnecesarry and method uses implicit stack, but left for demonstration)
        count = count + 1;
        markArray[vertex] = count;
        for (int w = 0; w < vertices; w++) {
            if (graph[vertex][w] == 1 && markArray[w] == 0) {
                depthFirst(w);
            }
        }

    }
}

