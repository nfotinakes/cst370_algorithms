/*
 * INSTRUCTION:
 *     This is a Java starting code for hw3_2.
 *     When you finish the development, download this file.
 *     Note that the current filename is "Main.java".
 *     But rename it to "Main_hw3_2.java".
 *     After that, upload the renamed file on Canvas.
 */

// Finish the head comment with Abstract, Name, and Date.
/*
 * Title: Main_hw3_2.java
 * Abstract: This program reads input graph data and then presents the optimal TSP path
 * Name: Nicholas Fotinakes
 * Date: 11/15/2022
 */
import java.util.ArrayList;
import java.util.Scanner;
 
class Main 
{
  static ArrayList<String> allPermutations = new ArrayList<>();
  
    public static void main(String[] args) {

      // Get vertices and edges from input
        Scanner input = new Scanner(System.in);
        int vertices = input.nextInt();
        int edges = input.nextInt();

      // ArrayList to store all vertices
        ArrayList<Integer> verticesList = new ArrayList<Integer>();
        for (int i = 0; i < vertices; i++) {
            verticesList.add(i);
        }

      // create adjacency matrix to represent graph
        int[][] graph = new int[vertices][vertices];

      // Populate adjacency matrix with 0's
        for (int i = 0; i < vertices; i++) {
            for (int j = 0; j < vertices; j++ ) {
                graph[i][j] = 0;
            }
        }
        input.nextLine();

      // Get edges from user and insert into adjacency matrix
        int[] inputNum = new int[3];
        int count = 0;
        while (count < edges) {
            String userInput = input.nextLine();
            String[] inputSet = userInput.split(" ");

            for(int i = 0; i <inputSet.length; i++) {
                inputNum[i] = Integer.parseInt(inputSet[i]);
            }
            graph[inputNum[0]][inputNum[1]] = inputNum[2];
            count++;

        }

      // Remove starting node from List
        int startingNode = input.nextInt();
        if (verticesList.contains(startingNode)) {
            verticesList.remove(startingNode);
        }

      // Change vertices list into array for Permutation method
        int[] verticesArray = new int[verticesList.size()];
          for (int i = 0; i < verticesList.size(); i++) {
              verticesArray[i] = verticesList.get(i);
          }

      
        // Calculate all possible permutations of edges minus starting node
        Permute(verticesArray, 0);

        // Add starting node to beginning and end of all possible permutations to create possible paths
        for(int i = 0; i < allPermutations.size(); i++) {
            String element = allPermutations.get(i);
            element = startingNode + " " + element + startingNode;
            allPermutations.set(i, element);
          }

        // Checking permutations for invalid paths
        // Array List to hold indices of the invalid paths
        ArrayList<Integer> invalidPerm = new ArrayList<>();
        for (int i = 0; i < allPermutations.size(); i++) {
            String permToCheck = allPermutations.get(i);
            int[] permInt = stringToArray(permToCheck);
            for (int j = 0; j < permInt.length - 1; j++) {
                if (graph[permInt[j]][permInt[j+1]] == 0) {
                    invalidPerm.add(i);
                    break;
                }
            }
        }

        // Add all valid paths to new array based on the invalid path array (omit these)
        ArrayList<String> validPerms = new ArrayList<>();
        for (int i = 0; i < allPermutations.size(); i++) {
            if(!invalidPerm.contains(i)) {
                validPerms.add(allPermutations.get(i));
            }
        }

        // Calculate costs of all valid paths, add cost value to end of path in array
        for(int i = 0; i < validPerms.size(); i++) {
            int cost = 0;
            String permString = validPerms.get(i);
            int[] permInt = stringToArray(permString);
            for (int j = 0; j < permInt.length - 1; j++) {
                cost = cost + graph[permInt[j]][permInt[j+1]];
            }
            validPerms.set(i, validPerms.get(i) + " " + cost);
        }

        // If no path vound, validPerms will be empty
        // Print correct output and exit program 
        if (validPerms.isEmpty()) {
            System.out.println("Path:");
            System.out.println("Cost:-1");
            System.exit(0);
        }
      
        int minCost = Integer.MAX_VALUE;
        // Iterate paths to find the lowest possible cost
        for (int i = 0; i < validPerms.size(); i++) {
            String costString = validPerms.get(i);
            int[] permInt = stringToArray(costString);
            for (int j = 0; j < vertices + 2; j++) {
                if (permInt[permInt.length - 1] < minCost) {
                    minCost = permInt[permInt.length - 1];
                }
            }
        }

        // Output the correct path with correct formatting that has the lowest path cost
        for (int i = 0; i < validPerms.size(); i++) {
            String costString = validPerms.get(i);
            int[] permInt = stringToArray(costString);
            if (permInt[permInt.length - 1] == minCost) {
                for (int j = 0; j < permInt.length; j++) {
                    if (j == 0) {
                        System.out.print("Path:" + permInt[j] + "->");
                    } else if ( j == permInt.length - 2) {
                        System.out.print(permInt[j] + "\n");
                    } else if (j == permInt.length - 1) {
                        System.out.println("Cost:" + permInt[j]);
                    } else {
                        System.out.print(permInt[j] + "->");
                    }
                }

            }
        }
      input.close();
    }

  // Method provided from Professor Feiling to calculate permutations 
  public static void Permute(int[] input, int startIndex)
    {
        int size = input.length;
        String perm = "";

        if (size == startIndex + 1)
        {
            for (int i = 0; i < size; i++)
            {
                perm = perm + input[i] + " ";
            }
            allPermutations.add(perm);
        }
        else
        {
            for (int i = startIndex; i < size; i++)
            {
                int temp = input[i];
                input[i] = input[startIndex];
                input[startIndex] = temp;

                Permute(input, startIndex + 1);
                temp = input[i];
                input[i] = input[startIndex];
                input[startIndex] = temp;
            }
        }

    }

  // Method to convert a string into an int array
  public static int[] stringToArray(String s) {
    String[] stringArray = s.split(" ");
    int[] intArray = new int[stringArray.length];
    for (int i = 0; i < stringArray.length; i++) {
      intArray[i] = Integer.parseInt(stringArray[i]);
    }
    return intArray;
  }
  
}

