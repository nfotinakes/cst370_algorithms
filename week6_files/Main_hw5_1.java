/*
 * INSTRUCTION:
 *     This is a Java starting code for hw5_1.
 *     When you finish the development, download this file.
 *     Note that the current filename is "Main.java".
 *     But rename it to "Main_hw5_1.java".
 *     After that, upload the renamed file on Canvas.
 */

// Finish the head comment with Abstract, Name, and Date.
/*
 * Title: Main_hw5_1.java
 * Abstract: This program takes values from input to store in an array, check if it is a max heap, and if not
 * makes it into one. Then takes commands from input to display heap, max value, delete max, and insert values
 * Name: Nicholas Fotinakes
 * Date: 12/6/2022
 */
import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;
 
class Main 
{
  
  static ArrayList<Integer> heap = new ArrayList<>(); // Max Heap Array
  
    public static void main(String[] args) {
        // Initial Heap array size
        Scanner input = new Scanner(System.in);
        int size = input.nextInt();
        heap.add(0); // Index 0 set to 0 (unused)

        // Populate heap array from input
        int count = 0;
        while (count < size) {
            int heapNum = input.nextInt();
            heap.add(heapNum);
            count++;
        }

        // Get commands and store in commandArray
        int commandSize = input.nextInt();
        String[] commandArray = new String[commandSize];
        input.nextLine();
        count = 0;
        while (count < commandSize) {
            commandArray[count] = input.nextLine();
            count++;
        }

        // Initial heap check, if not a heap, make it one!
        if(checkHeap(heap)) {
            System.out.println("This is a heap.");
        } else {
            System.out.println("This is NOT a heap.");
            while(!checkHeap(heap)) {
                heapify();
            }
        }

        // Iterate through commands and execute corresponding methods
        for (int i = 0; i < commandArray.length; i++) {
            if(commandArray[i].equals("display")) {
                display();
            } else if (commandArray[i].equals("displayMax")) {
                displayMax();
            } else if (commandArray[i].equals("deleteMax")) {
                deleteMax();
            } else if (commandArray[i].contains("insert")) {
                String numInput = commandArray[i].replaceAll("\\D+", "");
                int num = Integer.parseInt(numInput);
                insert(num);
            }
        }
        input.close();
    }

    // checkHeap method checks if an array is a heap or not and returns boolean result
    public static boolean checkHeap(ArrayList<Integer> A) {
      
        int size = A.size() - 1; // Size of heap (disregarding index 0)
        int mid = size/2; // Mid represents last index value of parent nodes

        // For the check, iterate through all parent nodes starting from root
        // checking if any of its children are larger than the parent
        int count = 1;
        while (count <= mid) {
          
            int leftChild = A.get(2 * count); // left child of parent
            // Inialize ints
            int rightChild = Integer.MIN_VALUE;
            int maxChild = Integer.MIN_VALUE;
            // Check if right child exists, if so set it
            if (2*count + 1 <= size) {
                rightChild = A.get(2*count + 1);
            }
            // get the max of the children nodes
            if (leftChild > rightChild) {
                maxChild = leftChild;
            } else if (rightChild > 0) {
                maxChild = rightChild;
            }
            // Return result
            if (maxChild > A.get(count)) {
                return false;
            }
            count++;
        }
        return true;
    }

    // heapify method sorts an array into valid heap 
    // Note: does one pass though nodes so must be run repeat
    // using checkHeap until valid heap is assembled
    public static void heapify() {
        int size = heap.size() - 1; // Size of heap array
        int count = size / 2; // count represents index of last parent node

        // Starting from last parent node, iterate back through parents checking children
        while (count >= 1) {

            // Get values for left and right children
            int leftChild = heap.get(2*count);
            int rightChild = Integer.MIN_VALUE;
            int maxChild = Integer.MIN_VALUE;
            char child = 'X'; // marker for children
            // check for right child
            if (2*count + 1 <= size) {
                rightChild = heap.get(2*count + 1);
            }
            // compare children and set max and marker
            if (leftChild > rightChild) {
                maxChild = leftChild;
                child = 'L';
            } else if (rightChild > 0) {
                maxChild = rightChild;
                child = 'R';
            }
            // if parent node less than max child/ swap
            if (heap.get(count) < maxChild) {
                if (child == 'L') {
                    Collections.swap(heap, 2*count, count);
                } else if (child == 'R') {
                    Collections.swap(heap, 2*count + 1, count);
                }
            }
            count--;
        }
    }

    // Displays heap
    public static void display() {
        for (int i = 1; i < heap.size(); i++) {
            if (i == heap.size() - 1) {
                System.out.println(heap.get(i));
            } else {
                System.out.print(heap.get(i) + " ");
            }

        }
    }

    // Displays max of heap (root node)
    public static void displayMax() {
        System.out.println(heap.get(1));
    }

    // Deletes max node (root) then calls heapify to update heap
    public static void deleteMax() {
        Collections.swap(heap, 1, heap.size() - 1 );
        heap.remove(heap.size()-1);
        while(!checkHeap(heap)) {
            heapify();
        }
    }

    // Insert value and update heap 
    public static void insert(int n) {
        heap.add(n);
        while(!checkHeap(heap)) {
            heapify();
        }
    }
    
}

