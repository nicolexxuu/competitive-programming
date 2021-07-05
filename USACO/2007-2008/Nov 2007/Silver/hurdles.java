// Cow Hurdles

import java.util.*;
import java.io.*;

public class hurdles {
	public static void main(String[] args) throws IOException {
		String file = "";
		// BufferedReader br = new BufferedReader(new FileReader(new File(file + ".in")));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());
		int T = Integer.parseInt(st.nextToken());
		
		int[][] dist = new int[N][N];
		for(int[] a: dist) Arrays.fill(a, Integer.MAX_VALUE);
		
		for(int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine());
			int a = Integer.parseInt(st.nextToken())-1;
			int b = Integer.parseInt(st.nextToken())-1;
			int height = Integer.parseInt(st.nextToken());
			
			dist[a][b] = Math.min(dist[a][b], height);
		}
		
		for(int k = 0; k < N; k++) {
			for(int i = 0; i < N; i++) {
				if(dist[i][k] == Integer.MAX_VALUE) continue;
				for(int j = 0; j < N; j++) {
					if(dist[k][j] == Integer.MAX_VALUE) continue;
					dist[i][j] = Math.min(dist[i][j], Math.max(dist[i][k], dist[k][j]));
				}
			}
		}
		
		
		for(int i = 0; i < T; i++) {
			st = new StringTokenizer(br.readLine());
			int a = Integer.parseInt(st.nextToken())-1;
			int b = Integer.parseInt(st.nextToken())-1;
			if(dist[a][b] == Integer.MAX_VALUE) System.out.println(-1);
			else System.out.println(dist[a][b]);
		}
		
		br.close();
	}
}
