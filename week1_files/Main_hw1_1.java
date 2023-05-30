/*
 * INSTRUCTION:
 *     This is a Java staring code for hw1_1.
 *     When you finish the development, download this file.
 *     Note that the current filename is "Main.java".
 *     But rename it to "Main_hw1_1.java". Please DO NOT change
 *     the name of the main class "Main"
 *     After that, upload the renamed file on Canvas.
 */

// Finish the head comment with Abstract, Name, and Date.
/*
 * Title: Main_hw1_1.java
 * Abstract: This program reads integers from input, calculates the minimum distance two integers from all inputted numbers,
 * and outputs that value, plus the pairs that determined it (multiple pairs if they exist)
 * Name: Nicholas Fotinakes
 * Date: 11/1/2022
 */
import java.util.Scanner;
import java.util.Arrays;
 
class Main 
{
    public static void main(String[] args) {
      
      // Get first int for amount of numbers to accept
      Scanner input = new Scanner(System.in);
      int size = input.nextInt();
      int[] inputArray = new int[size];

      // Insert all values into the array inputArray
      int insertCount = 0;
      while (insertCount < size) {
        int currentNum = input.nextInt();
        inputArray[insertCount] = currentNum;
        insertCount++;
      }

      // Sort array in ascending order
      Arrays.sort(inputArray);

      // Initialize min to max value of an integer as starting point
      int min = Integer.MAX_VALUE;

      // Iterate through sorted array to determine the minimum distance value between pairs
      for(int i = 0; i < inputArray.length; i++) {
        for(int j = i + 1; j < inputArray.length; j++) {
          int distance = Math.abs(inputArray[i] - inputArray[j]);
          if (distance <= min) {
            min = distance;
          }
        }
      }

      // Output the min distance
      System.out.println("Min Distance:" + min);

      // Iterate through array again, printing the pairs with distance matching the min distance
      // Since array is sorted, pairs should be in ascending order
      for(int i = 0; i < inputArray.length; i++) {
        for(int j = i + 1; j < inputArray.length; j++) {
          int distance = Math.abs(inputArray[i] - inputArray[j]);
          if (distance == min) {
            System.out.println("Pair:" + inputArray[i] + " " + inputArray[j]);
          }
        }
      }

      input.close();        
    }
}

