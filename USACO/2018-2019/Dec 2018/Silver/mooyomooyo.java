// This template code suggested by KT BYTE Computer Science Academy
//   for use in reading and writing files for USACO problems.

// https://content.ktbyte.com/problem.java

import java.util.*;
import java.io.*;

public class mooyomooyo {
	static int[] dr = {1, -1, 0, 0};
	static int[] dc = {0, 0, 1, -1};
	public static void main(String[] args) throws IOException {
		String file = "mooyomooyo";
		BufferedReader br = new BufferedReader(new FileReader(new File(file + ".in")));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken());
		int K = Integer.parseInt(st.nextToken());
		
		int[][] board = new int[N][10];
		
		for(int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			String ln = st.nextToken();
			for(int j = 0; j < ln.length(); j++) {
				board[i][j] = Integer.parseInt(ln.substring(j,  j + 1));
			}
		}
		br.close();

		boolean done = false;
		while(!done) {
			done = true;
			boolean[][] visited = new boolean[N][10];
			for(int r = 0; r < N; r++) {
				for(int c = 0; c < 10; c++) {
					if(board[r][c] == 0 || visited[r][c]) continue;
					Deque<Integer> toVisit = new ArrayDeque<>();
					ArrayList<Integer> cluster = new ArrayList<>();
					toVisit.add(r * 10 + c);
					cluster.add(r * 10 + c);
					visited[r][c] = true;
					while(!toVisit.isEmpty()) {
						int curr = toVisit.remove();
						int row = curr / 10;
						int col = curr % 10;
						for(int k = 0; k < 4; k++) {
							int row1 = row + dr[k];
							int col1 = col + dc[k];
							if(row1 >= 0 && row1 < N && col1 >= 0 && col1 < 10 && !visited[row1][col1] && board[row1][col1] != 0 && board[row1][col1] == board[row][col]) {
								visited[row1][col1] = true;
								toVisit.add(row1 * 10 + col1);
								cluster.add(row1 * 10 + col1);
							}
						}
					}
						
					if(cluster.size() >= K) {
						done = false;
						for(int i = 0; i < cluster.size(); i++) {
							int n = cluster.get(i);
							board[n / 10][n % 10] = 0;
						}
					}
				}
			}
			
			
			if(!done) fall(board, N);
		}
		PrintWriter out = new PrintWriter(new File(file + ".out"));
		for(int i = 0; i < board.length; i++) {
			for(int j = 0; j < 10; j++) {
				out.print(board[i][j]);
			}
			out.println();
		}
		out.close();
	}
	
	public static void fall(int[][] board, int N) {
		for(int c = 0; c < 10; c++) {
			for(int r = N - 1; r >= 0; r--) {
				if(board[r][c] != 0) {
					int rows = 0;
					for(int i = N - 1; i > r; i--) {
						if(board[i][c] == 0) rows++;
					}
					if(rows != 0) {
						board[r + rows][c] = board[r][c];
						board[r][c] = 0;
					}
				}
			}
		}
	}
}
