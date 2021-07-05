// "The Bovine Shuffle"
// http://usaco.org/index.php?page=viewproblem2&cpid=764
// times out?

import java.util.*;
import java.io.*;

public class shuffle {
  public static void main(String[] args) throws Exception {
    Scanner in = new Scanner(new File("2.in"));
    int N = in.nextInt();
    int [] cows = new int[N + 1];
    int [] paths = new int[N + 1];
    for(int i = 1; i <= N; i++) {
    	paths[i] = in.nextInt();
    }
    Arrays.fill(cows,  1);
    in.close();
    
    int result = 0;
    Set<String> prevLines = new HashSet<String>();
    boolean finished = false;
    while(!finished) {
    	step(cows, paths);
    	String temp = Arrays.toString(cows);;
    	if(!prevLines.contains(temp)) {
    		prevLines.add(temp);
    	} else {
    		for(int i = 1; i <= N; i++) {
    			if(cows[i] > 0) {
    				result++;
    			}
    			
    		}
    		finished = true;
    	}
    }

    PrintWriter out = new PrintWriter(new File("shuffle.out"));
    System.out.println(result);
    out.println(result);
    out.close();
  }
  
  static void step(int[] cows, int[] paths) {
	  int [] temp = cows.clone();
	  for(int i = 1; i < paths.length; i++) {
		  if(temp[i] > 0) {
			  cows[i] -= temp[i];
			  cows[paths[i]] += temp[i];
		  }
	  }
  }
}
