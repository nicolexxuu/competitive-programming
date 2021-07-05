import java.util.*;
import java.io.*;

public class sleepy {
	public static void main(String[] args) throws IOException {
		Scanner in = new Scanner(System.in);
		int N = in.nextInt();
		int[] cows = new int[N];
		for(int i = 0; i < N; i++) {
			int n = in.nextInt();
			cows[i] = n;
		}
		
		int streak = 0;
		int curr = 0;
		
		for(int i = 0; i < N; i++) {
			if(cows[i] > curr) {
				curr = cows[i];
				streak++;
			} else {
				streak = 1;
				curr = cows[i];
			}
		}
		
		int result = N - streak;
		System.out.println(result);
	}
}


/* ANALYSIS
4 3 2 1

3 2 1 4

2 1 3 4

1 2 3 4




1 2 4 3

2 4 1 3

4 1 2 3

1 2 3 4

*/
