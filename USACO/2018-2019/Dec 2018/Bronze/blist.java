
import java.util.*;
import java.io.*;

public class blist {
	public static void main(String[] args) throws IOException {
		Scanner in = new Scanner(System.in);
		int N = in.nextInt();
		int[] milk = new int[1001];
		for(int i = 0; i < N; i++) {
			int s = in.nextInt();
			int t = in.nextInt();
			int b = in.nextInt();
			milk[s] += b;
			milk[t] -= b;
		}
		
		int buckets = 0;
		int result = 0;
		for(int i = 1; i <= 1000; i++) {
			buckets += milk[i];
			result = Math.max(result,  buckets);
		}

		System.out.println(result);
	}
}
