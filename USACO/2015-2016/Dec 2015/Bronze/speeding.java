import java.util.*;
import java.io.*;

public class speeding {
	public static void main(String[] args) throws IOException {
		Scanner in = new Scanner(System.in);
		int N = in.nextInt();
		int M = in.nextInt();
		
		int[] rLength = new int[N + 1];
		int[] rSpeed = new int[N + 1];
		int[] bLength = new int[M + 1];
		int[] bSpeed = new int[M + 1];
		
		for(int i = 1; i <= N; i++) {
			rLength[i] = in.nextInt() + rLength[i - 1];
			rSpeed[i] = in.nextInt();
		}
		
		for(int i = 1; i <= M; i++) {
			bLength[i] = in.nextInt() + bLength[i - 1];
			bSpeed[i] = in.nextInt();
		}

		int result = 0;
		
		int r = 1;
		int b = 1;
		
		while(r <= N && b <= M) {
			result = Math.max(bSpeed[b] - rSpeed[r], result);
			int temp = r;
			if(rLength[r] <= bLength[b] && r < N) r++;
			if(bLength[b] <= rLength[temp]) b++;
		}
		System.out.println(result);
	}
}
