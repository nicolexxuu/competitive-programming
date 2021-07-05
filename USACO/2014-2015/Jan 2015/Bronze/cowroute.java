
import java.util.*;
import java.io.*;

public class cowroute {
  public static void main(String[] args) throws FileNotFoundException {

    Scanner in = new Scanner(new File("cowroute.in"));
    // Scanner reads in whitespace-separated format
    int a = in.nextInt();
    int b = in.nextInt();  // next int after a, skips any spaces in between
    int n = in.nextInt();

    int[] costs = new int[n];
    // each index is for different route, value = using that route
    int[] lens = new int[n];
    // value = length (# of cities) 

    int[][] cities = new int[n][];     // creates 2d array with n rows, unknown # cols

    for (int r = 0; r < n; r++) {
      costs[r] = in.nextInt();
      lens[r] = in.nextInt();

      cities[r] = new int[ lens[r] ];
      // length of row in grid/2d array is based on route info
      for (int i = 0; i < lens[r]; i++) {
        cities[r][i] = in.nextInt();
      }
    }


    in.close();
    
    
    int [] minInto = new int[10001];
    int [] minOutOf = new int[10001];
    
    Arrays.fill(minInto, 1001);
    Arrays.fill(minOutOf, 1001);
    
    for(int r = 0; r < n; r++) {
    	
    	boolean cityA = false;
    	for(int i = 0; i < lens[r]; i++) {
    		if(cities[r][i] == a) cityA = true;
    		else if(cityA) {
    			int c = cities[r][i];
    			minInto[c] = Math.min(minInto[c], costs[r]);
    		}
    	}
    	
    	boolean cityB = false;
    	for(int i = lens[r] - 1; i >= 0; i--) {
    		if(cities[r][i] == b) cityB = true;
    		else if(cityB) {
    			int c = cities[r][i];
    			minOutOf[c] = Math.min(minOutOf[c],  costs[r]);
    		}
    	}
    }
    
    
    int result = 2001;
    
    for(int c = 1; c <= 1000; c++) {
    	if(minInto[c] != 1001 && minOutOf[c] != 1001) {
    		int total = minInto[c] + minOutOf[c];
    		result = Math.min(result,  total);
    	}
    }
    
    if(minInto[b] != 1001 && minInto[b] < result) {
    	result = minInto[b];
    }

    PrintWriter out = new PrintWriter(new File("cowroute.out"));
    System.out.println(result);
    out.println(result);
    out.close();
  }
}
