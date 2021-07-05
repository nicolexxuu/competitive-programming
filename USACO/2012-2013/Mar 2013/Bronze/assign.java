
// http://www.usaco.org/index.php?page=viewproblem2&cpid=261
// "Breed Assignment"

import java.util.*;
import java.io.*;

public class assign {

	static Cow[] cows;
	static int N;

	public static void main(String[] args) throws Exception {
		Scanner in = new Scanner(new File("assign.in"));
		N = in.nextInt();
		cows = new Cow[N + 1];
		for(int c = 1; c <= N; c++) {
			cows[c] = new Cow();
		}

		int K = in.nextInt();

		for (int i = 0; i < K; i++) {
			String sd = in.next();
			int a = in.nextInt();
			int b = in.nextInt();
			if (sd.equals("S")) {
				cows[a].same.add(b);
				cows[b].same.add(a);
			} else {
				cows[a].different.add(b);
				cows[b].different.add(a);
			}
		}
		in.close();

		int result = findCombos(1);

		PrintWriter out = new PrintWriter(new File("assign.out"));
		System.out.println(result);
		out.println(result);
		out.close();
	}

	public static int findCombos(int currCow) {
		int res = 0;
		//System.out.println("add: " + currCow);
		if (currCow > N) {
			for(int i = 1; i <= N; i++) {
				//System.out.print(cows[i].breed);
			}
			//System.out.println();
			return 1;
			
		}
		
		// loop through all possibilities for one cow and call function for each cow
		// ONLY
		
		for(int b = 1; b <= 3; b++) {
			boolean canAdd = true;
			cows[currCow].breed = b;
			if(currCow != 1) {
				for(Integer other: cows[currCow].same) {
					if(b != cows[other].breed && currCow > other) {
						canAdd = false;
						break;
					}
				}
				
				if(!canAdd) continue;
				
				for(Integer other: cows[currCow].different) {
					if(b == cows[other].breed && currCow > other) {
						canAdd = false;
						break;
					}
				}
			}
			
			if(canAdd) {
				res += findCombos(currCow + 1);
			}
		}

			return res;
	}

	public static class Cow {
		HashSet<Integer> same, different;
		int breed;

		Cow() {
			same = new HashSet<>();
			different = new HashSet<>();
			breed = 0;
		}
	}

}

/*
 * ANALYSIS 4 2 
 * S 1 2 
 * D 1 3
 * 
 * 18
 * 
 */
