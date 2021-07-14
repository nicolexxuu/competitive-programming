// Prim's Algorithm (Jarnik's Algorithm) Implementation
// O(V^2) Approach

import java.util.*;
import java.io.*;

public class Prims {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken());
		int[][] adj = new int[N][N];
		
		for(int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			for(int j = 0; j < N; j++) {
				adj[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		br.close();
		
		boolean[] mark = new boolean[N];
		int[] dist = new int[N];
		
		Arrays.fill(dist, Integer.MAX_VALUE);
		dist[0] = 0;
		
		for(int in = 0; in < N; in++) {
			int minDist = Integer.MAX_VALUE;
			int minI = -1;
			
			for(int i = 1; i <= N; i++) {
				if(!mark[i] && dist[i] < minDist) {
					minI = i;
					minDist = dist[i];
				}
			}
			mark[minI] = true;
			
			for(int i = 1; i <= N; i++) 
				if(!mark[i]) dist[i] = Math.min(dist[i], adj[minI][i]);
		}
	}
}
