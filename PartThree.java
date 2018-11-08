
/*
 * Part Three
 * a.A Scavenger Hunt is a party game that people play in teams. They are 
 * given a list of items to find throughout the surrounding neighborhood. 
 * Our challenge is to find the best data structure to store the results of 
 * the hunt.
 * b.Create a list of 100 items that you might have people find on the hunt. 
 * These should be items that they have a chance of finding throughout the 
 * neighborhood. For example, yesterday's newspaper, a shoelace, a paperbag,
 * .... Load the list anyway you want (console input, flatfile, etc.)
 * •You will run the program two times, once using a set of ArrayLists, and 
 * once using a set of LinkedLists. Our purpose is to find the more efficient 
 * data structure to use for a scavenger hunt.
 * •Use an Iterator to traverse the Collection from beginning to end. Then 
 * iterate through the Collection from end to beginning (backwards). 
 * •Time the results. Which data structure is more efficient for looping 
 * through the entire Collection?
 * •Ask the user how many teams will play the game. Create this number of 
 * teams. For each team load all of the items from the list. Shuffle the list 
 * after loading the items each time. Find the total time it takes to add the 
 * items to all of the teams.
 * •Ask the user what position in the list to be used for retrieving and 
 * inserting elements. Retrieve that element from each of the teams. Find the 
 * total time it takes.
 * •Next insert a new element at that position in each of the lists. Find the 
 * total time it takes.
 * •Use the Random class to generate a number between 0 and the 100. Find the 
 * element in the scavenger hunt list at that position. Retrieve that element 
 * from each of the lists. Find the total time it takes.•Display the time in 
 * milliseconds for each of these operations.
 * •Run the program several times with different input values and see how it 
 * affects the output.
 * •Important: You cannot use both Collection objects in the run of the 
 * program. You must run the program twice -- once for each data structure
 */

import java.util.*;
import java.io.*;
public class PartThree {

    public static void main(String[] arg) 
    {
        String c = "";
        boolean b = true;
        int pos = 0;
        char cont;
        Scanner console = new Scanner(System.in);
        System.out.println("Which list would you like to use? (array/linked)");
        while(b) {
        c = console.next();
        if(c.equals("array")) {
        System.out.println("For ArrayList: Time to iterate through the list forward and backward: " + ArrList("iterate",pos) + " nanoseconds.");
        b = false;
        }
        else if ( c.equals("linked")) {
        System.out.println("For LinkedList: Time to iterate through the list forward and backward: " + Llist("iterate",pos) + " nanoseconds.");
        b = false;
        }
        else {
            System.out.println("That is not a choice. Please choose (array/linked)");
        }
        }
        run(c);
        console.close();
    }
    public static void run(String b) {
        String c2 = "";
        int NumOfTeam = 0;
        long Total = 0;
        int pos = 0;
        boolean cat = true;
        boolean cat2 = true;
        boolean cat3 = true;
        boolean cat4 = true;
        Scanner console = new Scanner(System.in);
        while(cat) {
        try {
        System.out.println("How many teams would you like?");
        NumOfTeam = console.nextInt();
        cat = false;
        }
        catch(InputMismatchException e) {
            System.out.println("Please enter an integer.");
            console.next();
        }
        }
        if(b.equals("array")) {
        for (int i = 0; i < NumOfTeam; i++) {
            Total += (long) ArrList("shuffle",pos);
        }
        
        System.out.println("Total time it takes to add items for all of the teams to an ArrayList and shuffle them: " + Total  + " nanoseconds.");
        Total = 0;
        }
        else if(b.equals("linked")) {
        for (int i = 0; i < NumOfTeam; i++) {
            Total += (long) Llist("shuffle",pos);         
        }
        
        System.out.println("Total time it takes to add items for all of the teams to a LinkedList and shuffle them: " + Total  + " nanoseconds.");
        }
        System.out.println("What position in the list would you like to be used for retrieving or inserting elements?");
        while(cat2) {
        if(console.hasNextInt()) {
        pos = console.nextInt();
        }
        else {
            System.out.println("Please enter a number");
            console.next();
            continue;
        }
        if(pos > 99)
        {
            System.out.println("Whoops! The position does not exist. Pick a number from 0-99.");
            continue;
        }
        cat2 = false;
        }
        System.out.println("Would you like to add or get from that position? (add/get)");
        while(cat4)
        {
        if(console.hasNext() && (console.hasNext("get") || console.hasNext("add"))) {
        c2 = console.next();
        }
        else {
            System.out.println("Sorry that is not a choice. Please choose (add/get)");
            console.next();
            continue;
        }
        cat4 = false;
        }
        
        GetAndAdd(NumOfTeam,pos,b,c2, true);
        System.out.println("Lets generate a random number 0-99");
        Random rand = new Random();
        int ran = rand.nextInt(100);
        System.out.println("The random number is " + ran + ".");
        GetAndAdd(NumOfTeam,ran,b,c2, false);
        console.close();
    }
    
    public static void GetAndAdd(int NumOfTeam, int pos, String b,String c2, boolean r) {
        long Total = 0;
        if(b.equals("array")) 
        {
            if(c2.equals("add")) {
                for(int i = 0; i < NumOfTeam; i++) {
                    Total += (long) ArrList("add",pos);
                }
                System.out.println("New item was added to the list");
                System.out.println("For ArrayList: Total time to add and item in position " + pos + " for " + NumOfTeam + " teams is: " + Total + " nanoseconds.");
                Total = 0;
            }
            if(c2.equals("get")) {
                for(int i = 0; i < NumOfTeam; i++) {
                    Total += (long) ArrList("get",pos);
                }
                System.out.println("The item in position " + pos + " is " + ArrList("getandreturn", pos) + ".");
                System.out.println("For ArrayList: Total time to get and item in position " + pos + " for " + NumOfTeam + " teams is: " + Total + " nanoseconds.");
                if(r == true)
                GetAndAdd(NumOfTeam, pos, b, "add", false);
                Total = 0;
            }
        }
        
        if(b.equals("linked")) {
            if(c2.equals("add")) {
                for(int i = 0; i < NumOfTeam; i++) {
                    Total += (long) Llist("add",pos);
                }
                System.out.println("New item was added to the list");
                System.out.println("For LinkedList: Total time to add and item in position " + pos + " for " + NumOfTeam + " teams is: " + Total + " nanoseconds.");
                Total = 0;
            }
            if(c2.equals("get")) {
                for(int i = 0; i < NumOfTeam; i++) {
                    Total += (long) Llist("get",pos);
                }
                System.out.println("The item in position " + pos + " is: " + Llist("getandreturn", pos) + ".");
                System.out.println("For LinkedList: Total time to get and item in position " + pos + " for " + NumOfTeam + " teams is: " + Total + " nanoseconds.");
                if(r == true)
                GetAndAdd(NumOfTeam, pos, b, "add", false);
                Total = 0;
            }
            }
    }
    public static Object ArrList(String c, int p) {
        ArrayList<String> list = new ArrayList<String>();
        long estimatedTime = 0;
        long startTime = 0;
        startTime = System.nanoTime();
        try { 
            BufferedReader file = new BufferedReader(new FileReader("hunt.txt"));
            String word = file.readLine();         
            while(word != null) {
                list.add(word);
                word = file.readLine();
            }
            file.close();
            if(c == "iterate") {
                ListIterator<String> ArrIt = list.listIterator();
                while(ArrIt.hasNext()) {
                    ArrIt.next();
                }
            
                while(ArrIt.hasPrevious()) {
                    ArrIt.previous();
                }
                return estimatedTime = System.nanoTime() - startTime;
            }
            if(c == "shuffle") { 
                Collections.shuffle(list);
                return estimatedTime = System.nanoTime() - startTime;   
            }
            if(c == "get") {
                startTime = System.nanoTime();
                list.get(p);
                return estimatedTime = System.nanoTime() - startTime;
            }
            if(c == "add") {
                startTime = System.nanoTime();
                list.add(p, "Shovel");
                return estimatedTime = System.nanoTime() - startTime ;
            }
            if(c == "getreturn") {
                System.out.print(list.get(p));
            }
        
        }
        catch(FileNotFoundException e) {
            System.out.println("File not Found");
        }
        catch(IOException e) {
            System.out.println("IOException Throw.");
        }
        
        return (String) list.get(p);
    }
    public static Object Llist(String c,int p) {
        LinkedList<String> list2 = new LinkedList<String>();
        long estimatedTime = 0;
        long startTime = 0;
        startTime = System.nanoTime();
        try { 
            BufferedReader file = new BufferedReader(new FileReader("hunt.txt"));
            String word = file.readLine(); 
            while(word != null) {
                list2.add(word);
                word = file.readLine();
            }
            file.close();
        }
        catch(FileNotFoundException e) {
            System.out.println("File not Found");
        }
        catch(IOException e) {
            System.out.println("IOException Throw.");
        }
        ListIterator<String> ListIt = list2.listIterator();
        if ( c == "iterate") 
        {
            while(ListIt.hasNext()) {
                ListIt.next();
            }
            while(ListIt.hasPrevious()) {
                ListIt.previous();
            }
            return estimatedTime = System.nanoTime() - startTime;
        }
        if(c == "shuffle") { 
            Collections.shuffle(list2);
            return estimatedTime = System.nanoTime() - startTime;
        }
        if(c == "get") {
            startTime = System.nanoTime();
            list2.get(p);
            return estimatedTime = System.nanoTime() - startTime;
        }
        if(c == "add") {
            startTime = System.nanoTime();
            list2.add(p,"shove");
            return estimatedTime = System.nanoTime() - startTime;
        }
        return (String) list2.get(p);
    }
}

