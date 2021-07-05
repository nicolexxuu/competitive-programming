
//http://www.usaco.org/index.php?page=viewproblem2&cpid=279
//Blink

// This template code suggested by KT BYTE Computer Science Academy
//   for use in reading and writing files for USACO problems.

// https://content.ktbyte.com/problem.java

import java.util.*;
import java.io.*;

public class blink {
	public static void main(String[] args) throws FileNotFoundException {
		Scanner in = new Scanner(new File("blink.in"));
		int n = in.nextInt();
		long b = in.nextLong();
		
		int[] bulbs = new int[n];
		for(int i = 0; i < n; i++) {
			bulbs[i] = in.nextInt();
		}
		in.close();
		
		String state = Arrays.toString(bulbs);
		Map<String, Long> prevTimes=  new HashMap<>();
		
		
		for(long t = 0; t < b; t++) {
			System.out.println(state);
			if(prevTimes.containsKey(state)) {
				long cycleLen = t - prevTimes.get(state);
				long remaining = b - t;
				long extraSteps = remaining % cycleLen;
				
				for(int i = 0; i < extraSteps; i++) {
					step(bulbs);
				}
				t = b;
			} else {
				prevTimes.put(state, t);
				step(bulbs);
				state = Arrays.toString(bulbs);
			}
		}
		
		  // t    if-statement   prevStates           state
	      //                     {}                   10011
	      // 0    no cycle       {10011=>0}           01001
	      // 1    no cycle       {10011=>0, 01001=>1} 11101

		PrintWriter out = new PrintWriter(new File("blink.out"));
		for(int i = 0; i < n; i++) {
			int result = bulbs[i];
			System.out.println(result);
			out.println(result);
		}
		out.close();
	}
	
	static void step(int[] bulbs) {
		int rb = bulbs[bulbs.length - 1];
		for(int i = bulbs.length - 1; i > 0; i--) {
			if(bulbs[i - 1] == 1) {
				bulbs[i] = 1 - bulbs[i];
			}
		}
		
		if(rb == 1) bulbs[0] = 1 - bulbs[0];
	}
}


/* ANALYSIS

5 6
1
0
0
0
0


The light bulb states are as follows:

          0 1 2 3 4

Time T=0: 1 0 0 0 0     1
Time T=1: 1 1 0 0 0     2
Time T=2: 1 0 1 0 0     2
Time T=3: 1 1 1 1 0     4
Time T=4: 1 0 0 0 1     2
Time T=5: 0 1 0 0 1     2
Time T=6: 1 1 1 0 1     4


     T=9: 1 1 0 1 1     4

    T=15: 0 1 1 1 1
    T=16: 1 1 0 0 0        <-- first repeat, a repeat of state at T=1

    T=31: same!   took 15 steps for repetition, this is 15 steps later than T=16
    T=32: same as T=17, T=2; OR same as T=31 plus 1 extra step

    T=15,000,000,000,016: same as T=16, since
         difference (15 tril) is div by 15 (cycle length)
    T=15,000,000,000,017: same as T=16 plus one extra step

modified outline:
  in the loop, if a state is same as a previous state, skip ahead to as late a time
    as we can (that has the same state as current one, based on cycle length), then
    allow the loop to complete any remaining cycles to reach b
  use map, with states as keys, times as values








2^16 = 65,536  # of states possible with 16 bulbs; if we're going any more than that # of times, some states MUST be repeating



b <= 10^15 = 1 quadrillion

int =  32 bits = 2^31-1 positive values = ~2bil
long = 64 bits = 2^63-1 positive values = ~8-9quintillion



*/

