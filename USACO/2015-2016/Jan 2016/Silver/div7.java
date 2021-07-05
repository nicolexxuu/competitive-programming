
//http://www.usaco.org/index.php?page=viewproblem2&cpid=595
//Subsequences Summing To Sevens

// This template code suggested by KT BYTE Computer Science Academy
//   for use in reading and writing files for USACO problems.

// https://content.ktbyte.com/problem.java

import java.util.*;
import java.io.*;

public class div7 {
	public static void main(String[] args) throws FileNotFoundException {
		Scanner in = new Scanner(new File("div7.in"));
		int n = in.nextInt();
		int[] prefixSums = new int[n + 1];
		
		
		for(int i = 1; i <= n; i++) {
			prefixSums[i] = (prefixSums[i - 1] + in.nextInt()) % 7;
		}
		
		
		in.close();
		
		Map<Integer, Integer> earliestPos = new HashMap<>();
		
		int result = 0;
		for(int i = 0; i < n; i++) {
			if(earliestPos.containsKey(prefixSums[i])) {
				int photoSize = i - earliestPos.get(prefixSums[i]);
				result = Math.max(photoSize,  result);
			} else {
				earliestPos.put(prefixSums[i], i);
			}
		}

		
		PrintWriter out = new PrintWriter(new File("div7.out"));
		System.out.println(result);
		out.println(result);
		out.close();
	}
}


/* ANALYSIS

7
   0  1  2  3  4   5   6
   3  5  1  6  2  14  10  <-- id #s
   3  5  1  6  2   0   3  <-- %7'd  id#s

   3  8  9  15 17 17  20   <-- starting at index 0, no endpoint has div7
      5  6  12 14 14  17   <-- starting at index 1, endpoint 5 (and 4) would be div7

0  3  1  2   1  3  3   6
      --------------    startin AFTER first 3, a bunch more stuff was added which must
                have been div7 because it adds up to something else which also had %=3


brute force:

for every starting cow in a possible sequence   - 50k     - O(n)
   loop through every other cow                 - avg 25k - O(n)
     check the sum if sequence ended at that cow (is it div7?) - O(1)
     (keep that sum going as you go forward)

total: 50k*25k = 1,250,000,000


*/

