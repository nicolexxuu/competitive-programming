// Superbull

import java.util.*;
import java.io.*;

public class superbull {
	public static void main(String[] args) throws IOException {
		String file = "";
		// BufferedReader br = new BufferedReader(new FileReader(new File(file + ".in")));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken());
		int[] IDs = new int[N];
		long[] dist = new long[N];
		
		for(int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			IDs[i] = Integer.parseInt(st.nextToken());
			dist[i] = -1;
		}
		br.close();

		long result = 0;
		dist[0] = 0;
		
		for(int i = 0; i < N; i++) {
			int maxInd = 0;
			long maxVal = -1;
			
			for(int j = 0; j < N; j++) {
				if(dist[j] != Long.MAX_VALUE && dist[j] > maxVal) {
					maxVal = dist[j];
					maxInd = j;
				}
			}
			result += maxVal;
			dist[maxInd] = Long.MAX_VALUE;
			
			for(int j = 0; j < N; j++) {
				if(dist[j] == Long.MAX_VALUE || j == maxInd) continue;
				if((IDs[maxInd] ^ IDs[j]) > dist[j]) dist[j] = IDs[maxInd] ^ IDs[j];
			}
		}
		//PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter(new File(file + ".out"))));
		System.out.println(result);
		//out.println(result);
		//out.close();
	}
}
