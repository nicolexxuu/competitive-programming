// Floyd-Warshall Algorithm Implementation
// finds shortest paths between all pairs of nodes
// O(V^3)

import java.util.*; 
import java.io.*;

public class FloydWarshall {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());
		
		int[][] dist = new int[N][N];
		for(int[] t: dist) Arrays.fill(t, Integer.MAX_VALUE);
		for(int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine());
			int a = Integer.parseInt(st.nextToken())-1;
			int b = Integer.parseInt(st.nextToken())-1;
			int len = Integer.parseInt(st.nextToken());
			dist[a][b] = Math.min(dist[a][b], len);
			dist[b][a] = dist[a][b];
		}
		br.close();
		
		for(int k = 0; k < N; k++) { // try using k as an intermediate node between i and j
			for(int i = 0; i < N; i++) {
				if(dist[i][k] == Integer.MAX_VALUE) continue;
				for(int j = 0; j < N; j++) {
					if(dist[k][j] == Integer.MAX_VALUE) continue;
					dist[i][j] = Math.min(dist[i][j], dist[i][k] + dist[k][j]);
				}
			}
		}
	}
}
