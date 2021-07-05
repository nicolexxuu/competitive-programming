// http://www.usaco.org/index.php?page=viewproblem2&cpid=594
// "Angry Cows"
 

import java.util.*;
import java.io.*;
 
public class angry {
  public static void main(String[] args) throws Exception {
    Scanner in = new Scanner(new File("angry.in"));
    int N = in.nextInt();
    int K = in.nextInt();
    int[] bales = new int[N];
    for(int i = 0; i < N; i++) {
    	bales[i] = in.nextInt();
    }
    in.close();
    
    Arrays.sort(bales);
    
    int min = 1;
    int max = 50000;
	
    while(min < max) {
    	int mid = (min + max) / 2;
    	int cowIndex = bales[0] + mid;
    	int cows = 1;
		for(int bale = 1; bale < bales.length; bale++) {
			if( cowIndex  + mid < bales[bale]) {
				cowIndex = bales[bale] + mid;
				cows++;
			}
		}
		
		if(cows > K) {
			min = mid + 1;
		} else {
			max = mid - 1;
		}
    }
    
    int result = min;
 
    PrintWriter out = new PrintWriter(new File("angry.out"));
    System.out.println(result);
    out.println(result);
    out.close();
  }
}
