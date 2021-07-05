// Watering Hole

import java.util.*;
import java.io.*;

public class water {
	public static void main(String[] args) throws IOException {
		String file = "";
		// BufferedReader br = new BufferedReader(new FileReader(new File(file + ".in")));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken());
		int[] W = new int[N];
		int[][] adj = new int[N][N];
		boolean[] mark = new boolean[N];
		int[] dist = new int[N];
		
		for(int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			W[i] = Integer.parseInt(st.nextToken());
			dist[i] = W[i];
		}
		
		for(int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			for(int j = 0; j < N; j++) {
				adj[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		br.close();

		int result = 0;
		
		for(int i = 0; i < N; i++) {
			int minInd = 0;
			int minVal = Integer.MAX_VALUE;
			for(int j = 0; j < N; j++) {
				if(mark[j]) continue;
				if(dist[j] < minVal) {
					minInd = j;
					minVal = dist[j];
				}
			}
			
			result += minVal;
			mark[minInd] = true;
			
			for(int j = 0; j < N; j++) {
				if(!mark[j] && adj[minInd][j] < dist[j]) {
					dist[j] = adj[minInd][j];
				}
			}
		}
		//PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter(new File(file + ".out"))));
		System.out.println(result);
		//out.println(result);
		//out.close();
	}
}

