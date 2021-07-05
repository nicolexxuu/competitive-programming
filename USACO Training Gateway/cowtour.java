// Cow Tours

import java.util.*;
import java.io.*;
import java.text.DecimalFormat;

public class cowtour {
	public static void main(String[] args) throws IOException {
		String file = "";
		// BufferedReader br = new BufferedReader(new FileReader(new File(file + ".in")));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken());
		int[][] coords = new int[N][2];
		for(int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			coords[i][0] = Integer.parseInt(st.nextToken());
			coords[i][1] = Integer.parseInt(st.nextToken());
		}
		
		double [][] dist = new double[N][N];
		for(double[] i: dist) Arrays.fill(i, 1000000000);
		for(int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			char[] line = st.nextToken().toCharArray();
			for(int j = 0; j < N; j++) {
				if(line[j] == '1') dist[i][j] = dist(coords[i][0], coords[i][1], coords[j][0], coords[j][1]);
				if(i == j) dist[i][j] = 0;
			}
		}
		br.close();
		
		for(int k = 0; k < N; k++) {
			for(int i = 0; i < N; i++) {
				for(int j = 0; j < N; j++) {
					dist[i][j] = Math.min(dist[i][j],  dist[i][k] + dist[k][j]);
				}
			}
		}
		
		
		for(int i = 0; i < N; i++) {
			for(int j = 0; j < N; j++) {
				System.out.print(dist[i][j] + " " );
			}
			System.out.println();
		}
		double[] maxDist = new double[N];
		double max = 0;
		for(int i = 0; i < N; i++) {
			for(int j = 0; j < N; j++) {
				if(dist[i][j] == 1000000000) continue;
				maxDist[i] = Math.max(maxDist[i], dist[i][j]);
				max = Math.max(max, maxDist[i]);
			}
		}

		double min = Integer.MAX_VALUE;
		for(int i = 0; i < N; i++) {
			for(int j = i+1; j < N; j++) {
				if(dist[i][j] != 1000000000) continue;
				min = Math.min(min, maxDist[i] + maxDist[j] + dist(coords[i][0], coords[i][1], coords[j][0], coords[j][1]));
			}
		}
		
		min = Math.max(max, min);
		DecimalFormat df = new DecimalFormat("#.000000");
		System.out.println(df.format(min));
	}
	
	public static double dist(int x1, int y1, int x2, int y2) {
		return Math.sqrt((x1-x2) * (x1-x2) + (y1-y2) * (y1-y2));
	}
}
