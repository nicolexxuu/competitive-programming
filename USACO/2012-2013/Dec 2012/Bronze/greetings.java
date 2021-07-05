
// http://www.usaco.org/index.php?page=viewproblem2&cpid=205
// Meet And Greet

// This template code suggested by KT BYTE Computer Science Academy
//   for use in reading and writing files for USACO problems.

// https://content.ktbyte.com/problem.java

import java.util.*;
import java.io.*;

public class greetings {
	public static void main(String[] args) throws FileNotFoundException {
		Scanner in = new Scanner(new File("greetings.in"));
		int B = in.nextInt();
		int E = in.nextInt(); //# of instructions
		
		int[] bTimes = new int[B];
		boolean[] bRight = new boolean[B];
		
		int[] eTimes = new int[E];
		boolean[] eRight = new boolean[E];
		
		for(int i = 0; i < B; i++) {
			bTimes[i] = in.nextInt();
			bRight[i] = in.next().equals("R");
		}
		
		for(int j = 0; j < E; j++) {
			eTimes[j] = in.nextInt();
			eRight[j] = in.next().equals("R");
		}
		
		in.close();
		
		int result = 0;
		int bIndex = 0;
		int eIndex = 0;
		int bProgress = 0;
		int eProgress = 0;
		int bX = 0, eX = 0;
		int relPos = 0;
		
		while(bIndex < B || eIndex < E) {
			if(bIndex >= B) {
				int timeLeft = eTimes[eIndex] - eProgress;

				eX += eRight[eIndex] ? +timeLeft : -timeLeft;
				
				int newRelPos = 0;
				if(eX < bX) newRelPos = -1;
				if(bX < eX) newRelPos = 1;
				
				if(newRelPos != relPos && relPos != 0) {
					result++;
				}
				
				relPos = newRelPos;

				eProgress += timeLeft;
				
				if(eProgress >= eTimes[eIndex]) {
					eIndex++;
					eProgress = 0;
				}
				
			} else if(eIndex >= E) {
				int timeLeft = bTimes[bIndex] - bProgress;

				bX += bRight[bIndex] ? +timeLeft : -timeLeft;
				
				int newRelPos = 0;
				if(eX < bX) newRelPos = -1;
				if(bX < eX) newRelPos = 1;
				
				if(newRelPos != relPos && relPos != 0) {
					result++;
				}
				
				relPos = newRelPos;

				bProgress += timeLeft;
				
				if(bProgress >= bTimes[bIndex]) {
					bIndex++;
					bProgress = 0;
				}
				
			} else {
				int timeLeft = Math.min(bTimes[bIndex] - bProgress, eTimes[eIndex] - eProgress);
				bX += bRight[bIndex] ? +timeLeft : -timeLeft;
				eX += eRight[eIndex] ? +timeLeft : -timeLeft;
				
				int newRelPos = 0;
				if(eX < bX) newRelPos = -1;
				if(bX < eX) newRelPos = 1;
				
				if(newRelPos != relPos && relPos != 0) {
					result++;
				}
				
				relPos = newRelPos;
				
				bProgress += timeLeft;
				eProgress += timeLeft;
				
				if(bProgress >= bTimes[bIndex]) {
					bIndex++;
					bProgress = 0;
				}
				if(eProgress >= eTimes[eIndex]) {
					eIndex++;
					eProgress = 0;
				}
			}
			
			
		}
		
		PrintWriter out = new PrintWriter(new File("greetings.out"));
		System.out.println(result);
		out.println(result);
		out.close();
	}
}


/* ANALYSIS
 
	COORDINATE COMPRESSED:
	track:
	 	-which instruction we're up to on each cow
	 	-which position we're up to on each cow
	 	-which position we're up to on each cow
	 	-current order
	
	for every pair of instructions
		find which one reaches a "turning point" earlier
		find postiion each cow ends up at that point
		check for crossing/moo logic, update result
		update order of cows
		advance to next instruction for either cow that finished one
		
	overall: 2 * 50k instructions  * ~6 steps = 600k
	4 5
	
	3 L
	5 R
	1 L
	2 R
	
	4 R
	1 L
	3 L
	4 R
	2 L
	
	T           7 6 5 4 3 2 1 0 1 2 3 4 5 6 7 8
0                         BE         <--- NOT a moo by rule/because there's no b4
1                            
2                                                
3                   B          (E)                 
4                    (B)          E              
5                      (B)      E              
6                                              
7                                         <--- moo
8                         E   B                
9                           B             <--- moo
10                                     <--- NOT a moo because cows were together b4
11                              B      <--- NOT a moo because cows were together b4
12                                E             
13                                     <--- moo
14                            E    
        
	
	
	brute force solution: loop over anything you need to to find an answer,
	         track/check everything one step at a time
	         
	 

	SUPER brute force:

	for every moment in time ~ 1mil ~ O(t)
  	loop through input data, tracking total length of movement, until we reach a length of
    movement corresponding to the current moment, ~ 50k ~ O(B)
    move in that direction for each cow
	
	total = outer loop * inner loop = 50 bil ~ O(Bt + Et)
	
	1 billion rule: i billion = TOO SLOW
	100 mil = DANGER ZONE
	10 mil = probably okay
	1 mil = okay
	
	ARRAY SOLUTION:
	for each instruction for Bessie:- 50k ~ O(B)
   	fill in array/list the position of the cow for each moment in time covered by that
       instruction - 1mil DISTRIBUTED - O(t)
	do the same for Elsie ~ O(E)
	loop through both timelines together to check matching(after non-matching), count
	O(t)
	
	overall = ~ 3mil
	
*/
