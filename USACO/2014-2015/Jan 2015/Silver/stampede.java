// http://www.usaco.org/index.php?page=viewproblem2&cpid=511
// stampede


// arrays
//    good if you're looking up things by index
//    good if you have a big collection and only need to sort it once
//           (can search again later)
//    List/ArrayList - works like an array, but easier to add elements later
//   
// Hashing - good for speed if no sorting is needed
// Trees - good for sorting, still quite fast
//    - good if you're adding and removing multiple times
// Set - simple collections; everything is "in or out"
// Map - if you need to find out other data linked to a key

// PriorityQueue - good if you ONLY need to lookup/remove the "lowest"/earliest data
//      point every time you access it

import java.util.*;
import java.io.*;

public class stampede {
  public static void main(String[] args) throws FileNotFoundException {
    Scanner in = new Scanner(new File("stampede.in"));
    int n = in.nextInt();
    Cow[] cows = new Cow[n];
    for(int i = 0; i < n; i++) {
    	cows[i] = new Cow(in);
    }
    in.close();
    
    Arrays.sort(cows, new Comparator<Cow>() {
    	public int compare(Cow a, Cow b) {
    		if(a.enterLOS < b.enterLOS) return -1;
    		else if(a.enterLOS > b.enterLOS)return 1;
    		return 0;
    	}
    });
    
    Set<Cow> seen = new HashSet<Cow>();
    TreeSet<Cow> inLOS = new TreeSet<>( (a, b) -> a.y - b.y);
    PriorityQueue<Cow> leavingCows = 
		new PriorityQueue<>(n/2, (a,b) -> a.leaveLOS - b.leaveLOS);
    
    for(int i = 0; i < n; i++) {
    	while(leavingCows.size() > 0 && leavingCows.peek().leaveLOS < cows[i].enterLOS) {
    		Cow leaving = leavingCows.poll();
    		inLOS.remove(leaving);
    		
    		
    		if(leavingCows.size() > 0 && leavingCows.peek().leaveLOS  > leaving.leaveLOS) {
    	
    		seen.add(inLOS.first());
    		}
		}
    	
    	while(leavingCows.size() > 0 && leavingCows.peek().leaveLOS == cows[i].enterLOS) {
    		Cow leaving = leavingCows.poll();
    		inLOS.remove(leaving);
    		
		}
    	
    	inLOS.add(cows[i]);
    	leavingCows.add(cows[i]);
    	
    	if(i == n - 1 || cows[i + 1].enterLOS > cows[i].enterLOS) {
    		seen.add(inLOS.first());
    	}
    }
    
    
    //
    int result = seen.size();
    PrintWriter out = new PrintWriter(new File("stampede.out"));
    System.out.println(result);
    out.println(result);
    out.close();
  }

  static class Cow {
    int enterLOS, leaveLOS;
    int y;

    Cow(Scanner in) {
      int x = in.nextInt();        // left side of cow
      y = in.nextInt();
      int slowness = in.nextInt();

      leaveLOS = (0 - x) * slowness;
      enterLOS = leaveLOS - slowness;

    }
  }
}



/* ANALYSIS

3
-2 1 3
-3 2 3
-5 100 1

      -5 -4 -3 -2 -1  0
y=100  ---            |                 1   enters LOS at 4, leaves at 5
y=2          ---      |        slowness=3   enters LOS at 2*3=6,  leaves 3*3=9
y=1             ---   |                 3                     3              6
                    FJ (0,0)

t       0 1 2 3 4 5 6 7 8 9
y=100           *--
y=2                 *------
y=1           *------

outline:
loop through COW ENTERING LOS times in order
   before the next cow enters LOS, also check which cows have left line of sight
     whenever a cow enters or leaves LOS, check which cow is in front,
     count it if not counted
*/
