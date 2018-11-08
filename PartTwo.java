
/*
 * Part Two
 * a.Insert all words from a large file such as the novel Alice in Wonderland 
 *   “included in the dropbox”, into a hash set and a tree set. 
 * b.Time the results. Which data structure is more efficient for inserting 
 *   data?
 * c.Choose a word that is in the novel and record the time it takes to search
 *   for that word 100 times.  
 * d.Time the results. Which data structure is more efficient for searching 
 *   data?
 * e.Important: You cannot use both Collection objects in the run of the 
 *   program. You must run the program twice -- once for each data structure
 */

import java.io.*;
import java.util.*;

public class PartTwo
{
    public static void main(String[] args)
    {
        char choice, cont;
        System.out.println("Hello");
        do
        {
            Scanner scan = new Scanner(System.in);
            System.out.println("Which structure would you like to use:\na)HashSet\nb)TreeSet");
            choice = scan.next().charAt(0);
            choice = Character.toLowerCase(choice);
            if(choice == 'a')
                HashSetRunner();
                else
                if(choice == 'b')
                    TreeSetRunner();
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
    
    public static void HashSetRunner()
    {
        long time1, time2, time3, time4;
        time1 = System.currentTimeMillis();
        Set hSet = new HashSet();
        try
        {
            Scanner file = new Scanner(new File("alice30.txt"));
            while (file.hasNextLine())
            {
                Scanner word = new Scanner(file.nextLine());
                while(word.hasNext())
                {
                    String w = word.next();
                    hSet.add(w);
                }
            }
        }
        catch(FileNotFoundException e)
        {
            System.out.println("No file found.");
        }
        catch(IOException e)
        {
            System.out.println("IOException thrown.");
        }
        time2=System.currentTimeMillis();
        System.out.println("HashSet: Time for the operation is: " + (time2-time1) + " millisecond(s).");
        
        int count = 0;
        Iterator<String> iter = hSet.iterator();
        time3 = System.nanoTime();
        for(int i = 0; i < 100; i++)
        {
            while(iter.hasNext())
            {
                String currentWord = iter.next();
                if(currentWord.equals("Alice"))
                    count++;
            }
        }
        time4 = System.nanoTime();
        System.out.println("Searching for \"Alice\" 100 times takes: " + (time4-time3) + " nanosecond(s).");
    }
    
    public static void TreeSetRunner()
    {
        long time1, time2, time3, time4;
        time1 = System.currentTimeMillis();
        Set tSet = new TreeSet();
        try
        {
            Scanner file = new Scanner(new File("alice30.txt"));
            while (file.hasNextLine())
            {
                Scanner word = new Scanner(file.nextLine());
                while(word.hasNext())
                {
                    String w = word.next();
                    tSet.add(w);
                }
            }
        }
        catch(FileNotFoundException e)
        {
            System.out.println("No file found.");
        }
        catch(IOException e)
        {
            System.out.println("IOException thrown.");
        }
        time2 = System.currentTimeMillis();
        System.out.println("TreeSet: Time for the operation is: " + (time2-time1) + " millisecond(s).");
        
        int count = 0;
        Iterator<String> iter = tSet.iterator();
        time3 = System.nanoTime();
        for(int i = 0; i < 100; i++)
        {
            while(iter.hasNext())
            {
                String currentWord = iter.next();
                if(currentWord.equals("Alice"))
                    count++;
            }
        }
        time4 = System.nanoTime();
        System.out.println("Searching for \"Alice\" 100 times takes: " + (time4-time3) + " nanosecond(s).");
    }
}
