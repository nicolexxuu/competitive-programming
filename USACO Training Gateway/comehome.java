// Bessie Come Home

import java.util.*;
import java.io.*;

public class comehome {
	public static void main(String[] args) throws IOException {
		String file = "";
		// BufferedReader br = new BufferedReader(new FileReader(new File(file + ".in")));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int P = Integer.parseInt(st.nextToken());
		int[][] adj = new int[52][52];
		for(int[] i: adj) Arrays.fill(i, Integer.MAX_VALUE);
		boolean[] cow = new boolean[26];
		
		for(int i = 0; i < P; i++) {
			st = new StringTokenizer(br.readLine());
			char a = st.nextToken().toCharArray()[0];
			char b = st.nextToken().toCharArray()[0];
			int dist = Integer.parseInt(st.nextToken());
			
			int aI, bI;
			if(a >= 'A' && a <= 'Z') {
				aI = a - 'A';
				cow[aI] = true;
			}
			else aI = a - 'a' + 26;
			
			if(b >= 'A' && b <= 'Z') {
				bI = b - 'A';
				cow[bI] = true;
			}
			else bI = b - 'a' + 26;

			adj[aI][bI] = Math.min(adj[aI][bI], dist);
			adj[bI][aI] = Math.min(adj[bI][aI], dist);
		}
		br.close();

		
		for(int k = 0; k < 52; k++) {
			for(int i = 0; i < 52; i++) {
				if(adj[i][k] == Integer.MAX_VALUE) continue;
				for(int j = 0; j < 52; j++) {
					if(adj[k][j] == Integer.MAX_VALUE) continue;
					adj[i][j] = Math.min(adj[i][j], adj[i][k] + adj[k][j]);
				}
			}
		}
		
		int result = Integer.MAX_VALUE;
		int index = 0;
		for(int i = 0; i < 25; i++) {
			if(adj[i][25] <= result && cow[i]) {
				result = adj[i][25];
				index = i;
			}
		}
		
		System.out.println((char)(index+'A') + " " + result);
	}
}
