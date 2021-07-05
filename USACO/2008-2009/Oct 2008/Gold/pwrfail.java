// Power Failure

import java.util.*;
import java.io.*;

public class pwrfail2 {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken());
		int W = Integer.parseInt(st.nextToken());
		st = new StringTokenizer(br.readLine());
		double M = Double.parseDouble(st.nextToken());
		
		int[] x = new int[N], y = new int[N];
		double[][] dist = new double[N][N];
		
		for(int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			x[i] = Integer.parseInt(st.nextToken());
			y[i] = Integer.parseInt(st.nextToken());
		}
		
		for(int i = 0; i < N; i++) { 
			for(int j = 0; j < N; j++) {
				dist[i][j] = Math.sqrt((double)(x[i] - x[j])*(x[i] - x[j]) + (double)(y[i] - y[j])*(y[i] - y[j]));
			}
		}
		
		for(int i = 0; i < W; i++) {
			st = new StringTokenizer(br.readLine());
			int a = Integer.parseInt(st.nextToken()) - 1, b = Integer.parseInt(st.nextToken()) - 1;
			dist[a][b] = dist[b][a] = 0;
		}
		br.close();

		double[] result = new double[N];
		boolean[] mark = new boolean[N];
		Arrays.fill(result, Integer.MAX_VALUE);
		result[0] = 0;
		
		for(int i = 0; i < N; i++) {
			int minInd = -1;
			double minDist = Integer.MAX_VALUE;
			
			for(int j = 0; j < N; j++) {
				if(!mark[j] && result[j] < minDist) {
					minInd = j;
					minDist = result[j];
				}
			}
			//System.out.println((minInd + 1) + " " + minDist);
			if(minInd == -1) break;
			mark[minInd] = true;
			
			for(int j = 0; j < N; j++) {
				if(!mark[j] && dist[minInd][j] <= M) result[j] = Math.min(result[j], result[minInd] + dist[minInd][j]);
			}
		}
		
		if(result[N-1] == Integer.MAX_VALUE) System.out.println(-1);
		else System.out.println((int)(result[N-1]*1000));
	}
}
