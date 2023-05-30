/*
 * INSTRUCTION:
 *     This is a Java starting code for hw1_2.
 *     When you finish the development, download this file.
 *     Note that the current filename is "Main.java".
 *     But rename it to "Main_hw1_2.java". Please DO NOT change
 *     the name of the main class "Main"
 *     After that, upload the renamed file on Canvas.
 */

// Finish the head comment with Abstract, Name, and Date.
/*
 * Title: Main_hw1_2.java
 * Abstract: This program accepts a string as input, and checks if it is a palindrome (same backwards and forward)
 * Name: Nicholas Fotinakes
 * Date: 11/1/2022
 */
import java.util.Scanner;
 
class Main 
{
    public static void main(String[] args) {

      // Get word from input, save as string, and convert to lowercase
      Scanner input = new Scanner(System.in);
      String word = input.nextLine();
      word = word.toLowerCase();

      // Result boolean to hold if is palindrome or not (true until checked to be false)
      boolean result = true;
      
      // String to hold word with all symbols removed
      String wordNoSymbols = "";

      // Remove any possible symbols or spaces, save to wordNoSymbols
      for (int i = 0; i < word.length(); i++) {
        if(Character.isLetterOrDigit(word.charAt(i))) {
          wordNoSymbols = wordNoSymbols + word.charAt(i);
        } 
      }

      // Compare each character starting from either end, if characters do not match change result to false and exit loop
      // otherwise if all characters match, keep true and word is valid palindrome
      int i = 0;
      int j = wordNoSymbols.length() - 1;
      while (i < j) {
        if (wordNoSymbols.charAt(i) != wordNoSymbols.charAt(j)) {
          result = false;
          break;
        }
        i++;
        j--;
      }

      // Output result as string
      if (result) {
        System.out.println("TRUE");
      } else {
        System.out.println("FALSE");
      }

      input.close();
      
    }
}

