// Agri-Net

import java.util.*;
import java.io.*;

public class agrinet {
	public static void main(String[] args) throws IOException {
		String file = "";
		// BufferedReader br = new BufferedReader(new FileReader(new File(file + ".in")));
		Scanner in = new Scanner(System.in);
		int N = in.nextInt();
		int[][] adj = new int[N][N];

		for(int i = 0; i < N; i++) {
			for(int j = 0; j < N; j++) {
				adj[i][j] = in.nextInt();
			}
		}

		int result = 0;
		int[] dist = new int[N];
		boolean[] visited = new boolean[N];
		int inTree = 0;
		for(int i = 1; i < N; i++) dist[i] = Integer.MAX_VALUE;
		
		while(inTree < N) {
			int minInd = 0;
			int minVal = Integer.MAX_VALUE;
			
			for(int i = 0; i < N; i++) {
				if(dist[i] < minVal && !visited[i]) {
					minInd = i;
					minVal = dist[i];
				}
			}
			
			visited[minInd] = true;
			result += minVal;
			
			for(int i = 0; i < N; i++) {
				if(i == minInd) continue;
				dist[i] = Math.min(dist[i], adj[minInd][i]);
			}
			
			inTree++;
		}
		//PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter(new File(file + ".out"))));
		System.out.println(result);
		//out.println(result);
		//out.close();
	}
}
