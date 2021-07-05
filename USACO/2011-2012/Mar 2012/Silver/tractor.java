// Tractor

import java.util.*;
import java.io.*;

public class tractor {
	
	static int[] dr = {-1, 0, 1, 0};
	static int[] dc = {0, 1, 0, -1};
	public static void main(String[] args) throws IOException {
		//String file = "";
		//BufferedReader br = new BufferedReader(new FileReader(new File(file + ".in")));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken());
		int R = Integer.parseInt(st.nextToken());
		int C = Integer.parseInt(st.nextToken());
		
		int[][] field = new int[1002][1002];
		for(int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			field[Integer.parseInt(st.nextToken())][Integer.parseInt(st.nextToken())] = -1;
		}
		br.close();
		
		int result = -1;
		
		Deque<int[]> now = new ArrayDeque<>();
		now.add(new int[] {R, C});
		field[R][C] = 1;
		
		while(result == -1) {
			//System.out.println("new");
			Deque<int[]> later = new ArrayDeque<>();
			
			while(!now.isEmpty()) {
				int[] curr = now.remove();
				int r = curr[0];
				int c = curr[1];
				//System.out.println("now looking at : " + r + " " + c);
				
				if(r == 0 && c == 0) {
					result = field[r][c] - 1;
					break;
				}
				
				for(int k = 0; k < 4; k++) {
					int nr = r + dr[k];
					int nc = c + dc[k];
					if(nr >= 0 && nr <= 1001 && nc >= 0 && nc <= 1001) {
						if(field[nr][nc] == 0) {
							now.add(new int[] {nr, nc});
							field[nr][nc] = field[r][c];
						} else if (field[nr][nc] == -1) {
							field[nr][nc] = field[r][c]+1;
							later.add(new int[] {nr, nc});
						}
					}
				}
			}
			
			now = later;
		}
		//PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter(new File(file + ".out"))));
		System.out.println(result);
		//out.println(result);
		//out.close();
	}
}

