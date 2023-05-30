/*
 * INSTRUCTION:
 *     This is a Java starting code for hw3_1.
 *     When you finish the development, download this file.
 *     Note that the current filename is "Main.java".
 *     But rename it to "Main_hw3_1.java".
 *     After that, upload the renamed file on Canvas.
 */

// Finish the head comment with Abstract, Name, and Date.
/*
 * Title: Main_hw3_1.java
 * Abstract: This program takes integers as input from the user and sorts them in ascending order abbreviating consecutive integers 
 * Name: Nicholas Fotinakes
 * Date: 11/15/2022
 */
import java.util.Arrays;
import java.util.Scanner;
 
class Main 
{
    public static void main(String[] args) {

      // Get number of input values and create array to store them
      Scanner input = new Scanner(System.in);
      int size = input.nextInt();
      int[] inputArray = new int[size];

      // Insert values from input into array
      int insertCount = 0;
      while (insertCount < size) {
        int currentNum = input.nextInt();
        inputArray[insertCount] = currentNum;
        insertCount++;
      }

      // Sort the array in ascending order
      Arrays.sort(inputArray);

      // Iterate through the array looking for consecutive numbers
      // If difference is value of 1 and start of consecutive sequence store that value as first then
      // mark flag as true that we are in a consecutive sequence until sequence ends
      // then output correct value and reset flag and first variable (print normally otherwise)
      boolean flag = false;
      int first = 0;
      for (int i = 0; i < inputArray.length; i++) {
        if(i != inputArray.length - 1 && inputArray[i + 1] - inputArray[i] == 1) {
          flag = true;
          if (first == 0) {
            first = inputArray[i];
          }
        } else {
          if (flag) {
            System.out.print(first + "-");
          }
          if (i != inputArray.length - 1) {
            System.out.print(inputArray[i] + " ");
          } else {
            System.out.print(inputArray[i]);
          }
          flag = false;
          first = 0;
        }
      }
      System.out.print("\n");
      input.close();

    }
}

