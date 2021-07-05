// The Castle

import java.util.*;
import java.io.*;

public class castle {
	
	static int[] dr = {1, 0, -1, 0};
	static int[] dc = {0, 1, 0, -1};
	public static void main(String[] args) throws IOException {
		String file = "";
		// BufferedReader br = new BufferedReader(new FileReader(new File(file + ".in")));
		Scanner in = new Scanner(System.in);
		//StringTokenizer st = new StringTokenizer(br.readLine());
		int M = in.nextInt();
		int N = in.nextInt();
		Room[][] map = new Room[N][M];
		ArrayList<Integer> sizes = new ArrayList<Integer>();

		int b = 0, q = 0;
		while(in.hasNext()) {
			int a = in.nextInt();
			//System.out.println(b + " " + q + ": " + a);
			map[b][q] = new Room(a, b, q);
			q++;
			if(q >= M) {
				b++;
				q=0;
			}
		}
		
		int max = 0;
		int id = 0;
		for(int i = 0; i < N; i++) {
			for(int j = 0; j < M; j++) {
				if(map[i][j].id != -1) continue;
				Deque<Room> toVisit = new ArrayDeque<Room>();
				map[i][j].id = id;
				int size = 0;
				toVisit.add(map[i][j]);
				while(!toVisit.isEmpty()) {
					Room curr = toVisit.remove();
					size++;
					
					for(int k = 0; k < 4; k++) {
						if(curr.walls[k]) continue;
						int nr = curr.r + dr[k];
						int nc = curr.c + dc[k];
						if(nr >= 0 && nr < N && nc >= 0 && nc < M && map[nr][nc].id == -1) {
							map[nr][nc].id = id;
							toVisit.add(map[nr][nc]);
						}
					}
				
				}
				sizes.add(size);
				max = Math.max(max, size);
				id++;
			}
		}
		
		int maxR = max;
		for(int r = 0; r < N; r++) {
			for(int c = 0; c < M; c++) {
				for(int k = 1; k <= 2; k++) {
					if(map[r][c].walls[k]) {
						int nr = r + dr[k];
						int nc = c + dc[k];
						if(nr >= 0 && nr < N && nc >= 0 && nc < M && map[nr][nc].id != map[r][c].id) 
							maxR = Math.max(maxR, sizes.get(map[nr][nc].id) + sizes.get(map[r][c].id));
					}
				}
			}
		}
		
		//PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter(new File(file + ".out"))));
		System.out.println(sizes.size());
		System.out.println(max);
		System.out.println(maxR);
		//out.println(result);
		//out.close();
	}
	
	public static class Room {
		int id;
		boolean[] walls;
		int size;
		int r, c;
		Room (int w, int r, int c){
			this.r = r;
			this.c = c;
			id = -1;
			walls = new boolean[4];
			int dir = 0;
			for(int i = 8; i >= 1; i/=2) {
				if(w-i >= 0) {
					w-=i;
					walls[dir] = true;
				}
				dir++;
			}
		}
	}
	
}
