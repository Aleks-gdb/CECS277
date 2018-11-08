/*
 * Part One
 * a.This program will work with maps. Use the attached file “QnotfollowedbyU”. 
 *   It contains the words that start with Q not followed by U.
 * b.Read this file into a TreeMap data structure and a HashMap data structure.
 * c.Time the results. Which data structure is more efficient for loading the 
 *   data?
 * d.Create a second file that contains each of the letters in the alphabet 
 *   and their scrabble point value. You can find the file at Scrabble Site.
 * e.For each of the words in the file, find their point value and print it out.
 * f.Time the results. Which data structure is more efficient for searching 
 *   data?
 * g.Important: You cannot use both Collection objects in the run of the program. 
 *   You must run the program twice -- once for each data structure
 */

import java.util.*;
import java.io.*;
public class PartOne
{
    public static void main(String[] args)
    {
        char choice, cont;
        System.out.println("Hello");
        do
        {
            Scanner scan = new Scanner(System.in);
            System.out.println("Which structure would you like to use:\na)TreeMap\nb)HashMap");
            choice = scan.next().charAt(0);
            choice = Character.toLowerCase(choice);
            if(choice == 'a')
                TreeMapRunner();
                else
                if(choice == 'b')
                    HashMapRunner();
                    else
                            System.out.println("Whoops! Your choice is not on the list.");        
            System.out.println("Would you like to select again? (y/n)");
            cont = scan.next().charAt(0);
            while(Character.toLowerCase(cont) != 'y' && Character.toLowerCase(cont) != 'n')
              {
                  System.out.println("Whoops! That was unclear. Would you like to continue? (y/n)");
                  cont = scan.next().charAt(0);
              }
        }while(Character.toLowerCase(cont) == 'y');
        System.out.println("Goodbye!");
    }
    
    public static void TreeMapRunner()
    {
        long time1, time2, time3, time4;
        int key = 1;
        time1 = System.nanoTime();
        Map tMap = new TreeMap();
        Map tMap2 = new TreeMap();
        try
        {
            BufferedReader file = new BufferedReader(new FileReader("QnotfollowedbyU.txt"));
            String word = file.readLine();
            while (word != null)
            {
                tMap.put(word,key);
                key++;
                word = file.readLine();
            }
            file.close();
        }
        catch(FileNotFoundException e)
        {
            System.out.println("No file found.");
        }
        catch(IOException e)
        {
            System.out.println("IOException thrown.");
        }

        time2=System.nanoTime();
        System.out.println("TreeMap: Time for the operation is: " + (time2-time1) + " nanosecond(s).");
        
        try
        {
            BufferedReader file = new BufferedReader(new FileReader("Scrable.txt"));
            String word = file.readLine();
            while (word != null)
            {
                char letter = word.charAt(0);
                int value = Character.getNumericValue(word.charAt(2));
                if(word.charAt(2) == '0')
                    value = 10;
                tMap2.put(letter,value);
                word = file.readLine();
            }
            file.close();
        }
        catch(FileNotFoundException e)
        {
            System.out.println("No file found.");
        }
        catch(IOException e)
        {
            System.out.println("IOException thrown.");
        }

        Set<Map.Entry<String, Integer>> s = tMap.entrySet();
        Set<Map.Entry<Character, Integer>> s2 = tMap2.entrySet();
        int wordValue;
        time3 = System.nanoTime();
        for(Map.Entry<String, Integer> tMapSet : s)
        {
            String entry = (String) tMapSet.getKey();
            char currentLetter;
            wordValue = 0;
            for(int i = 0; i < entry.length(); i++)
            {
                currentLetter = entry.charAt(i);
                for(Map.Entry<Character, Integer> tMap2Set : s2)
                {
                    if(currentLetter == (char) Character.toLowerCase(tMap2Set.getKey()))
                        wordValue += (int) tMap2Set.getValue();
                }
            }
            tMapSet.setValue(wordValue);
        }
        time4 = System.nanoTime();
        System.out.println(tMap);
        System.out.println("TreeMap: Time for finding the word value is: " + (time4-time3) + " nanosecond(s).");
    }
    
    public static void HashMapRunner()
    {
        long time1, time2, time3, time4;
        int key = 1;
        time1 = System.nanoTime();
        Map hMap = new HashMap();
        Map hMap2 = new HashMap();
        try
        {
            BufferedReader file = new BufferedReader(new FileReader("QnotfollowedbyU.txt"));
            String word = file.readLine();
            while (word != null)
            {
                hMap.put(word,key);
                key++;
                word = file.readLine();
            }
            file.close();
        }
        catch(FileNotFoundException e)
        {
            System.out.println("No file found.");
        }
        catch(IOException e)
        {
            System.out.println("IOException thrown.");
        }
        
        time2=System.nanoTime();
        System.out.println("HashMap: Time for the operation is: " + (time2-time1) + " nanosecond(s).");
        
        try
        {
            BufferedReader file = new BufferedReader(new FileReader("Scrable.txt"));
            String word = file.readLine();
            while (word != null)
            {
                char letter = word.charAt(0);
                int value = Character.getNumericValue(word.charAt(2));
                if(word.charAt(2) == '0')
                    value = 10;
                hMap2.put(letter,value);
                word = file.readLine();
            }
            file.close();
        }
        catch(FileNotFoundException e)
        {
            System.out.println("No file found.");
        }
        catch(IOException e)
        {
            System.out.println("IOException thrown.");
        }

        Set<Map.Entry<String, Integer>> s = hMap.entrySet();
        Set<Map.Entry<Character, Integer>> s2 = hMap2.entrySet();
        int wordValue;
        time3 = System.nanoTime();
        for(Map.Entry<String, Integer> hMapSet : s)
        {
            String entry = (String) hMapSet.getKey();
            char currentLetter;
            wordValue = 0;
            for(int i = 0; i < entry.length(); i++)
            {
                currentLetter = entry.charAt(i);
                for(Map.Entry<Character, Integer> hMap2Set : s2)
                {
                    if(currentLetter == (char) Character.toLowerCase(hMap2Set.getKey()))
                        wordValue += (int) hMap2Set.getValue();
                }
            }
            hMapSet.setValue(wordValue);
        }
        time4 = System.nanoTime();
        System.out.println(hMap);
        System.out.println("HashMap: Time for finding the word value is: " + (time4-time3) + " nanosecond(s).");
    }
}
