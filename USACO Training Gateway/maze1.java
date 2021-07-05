// Overfencing

import java.util.*;
import java.io.*;

public class maze1 {
	static int[][] grid;
	static int[][] grid2;
	static int w, h, H, W;
	static int[] dr = {-1, 0, 1, 0};
	static int[] dc = {0, 1, 0, -1};
	static int[] DR = {-2, 0, 2, 0};
	static int[] DC = {0, 2, 0, -2};
	public static void main(String[] args) throws IOException {
		String file = "";
		// BufferedReader br = new BufferedReader(new FileReader(new File(file + ".in")));
		Scanner in = new Scanner(System.in);
		w = in.nextInt();
		h = in.nextInt();
		H = 2*h+1;
		W = 2*w+1;
		
		grid = new int[H][W];
		grid2 = new int[H][W];
		int[][] e = new int[2][2];
		int ei = 0;
		in.nextLine();
		for(int i = 0; i < H; i++) {
			String s = in.nextLine();
			char[] arr = s.toCharArray();
			for(int j = 0; j < W; j++) {
				if(arr[j] == '|' || arr[j] == '+' || arr[j] == '-') {
					grid[i][j] = -1;
					grid2[i][j] = -1;
				} else {
					if (i == 0 || j == 0 || i == H-1 || j == W-1) {
						e[ei][0] = i;
						e[ei][1] = j;
						ei++;
					}
					
				}
				
			}
		}
		
		bfs(e[0][0], e[0][1], grid);
		bfs(e[1][0], e[1][1], grid2);
		
//		printArr(grid);
//		System.out.println();
//		printArr(grid2);
		int result = 0;
		for(int i = 1; i < H-1; i++) {
			for(int j = 0; j < W-1; j++) {
				result = Math.max(result, Math.min(grid[i][j], grid2[i][j]));
			}
		}
		//PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter(new File(file + ".out"))));
		System.out.println(result);
		//out.println(result);
		//out.close();
	}
	
	public static void bfs(int r1, int c1, int[][] grid) {
		Deque<int[]> toVisit = new ArrayDeque<>();
		for(int k = 0; k < 4; k++) {
			int nr = r1 + dr[k];
			int nc = c1 + dc[k];
			if(nr >= 0 && nr < H && nc >= 0 && nc < W) {
				toVisit.add(new int[] {nr, nc});
				grid[nr][nc] = 1;
			}
		}
		
		while(!toVisit.isEmpty()) {
			int[] curr = toVisit.remove();
			int r = curr[0];
			int c = curr[1];
			
			for(int k = 0; k < 4; k++) {
				int nr = r + DR[k];
				int nc = c + DC[k];
				if(nr >= 0 && nr < H && nc >= 0 && nc < W && grid[r + dr[k]][c + dc[k]] == 0 && grid[nr][nc] == 0) {
					grid[nr][nc] = grid[r][c] + 1;
					toVisit.add(new int[] {nr, nc});
				}
			}
		}
	}
	
	public static void printArr(int[][] arr) {
		for (int i = 0; i < arr.length; i++) {
			for (int j = 0; j < arr[i].length; j++) {
				System.out.print(arr[i][j] + "  ");
				if(arr[i][j] >= 0) System.out.print(" ");
			}
			System.out.println();
		}
	}
}

