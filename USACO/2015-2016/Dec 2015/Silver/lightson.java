// This template code suggested by KT BYTE Computer Science Academy
//   for use in reading and writing files for USACO problems.

// https://content.ktbyte.com/problem.java

//http://usaco.org/index.php?page=viewproblem2&cpid=570
//"Switching On The Lights", 2015 December Silver Contest

import java.util.*;
import java.io.*;

public class lightson {
	static int[] dr = {-1, 0, 1, 0};
	static int[] dc = {0, 1, 0, -1};
	public static void main(String[] args) throws IOException {
		String file = "lightson";
		BufferedReader br = new BufferedReader(new FileReader(new File(file + ".in")));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());
		
		Light[][] lights = new Light[N][N];
		for(int i = 0; i < N; i++) {
			for(int j = 0; j < N; j++) {
				lights[i][j] = new Light(i, j);
			}
		}
		lights[0][0].on = true;
		for(int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine());
			int x = Integer.parseInt(st.nextToken()) - 1;
			int y = Integer.parseInt(st.nextToken()) - 1;
			int a = Integer.parseInt(st.nextToken()) - 1;
			int b = Integer.parseInt(st.nextToken()) - 1;
			
			lights[x][y].toggle.add(lights[a][b]);
		}
		
		br.close();
		
		int result = 0;
		
		Deque<Light> toVisit = new ArrayDeque<Light>();
		Deque<Light> toVisit2 = new ArrayDeque<Light>();
		toVisit.add(lights[0][0]);
		lights[0][0].adj = true;
		
		while(!toVisit.isEmpty()) {
			Light curr = toVisit.remove();

			lights[curr.x][curr.y].visited = true;
			result++;
			
			for(Light l: curr.toggle) {
				l.on = true;
				toVisit2.add(l);
			}
			
			for(int k = 0; k < 4; k++) {
				int nr = curr.x + dr[k];
				int nc = curr.y + dc[k];
				if(nr >= 0 && nr < N && nc >= 0 && nc < N && !lights[nr][nc].visited && lights[nr][nc].on) {
					toVisit.add(lights[nr][nc]);
					lights[nr][nc].adj = true;
				}
			}
		}
		
		while(!toVisit2.isEmpty()) {
			Light curr = toVisit2.remove();
			boolean good = false;
			if(!curr.adj) {
				good = false;
				for(int k = 0; k < 4; k++) {
					int nr = curr.x + dr[k];
					int nc = curr.y + dc[k];
					if(nr >= 0 && nr < N && nc >= 0 && nc < N && lights[nr][nc].visited && lights[nr][nc].on) {
						good = true;
					}
				}
			}
			
			if(!good || lights[curr.x][curr.y].visited) continue;
		}
		
		PrintWriter out = new PrintWriter(new File(file + ".out"));
		System.out.println(result);
		out.println(result);
		out.close();
	}
	
	public static class Light {
		boolean on, visited, adj;
		ArrayList<Light> toggle;
		int x, y;
		
		Light(int x, int y){
			this.x = x;
			this.y = y;
			visited = false;
			on = false;
			adj = false;
			toggle = new ArrayList<>();
		}
	}
}
