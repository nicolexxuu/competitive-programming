// "Daisy Chains in the Field"

import java.util.*;
import java.io.*;

public class daisy {
	public static void main(String[] args) throws IOException {
		String file = "";
		// BufferedReader br = new BufferedReader(new FileReader(new File(file + ".in")));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());
		
		int[][] adj = new int[N][N];
		for(int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine());
			int a = Integer.parseInt(st.nextToken())-1;
			int b = Integer.parseInt(st.nextToken())-1;
			adj[a][b] = 1;
			adj[b][a] = 1;
		}
		boolean[] visited = new boolean[N];
		br.close();

//		ArrayDeque<Integer> toVisit = new ArrayDeque<>();
//		toVisit.add(0);
//		
//		while(!toVisit.isEmpty()) {
//			int curr = toVisit.remove();
//			if(visited[curr]) continue;
//			visited[curr] = true;
//			
//			for(int i = 0; i < N; i++) {
//				if(adj[i][curr] == 1) toVisit.add(i);
//			}
//		}
		
		dfs(0, adj, visited);
		
		boolean none = true;
		for(int j = 0; j < N; j++) {
			if(!visited[j]) {
				System.out.println(j+1);
				none = false;
			}
		}
		if(none) System.out.println(0);
	}
	
	public static void dfs(int curr, int[][] adj, boolean[] visited) {
		if(visited[curr]) return;
		visited[curr] = true;
		
		for(int j = 0; j < adj.length; j++) 
			if(adj[j][curr] == 1) dfs(j, adj, visited);
	}
}
