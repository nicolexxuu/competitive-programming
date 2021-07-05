// Cross Country Skiing

import java.util.*;
import java.io.*;

public class ccski {
	
	static int[] dr = {-1, 0, 1, 0};
	static int[] dc = {0, 1, 0, -1};
	public static void main(String[] args) throws IOException {
		String file = "ccski";
		BufferedReader br = new BufferedReader(new FileReader(new File(file + ".in")));
		// BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int M = Integer.parseInt(st.nextToken());
		int N = Integer.parseInt(st.nextToken());
		
		int[][] grid = new int[M][N];
		for(int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine());
			for(int j = 0; j < N; j++) {
				grid[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		
		ArrayList<Integer> waypoints = new ArrayList<Integer>();
		for(int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine());
			for(int j = 0; j < N; j++) {
				if(Integer.parseInt(st.nextToken()) == 1) waypoints.add(N * i + j);
			}
		}
		br.close();
		
		int lo = 0, hi = 1000000000;
		
		while(lo < hi) {
			int mid = (lo+hi)/2;
			boolean[][] visited = new boolean[M][N];
			
			ArrayDeque<Integer> toVisit = new ArrayDeque<>();
			toVisit.add(0);
			visited[0][0] = true;
			
			while(!toVisit.isEmpty()) {
				int curr = toVisit.remove();
				int r = curr/N, c = curr%N;
				
				for(int k = 0; k < 4; k++) {
					int nr = r + dr[k];
					int nc = c + dc[k];
					if(nr < 0 || nr >= M || nc < 0 || nc >= N || visited[nr][nc] || Math.abs(grid[r][c] - grid[nr][nc]) > mid) continue;
					visited[nr][nc] = true;
					toVisit.add(nr * N + nc);
				}
			}
			
			boolean valid = true;
			for(int i: waypoints) if(!visited[i/N][i%N]) valid = false;
			
			if(valid) hi = mid;
			else lo = mid + 1;
		}
		
		int result = lo;
		PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter(new File(file + ".out"))));
		System.out.println(result);
		out.println(result);
		out.close();
	}
}

