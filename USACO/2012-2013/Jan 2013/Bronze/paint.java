
// http://www.usaco.org/index.php?page=viewproblem2&cpid=224
// "Painting The Fence"

// This template code suggested by KT BYTE Computer Science Academy
//   for use in reading and writing files for USACO problems.

// https://content.ktbyte.com/problem.java

import java.util.*;
import java.io.*;

public class paint {
	public static void main(String[] args) throws FileNotFoundException {
		Scanner in = new Scanner(new File("paint.in"));
		int N = in.nextInt();
		TreeMap<Integer, Integer> diffs = new TreeMap<>();
		int cur = 0;
		for(int i = 0; i < N; i++) {
			int steps = in.nextInt();
			int dir = in.next().equals("R") ? +1 : -1;
			
			int next = cur + dir * steps;
			int left = Math.min(cur,  next);
			int right = Math.max(cur, next);
			
			Integer diffLeft = diffs.get(left);
			if(diffLeft == null) diffLeft = 0;
			diffs.put(left,  diffLeft + 1);
			
			if(diffs.containsKey(right)) diffs.put(right,  diffs.get(right) - 1);
			else 						 diffs.put(right, -1);
			
			cur = next;
		}
		in.close();

		System.out.println(diffs);
		int result = 0;		
		int coats = 0;
		int prevPoint = Integer.MIN_VALUE;
		
		for(int x : diffs.keySet()) {
			if(coats >= 2) {
				int area = x - prevPoint;
				result += area;
			}
			
			coats += diffs.get(x);
			prevPoint = x;
		}
		
		
		PrintWriter out = new PrintWriter(new File("paint.out"));
		System.out.println(result);
		out.println(result);
		out.close();
	}
}


/* ANALYSIS

32 bits/4 bytes per int/float  (64 bits/8 bytes per long/double)

1000 ints = 4000 bytes = 4 kB
1 mil = 4 MB
 (memory limits in problem assume ~100MB)
1 bil = 4 GB




2 R
6 L
1 R
8 L
1 R
2 R

 2 1 0 9 8 7 6 5 4 3 2 1 0 1 2 3 4 5 <-- numbers of all "points" (not areas themselves)
                         ####|       <-- |   stop and start points for areas
                 |############           #   area getting painted
                 ##|
   |################
   ##|
     ####|
   
  0 2  2    1     3   1    2      0    <--- # of coats of paint on certain areas


pretend we have just 1 move:

             0 1 2 3 4 5 6 7 8 9 10

      1 1 1 1 2 2 2 3 3 3 3 2 3 3 1 1 1 1 1   <-- # of actual coats may change when more
                                                 "moves" are applied accross this region
            +1    +1      -1+1  -2         <-- these #s "differentials" don't need updating, even if the actual # of coats in this whole region needs updating

coordinate compression: jumping over certain coordinates in a dimension, instead of
         looping through all of them
  - depends on being able to identify "critical points"


need a data structure (DS) that can tell us where the differentials are in order, and
    what the amount of difference is at those points

TreeMap
keys:  locations of differentials in coats of paint (sorted)
values: amount of difference in coats at that point

array of pairs (custom-defined) of locations & differentials, sort the array
   (harder to build in an unpredictable order)
this is a little more efficient than a map,
     since maps have more "overhead" than arrays
     
 
outline:
trace out all moves, for each one
  mark +1 and -1 coat at right/left ends of the move
  update position
loop through all marks, for each one
  check dist from prev mark, if coats of paint was >= 2, count the distance covered
  calculate coats of paint going forward from each mark, based on total marks at that
        position







*/
