// http://www.usaco.org/index.php?page=viewproblem2&cpid=619
// "Load Balancing"

import java.util.*;
import java.io.*;

public class balancing {
  public static void main(String[] args) throws IOException {
	  String file = "balancing";
	    BufferedReader b = new BufferedReader(new FileReader(new File(file + ".in")));
		StringTokenizer st = new StringTokenizer(b.readLine());
		int n = Integer.parseInt(st.nextToken());
    
    Cow[] ySorted = new Cow[n];
    Cow[] xSorted = new Cow[n];
    
    for(int i = 0; i < n; i++) {
    	st = new StringTokenizer(b.readLine());
    	Cow c = new Cow(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()));
    	ySorted[i] = c;
    	xSorted[i] = c;
    }
    b.close();
    
    Arrays.sort(ySorted, new Comparator<Cow>() {
      public int compare(Cow a, Cow b) {
        return a.y - b.y;
      }
    });
    
    Arrays.sort(xSorted, new Comparator<Cow>() {
        public int compare(Cow a, Cow b) {
          return a.x - b.x;
        }
      });
    
    int result = n;
    
    // TODO: slide the fences in both directions
    int l = 0, r = n;
    for(int x = 0; x < n - 1; x++) { //divider is between x and x + 1
    	l++;
    	r--;
    	while(x < n - 1 && xSorted[x + 1].x == xSorted[x].x) {
    		l++;
    		r--;
    		x++;
    	}
    	
    	int tl = 0, bl = l, tr = 0, br = r;
    	for(int y = 0; y < n - 1; y++) { //divider is between y and y + 1
    		if(ySorted[y].x <= xSorted[x].x) {
    			tl++;
    			bl--;
    		} else {
    			tr++;
    			br--;
    		}
    		while(y < n - 1 && ySorted[y + 1].y == ySorted[x].y) {
    			y++;
    			if(ySorted[y].x <= xSorted[x].x) {
        			tl++;
        			bl--;
        		} else {
        			tr++;
        			br--;
        		}
        	}
    		
    		result = Math.min(Math.max(tl, Math.max(tr, Math.max(bl, br))), result);
    	}
    }
    
    PrintWriter out = new PrintWriter(new File("balancing.out"));
    System.out.println(result);
    out.println(result);
    out.close();
  }
  
  static class Cow {
    int x, y;
    
    Cow(int x, int y) {
      this.x = x;
      this.y = y;
    }
  }
}
