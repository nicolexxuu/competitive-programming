// Distant Pastures

import java.util.*;
import java.io.*;

public class distant {
	
	static int[] dr = {-1, 0, 1, 0};
	static int[] dc = {0, 1, 0, -1};
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken());
		int A = Integer.parseInt(st.nextToken());
		int B = Integer.parseInt(st.nextToken());
		
		int[][] grid = new int[N][N];
		for(int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			char[] s = st.nextToken().toCharArray();
			for(int j = 0; j < N; j++) {
				if(s[j] == '(') grid[i][j] = 1;
			}
		}
		br.close();

		int result = 0;
		for(int start = 0; start < N*N; start++) {
			boolean[][] mark = new boolean[N][N];
			int[][] dist = new int[N][N];
			for(int i = 0; i < N; i++) Arrays.fill(dist[i], Integer.MAX_VALUE);
			dist[start/N][start%N] = 0;
			
			PriorityQueue<Pair> toVisit = new PriorityQueue<>();
			toVisit.add(new Pair(start, 0));
			while(!toVisit.isEmpty()) {
				Pair curr = toVisit.remove();
				int r = curr.l/N, c = curr.l%N, d = curr.d;
				if(mark[r][c]) continue;
				mark[r][c] = true;
				
				result = Math.max(result, d);
				for(int k = 0; k < 4; k++) {
					int nr = r + dr[k];
					int nc = c + dc[k];
					
					if(nr < 0 || nr >= N || nc < 0 || nc >= N || mark[nr][nc])  continue;
					if(grid[r][c] == grid[nr][nc] && d + A < dist[nr][nc]) {
						dist[nr][nc] = d + A;
						toVisit.add(new Pair(nr * N + nc, d + A));
					} else if (grid[r][c] != grid[nr][nc] && d + B < dist[nr][nc]) {
						dist[nr][nc] = d + B;
						toVisit.add(new Pair(nr * N + nc, d + B));
					}
				}
			}
		}

		System.out.println(result);
	}
	
	public static class Pair implements Comparable<Pair> {
		int l, d;
		
		Pair(int l, int d) {
			this.l = l;
			this.d = d;
		}
		
		public int compareTo(Pair other) {
			return this.d - other.d;
		}
	}
}


