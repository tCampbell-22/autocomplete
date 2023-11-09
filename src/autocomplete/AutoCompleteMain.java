package autocomplete;

import java.io.*;
import java.util.*;

public class AutoCompleteMain {
    public static void main(String[] args) {
        //int numResults = Integer.valueOf(args[0]);

        //File data = new File(args[1]);  
        String[] entries = {"10", "wiktionary.txt"};
        int numResults = Integer.parseInt(entries[0]);

        File data = new File(".//" + entries[1]); 

        ArrayList<Term> terms = new ArrayList<Term>();

        try {
            Scanner readFile = new Scanner(data, "utf-8");
            int numLines = Integer.parseInt(readFile.nextLine());
            for (int i = 0; i < numLines; i++) {
                String line = readFile.nextLine().trim();
                String[] dataLine = line.split("\t");
                terms.add(new Term(dataLine[1], Long.valueOf(dataLine[0])));
            }

            readFile.close();
        } catch (IOException e) {
            System.err.println(e);
        }
        Scanner input;
        for (int j = 0; j < 2; j++) {
            
            System.out.print("Enter a new prefix: ");
            input = new Scanner(System.in);
            String prefix = input.nextLine();
            Autocomplete a = new Autocomplete(terms);
            List<Term> matches = a.allMatches(prefix);

            System.out.println("There are " + matches.size() + " matches.");
            if (matches.size() != 0) {
                if (matches.size() < numResults) {
                    System.out.println("The matching items are: ");
                } else {
                    System.out.println("The " + numResults + " largest are: ");
                }   
            }
            
            for (int i = 0; i < numResults; i++) {
                if (i >= matches.size()) {
                    break;
                }
                System.out.println(matches.get(i));
                
            }
        }   
        
    }

}
