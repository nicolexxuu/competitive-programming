// Cow Roller Coaster

import java.util.*;
import java.io.*;

public class coaster {
	public static void main(String[] args) throws IOException {
		String file = "";
		// BufferedReader br = new BufferedReader(new FileReader(new File(file + ".in")));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int L = Integer.parseInt(st.nextToken());
		int N = Integer.parseInt(st.nextToken());
		int B = Integer.parseInt(st.nextToken());
		
		Part[] parts = new Part[N];
		
		for(int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			parts[i] = new Part(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()));
		}
		br.close();
		
		int[][] dp = new int[L+1][B+1];
		for(int i = 1; i <= L ;i++) Arrays.fill(dp[i], -1);

		Arrays.sort(parts);
		for(Part p: parts) {
			//System.out.println("s: "+ p.s + " e:" + p.e + " c:" + p.c);
			for(int b = p.c; b <= B; b++) {
				if(dp[p.s][b-p.c]>-1) dp[p.e][b] = Math.max(dp[p.e][b], dp[p.s][b-p.c]+ p.f );
			}
		}

		int result = dp[L][B];
		//PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter(new File(file + ".out"))));
		System.out.println(result);
		//out.println(result);
		//out.close();
	}
	
	public static class Part implements Comparable <Part>{
		int s, e, w, f, c;
		Part (int s, int w, int f, int c){
			this.s = s;
			this.w = w;
			this.f = f;
			this.c = c;
			this.e = s + w;
		}
		
		public int compareTo(Part other) {
			if(this.s != other.s) return this.s - other.s;
			return this.e - other.e;
		}

	}
}
