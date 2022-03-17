// Counting Rooms

import java.util.*;
import java.io.*;

public class CountingRooms {
	static int[] dr = {-1, 0, 1, 0};
	static int[] dc = {0, 1, 0, -1};
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int n = Integer.parseInt(st.nextToken()), m = Integer.parseInt(st.nextToken());
		boolean[][] map = new boolean[n+2][m+2];
		for(int i = 1; i <= n; i++) {
			st = new StringTokenizer(br.readLine());
			String s = st.nextToken();
			for(int j = 1; j <= m; j++) {
				map[i][j] = s.charAt(j-1) == '.';
			}
		}
		br.close();
		
		int count = 0;
		for(int i = 1; i <= n; i++) {
			for(int j = 1; j <= m; j++) {
				if(!map[i][j]) continue;
				count++;
				
				ArrayDeque<int[]> toVisit = new ArrayDeque<>();
				toVisit.add(new int[] {i, j});
				map[i][j] = false;
				while(!toVisit.isEmpty()) {
					int r = toVisit.peek()[0], c = toVisit.poll()[1];
					
					for(int k = 0; k < 4; k++) {
						int nr = r + dr[k], nc = c + dc[k];
						if(map[nr][nc]) {
							toVisit.add(new int[] {nr, nc});
							map[nr][nc] = false;
						}
					}
				}
			}
		}
		
		System.out.println(count);
	}
}