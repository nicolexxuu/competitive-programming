
// This template code suggested by KT BYTE Computer Science Academy
//   for use in reading and writing files for USACO problems.

// https://content.ktbyte.com/problem.java

//http://www.usaco.org/index.php?page=viewproblem2&cpid=132
//"Islands", 2012 US Open Bronze Division

import java.util.*;
import java.io.*;

public class islands {
	public static void main(String[] args) throws FileNotFoundException {
		Scanner in = new Scanner(new File("islands.in"));
		int N = in.nextInt();
		Island[] regions = new Island[N + 2];
		for(int i = 1; i <= N; i++) {
			int height = in.nextInt();
			regions[i] = new Island(i, height);
		}
		regions[0] = new Island(0, 0);
		regions[N + 1] = new Island(N + 1, 0);

		Island[] inOrder = Arrays.copyOf(regions, N + 2);
		Arrays.sort(regions);
		in.close();

		int result = 1;
		int max = 1;
		int lastVal = 0;
		//don't update result until all ties are looped through!
		/*
		 *   - adjacent regions are higher
			  - adjacent regions are lower
			  - left lower, right higher
			  - left higher, right lower
		 */

		boolean tie = false;
		for(int island = 2; island <= N + 1; island++) {
			int currHeight = regions[island].h;
			int currI = regions[island].i;
			if(inOrder[currI + 1].h > currHeight) {
				if(inOrder[currI - 1].h > currHeight) {
					result++;
				} else {
					if(tie) {
						if(lastVal > currHeight) {
							result--;
						}
					}
				}
				tie = false;
			} else if(inOrder[currI + 1].h < currHeight){
				if(inOrder[currI - 1].h > currHeight) {

				} else {
					if(tie) {
						if(lastVal < currHeight) {
							result--;
						}
					} else {
						result--;
					}
				}
				tie = false;
			} else { //tie
				if(!tie) lastVal = inOrder[island - 1].h;
				tie = true;

			}

			max = Math.max(max, result);
		}

		PrintWriter out = new PrintWriter(new File("islands.out"));
		out.println(max);
		out.close();
	}
	
	static class Island implements Comparable<Island> {
	    int h, i;

	    Island(int index, int height) {
	    	i = index;
	    	h = height;
	    }
	    public int compareTo(Island other) {
	    	return this.h - other.h;
	    }
	}
}


/* ANALYSIS

brute force:

loop through all possible water levels (1 billion), for each one
OR
loop through all water levels that exactly flood a region (100,000), for each one
  start island count at 0
  loop through all regions (left to right) (100,000)
    every time we move from one underwater to one that's above water,
        count 1 more island
  maximize island count

overall: 100,000 * 100,000 = 10 bil


improved:

sort regions first
loop through flood levels in lowest-highest order (100k)
  

0 1 2 3 4 5 6  7     <-- region indexes
               -
        - -   /
       /   \ /
  -   -     -
-/ | |             
   \ /             <-- region #2 is now flooded, the regions on either side aren't,
    -                           so total islands -> 2
                   <--- flood level has 1 island (entire farm is above water)

when 0 goes under, island count  doesn't change, because only right side was above water

next regions to go under (all at same time): 1, 3, 6
when region 1 goes under, we lose an island - count is = 1 (but there are ties)
when region 3 goes under, nothing changes (still have flood on left, island on right)
when region 6 goes under, island count goes up (higher land on both sides), count = 2

(now that all ties have been handled, check the actual result for maximization)

4,5 go underwater together
we could use the rule that 4 is "lower" than 5 simply by being on the left, therefore
    there is no change at region 4 (left lower, right higher)
at 5, left side 4 is "lower", 6 is forreal lower, so 5 goes under, island -> 1

(now we maximize again)

then 7 goes under, that's it (islands = 0)


when you get to a region that's going under, 4 cases:
  - adjacent regions are higher
  - adjacent regions are lower
  - left lower, right higher
  - left higher, right lower




- -
   \    <-- if water level drown the region below, islands don't change
    -
     \
      -





*/
