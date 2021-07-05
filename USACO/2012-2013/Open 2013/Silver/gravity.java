// Gravity

import java.util.*;
import java.io.*;

public class gravity {
	static int[] d = {1, -1};
	public static void main(String[] args) throws IOException {
		String file = "";
		// BufferedReader br = new BufferedReader(new FileReader(new File(file + ".in")));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());
		int[][][] world = new int[N][M][2];
		int cr = 0, cc = 0, dr = 0, dc = 0;
		
		for(int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			char[] arr = st.nextToken().toCharArray();
			for(int j = 0; j < M; j++) {
				if(arr[j] == '#') {
					world[i][j][0] = -1;
					world[i][j][1] = -1;
				} else if (arr[j] == 'D') {
					dr = i;
					dc = j;
				} else if (arr[j] == 'C') {
					cr = i;
					cc = j;
					world[i][j][0] = 1;
				}
			}
		}
		br.close();

		ArrayDeque<int[]> now = new ArrayDeque<>();
		ArrayDeque<int[]> later = new ArrayDeque<>();
		now.add(new int[] {cr, cc, 0});
		
		while(!now.isEmpty() || !later.isEmpty()) {
			
			while(!now.isEmpty()) {
				int[] curr = now.remove();
				int r = curr[0];
				int c = curr[1];
				int g = curr[2];
				boolean gravity = g == 0;
				
				if((gravity && r == N-1) || (!gravity && r == 0)) continue;
				
				int nr = r+d[g];
				if(world[nr][c][g] >= 0) {
					if(world[nr][c][g] == 0) {
						world[nr][c][g] = world[r][c][g];
						now.add(new int[] {nr, c, g});
					}
				} else {
					for(int k = 0; k < 2; k++) {
						int nc = c + d[k];
						if(nc >= 0 && nc < M && world[r][nc][g] == 0) {
							world[r][nc][g] = world[r][c][g];
							now.add(new int[] {r, nc, g});
						}
					}
						
					if(world[r][c][1-g] == 0) {
						world[r][c][1-g] = world[r][c][g] + 1;
						later.add(new int[] {r, c, 1-g});
					}
				}
			}
			
			for(int[]a: later) now.add(a);
			
			later.clear();
		}
		
		int result = Math.max(-1, world[dr][dc][0]);
		if(world[dr][dc][0] == 0) result = world[dr][dc][1];
		else result = Math.min(result, world[dr][dc][1]);
		result--;
		//PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter(new File(file + ".out"))));
		System.out.println(result);
		//out.println(result);
		//out.close();
	}
}
