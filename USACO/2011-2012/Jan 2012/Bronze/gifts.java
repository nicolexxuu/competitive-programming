// "Gifts"
// http://www.usaco.org/index.php?page=viewproblem2&cpid=103

import java.util.*;
import java.io.*;

public class gifts {
  public static void main(String[] args) throws FileNotFoundException {
    Scanner in = new Scanner(new File("gifts.in"));
    int N = in.nextInt();
    int B = in.nextInt();
    Gift[] gs = new Gift[N];  // TODO: not 10
    
    for(int i = 0; i < N; i++) {
    	gs[i]= new Gift(in);
    }
    in.close();

    Arrays.sort(gs);  // crash if data type wasn't Comparable

    int result = 0;
    for(int gift = 0; gift < N; gift++) {
    	if(gs[gift].t <= B) {
    		result++;
    		B -= gs[gift].t;
    	} else {
    		if(gs[gift].d <= B) {
    			result++;
    			B -= gs[gift].d;
    			break;
    		} else {
    			break;
    		}
    	}
    }
    PrintWriter out = new PrintWriter(new File("gifts.out"));
    System.out.println(result);
    out.println(result);
    out.close();

  }

  static class Gift implements Comparable<Gift> {
    int p, s, t, d, ao;

    Gift(Scanner in) {
      p = in.nextInt();
      s = in.nextInt();
      t = p + s;
      d = p/2 + s;
      ao = p/2;      // amount off
    }

    // rule: must return an int which is:
    //   +1 or any positive: means this is later/greater than other
    //    0                : means they're equal
    //   -1 or any negative: means this is earlier/less than other
    public int compareTo(Gift other) {
      return this.t - other.t;
      // ex:
      //       6        9            = -3   (this less than other)
      //       9        9            = 0    (both tied)
 
      // or just use an if-statement!

      // this only works with a SINGLE int criteria
    }
  }


}

/* ANALYSIS

5 24    <-- n=5 cows       b=24 budget
4 2    6
2 0    2   <-- input on each line only gives price & shipping, we can also get total
8 1    9
6 3    9
12 5   17



2 4 8  6  12
0 2 1  3  4

2 6 9  9  16

2 8 17 26    <-- total amount spent - have to stop BEFORE we get to 26
------                    after 3 gifts
----      --  <-- this is another possibility, but it's no better than "greedy" approach

brute force:
sort all cows first
for each gift we can coupon
     sort the cows using modified price for that gift (only involves moving 1 cow)
     do greedy algorithm



if we only did the greedy approach on unmodified prices
2 4 8  0  12
0 2 1  9  4

2 6 9  9  16

------
2 8 17 26
    13 22
    ^ this savings on gift already budgeted lets us buy one more


optimized:
sort all gifts
find the most we can buy without coupon (greedy)

loop through "already budgeted" gifts, find the most savings possible
     see if you can buy the next unbought gift
IF THAT DIDN'T WORK,
loop through unbought gifts, see if coupon can let you buy THAT GIFT

*/
