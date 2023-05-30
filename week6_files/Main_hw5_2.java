/*
 * INSTRUCTION:
 *     This is a Java starting code for hw5_2.
 *     When you finish the development, download this file.
 *     Note that the current filename is "Main.java".
 *     But rename it to "Main_hw5_2.java".
 *     After that, upload the renamed file on Canvas.
 */

// Finish the head comment with Abstract, Name, and Date.
/*
 * Title: Main_hw5_2.java
 * Abstract: This program sorts an array of random integers (size determined by input) using both merge sort
 * and quick sort algorithms, and compares the time to compelete.
 * Name: Nicholas Fotinakes
 * Date: 12/6/2022
 * Credit: QuickSort algorithm adapted from: https://www.geeksforgeeks.org/quick-sort/
 * Merge Sort algorithm adapted from: https://www.geeksforgeeks.org/merge-sort/
 */
import java.util.Random;
import java.util.Scanner;
 
class Main 
{
  public static void main(String[] args) {

        System.out.print("Enter input size: ");
        Scanner input = new Scanner(System.in);
        int size = input.nextInt();
        int[] mergeArray = new int[size];
        int[] quickArray = new int[size];

        Random rd = new Random();
        for (int i = 0; i < size; i++) {
            int insert = rd.nextInt();
            mergeArray[i] = insert;
            quickArray[i] = insert;
        }

        long startTime = System.currentTimeMillis();
        mergeSort(mergeArray, 0, mergeArray.length - 1);
        long endTime = System.currentTimeMillis();
        long mergeTime = endTime - startTime;


        long quickStartTime = System.currentTimeMillis();
        quickSort(quickArray, 0, quickArray.length - 1);
        long quickEndTime = System.currentTimeMillis();
        long quickTime = quickEndTime - quickStartTime;


        System.out.println();
        System.out.println("===================== Execution Time ====================");
        System.out.println("Merge sort: " + mergeTime + " milliseconds");
        System.out.println("Quick sort: " + quickTime + " milliseconds");
        System.out.println("=========================================================");

        input.close();

    }

    // Merge sort algorithm adapted from: https://www.geeksforgeeks.org/merge-sort/
    public static void mergeSort(int[] arr, int l, int r) {
        if (l < r) {
            // Find the middle point
            int m = l + (r - l) / 2;

            // Sort first and second halves
            mergeSort(arr, l, m);
            mergeSort(arr, m + 1, r);

            // Merge the sorted halves
            merge(arr, l, m, r);
        }

    }

    // Used in merge sort
    public static void merge(int[] arr, int l, int m, int r) {
        // Find sizes of two subarrays to be merged
        int n1 = m - l + 1;
        int n2 = r - m;

        /* Create temp arrays */
        int L[] = new int[n1];
        int R[] = new int[n2];

        /*Copy data to temp arrays*/
        for (int i = 0; i < n1; ++i)
            L[i] = arr[l + i];
        for (int j = 0; j < n2; ++j)
            R[j] = arr[m + 1 + j];

        /* Merge the temp arrays */

        // Initial indexes of first and second subarrays
        int i = 0, j = 0;

        // Initial index of merged subarray array
        int k = l;
        while (i < n1 && j < n2) {
            if (L[i] <= R[j]) {
                arr[k] = L[i];
                i++;
            }
            else {
                arr[k] = R[j];
                j++;
            }
            k++;
        }

        /* Copy remaining elements of L[] if any */
        while (i < n1) {
            arr[k] = L[i];
            i++;
            k++;
        }

        /* Copy remaining elements of R[] if any */
        while (j < n2) {
            arr[k] = R[j];
            j++;
            k++;
        }
    }

    // Quick sort algorithm adapted from: https://www.geeksforgeeks.org/quick-sort/
    public static void quickSort(int[] arr, int low, int high) {
        if (low < high) {

            // pi is partitioning index, arr[p]
            // is now at right place
            int pi = partition(arr, low, high);

            // Separately sort elements before
            // partition and after partition
            quickSort(arr, low, pi - 1);
            quickSort(arr, pi + 1, high);
        }

    }

    // Parition method used in quick sort 
    public static int partition(int[] arr, int low, int high) {
        // pivot
        int pivot = arr[high];

        // Index of smaller element and
        // indicates the right position
        // of pivot found so far
        int i = (low - 1);

        for (int j = low; j <= high - 1; j++) {

            // If current element is smaller
            // than the pivot
            if (arr[j] < pivot) {

                // Increment index of
                // smaller element
                i++;
                int temp = arr[i];
                arr[i] = arr[j];
                arr[j] = temp;
            }
        }
        int temp = arr[i + 1];
        arr[i + 1] = arr[high];
        arr[high] = temp;


        return (i + 1);
    }
  
}

