// Tractor 2

import java.util.*;
import java.io.*;

public class tractor2 {
	static int[] size, arr;
	public static void main(String[] args) throws IOException {
		String file = "";
		// BufferedReader br = new BufferedReader(new FileReader(new File(file + ".in")));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken());
		ArrayList<Edge> edges = new ArrayList<>();
		int[][] grid = new int[N][N];
		size = new int[N*N];
		arr = new int[N*N];
		
		for(int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			for(int j = 0; j < N; j++) {
				int id = i*N + j;
				size[id] = 1;
				arr[id] = id;
				grid[i][j] = Integer.parseInt(st.nextToken());
				
				if(i > 0) edges.add(new Edge(id, id-N, Math.abs(grid[i][j] - grid[i-1][j])));
				if(j > 0) edges.add(new Edge(id, id-1, Math.abs(grid[i][j] - grid[i][j-1])));
			}
		}
		br.close();
		
		Collections.sort(edges);
		for(Edge e: edges) {
			//System.out.println(e.from + " " + e.to + " " + e.weight);
			union(e.from, e.to);
			//System.out.println("from " + e.from + " to " + e.to);
			//System.out.println(e.from + " has a size of " + size[arr[e.from]]);
			if(size[find(e.from)] >= (N*N+1)/2) {
				System.out.println(e.weight);
				break;
			}
		}
		//System.out.println("done!");
		//PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter(new File(file + ".out"))));
		//out.println();
		//out.close();
	}
	
	public static int find(int a) {
		int curr = a;
		while(arr[curr] != curr) {
			curr = arr[curr];
		}
		
		while(a != curr) { // path compression
			int temp = arr[a];
			arr[a] = curr;
			a = temp;
		}
		
		return curr;
	}
	
	public static void union(int a, int b) {
		int ra = find(a);
		int rb = find(b);
		if(ra == rb) return;
		
		if(size[ra] < size[rb]) {
			arr[ra] = rb;
			size[rb] += size[ra];
		} else {
			arr[rb] = ra;
			size[ra] += size[rb];
		}
	}
	
	public static class Edge implements Comparable<Edge> {
		int from, to, weight;
		
		Edge(int from, int to, int weight) {
			this.from = from;
			this.to = to;
			this.weight = weight;
		}
		
		public int compareTo (Edge other) {
			return weight - other.weight;
		}
	}
}
