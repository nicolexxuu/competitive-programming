// Total Flow

import java.util.*;
import java.io.*;

public class flow {
	public static void main(String[] args) throws IOException {
		String file = "";
		// BufferedReader br = new BufferedReader(new FileReader(new File(file + ".in")));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int M = Integer.parseInt(st.nextToken());
		int[][] adj = new int[52][52];
		for(int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine());
			char a = st.nextToken().charAt(0), b = st.nextToken().charAt(0);
			int ai = 0, bi = 0;
			if(a >= 'A' && a <= 'Z') ai = a - 'A';
			else ai = a - 'a' + 26;
			if(b >= 'A' && b <= 'Z') bi = b - 'A';
			else bi = b - 'a' + 26;
			int w = Integer.parseInt(st.nextToken());
			adj[ai][bi] += w;
			adj[bi][ai] += w;
		}
		br.close();

		boolean finished = false;
		while(!finished) {
			finished = true;
			
			for(int j = 1; j < 52; j++) {
				if(j == 25) continue;
				ArrayList<Integer> connects = new ArrayList<>();
				for(int k = 0; k < 52; k++) if(adj[j][k] > 0) connects.add(k);
				if(connects.size() == 2) {
					finished = false;
					int a = connects.get(0);
					int b = connects.get(1);
					adj[a][b] += Math.min(adj[j][a], adj[j][b]);
					adj[b][a] = adj[a][b];
					for(int k: connects) {
						adj[j][k] = 0;
						adj[k][j] = 0;
					}
				} else if (connects.size() == 1) {
					int a = connects.get(0);
					adj[j][a] = 0;
					adj[a][j] = 0;
				}
			}
		}
		
		//PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter(new File(file + ".out"))));
		System.out.println(adj[0][25]);
		//out.println(result);
		//out.close();
	}
}
