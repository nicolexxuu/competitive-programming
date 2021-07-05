// Lights Out

import java.util.*;
import java.io.*;

public class lightsout {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken());
		
		int[] x = new int[N], y = new int[N];
		for(int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			x[i] = Integer.parseInt(st.nextToken());
			y[i] = Integer.parseInt(st.nextToken());
		}
		br.close();
		
		int[] len = new int[N];
		for(int i = 0; i < N; i++) len[i] = Math.abs(x[i] - x[(i+1)%N]) + Math.abs(y[i] - y[(i+1)%N]);
		
		int[] cost = new int[N+1];
		for(int i = 0; i < N-1; i++) cost[i+1] = cost[i] + len[i];
		
		for(int i = N-1; i > 0; i--) cost[i] = Math.min(cost[i], cost[i+1] + len[i]);
		
		int[] turn = new int[N];
		for(int i = 0; i < N; i++) {
			int v1 = (i-1+N)%N;
			int v2 = (i+1+N)%N;
			
			if((x[i] - x[v1]) * (y[i] - y[v2]) - (y[i] - y[v1]) * (x[i] - x[v2]) > 0) turn[i] = 1;
		}
		
		
		int result = 0;
		
		for(int start = 1; start < N; start++) {
			for(int fake = 1; fake < N; fake++) { // checking if this could possibly be the starting location
				if(start == fake) continue;
				int s = start, f = fake;
				int total = 0;
				while(s < N && f < N) {
					if(turn[s] != turn[f]) break;
					total += len[s];
					if(len[s] != len[f]) {
						s++;
						f++;
						break;
					}
					
					s++;
					f++;
				}
				
				s %= N; 
				f %= N;
				total += cost[s];
				result = Math.max(result, total - cost[start]);
			}
		}
		
		System.out.println(result);
	}
}
