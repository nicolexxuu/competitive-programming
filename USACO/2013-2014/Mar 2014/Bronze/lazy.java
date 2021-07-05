
// http://www.usaco.org/index.php?page=viewproblem2&cpid=413
// "The Lazy Cow"

// This template code suggested by KT BYTE Computer Science Academy
//   for use in reading and writing files for USACO problems.

// https://content.ktbyte.com/problem.java

import java.util.*;
import java.io.*;

public class lazy {
	public static void main(String[] args) throws FileNotFoundException {
		Scanner in = new Scanner(new File("lazy.in"));
		int n = in.nextInt();
		int k = in.nextInt();
		
		//TreeMap: key = position, value = grass;
		Patch[] field = new Patch[n];
		for(int i = 0; i < n; i++) {
			field[i] = new Patch(in);
		}
		in.close();
		
		Arrays.sort(field);
		
		int result = 0;
		
		int windowTotal = 0;
		
		int leftmostPatch = 0;
		int rightmostLimit = 0;
		
		while(rightmostLimit < n && field[rightmostLimit].x - field[leftmostPatch].x <= 2 * k) {
			windowTotal += field[rightmostLimit].g;
			rightmostLimit++;
		}
		
		result = windowTotal;
		while(rightmostLimit < n) {
			windowTotal -= field[leftmostPatch].g;
			leftmostPatch++;
			while(rightmostLimit < n && field[rightmostLimit].x - field[leftmostPatch].x <= 2 * k) {
				windowTotal += field[rightmostLimit].g;
				rightmostLimit++;
			}
			
			result = Math.max(result, windowTotal);
		}
		
		
		PrintWriter out = new PrintWriter(new File("lazy.out"));
		System.out.println(result);
		out.println(result);
		out.close();
	}
	
	static class Patch implements Comparable<Patch>{
		int x, g;
		
		Patch( Scanner in ){
			g = in.nextInt();
			x = in.nextInt();
		}
		
		public int compareTo(Patch other) {
			return this.x - other.x;
		}
	}
}


/* ANALYSIS

4 3   <--  n=4 patches,   Bessie needs patches within k=3 units of her position

4 7     <-- g_0 = 4 units of grass   at  x_0 = 7 position
10 15
2 2
5 1


   1   2                  7                        15  <-- xs
   5   2                  4                        10  <-- gs
   ------------B-----------  total 11 = 5+2+4
              b=4
       ------------B----------- total 6 = 11 - 5 + 0
                  b=5
           ------------B----------- total = 4 =  6 - 2 + 0  <-- why bother stopping here
                      b=6                                       why not go as far right
                          ------------B-----------           as we can without losing
                                     b=10                    any more grass
                                                   ------------B-----------
                                                              b=18


                             ------------B-----------
                             bmin=9     b=12     bmax=15
                                        ------------B-----------
                                                   b=15
                                                   ------------B-----------  total 10
                                                              b=18

BRUTE FORCE:
for every possible position she could stand  O(1mil)
   loop through grass patches before and after her position within k steps (1mil)
      add them up

SLIDING WINDOW:
loop through all grass in range from her leftmost poss position - O(x_max)
   [or, leftmost position that has the leftmost patch on left side of window - O(n) ]

for every poss pos after the initial window, in order -  O(x_max) ~ 1mil
   [or, every position that places a patch of grass on left side of window - O(n) ]
   add the grass that wasn't in prev window to total - O(1)
      [might be more than just 1 if we jumped the window using coord compression
        - O(n) max for one step, but in that case other steps would have far less
        - amortized O(1), or total O(n) across all repetitions of this step  ]
   take out the grass that is no longer in window from total - O(1)


G                             G G G G G G G G G G G G G
------------------------------
                              ------------------------------


*/

