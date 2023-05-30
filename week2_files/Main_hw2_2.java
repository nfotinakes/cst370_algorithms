/*
 * INSTRUCTION:
 *     This is a Java starting code for hw2_2.
 *     When you finish the development, download this file.
 *     Note that the current filename is "Main.java".
 *     But rename it to "Main_hw2_2.java".
 *     After that, upload the renamed file on Canvas.
 */

// Finish the head comment with Abstract, Name, and Date.
/*
 * Title: Main_hw2_2.java
 * Abstract: This program reads a number for elements in a set, the elements for the set, and then outputs all possible
 * decimal numbers with their corresponding binary numbers and subsets, with matching set elements.
 * Method toBinary() adapted from provided geeksforgeeks.org link in HW prompt 
 * Name: Nicholas Fotinakes
 * Date: 11/08/2022
 */
import java.util.Scanner;
 
class Main 
{
    public static void main(String[] args) {
      // Get initial number from input
      Scanner input = new Scanner(System.in);
      int num = input.nextInt();

      // Determine how many outputs there will be and create an array to store
      // if initial number was 0, create an array of size 1
      String[] binaryArray;
      int setLength = (int)Math.pow(2, num);
      if (setLength == 0) {
        binaryArray = new String[1];
      } else {
        binaryArray = new String[setLength];
      }

      input.nextLine();

      // Receive inputs for elements and store in array
      // check for initial number of 0 to avoid input prompt (not neccesary for testing)
      String userInput = "";
      String[] inputSet;
      if (num > 0) {
        userInput = input.nextLine();
        inputSet = userInput.split(" ");
      } else {
        inputSet = userInput.split(" ");
      }

      // Populate binary array with correct binary numbers
      // counting to the initial input number and padding with 0's if needed
      for (int j = 0; j < setLength; j++) {
        String getBinary = toBinary(j);
        while (getBinary.length() < num) {
          getBinary = "0" + getBinary;
        }
        if (num == 0) {
          getBinary = "0";
        } 
        binaryArray[j] = getBinary;
      }

      
      // Build the output format matching if where if there is a '1' in the binary
      // print the corresponding string from inputSet array
      for (int k = 0; k < binaryArray.length; k++) {
        String output = "";
        for (int j = 0; j < num; j++) {
          // first sting/character dont print a space
          if (binaryArray[k].charAt(j) == '1' && output.equals("")) {
            output = inputSet[j];
          // otherwise print the corresponding string/symbol with leading space
          } else if (binaryArray[k].charAt(j) == '1') {
            output = output + " " + inputSet[j];
          }
        }
        // Check for empty output
        if(output.equals("")) {
          output = "EMPTY";
        }
        System.out.println(k + ":" + binaryArray[k] + ":" + output);
      }
      
      input.close();
    }

  // Method from geeksforgeeks.org to convert decimal into binary
  static String toBinary(int n) {
    int[] binaryNum = new int[32];
    String binaryString = "";

    int i = 0;
    while (n > 0) {
      binaryNum[i] = n % 2;
      n = n / 2;
      i++;
    }

    for (int j = i - 1; j >= 0; j--) {
      binaryString = binaryString + binaryNum[j];
    }
    return binaryString;
  }
}

