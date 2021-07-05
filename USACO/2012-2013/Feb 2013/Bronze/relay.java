
// This template code suggested by KT BYTE Computer Science Academy
//   for use in reading and writing files for USACO problems.

// https://content.ktbyte.com/problem.java

//http://www.usaco.org/index.php?page=viewproblem2&cpid=241
//"Message Relay"

import java.util.*;
import java.io.*;

public class relay {
	public static void main(String[] args) throws FileNotFoundException {
		Scanner in = new Scanner(new File("relay.in"));
		int N = in.nextInt();
		int[] cowPaths = new int[N + 1];
		boolean [] lCows = new boolean[N + 1];
		for(int i = 1; i <= N; i++) {
			cowPaths[i] = in.nextInt();
		}
		in.close();
		
		int result = 0;
		for(int cow = 1; cow <= N; cow++) {
			boolean loopy = false;
			if(cowPaths [cow] != 0) {
				Set<Integer> prevCows = new HashSet<>();
				prevCows.add(cow);
				int leadsTo = cow;
				for(int i = 1; i <= N; i++) {
					leadsTo = cowPaths[leadsTo];
					if(leadsTo == 0) {
						break;
					} else if(lCows[leadsTo] || prevCows.contains(leadsTo)) {
						loopy = true;
						lCows[cow] = true;
						break;
					} else {
						prevCows.add(leadsTo);
					}
				}
			}
			
			if(!loopy) result++;
		}
		PrintWriter out = new PrintWriter(new File("relay.out"));
		System.out.println(result);
		out.println(result);
		out.close();
	}
}


/* ANALYSIS
 * 
 * if a specific cow reaches a cow already marked as loopy, it is safe to say that specific cow is also loopy
 * 
There is one more nice "trick" that is worth mentioning in the context of this problem. 
In order to detect whether we have entered a loop, the most obvious thing to do is to mark cows we've visited, stopping once we reach a marked cow. 
However, an alternative method for doing this is to walk down the list twice at the same time, once at normal speed and the second time at twice our normal speed.
Think of this as sending one message at normal speed and another at twice normal speed, both moving at the same time. If the fast message catches the slow message, we know we are in a loop!




*/
