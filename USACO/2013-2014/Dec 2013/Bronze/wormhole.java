// http://www.usaco.org/index.php?page=viewproblem2&cpid=360
// "Wormholes"

import java.util.*;
import java.io.*;

public class wormhole {
  static Wormhole[] field;
  static int N;

  public static void main(String[] args) throws FileNotFoundException {
    Scanner in = new Scanner(new File("wormhole.in"));
    N = in.nextInt();
    field = new Wormhole[N];
    for(int i = 0; i < N; i++) {
    	field[i] = new Wormhole(in);
    }
    in.close();

    for(int wh1 = 0; wh1 < N; wh1++) {
    	for(int wh2 = wh1 + 1; wh2 < N; wh2++) {
    		if(field[wh2].y == field[wh1].y) {
    			if(field[wh2].x > field[wh1].x) {
        			if(field[wh1].toRight == -1 || field[wh2].x < field[field[wh1].toRight].x)
        			field[wh1].toRight = wh2;
        		} else {
        			if(field[wh2].toRight == -1 || field[wh1].x < field[field[wh2].toRight].x)
            			field[wh2].toRight = wh1;
        		}
    		}
    	}
    }

    int result = findLoopyCombos(0);

    PrintWriter out = new PrintWriter(new File("wormhole.out"));
    System.out.println(result);
    out.println(result);
    out.close();
  }
  
  static int findLoopyCombos(int nextToPair) {
	int count = 0;
	boolean done = true;
    for(int i = 0; i < N; i++) {
    	if(field[i].linked == -1) {
    		done = false;
    		break;
    	}
    }
    
    if(done) {
    	if(isLoopy()) return 1;
    	else return 0;
    }

    for (int pairWith = nextToPair + 1; pairWith < field.length; pairWith++) {
      if (field[pairWith].linked != -1) continue;

      field[nextToPair].linked = pairWith;
      field[pairWith].linked = nextToPair;
      int n = nextToPair + 1;
      while(n < N) {
    	  if(field[n].linked != -1) n++;
    	  else break;
      }
      count += findLoopyCombos(n);

      field[nextToPair].linked = -1;
      field[pairWith].linked = -1;
    }
    return count;
  }
  
  static boolean isLoopy() {
	  for(int start = 0; start < N; start++) {
		  int s = start;
		  int goingTo;
		  while(true) {
			  if(field[s].toRight == -1) {
				  break;
			  } else {
				  goingTo = field[field[s].toRight].linked;
				  if(goingTo == start)return true;
				  s = goingTo;
			  }
		  }
	  }
	  return false;
  }
  
  static class Wormhole{
    int x, y;
    int linked = -1;
    
    int toRight = -1;

    Wormhole(Scanner in){
    	x = in.nextInt();
    	y = in.nextInt();
    }
  }
}
