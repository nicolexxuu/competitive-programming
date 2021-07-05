// Heat Wave

import java.util.*;
import java.io.*;

public class heatwv {
	public static void main(String[] args) throws IOException {
		String file = "";
		// BufferedReader br = new BufferedReader(new FileReader(new File(file + ".in")));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());
		int A = Integer.parseInt(st.nextToken()) - 1;
		int B = Integer.parseInt(st.nextToken()) - 1;
		
		int[][] mat = new int[N][N];
		for(int i = 0; i < N; i++) Arrays.fill(mat[i], Integer.MAX_VALUE);
		
		for(int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine());
			int a = Integer.parseInt(st.nextToken()) - 1;
			int b = Integer.parseInt(st.nextToken()) - 1;
			int w = Integer.parseInt(st.nextToken());
			mat[a][b] = Math.min(mat[a][b], w);
			mat[b][a] = mat[a][b];
		}
		
		br.close();

		int[] dist = new int[N];
		Arrays.fill(dist, Integer.MAX_VALUE);
		dist[A] = 0;
		boolean[] visited = new boolean[N];
		
		for(int i = 0; i < N; i++) {
			int minInd = -1;
			int minVal = Integer.MAX_VALUE;
			
			for(int j = 0; j < N; j++) {
				if(!visited[j] && dist[j] < minVal) {
					minVal = dist[j];
					minInd = j;
				}
			}
			
			if(minInd == -1) break;
			visited[minInd] = true;
			
			for(int j = 0; j < N; j++) {
				if(!visited[j] && mat[minInd][j] < Integer.MAX_VALUE)
					dist[j] = Math.min(dist[j], minVal + mat[minInd][j]);
			}
		}
		
		//PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter(new File(file + ".out"))));
		System.out.println(dist[B]);
		//out.println(result);
		//out.close();
	}
}
