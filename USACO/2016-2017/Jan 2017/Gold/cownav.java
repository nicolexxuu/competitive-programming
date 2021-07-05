// Cow Navigation

import java.util.*;
import java.io.*;

public class cownav {
	static int N;
	static boolean[][] grid;
	static int[] dr = {-1, 0, 1, 0};
	static int[] dc = {0, 1, 0, -1};
	public static void main(String[] args) throws IOException {
		String file = "cownav";
		BufferedReader br = new BufferedReader(new FileReader(new File(file + ".in")));
		//BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		grid = new boolean[N][N];
		for(int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			char[] temp = st.nextToken().toCharArray();
			for(int j = 0; j < N; j++) {
				grid[i][j] = temp[j] == 'H';
			}
		}
		br.close();
		
		int[][][][][][] dist = new int[N][N][4][N][N][4];
		ArrayDeque<State> toVisit = new ArrayDeque<>();
		toVisit.add(new State(N-1, 0, 0, N-1, 0, 1));
		
		while(!toVisit.isEmpty()) {
			State curr = toVisit.remove();
			curr.generateStates();
			for(State next: curr.nextStates) {
				if(dist[next.r1][next.c1][next.d1][next.r2][next.c2][next.d2] == 0) {
					dist[next.r1][next.c1][next.d1][next.r2][next.c2][next.d2] = dist[curr.r1][curr.c1][curr.d1][curr.r2][curr.c2][curr.d2] + 1;
					toVisit.add(next);
				}
			}
		}
		

		int result = Integer.MAX_VALUE;
		for(int i = 0; i < 4; i++) {
			for(int j = 0; j < 4; j++) {
				if(dist[0][N-1][i][0][N-1][j] != 0) result = Math.min(result, dist[0][N-1][i][0][N-1][j]);
			}
		}
		PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter(new File(file + ".out"))));
		System.out.println(result);
		out.println(result);
		out.close();
	}
	
	public static class State {
		int r1, c1, d1, r2, c2, d2;
		ArrayList<State> nextStates;
		State(int r1, int c1, int d1, int r2, int c2, int d2) {
			this.r1 = r1;
			this.c1 = c1;
			this.d1 = d1;
			this.r2 = r2;
			this.c2 = c2;
			this.d2 = d2;
		}
		
		void generateStates () {
			nextStates = new ArrayList<>();
			// try left
			nextStates.add(new State(r1, c1, d1 == 0 ? 3: d1-1, r2, c2, d2 == 0 ? 3: d2-1));
			// try right
			nextStates.add(new State(r1, c1, d1 == 3 ? 0: d1+1, r2, c2, d2 == 3 ? 0: d2+1));
			// try forward
			int[] newState = {r1, c1, d1, r2, c2, d2};
			for(int i = 0; i < 6; i += 3) {
				int r = newState[i], c = newState[i+1], d = newState[i+2];
				if(r == 0 && c == N-1) continue;
				int nr = r + dr[d], nc = c + dc[d];
				if(nr >= 0 && nr < N && nc >= 0 && nc < N && !grid[nr][nc]) {
					newState[i] = nr;
					newState[i+1] = nc;
				}
			}
			
			nextStates.add(new State(newState[0], newState[1], newState[2], newState[3], newState[4], newState[5]));
		}
	}
}
