// Mud Puddles

import java.util.*;
import java.io.*;

public class mud {
	static int[] dr = {-1, 0, 1, 0};
	static int[] dc = {0, 1, 0, -1};
	public static void main(String[] args) throws IOException {
		String file = "";
		// BufferedReader br = new BufferedReader(new FileReader(new File(file + ".in")));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int X = Integer.parseInt(st.nextToken())+500;
		int Y = Integer.parseInt(st.nextToken())+500;
		int N = Integer.parseInt(st.nextToken());
		int[][] grid = new int[1001][1001];
		
		for(int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			int r = Integer.parseInt(st.nextToken())+500;
			int c = Integer.parseInt(st.nextToken())+500;
			grid[r][c] = -1;
		}
		br.close();
		
		ArrayDeque<Point> toVisit = new ArrayDeque<>();
		toVisit.add(new Point(500, 500));
		grid[500][500] = 1;
		
		while(!toVisit.isEmpty()) {
			Point curr = toVisit.remove();
			int r = curr.x;
			int c = curr.y;
			
			for(int k = 0; k < 4; k++) {
				int nr = r+dr[k];
				int nc = c + dc[k];
				if(nr >= 0 && nr <= 1000 && nc >= 0 && nc <= 1000 && grid[nr][nc]==0) {
					grid[nr][nc] = grid[r][c]+1;
					toVisit.add(new Point(nr, nc));
				}
			}
		}
		
		System.out.println(grid[X][Y]-1);
	}
	
	public static class Point{
		int x, y;
		Point(int x, int y){
			this.x = x;
			this.y = y;
		}
	}
}
