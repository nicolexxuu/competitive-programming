// Why Did the Cow Cross the Road

import java.util.*;
import java.io.*;

public class visitfj {
	static int[] dr = {-1, 0, 1, 0};
	static int[] dc = {0, 1, 0, -1};
	public static void main(String[] args) throws IOException {
		String file = "visitfj";
		BufferedReader br = new BufferedReader(new FileReader(new File(file + ".in")));
		// BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken());
		int T = Integer.parseInt(st.nextToken());
		
		int[][] grass = new int[N][N];
		long dist[][][] = new long[N][N][4];
		for(int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			for(int j = 0; j < N; j++) {
				grass[i][j] = Integer.parseInt(st.nextToken());
				for(int k = 0; k <= 3; k++) dist[i][j][k] = Long.MAX_VALUE;
			}
		}
		br.close();
		
		PriorityQueue<Edge> toVisit = new PriorityQueue<>();
		toVisit.add(new Edge(0, 0, 0, 0));
		while(!toVisit.isEmpty()) {
			Edge c = toVisit.remove();
			if(dist[c.r][c.c][c.t] != Long.MAX_VALUE) continue;
			dist[c.r][c.c][c.t] = c.d; 
			
			int nt = c.t + 1;
			if(nt == 4) nt = 1;
			
			for(int k = 0; k < 4; k++) {
				int nr = c.r + dr[k], nc = c.c + dc[k];
				if(nr < 0 || nr >= N || nc < 0 || nc >= N || dist[nr][nc][nt] != Long.MAX_VALUE) continue;
				long nd = dist[c.r][c.c][c.t] + T + ((nt == 3) ? grass[nr][nc] : 0);
				toVisit.add(new Edge(nr, nc, nt, nd));
			}
		}
		
		long res = Long.MAX_VALUE;
		for(int i = 1; i <= 3; i++) res = Math.min(res, dist[N-1][N-1][i]);
		PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter(new File(file + ".out"))));
		System.out.println(res);
		out.println(res);
		out.close();
	}
	
	public static class Edge implements Comparable<Edge> {
		int r, c, t;
		long d;
		Edge(int r, int c, int t, long d) {
			this.r = r;
			this.c = c;
			this.t = t;
			this.d = d;
		}
		
		public int compareTo(Edge other) {
			if(d < other.d) return -1;
			if(d > other.d) return 1;
			return 0;
		}
	}
}
