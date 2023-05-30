/*
 * INSTRUCTION:
 *     This is a Java starting code for hw5_3.
 *     When you finish the development, download this file.
 *     Note that the current filename is "Main.java".
 *     But rename it to "Main_hw5_3.java".
 *     After that, upload the renamed file on Canvas.
 */

// Finish the head comment with Abstract, Name, and Date.
/*
 * Title: Main_hw5_3.java
 * Abstract: This program creates a hash table determined by input size and utilizes linear probing.
 * Commands can be given to insert a value, display value at index, display table size and search for a value
 * Name: Nicholas Fotinakes
 * Date: 12/6/2022
 */

import java.util.ArrayList;
import java.util.Scanner;
 
class Main 
{

  // Static values to act as global variables
    static int modValue;
    static int[] hashTable;
    // All possible primes that could be used for this program
    static int[] primes = {2, 3, 5, 7, 11, 13, 17, 19, 23, 29, 31, 37, 41, 43, 47, 53, 59, 61, 67, 71, 73,
            79, 83, 89, 97, 101, 103, 107, 109, 113, 127, 131, 137, 139, 149, 151, 157, 163, 167, 173, 179,
            181, 191, 193, 197, 199};

    public static void main(String[] args) {
        // Get modulo value and create hash table
        Scanner input = new Scanner(System.in);
        modValue = input.nextInt();
        hashTable = new int[modValue];
        populateArray();

        // Get commands and store in command array
        int commandSize = input.nextInt();
        String[] commands = new String[commandSize];
        input.nextLine();
        int count = 0;
        while (count < commandSize) {
            String command = input.nextLine();
            commands[count] = command;
            count++;
        }

        // Execute commands via array order
        // calling corresponding methods
        for (String command : commands) {
            if (command.contains("tableSize")) {
                System.out.println(tableSize());
            } else if (command.contains("search")) {
                String numInput = command.replaceAll("\\D+", "");
                int num = Integer.parseInt(numInput);
                System.out.println(search(num));
            } else if (command.contains("display")) {
                String numInput = command.replaceAll("\\D+", "");
                int num = Integer.parseInt(numInput);
                displayStatus(num);
            } else if (command.contains("insert")) {
                String numInput = command.replaceAll("\\D+", "");
                int num = Integer.parseInt(numInput);
                insert(num);
            }
        }
        input.close();

    }

    // Insert value into hash table
    public static void insert(int n) {
        
        // Before insert, calculate updated load
        // Store values in a hold array in case rehash neccesary
        ArrayList<Integer> holdArray = new ArrayList<>();
        double values = 0.0;
        for (int i = 0; i < hashTable.length; i++) {
            if (hashTable[i] > -1) {
                holdArray.add(hashTable[i]);
                values++;
            }
        }
        holdArray.add(n);
        values++;
        double load = values / hashTable.length;

        // If load exceeded, rehash table using holdArray
        // Otherwise insert value into table using linear probing
        if (load > 0.5) {
            rehash(holdArray);
        } else {
            int index = n % modValue;
            while(hashTable[index] != -1) {
                if (index == hashTable.length - 1) {
                    index = 0;
                } else {
                    index++;
                }
            }
            hashTable[index] = n;
        }
    }


    public static void rehash(ArrayList<Integer> A) {
        
        int nextPrime = 0; // to get new prime to create table
        int newSize = hashTable.length * 2; // Get double size of current table
      
        // find next prime
        int count = 0;
        while( nextPrime < newSize ) {
            nextPrime = primes[count];
            count++;
        }
        modValue = nextPrime; // Set new mod value
        hashTable = new int[modValue]; // create new hash table with updated size
        populateArray();
        // insert each value back into new hashtable with new mod value
        // using values from A holding array passed 
        for (int i = 0; i < A.size(); i++) {
            insert(A.get(i));
        }
    }

    // Get table size
    public static int tableSize() {
        return hashTable.length;
    }

    // Display status of entry in table
    public static void displayStatus(int n) {
        int index = n % modValue;
        if (hashTable[index] >= 0) {
            System.out.println(hashTable[index]);
        } else {
            System.out.println("Empty");
        }
    }

    // Search for value in hash table
    public static String search (int n) {

        // If index has a value, but not the one searched
        // check if next adjacent spot has value (loop around if at end of table)
        int index = n % modValue;
        if (hashTable[index] == n) {
            return n + " Found";
        } else {
            while (index < hashTable.length && hashTable[index] != -1) {
                if (hashTable[index] == n) {
                    return n + " Found";
                }
                if (index == hashTable.length - 1) {
                  index = 0;
                } else {
                  index++;
                }
                
            }
        }
        return n + " Not found";
    }

    public static void populateArray() {
        for(int i = 0; i < hashTable.length; i++) {
            hashTable[i] = -1;
        }
    }
    
  
}

