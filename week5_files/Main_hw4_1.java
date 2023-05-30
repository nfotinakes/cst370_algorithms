/*
 * INSTRUCTION:
 *     This is a Java starting code for hw4_1.
 *     When you finish the development, download this file.
 *     Note that the current filename is "Main.java".
 *     But rename it to "Main_hw4_1.java".
 *     After that, upload the renamed file on Canvas.
 */

// Finish the head comment with Abstract, Name, and Date.
/*
 * Title: Main_hw4_1.java
 * Abstract: This program reads integer input then puts all negative values in front of positive, using
 * two different partitioning approaches
 * Name: Nicholas Fotinakes
 * Date: 11/29/2022
 */
import java.util.Scanner;
 
class Main 
{
    public static void main(String[] args) {

        // Get input size from user and create two versions of array for both approaches
        Scanner input = new Scanner(System.in);
        int size = input.nextInt();
        int[] numArrayV1 = new int[size];
        int[] numArrayV2 = new int[size];

        // Populate both areas with integer input
        int count = 0;
        while (count < size) {
            int numInput = input.nextInt();
            numArrayV1[count] = numInput;
            numArrayV2[count] = numInput;
            count++;
        }


        //First Approach
        int m = 0;
        int n = numArrayV1.length - 1;
        while (m <= n) {
            if (numArrayV1[m] >= 0 && numArrayV1[n] < 0) {
                int temp1 = numArrayV1[m];
                int temp2 = numArrayV1[n];
                numArrayV1[m] = temp2;
                numArrayV1[n] = temp1;
            } else if ( numArrayV1[m] < 0) {
                m++;
            } else if (numArrayV1[n] >= 0) {
                n--;
            }
        }

        // Display first result of first approach
        for (int q = 0; q < numArrayV1.length; q++) {
            if (q == numArrayV1.length - 1) {
                System.out.println(numArrayV1[q]);
            } else {
                System.out.print(numArrayV1[q] + " ");
            }
        }


        // Second Approach
        int i = 0;
        int j = 0;
        while (j < size) {
            if (numArrayV2[i] >= 0 && numArrayV2[j] < 0) {
                int temp1 = numArrayV2[i];
                int temp2 = numArrayV2[j];
                numArrayV2[i] = temp2;
                numArrayV2[j] = temp1;
            } else if (numArrayV2[i] < 0 && numArrayV2[j] < 0 && i < j) {
                i++;
            } else if (numArrayV2[i] >= 0 && numArrayV2[j] >= 0) {
                j++;
            } else if (numArrayV2[i] < 0 && numArrayV2[j] >= 0 ) {
                i++;
                j++;
            } else if (numArrayV2[i] < 0 && numArrayV2[j] < 0 &&  i == j) {
                i++;
                j++;
            }
        }

        // Display results of second appraoch
        for (int q = 0; q < numArrayV2.length; q++) {
            if (q == numArrayV2.length - 1) {
                System.out.println(numArrayV2[q]);
            } else {
                System.out.print(numArrayV2[q] + " ");
            }
        }

        input.close();

    }
}

