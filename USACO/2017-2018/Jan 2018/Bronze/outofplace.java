import java.util.*;
import java.io.*;

public class outofplace {
	public static void main(String[] args) throws IOException {
		Scanner in = new Scanner(System.in);
		int N = in.nextInt();
		int[] cows = new int[N];
		int[] sorted = new int[N];
		for(int i = 0; i < N; i++) {
			int n = in.nextInt();
			cows[i] = n;
			sorted[i] = n;
		}
		
		Arrays.sort(sorted);
		int result = 0;
		for(int i = 0; i < N; i++) {
			if(sorted[i] != cows[i]) result++;
		}
		
		System.out.println(result - 1);
	}
}


/* ANALYSIS
2 4 7 7 9 3 - Original Lineup
2 4 7 7 3 9
2 4 3 7 7 9
2 3 4 7 7 9

9 2 3 4 7 7s
*/
