
// http://www.usaco.org/index.php?page=viewproblem2&cpid=281
// haywire

import java.util.*;
import java.io.*;

public class haywire {

  static int n;
  static int[][] friends;
  // row #s:  cow id # that has some friends  (0-n)  (0 unused)
  // col #s:  which friend of that cow are we talking about (0-3)
  // values:  id # that the first cow is friends with

  static int result = 0;

  public static void main(String[] args) throws FileNotFoundException {
    Scanner in = new Scanner(new File("haywire.in"));
    n = in.nextInt();
    friends = new int[n+1][3];

    for (int r = 1; r <= n; r++) {
      for (int c = 0; c < 3; c++) {
        friends[r][c] = in.nextInt();
      }
    }
    in.close();

    for (int start = 1; start <= n; start++) {
      int[] stallAssignments = new int[13];
      // index: cow id # (0 unused)
      // value: which stall did it go in?  (0 means unassigned)

      stallAssignments[start] = 1;

      Deque<Integer> possibilities = new ArrayDeque<>();
      int[] friendList = friends[start];
      for(int i = 0; i < 3; i++) {
    	  possibilities.add(friendList[i]);
      }

      fillStalls( stallAssignments, 2, possibilities, 0);
    }

    PrintWriter out = new PrintWriter(new File("haywire.out"));
    System.out.println(result);
    out.println(result);
    out.close();
  }

  // let's make a recursive function that says "starting with stall s, with some cows
  //    already assigned in some combination to stalls up to s, what is shortest
  //    wire length we can get out of all possible combinations of remaining cows?"
  //    (updates global var based on results found...)
  //    "possibilities" holds all cows that COULD go into stall s, based on previous
  //      cows already assigned to stalls
  static void fillStalls(int[] stallAssignments, int s, Deque<Integer> possibilities, int wireLen) {
    
	  if(s > n) {
		  if(wireLen > result) result = wireLen;
		  return;
	  }
	  int k = possibilities.size();
	  
	  for(int i = 0; i < k; i++) {
		  int curCow = possibilities.remove();
		  stallAssignments[curCow] = s;
		  int newWireLen = wireLen;
		  for(int j = 0; j < 3; j++) {
			  int friendID = friends[curCow][j];
			  if(stallAssignments[friendID] == 0 && !possibilities.contains(friendID)) {
				  possibilities.add(friendID);
			  } else if(stallAssignments[friendID] != 0) {
				  newWireLen += s - stallAssignments[friendID];
			  }
		  }
		  
		  fillStalls(stallAssignments, s + 1, possibilities, newWireLen);
		  stallAssignments[curCow] = 0;
		  
		  possibilities.add(curCow);
	  }
  }

  // poss: {2, 3, 4} <--- possibility set
  //   cycle for choice 2: 
  //          {3, 4} -> recursive function call
  //   cycle for choice 3:
  //          {2, 4} -> recursive function call
  //   cycle for choice 4:
  //          {2, 3} -> recursive function call
  //   (we'd need to copy 6 numbers, to make 3 duplicate sets)
  
  // poss: [2, 3, 4]  <--- possibility queue
  //    cycle dequeue choice 2:
  //         pass THE SAME queue which is now [3, 4] into recursive function
  //            (function would add friends of 2 into queue, process all later choices)
  //         re-add 2 at end of the queue:  [3, 4, 2]
  //    cycle dequeue choice 3:
  //         pass the same queue [4, 2]  into recursive function
  //         re-add 3 at end [4, 2, 3]
  //    cycle dequeue choice 4:
  //         pass the same queue [2, 3] into recursive function
  //         re-add 4 at end [2, 3, 4]
  //    queue is now back to where it started [2,3,4]
  //    as long as every recursive call leaves the queue as it started, this approach
  //      is great and more efficient than adding/removing from sets
 
}

/* ANALYSIS

n=12


12 * 3 * 4 * 5 * 6 * 7 * 8 * 9 * 10 * 11    = 239,500,800?
  2nd choice: 3 friends with first cow
  3rd choice: 4 (friends with one of those 2 cows) = 6-2 (2 for already chosen  
                           cows because they're friends with each other, 6 for total #
                           of friends listed)
  4th choice: 5 = 9 - 4   (9 total, -2 for friendship b/w 1&2,
                                          -2 for fs b/w 3&another)
  5th choice: 6 cows     6th: 7    7th: 8   8th: 9     9th: 10    10th: 11    ?
                                    NO!
                                   7th: 6 only! because I've already chosen 6!
                                            8th: 5    9th: 4   10th: 3  11th: 2 

12 * 3 * 4 * 5 * 6 * 7 * 6 * 5 * 4 * 3 * 2 * 1 = 21,772,800
   (doesn't even take into account that many choices will have a smaller pool of
      friendships than in upper bound worst case scenario)





12 * 11 * 10 ... * 1 = 12!   = # permutations of 12 cows (perm: order matters)



(not this problem, but related combinatorics:
    12 * 11 * 10 = 12! / 9!
                 = n! / (n-k)! = # permutations of k cows out of n choices = P(n,k)

    12 * 11 * 10 / (3 * 2) = # combinations of k cows (order doesn't matter) = C(n,k)
                           = # permutations / # orderings of those choices
                           = P(n,k) / k!
                           = n! / [(n-k)! k!]
                          "binomial coefficient"            )




*/

