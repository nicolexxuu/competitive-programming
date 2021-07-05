// Bovine Alliance

import java.util.*;
import java.io.*;

public class alliance {
	public static void main(String[] args) throws IOException {
		String file = "";
		// BufferedReader br = new BufferedReader(new FileReader(new File(file + ".in")));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());
		
		ArrayList<ArrayList<Integer>> adj = new ArrayList<>();
		
		for(int i = 0; i < N; i++) adj.add(new ArrayList<>());
		
		for(int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine());
			int a = Integer.parseInt(st.nextToken())-1;
			int b = Integer.parseInt(st.nextToken())-1;
			adj.get(a).add(b);
			adj.get(b).add(a);
		}
		br.close();

		long result = 1;
		boolean[] visited = new boolean[N];
		
		for(int i = 0; i < N; i++) {
			if(visited[i]) continue;
			
			int nodes = 0, edges = 0;
			ArrayDeque<Integer> toVisit = new ArrayDeque<>();
			toVisit.add(i);
			visited[i] = true;
			while(!toVisit.isEmpty()) {
				int curr = toVisit.remove();
				nodes++;
				
				for(int to: adj.get(curr)) {
					edges++;
					if(!visited[to]) {
						visited[to] = true;
						toVisit.add(to);
					}
				}
			}
			
			edges /=2;
			
			// System.out.println("starting: " + i);
			// System.out.println("nodes: " + nodes + " edges: " + edges);
			if(nodes == edges) result *= 2;
			else if(edges > nodes) result *= 0;
			else result *= nodes;
			result %= 1000000007;
		}
		
		//PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter(new File(file + ".out"))));
		System.out.println(result);
		//out.println(result);
		//out.close();
	}
}
