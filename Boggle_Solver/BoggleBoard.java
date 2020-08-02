/**
 * This class represents a 4 x 4 Boggle grid. In the game of Boggle, the 
 * player must try to find all of the words that can be made with adjacent 
 * letters. This class can read in from a dictionary text file to output a 
 * list of all possible word solutions on a given board. 
 *
 * @Anna Lieb
 * @Java version 1.8.0 - 5/28/20
 */

import java.util.Scanner;

public class BoggleBoard
{
    private String[][] board = new String[4][4];
    private boolean[][] usedLetters = new boolean[4][4];

    /**
     * Constructor for objects of class BoggleBoard. Creates a 4 x 4 boggle
     * grid based on user input. 
     */
    public BoggleBoard()
    {
        Scanner scan = new Scanner(System.in);
        for (int i = 0; i < 4; i++) {
            System.out.println("Enter the 4 letters in row " + (i + 1) + ": ");
            String row = scan.next().toUpperCase();
            for (int j = 0; j < 4; j++) {
                board[i][j] = row.substring(j, j + 1);
            }
        }
    }

    /**
     * Overloaded constructor for objects of class BoggleBoard. 
     * Creates a 4 x 4 boggle grid based on hardcoded values 
     * for testing purposes. 
     */
    public BoggleBoard(String[][] hardcodedInput)
    {
        board = hardcodedInput;
    }

    /**
     * Returns true if the board contains the given word, false otherwise. 
     * Has the same function as testWord, but also clears the usedLetters array. 
     */ 
    public boolean containsWord(String word) {
        word = word.toUpperCase(); // word must be in upper case

        // store indices for all locations of current letter
        String indices = getLetterIndices(word.substring(0, 1)); 
        boolean output = testWord(word, indices);
        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 4; j++) {
                usedLetters[i][j] = false; // reset the usedLetters array
            }
        }
        return output;
    }

    /**
     * Returns true if the board contains the given word, false otherwise. 
     * 
     * Recursion within a for loop creates a kind of tree. The program
     * can go back to a previous node to search a new letter branch
     * to see if the word can be made.
     */ 
    private boolean testWord(String word, String indices) {
        if (word.length() < 2) return false; // word must be at least two letters

        String currentLetter = String.valueOf(word.charAt(0));
        String nextLetter = String.valueOf(word.charAt(1));

        for (int i = 0; i < indices.length(); i += 2) {  // repeat for every valid instance of the letter to check all possibilites.
            // subtract 48 to convert from character ascii value to correct int indices
            int row = indices.charAt(i) - 48; 
            int column = indices.charAt(i + 1) - 48;

            // find adjacent letters of the given index
            String adjIndices = adjLetterIndices(row, column, nextLetter); 
            if (adjIndices.length() > 0 && word.substring(1).length() > 1 && !isUsed(row, column)) {
                usedLetters[row][column] = true;
                return testWord(word.substring(1), adjIndices);
            } else if (adjIndices.length() > 0 && word.substring(1).length() == 1 && !isUsed(row, column)) { 
                // when two letters remain
                usedLetters[row][column] = true;
                return true;
            } 
        }
        return false;
    }

    /**
     * Returns the indices for ALL locations of a given letter on the board. 
     * Returns indices as a string of integers in the form 
     * (row index 1)(column index 1)(row index 2)(column index 2)... etc
     */
    public String getLetterIndices(String letter) {
        String indices = "";
        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 4; j++) {
                if (board[i][j].equalsIgnoreCase(letter) && !isUsed(i, j)) {
                    indices += i;
                    indices += j;
                }
            }
        }
        return indices;
    }

    /**
     * Returns the indices for the locations of a given letter on the board
     * which are ADJACENT TO A GIVEN INDEX.
     * Returns an empty string if the target letter is not found 
     * adjacent to the given index.
     * Returns indices as a string of integers in the form 
     * (row index 1)(column index 1)(row index 2)(column index 2)... etc
     */
    public String adjLetterIndices(int row, int column, String targetLetter) {
        String validIndices = "";
        for (int i = row - 1; i <= row + 1; i++){
            for (int j = column - 1; j <= column + 1; j++) {
                if (i >= 0 && j >= 0 && i < 4 && j < 4 && !(i == row && j == column)) {
                    if (!isUsed(i, j) && board[i][j].equalsIgnoreCase(targetLetter)){
                        validIndices += i;
                        validIndices += j;
                    }
                }
            }
        }
        return validIndices; // return indices where valid adjacent target letter is found
    }

    /**
     * Returns true if the letter at a given row and column has already been 
     * used, false if it has not.
     */
    public boolean isUsed(int row, int column) {
        return usedLetters[row][column];
    }

    public String toString() {
        String output = "";
        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 4; j++) {
                output += board[i][j] + " ";
            }
            output += "\n";
        }
        return output;
    }
}