/*
 * INSTRUCTION:
 *     This is a Java starting code for hw4_2.
 *     When you finish the development, download this file.
 *     Note that the current filename is "Main.java".
 *     But rename it to "Main_hw4_2.java".
 *     After that, upload the renamed file on Canvas.
 */

// Finish the head comment with Abstract, Name, and Date.
/*
 * Title: Main_hw4_2.java
 * Abstract: This program uses a divide-and-conquer technique to find the max integer of an array
 * Name: Nicholas Fotinakes  
 * Date: 11/29/2022
 */
import java.util.Scanner;
 
class Main 
{
    public static void main(String[] args) {

      // Get input size and create array 
      Scanner input = new Scanner(System.in);
      int size = input.nextInt();
      int[] numArray = new int[size];

      // Store integer input in array
      int count = 0;
      while (count < size) {
        int userInt = input.nextInt();
        numArray[count] = userInt;
        count++;
      }

      // Print results of calling divide and conquer method to determine max value
      System.out.println(maxDivNConq(numArray, 0, size - 1));
      input.close();
    }

  // Divide and Conquer method to determine max value of integer in an array
  public static int maxDivNConq(int[] A, int start, int end) {
    if (start == end) {
      return A[start];
    } else {
      int max1 = maxDivNConq(A, start, (start + end) / 2);
      int max2 = maxDivNConq(A, (start + end) / 2 + 1, end);
      return max1 > max2 ? max1 : max2;
    }
  }
}

