/*
 * INSTRUCTION:
 *     This is a Java starting code for hw2_1.
 *     When you finish the development, download this file.
 *     Note that the current filename is "Main.java".
 *     But rename it to "Main_hw2_1.java".
 *     After that, upload the renamed file on Canvas.
 */

// Finish the head comment with Abstract, Name, and Date.
/*
 * Title: Main_hw2_1.java
 * Abstract: This program takes two time stamps as input and calculates the time difference between them
 * Name: Nicholas Fotinakes
 * Date: 11/8/2022
 */
import java.util.Scanner;
 
class Main 
{
    public static void main(String[] args) {

      // Accept two timestamps as input
      Scanner input = new Scanner(System.in);
      String time1 = input.nextLine();
      String time2 = input.nextLine();

      // Convert each subsection of string into hour, min, and seconds for 
      // each timestamp
      int time1Hr = Integer.parseInt(time1.substring(0,2));
      int time1Min = Integer.parseInt(time1.substring(3, 5));
      int time1Sec = Integer.parseInt(time1.substring(6, 8));
      int time2Hr = Integer.parseInt(time2.substring(0,2));
      int time2Min = Integer.parseInt(time2.substring(3, 5));
      int time2Sec = Integer.parseInt(time2.substring(6, 8));

      // Initialize variables to hold final differences
      int hourDif;
      int minDif;
      int secDif;

      // Calculate seconds difference, carry over into mins if negative
      if (time2Sec - time1Sec < 0) {
        time2Sec = time2Sec + 60;
        time1Min = time1Min + 1;
      }
      secDif = time2Sec - time1Sec;

      // Calculate minute difference, carry over into hour if negative
      if (time2Min - time1Min < 0) {
        time2Min = time2Min + 60;
        time1Hr = time1Hr + 1;
      }
      minDif = time2Min - time1Min;

      // Calculate hour difference, adjst if negative
      if(time2Hr - time1Hr < 0) {
        time2Hr = time2Hr + 24;
      }
      hourDif = time2Hr - time1Hr;

      // Print results in correcnt format
      System.out.printf("%02d:%02d:%02d\n", hourDif, minDif, secDif);

      input.close();
      
    }
}

