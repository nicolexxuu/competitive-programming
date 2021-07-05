// Bessie's Dream

import java.util.*;
import java.io.*;

public class dream {
	
	static int[] dr = {-1, 0, 1, 0};
	static int[] dc = {0, 1, 0, -1};
	public static void main(String[] args) throws IOException {
		String file = "";
		// BufferedReader br = new BufferedReader(new FileReader(new File(file + ".in")));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());
		
		int[][] grid = new int[N][M];
		int[][][] dist = new int[N][M][2];
		
		for(int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			for(int j = 0; j < M; j++) {
				dist[i][j][0] = Integer.MAX_VALUE;
				dist[i][j][1] = Integer.MAX_VALUE;
				grid[i][j] = Integer.parseInt(st.nextToken());
			}
		}

		br.close();

		PriorityQueue<State> toVisit = new PriorityQueue<State>((a, b) -> a.cost - b.cost);
		toVisit.add(new State(0, 0, 0, 0));
		int result = Integer.MAX_VALUE;
		
		while(!toVisit.isEmpty()) {
			State curr = toVisit.poll();
			int r = curr.r, c = curr.c, smells = curr.smells, cost = curr.cost;
			if(dist[r][c][smells] != Integer.MAX_VALUE) continue;
			if(r == N-1 && c == M-1) result = Math.min(result, cost);
			dist[r][c][smells] = cost;
			
			for(int k = 0; k < 4; k++) {
				int nsmell = smells;
				int nr = r, nc = c;
				int traveled = 0;
				
				boolean finish = false;
				while(!finish || grid[nr][nc] == 4) {
					finish = true;
					if(grid[nr][nc] == 4) nsmell = 0;
					else if (grid[nr][nc] == 2) nsmell = 1;
					
					int nnr = nr + dr[k], nnc = nc + dc[k];
					if(nnr < 0 || nnr >= N || nnc < 0 || nnc >= M || grid[nnr][nnc] == 0 || (grid[nnr][nnc] == 3 && nsmell == 0)) break;
					nr = nnr; nc = nnc;
					traveled++;
				}
				
				if(dist[nr][nc][nsmell] > cost + traveled)
					toVisit.add(new State(nr, nc, nsmell, cost + traveled));
			}
		}
		
		//PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter(new File(file + ".out"))));
		if(result == Integer.MAX_VALUE) System.out.println(-1);
		else System.out.println(result);
		//out.println(result);
		//out.close();
	}
	
	public static class State {
		int r, c, smells, cost;
		
		State(int r, int c, int smells, int cost) {
			this.r = r;
			this.c = c;
			this.smells = smells;
			this.cost = cost;
		}
	}
}
