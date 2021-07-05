
// http://www.usaco.org/index.php?page=viewproblem2&cpid=96
// "Escaping the Farm"

import java.util.*;
import java.io.*;

public class escape {
	static int N;
	static int[] cows;

	public static void main(String[] args) throws Exception {
		Scanner in = new Scanner(new File("escape.in"));
		N = in.nextInt();
		cows = new int[N];
		for (int i = 0; i < N; i++) {
			cows[i] = in.nextInt();
		}
		in.close();

		int result = findCombos(0, 0, -1);
		PrintWriter out = new PrintWriter(new File("escape.out"));
		System.out.println(result);
		out.println(result);
		out.close();
	}

	static int findCombos(int currWeight, int c, int lastCow) {
		int maxCows = c;
		// loop through and call all other combinations of cows - start checking from
		// lastCow added + 1
		for (int cowToAdd = lastCow + 1; cowToAdd < N && (N - cowToAdd) + c > maxCows; cowToAdd++) {
			// if it does not cause a carry, call function
			if(!causesCarries(currWeight, cows[cowToAdd])) {
				maxCows = Math.max(maxCows, findCombos(currWeight + cows[cowToAdd], c + 1, cowToAdd));
			}
		}
		return maxCows;
	}

	// check if any of the digits in the two numbers add up to greater than 10
	static boolean causesCarries(int a, int b) {
	  while(a > 0 && b > 0) { //continue until there are no digits left in one of the numbers
		  if(a % 10 + b % 10 >= 10) return true; //isolate rightmost digit of both numbers
		  a /= 10; //get rid of rightmost digit
		  b /= 10;
	  }
	  return false;
	}
}

/*
 * OUTLINE
 *  
 * combinatrics
 * brute force all combos of possible cows
 * 	for each one, give up if a carry is caused or the number of remaining
 * 	cows you could possibly still add combined with current cows is less or equal to the
 * 	current maximum
 * 	evalute the maximum
 * 
 * recursion
 * maxCows = curr number of cows 
 * loop through and call all other
 * combinations of cows 
 * if it does not cause a carry, call function again
 * maxCows = Math.max(maxCows, findCombos(currWeight + cows weight, cows + 1, blah))
 * return maxCows
 */
