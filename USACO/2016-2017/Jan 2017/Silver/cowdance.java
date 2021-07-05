// "Cow Dance Show"
// http://www.usaco.org/index.php?page=viewproblem2&cpid=690
//


import java.util.*;
import java.io.*;

public class cowdance {
  public static void main(String[] args) throws Exception {
    Scanner in = new Scanner(new File("cowdance.in"));
    int n = in.nextInt();
    int maxT = in.nextInt();
    int[] cows = new int[n];
    for(int i = 0; i < n; i++) {
    	cows[i] = in.nextInt();
    }
    in.close();
    	
    int min = 1;
    int max = n;
    while(min < max) {
    	int med = (min + max) / 2;
    	if(checkStageTime(med, cows, maxT)) {
    		max = med;
    	} else {
    		min = med + 1;
    	}
    }
    PrintWriter out = new PrintWriter(new File("cowdance.out"));
    System.out.println(min);
    out.println(min);
    out.close();
  }
  
  public static boolean checkStageTime(int k, int[] cows, int maxT) {
	  PriorityQueue<Integer> finishTimes = new PriorityQueue<Integer>();
	  for(int i = 0; i < k; i++) {
		  finishTimes.add(cows[i]);
	  }
	  
	  int time = 0;
	  for(int i = k; i < cows.length; i++) {
		  time = finishTimes.poll();
		  time += cows[i];
		  if(time > maxT) return false;
		  finishTimes.add(time);
	  }
	  return true;
  }
}
