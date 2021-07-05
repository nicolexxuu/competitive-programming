
// This template code suggested by KT BYTE Computer Science Academy
//   for use in reading and writing files for USACO problems.

// https://content.ktbyte.com/problem.java

//http://usaco.org/index.php?page=viewproblem2&cpid=412
//"Reordering the Cows"

import java.util.*;
import java.io.*;

public class reorder {
	public static void main(String[] args) throws FileNotFoundException {
		Scanner in = new Scanner(new File("reorder.in"));
		int N = in.nextInt();
		int [] A = new int[N + 1]; //indexes: cows   values: locations
		int [] B = new int[N + 1]; //other way around works too?!?!?!?
		
		for(int i = 1; i <= N; i++) {
			A[in.nextInt()] = i;
		}
		
		for(int i = 1; i <= N; i++) {
			B[in.nextInt()] = i;
		}
		in.close();
		
		boolean[] shifted = new boolean[N + 1];
		
		int cyclicShifts = 0;
		int maxCows = -1;
		

		for(int cow = 1; cow <= N; cow++) {
			if(!shifted[cow]) {
				if(A[cow] == B[cow]) {
					shifted[cow] = true;
				} else {
					cyclicShifts++;
					int count = 0;
					int currCow = cow;
					while(B[currCow] != A[currCow]) {
						count++;
						shifted[currCow] = true;
						
						int temp = findCow(A, B[currCow]);
						A[currCow] = B[currCow];
						currCow = temp;
						//hold the cow at the place where A is going to as temp
					}
					maxCows = Math.max(maxCows,  count);
				}
			}
		}
		
		
		PrintWriter out = new PrintWriter(new File("reorder.out"));
		System.out.println(cyclicShifts + " " + maxCows);
		out.println(cyclicShifts + " " + maxCows);
		out.close();
	}
	
	public static int findCow(int[] arr, int cow) {
		for(int i = 1; i < arr.length; i++) {
			if(arr[i] == cow) return i;
		}
		return 0;
	}
}


/* ANALYSIS

	hold the cow at the location the shifting cow is supposed to be
	set it as the next shifting cow

*/
