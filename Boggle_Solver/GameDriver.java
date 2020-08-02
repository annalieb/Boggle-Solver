/**
 * Driver for the BoggleBoard class. Displays all possible word solutions on 
 * a given board. 
 *
 * @Anna Lieb
 * @Java version 1.8.0 - 5/28/20
 */

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class GameDriver
{
    public static void main(String [] args) {
        /** Uncomment this section to do hardcoded test: 
        
        String[][] testerBoard = new String[][] {
        {"H", "R", "K", "X"},
        {"E", "L", "L", "M"},
        {"L", "C", "J", "A"},
        {"O", "Y", "O", "L"}
        };
        BoggleBoard test = new BoggleBoard(testerBoard);
        System.out.println("The current board: \n" + test);

        System.out.println(test.getLetterIndices("H")); // expected output: 00
        System.out.println(test.adjLetterIndices(2, 3, "H")); // expected output: *empty string*
        System.out.println(test.adjLetterIndices(2, 3, "M")); // expected output: 13

        System.out.println(test.containsWord("Hello")); // expected output: true
        System.out.println(test.containsWord("Hellos")); // expected output: false
        System.out.println(test.containsWord("O")); // expected output: false
        System.out.println(test.containsWord("OJ")); // expected output: true
        
         */

        // Implementation of the final product: 

        BoggleBoard board = new BoggleBoard(); // user creates boggle board with input
        System.out.println("\nThe current board: \n" + board + "\n");
        int counter = 0; // keep track of total words found

        // check against dictionary
        File dictionary = new File("dictionary.txt"); // text file containing a scrabble dictionary
        // dictionary source: https://github.com/redbo/scrabble/blob/master/dictionary.txt
        try {
            Scanner scan = new Scanner(dictionary);
            while (scan.hasNextLine()) {
                String dictWord = scan.nextLine();
                if (board.containsWord(dictWord)) {
                    // print the word if it's on the board
                    System.out.println(dictWord);
                    counter++;
                }
                else {
                    String nextWord = scan.nextLine();
                    while (nextWord.contains(dictWord)) {
                        // skip over a word in the dictionary if it contains
                        // a word that is already shown to not be on the board
                        nextWord = scan.nextLine();
                    }
                    if (board.containsWord(nextWord)) {
                        System.out.println(nextWord);
                        counter++;
                    }
                }
            }
            scan.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        System.out.println("The computer found " + counter + " valid words. ");
    }
}
/**
 * Sample output: 
Enter the 4 letters in row 1: 
asdf
Enter the 4 letters in row 2: 
qwer
Enter the 4 letters in row 3: 
dfgh
Enter the 4 letters in row 4: 
erty

The current board: 
A S D F 
Q W E R 
D F G H 
E R T Y 


AS
AW
AWE
AWED
AWES
DE
DEF
DEFER
DEFT
DEW
DEWS
DREG
DREW
ED
EDS
EF
EFT
EH
ER
ERG
ES
FE
FED
FEDS
FEH
FER
FES
FEW
GED
GEDS
GREW
HE
HEFT
HEFTY
HER
HERD
HERDS
HES
HEW
HEWS
RE
RED
REDS
REF
REFED
REFER
REFT
REG
RES
RESAW
SAW
SAWED
SAWER
SEG
SER
SERF
SEW
THE
THEW
THEWS
THREW
THY
TREF
WAS
WE
WED
WEDS
WEFT
The computer found 68 valid words. 

 */