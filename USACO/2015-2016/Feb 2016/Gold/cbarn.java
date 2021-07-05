// Circular Barn

import java.util.*;
import java.io.*;

public class cbarn {
	public static void main(String[] args) throws IOException {
		String file = "";
		// BufferedReader br = new BufferedReader(new FileReader(new File(file + ".in")));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken());
		int[] cows = new int[N];
		int start = 0;
		for(int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			cows[i] = Integer.parseInt(st.nextToken());
		}
		br.close();
		
		int curr = 0;
		int min = Integer.MAX_VALUE, minI = 0;
		for(int i = 0; i < N; i++) {
			curr += cows[i] - 1;
			if(curr < min) {
				min = curr;
				minI = i;
			}
		}
		
		long res = 0;
		int visited = 0;
		int count = 0;
		for(int i = minI+1; visited < N; i = (i+1)%N) {
			int f = cows[i]-1+count;
			res += sum(f) - sum(f-cows[i]);
			count += cows[i] - 1;
			visited++;
		}
		//PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter(new File(file + ".out"))));
		System.out.println(res);
		//out.println();
		//out.close();
	}
	
	public static long sum(long n) {
		return n*(n+1)*(2*n+1)/6;
	}
}
