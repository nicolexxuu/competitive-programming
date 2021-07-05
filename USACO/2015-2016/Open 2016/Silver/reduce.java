// "Field Reduction" - Silver level
// http://www.usaco.org/index.php?page=viewproblem2&cpid=642

import java.util.*;
import java.io.*;

public class reduce {
  public static void main(String[] args) throws Exception {
    Scanner in = new Scanner(new File("reduce.in"));
    
    int N = in.nextInt();

    Cow[] sortedHoriz = new Cow[N];
    Cow[] sortedVert = new Cow[N];
    
    for(int i = 0; i < N; i++) {
    	int x = in.nextInt();
    	int y = in.nextInt();
    	sortedHoriz[i] = new Cow(x, y);
    	sortedVert[i] = sortedHoriz[i];
    }
    in.close();
    
    Arrays.sort(sortedHoriz, new HComparator());
    Arrays.sort(sortedVert, new VComparator());
    
    int res = (sortedHoriz[N - 1].x - sortedHoriz[0].x) * (sortedVert[N - 1].y - sortedVert[0].y);
    Cow[] remove = new Cow[12];
    for(int i = 0; i < 3; i++) {
    	remove[i] = sortedVert[i];
    	remove[3 + i] = sortedVert[N - 1 - i];
    	remove[6 + i] = sortedHoriz[i];
    	remove[9 + i] = sortedHoriz[N - i - 1];
    }
    System.out.println(res);
    
    for(int c1 = 0; c1 < 12; c1++) {
    	for(int c2 = c1 + 1; c2 < 12; c2++) {
    		if(remove[c2] == remove[c1]) continue;
    		for(int c3 = c2 + 1; c3 < 12; c3++) {
    			if(remove[c2] == remove[c3]) continue;
    			int minX = 0;
    			int maxX = N - 1;
    			int minY = 0;
    			int maxY = N - 1;
    			while(sortedHoriz[minX] == remove[c1] || sortedHoriz[minX] == remove[c2] || sortedHoriz[minX] == remove[c3]) {
    				minX++;
    			}
    			while(sortedHoriz[maxX] == remove[c1] || sortedHoriz[maxX] == remove[c2] || sortedHoriz[maxX] == remove[c3]) {
    				maxX--;
    			}
    			while(sortedVert[minY] == remove[c1] || sortedVert[minY] == remove[c2] || sortedVert[minY] == remove[c3]) {
    				minY++;
    			}
    			while(sortedVert[maxY] == remove[c1] || sortedVert[maxY] == remove[c2] || sortedVert[maxY] == remove[c3]) {
    				maxY--;
    			}
    			
    			System.out.print(sortedHoriz[minX].x + " " + sortedHoriz[maxX].x + " " + sortedVert[minY].y + " " + sortedVert[maxY].y + " ");
    			res = Math.min(res, (sortedHoriz[maxX].x - sortedHoriz[minX].x) * (sortedVert[maxY].y - sortedVert[minY].y));
    			System.out.println(res);
    		}
    	}
    }
    
    
    
    PrintWriter out = new PrintWriter(new File("reduce.out"));
    System.out.println(res);
    out.println(res);
    out.close();
  }
  
  static class HComparator implements Comparator<Cow> {
      public int compare(Cow a, Cow b) {
    	  return a.x - b.x;
      }
  }
  
  static class VComparator implements Comparator<Cow> {
      public int compare(Cow a, Cow b) {
          return a.y - b.y;
      }
  }
  
  static class Cow {
	  int x, y;
	  Cow(int a, int b){
		  x = a;
		  y = b;
	  }
  }
}
