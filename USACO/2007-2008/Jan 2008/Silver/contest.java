// Cow Contest

import java.util.*;
import java.io.*;

public class contest {
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
		}
		br.close();
		
		for(int i = 0; i < N; i++) {
			boolean[] visited = new boolean[N];
			ArrayDeque<Integer> toVisit = new ArrayDeque<>();
			
			toVisit.add(i);
			visited[i] = true;
			
			while(!toVisit.isEmpty()) {
				int curr = toVisit.remove();
				for(int j = 0; j < N; j++) {
					if(curr != j && !visited[j] && adj[curr][j] > 0) {
						visited[j] = true;
						adj[i][j] = 1;
						toVisit.add(j);
					}
				}
			}
		}

		int result = 0;
		for(int i = 0; i < N; i++) {
			result++;
			for(int j = 0; j < N; j++) {
				if(i == j) continue;
				if(!(adj[i][j] > 0 || adj[j][i] > 0)) {
					result--;
					break;
				}
			}
		}
		//PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter(new File(file + ".out"))));
		System.out.println(result);
		//out.println(result);
		//out.close();
	}
}
