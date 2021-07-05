// "COW"
// http://www.usaco.org/index.php?page=viewproblem2&cpid=527

import java.util.*;
import java.io.*;

public class cow {
  public static void main(String[] args) throws Exception {
    Scanner in = new Scanner(new File("cow.in"));
    int N = Integer.parseInt(in.nextLine());
    String letters = in.nextLine();
    in.close();

    long[] cow = new long[3];
    
    for(int i = 0; i < N; i++) {
    	if(letters.charAt(i) == 'C') {
    		cow[0]++;
    	} else if (letters.charAt(i) == 'O') {
    		cow[1] += cow[0];
    	} else {
    		cow[2] += cow[1];
    	}
    }
    long result = cow[2];

    PrintWriter out = new PrintWriter(new File("cow.out"));
    System.out.println(result);
    out.println(result);
    out.close();
  }
}
