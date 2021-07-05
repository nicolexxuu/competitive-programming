// Job Hunt

import java.util.*;
import java.io.*;

public class jobhunt {
	public static void main(String[] args) throws IOException {
		String file = "";
		// BufferedReader br = new BufferedReader(new FileReader(new File(file + ".in")));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int D = Integer.parseInt(st.nextToken());
		int P = Integer.parseInt(st.nextToken());
		int C = Integer.parseInt(st.nextToken());
		int F = Integer.parseInt(st.nextToken());
		int S = Integer.parseInt(st.nextToken())-1;
		
		int[][] adj = new int[C][C];
		for(int i = 0; i < C; i++) Arrays.fill(adj[i], Integer.MIN_VALUE);
		
		for(int i = 0; i < P; i++) {
			st = new StringTokenizer(br.readLine());
			int a = Integer.parseInt(st.nextToken())-1;
			int b = Integer.parseInt(st.nextToken())-1;
			adj[a][b] = Math.max(adj[a][b], -D);
		}
		
		for(int i = 0; i < F; i++) {
			st = new StringTokenizer(br.readLine());
			int a = Integer.parseInt(st.nextToken())-1;
			int b = Integer.parseInt(st.nextToken())-1;
			int T = Integer.parseInt(st.nextToken());
			adj[a][b] = Math.max(adj[a][b], T-D);
		}
		br.close();

		int result = Integer.MAX_VALUE;
		int[] dist = new int[C];
		
		Arrays.fill(dist, 1000000);
		dist[S] = 0;
		for(int n = 0; n < C-1; n++) {
			for(int i = 0; i < C; i++) {
				for(int j = 0; j < C; j++) {
					if(adj[i][j] == Integer.MIN_VALUE) continue;
					if(dist[j] > dist[i] + adj[i][j]) dist[j] = dist[i] + adj[i][j];
				}
			}
		}
		
		boolean cycle = false;
		for(int i = 0; i < C; i++) {
			result = Math.min(result, dist[i]);
			for(int j = 0; j < C; j++) {
				if(adj[i][j] == Integer.MIN_VALUE) continue;
				if(dist[j] > dist[i] + adj[i][j]) cycle = true;
			}
		}
		
		//PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter(new File(file + ".out"))));
		if(cycle) System.out.println(-1);
		else System.out.println(-result + D);
		//out.println(result);
		//out.close();
	}
}
