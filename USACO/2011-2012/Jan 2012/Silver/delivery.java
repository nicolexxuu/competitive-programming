// Delivery Route

import java.util.*;
import java.io.*;

public class delivery {
	static int[] dx = {-1, 0, 1, 0};
	static int[] dy = {0, 1, 0, -1};
	public static void main(String[] args) throws IOException {
		String file = "";
		// BufferedReader br = new BufferedReader(new FileReader(new File(file + ".in")));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken());
		int[][] xs = new int[N][5];
		int[][] ys = new int[N][5];
		for(int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			xs[i][0] = Integer.parseInt(st.nextToken());
			ys[i][0] = Integer.parseInt(st.nextToken());
			//System.out.println(xs[i][0] + " " + ys[i][0]);
			for(int k = 0; k < 4; k++) {
				xs[i][k+1] = xs[i][0] + dx[k];
				ys[i][k+1] = ys[i][0] + dy[k];
				
			}
			///System.out.println();
		}
		
		ArrayList<ArrayList<Edge>> adj = new ArrayList<>();
		for(int from = 0; from < N; from++) {
			adj.add(new ArrayList<>());
			int to = (from + 1) % N;
			int x1 = xs[from][0];
			int y1 = ys[from][0];
			
			for(int j = 0; j < 5; j++) {
				
				int x2 = xs[to][j]; //destination
				int y2 = ys[to][j];
				boolean fine1 = true, fine2 = true;
				
				for(int k = 0; k < N; k++) {
					if(k == to || k == from) continue;
					int x3 = xs[k][0];
					int y3 = ys[k][0];
					// horizontal first
					if((y3 == y1 && x3 >= Math.min(x1, x2) && x3 <= Math.max(x1, x2)) || (x3 == x2 && y3 >= Math.min(y2, y1) && y3 <= Math.max(y2, y1)))
						fine1 = false;
					// vertical first
					if((y3 == y2 && x3 >= Math.min(x1, x2) && x3 <= Math.max(x1, x2)) || (x3 == x1 && y3 >= Math.min(y1, y2) && y3 <= Math.max(y1, y2)));
						fine2 = false;
				}
				
				if(fine1 || fine2) {
					int d = dist(xs[from][0], ys[from][0], xs[to][j], ys[to][j]);
					if(j > 0) d++;
					adj.get(from).add(new Edge(to, j, d));
				}
			}
		}
		br.close();
		
		boolean[] mark = new boolean[N];
		int[] dist = new int[N];
		Arrays.fill(dist, Integer.MAX_VALUE);
		PriorityQueue<int[]> toVisit = new PriorityQueue<>((a, b) -> a[1] - b[1]);
		for(Edge e: adj.get(0)) dist[1] = Math.min(dist[1], e.weight);
		toVisit.add(new int[] {1, dist[1]});
		
		while(!toVisit.isEmpty()) {
			int[] curr = toVisit.remove();
			int r = curr[0], d = curr[1];
			if(mark[r]) continue;
			mark[r] = true;
			
			for(Edge e: adj.get(r)) {
				if(d + e.weight < dist[e.r]) {
					dist[e.r]= d + e.weight;
					toVisit.add(new int[] {e.r, dist[e.r]});
				}
			}
		}
		
		for(int i: dist) System.out.println(i);
		if(dist[0] == Integer.MAX_VALUE) System.out.println(-1);
		else System.out.println(dist[0]);
	}
	
	public static class Edge /*implements Comparable<Edge>*/{
		int r, c, weight;
		Edge(int r, int c, int weight){
			this.r = r;
			this.c = c;
			this.weight = weight;
		}
		
//		public int compareTo(Edge other) {
//			return this.weight - other.weight;
//		}
	}
	
	public static int dist(int x1, int y1, int x2, int y2) {
		return Math.abs(x1 - x2) + Math.abs(y1 - y2);
	}
}
