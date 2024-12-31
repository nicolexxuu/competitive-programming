// Flight Routes

import java.util.*;
import java.io.*;

public class flightroutes {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken());
		
		int[][] r = new int[N+1][N+1];
		
		for(int i = 1; i <= N; i++) {
			if(i < N) {
				st = new StringTokenizer(br.readLine());
				String str = st.nextToken();
				for(int j = 0; j < N-i; j++) r[i][i+j+1] = str.charAt(j) - '0';
			}
		}
		br.close();

		int[][] dr = new int[N+1][N+1];
		int res = 0;
		for(int sz = 1; sz < N; sz++) {
			for(int start = 1; start <= N-sz; start++) {
				int end = start + sz;
				
				int dp = 0;
				for(int s = start+1; s < end; s++) 
					dp ^= dr[start][s] * r[s][end];
				
				dr[start][end] = r[start][end] ^ dp;
				if(dr[start][end] == 1) res++;
			}
		}
		
		System.out.println(res);
	}
}