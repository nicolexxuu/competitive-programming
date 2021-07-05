import java.util.*;
import java.io.*;

public class replication {
	static int[] dr = {-1, 0, 1, 0};
	static int[] dc = {0, 1, 0, -1};
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		int N = Integer.parseInt(st.nextToken());
		int D = Integer.parseInt(st.nextToken());
		int[][] grid = new int[N][N];
		ArrayDeque<State> rocks = new ArrayDeque<>();
		ArrayDeque<State> centers = new ArrayDeque<>();
		ArrayDeque<State>[] levels = new ArrayDeque[N/2];
		for(int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			String str = st.nextToken();
			if(i < N/2) levels[i] = new ArrayDeque<>();
			for(int j = 0; j < N; j++) {
				if(str.charAt(j) == '#') {
					grid[i][j] = -1;
					rocks.add(new State(i, j, 0));
				} else if(str.charAt(j) == '.') {
					grid[i][j] = 0;
				} else {
					grid[i][j] = 1;
					centers.add(new State(i, j, 0));
				}
			}
		}
		
		// bfs: find smallest distance of empty cell to a rock 
		int[][] rockDist = new int[N][N];
		for(int[] i: rockDist) Arrays.fill(i, -1);
		while(!rocks.isEmpty()) {
			State curr = rocks.remove();
			int r = curr.r, c = curr.c, val = curr.val;
			if(rockDist[r][c] != -1) continue;
			rockDist[r][c] = val;
			
			for(int k = 0; k < 4; k++) {
				int nr = r + dr[k], nc = c + dc[k];
				if(nr < 0 || nr >= N || nc < 0 || nc >= N || grid[nr][nc] == -1 || rockDist[nr][nc] != -1) continue;
				rocks.add(new State(nr, nc, val + 1));
			}
		}
		
		boolean[][] center = new boolean[N][N];
		while(!centers.isEmpty()) {
			State curr = centers.remove();
			int r = curr.r, c = curr.c, t = curr.val;
			if(center[r][c]) continue;
			center[r][c] = true;
			levels[rockDist[r][c]].add(new State(r, c));
			
			// robot dies immediately after arriving, can't replicate
			if(1 + t/D > rockDist[r][c]) continue;
			
			for(int k = 0; k < 4; k++) {
				int nr = r + dr[k], nc = c + dc[k];
				int sz = 1 + t/D; // give 1 time-unit margin - robot will move before it replicates
				if(grid[nr][nc] == -1 || center[nr][nc]) continue;
				if(sz <= rockDist[nr][nc]) centers.add(new State(nr, nc, t + 1));
			}
		}
		
		boolean[][] possible = new boolean[N][N];
		for(int sz = N/2-1; sz >= 1; sz--) {
			ArrayDeque<State> level = levels[sz];
			while(!level.isEmpty()) {
				State curr = level.remove();
				int r = curr.r, c = curr.c;
				if(possible[r][c]) continue;
				possible[r][c] = true;
				
				for(int k = 0; k < 4; k++) {
					int nr = r + dr[k], nc = c + dc[k];
					if(grid[nr][nc] == -1 || possible[nr][nc]) continue;
					levels[sz-1].add(new State(nr, nc));
				}
			}
		}
		
		int res = 0;
		for(boolean[] arr: possible)
			for(boolean b: arr) if(b) res++;

		System.out.println(res);
	}
	
	public static class State {
		int r, c, val;
		State(int r, int c, int val) {
			this.r = r;
			this.c = c;
			this.val = val;
		}
		
		State(int r, int c) {
			this.r = r;
			this.c = c;
		}
	}
}