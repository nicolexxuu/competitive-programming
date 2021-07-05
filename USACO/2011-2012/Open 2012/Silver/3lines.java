
// This template code suggested by KT BYTE Computer Science Academy
//   for use in reading and writing files for USACO problems.

// https://content.ktbyte.com/problem.java

// https://docs.google.com/presentation/d/e/2PACX-1vQh31vF52-z-beK7QgahnX14YJGg3TYuEYg9_iuXZzyGDiHh7jsZHcopRnuJyNETiHLd0F1D0jglLre/pub?start=false&loop=false&delayms=3000&slide=id.p
// 3 lines

import java.util.*;
import java.io.*;

public class 3lines {
	public static void main(String[] args) throws FileNotFoundException {
		Scanner in = new Scanner(new File("3lines.in"));
		HashMap<Integer, Set<Integer>> Xs = new HashMap<>();
		HashMap<Integer, Set<Integer>> Ys = new HashMap<>();
		
		int N = in.nextInt();
		for(int i = 0; i < N; i++) {
			int x = in.nextInt();
			int y = in.nextInt();
			addToMap(Xs, x, y);
			addToMap(Ys, y, x);
		}
		in.close();

		int result = 0;
		if(Xs.size() <= 3 || Ys.size() <= 3) result = 1;
		
		if(result != 1) {
			for(int x : Xs.keySet()) {
				Set<Integer> values = Xs.get(x); //all y values for xs in that line
				for(int i: values) {
					removeFromMap(Ys, i, x);
				}
				
				if(Ys.size() <= 2) {
					result = 1;
					break;
				} else {
					for(int i: values) {
						addToMap(Ys, i, x);
					}
				}
			}
		}
			
		if(result != 1) {
			for(int y : Ys.keySet()) {
				Set<Integer> values = Ys.get(y); //all x values for ys in that line
				for(int i: values) {
					removeFromMap(Xs, i, y);
				}
				
				if(Xs.size() <= 2) {
					result = 1;
					break;
				} else {//if doesn't work, put back
					for(int i: values) {
						addToMap(Xs, i, y);
					}
				}
			}
		}
		
		PrintWriter out = new PrintWriter(new File("_3lines.out"));
		System.out.println(result);
		out.println(result);
		out.close();
	}		
	
	// this method "remembers" a pair of coordinates by adding valCoord
	  //   to the Set corresponding to the given keyCoord, for instance adding
	  //   a y value to the Set corresponding to a given x value, or vice versa
	  static void addToMap(Map<Integer, Set<Integer>> map,
	                       int keyCoord, int valCoord) {
	    // TODO: if set does not exist for given key, must create it!
	    // will probably use a line something like this:
		  if(!map.containsKey(keyCoord)) {
			  Set<Integer> valSet = new HashSet<>();
			  valSet.add(valCoord);
			  map.put(keyCoord, valSet);
		  } else {
			  map.get(keyCoord).add(valCoord);
		  }
	    // TODO: add valCoord to Set for keyCoord
	  }

	  // removeFromMap should work similarly
	  // TODO:
	  //   remove the value from the set corresponding the given key
	  //   if that set is then empty, REMOVE it
	  //   (if not empty, it can stay)
	  
	  static void removeFromMap(Map<Integer, Set<Integer>> map,
		              int keyCoord, int valCoord) {
		  map.get(keyCoord).remove(valCoord);
		  if(map.get(keyCoord).size() == 0) {
			  map.remove(keyCoord);
		  }
	  }
}
