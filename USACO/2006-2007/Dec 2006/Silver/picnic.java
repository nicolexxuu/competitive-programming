// Cow Picnic

import java.util.*;
import java.io.*;

public class picnic {
	public static void main(String[] args) throws IOException {
		String file = "";
		// BufferedReader br = new BufferedReader(new FileReader(new File(file + ".in")));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int K = Integer.parseInt(st.nextToken());
		int N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());
		
		int[] cows = new int[K];
		for(int i = 0; i < K; i++) {
			st = new StringTokenizer(br.readLine());
			cows[i] = Integer.parseInt(st.nextToken()) - 1;
		}
		
		boolean[][] adj = new boolean[N][N];
		for(int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine());
			int a = Integer.parseInt(st.nextToken()) - 1;
			int b = Integer.parseInt(st.nextToken()) - 1;
			adj[a][b] = true;
		}
		
		int[] res = new int[N];
		br.close();

		for(int start = 0; start < K; start++) {
			boolean[] visited = new boolean[N];
			ArrayDeque<Integer> toVisit = new ArrayDeque<>();
			toVisit.add(cows[start]);
			while(!toVisit.isEmpty()) {
				int curr = toVisit.remove();
				if(visited[curr]) continue;
				visited[curr] = true;
				res[curr]++;
				for(int i = 0; i < N; i++) if(adj[curr][i]) toVisit.add(i);
			}
		}
		
		int result = 0;
		for(int i: res) if(i == K) result++;
		//PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter(new File(file + ".out"))));
		System.out.println(result);
		//out.println(result);
		//out.close();
	}
}
