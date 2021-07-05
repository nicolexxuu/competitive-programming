
// "Ski Course Design"
// http://usaco.org/index.php?page=viewproblem2&cpid=376

import java.util.*;
import java.io.*;

public class skidesign {
  static final String FILE_NAME = "skidesign";

  public static void main(String[] args) throws FileNotFoundException {
    // INPUT //
    Scanner in = new Scanner(new File(FILE_NAME + ".in"));
    int n = in.nextInt();
    
    int [] hills = new int[n];
    
    for(int i = 0; i < n; i++){
        hills[i] = in.nextInt();
    }
    
    in.close();

    Arrays.sort(hills);
    
    // CALCULATION //
    int result = 0;
    
    int minCost = Integer.MAX_VALUE;

    
    for(int min = 0; min <= 83; min++) {
    	int tempCost = 0;
    	int max = min + 17;
        for(int i = 0; i < n; i++) {
        	if(hills[i] < min) {
        		tempCost += (min - hills[i]) * (min - hills[i]);
        	} else if(hills[i] > max) {
    			tempCost += (hills[i] - max) * (hills[i] - max);
    		}
        }
        
        if(tempCost < minCost) minCost = tempCost;
    }
    result = minCost;
    
    // OUTPUT //
    PrintWriter out = new PrintWriter(new File(FILE_NAME + ".out"));
    out.println(result);
    out.close();
  }
}
