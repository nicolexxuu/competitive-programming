// Laserphones

import java.util.*;
import java.io.*;

public class lphone {
	static int[] dr = new int[] {1, 0, -1, 0};
	static int[] dc = new int[] {0, 1, 0, -1};
	public static void main(String[] args) throws IOException {
		String file = "";
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int W = Integer.parseInt(st.nextToken());
		int H = Integer.parseInt(st.nextToken());
		int r1 = -1, c1 = -1, r2 = -1, c2 = -1;
		int[][] field = new int[H][W];
		
		for(int i = 0; i < H; i++) {
			st = new StringTokenizer(br.readLine());
			char[] arr = st.nextToken().toCharArray();
			for(int j = 0; j < W; j++) {
				if(arr[j] == '*') field[i][j] = -1;
				else if (arr[j] == 'C') {
					if(r1 == -1) { r1 = i; c1 = j;} 
					else { r2 = i; c2 = j;}
				}
			}
		}
		
		br.close();

		Deque<int[]> toVisit = new ArrayDeque<>();
		toVisit.add(new int[] {r1, c1, 1});
		boolean[][] visited = new boolean[H][W];
		visited[r1][c1] = true;
		boolean found = false;
		
		while(!toVisit.isEmpty() && !found) {
			int[] curr = toVisit.remove();
			int r = curr[0];
			int c = curr[1];
			int turns = curr[2];
			
			for(int k = 0; k < 4; k++) {
				int nr = r + dr[k];
				int nc = c + dc[k];
				//System.out.println(nr + " " + nc);
				while(nr < H && nr >= 0 && nc >= 0 && nc < W && field[nr][nc] != -1) {
					if(!visited[nr][nc]) {
						if(nr == r2 && nc == c2) {
							System.out.println(turns-1);
							found = true;
							break;
						}
						visited[nr][nc] = true;
						toVisit.add(new int[] {nr, nc, turns + 1});
					}
					
					nr += dr[k];
					nc += dc[k];
				}
			}
		}
		//PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter(new File(file + ".out"))));
		
		//out.println(result);
		//out.close();
	}
}

