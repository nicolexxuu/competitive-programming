
// Cow Checklist

import java.util.*;
import java.io.*;

public class checklist {
	static int[][] hs;
	static int[][] gs;

	public static void main(String[] args) throws IOException {
		String file = "";
		// BufferedReader br = new BufferedReader(new FileReader(new File(file +
		// ".in")));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int H = Integer.parseInt(st.nextToken());
		int G = Integer.parseInt(st.nextToken());

		hs = new int[H + 1][2];
		gs = new int[G + 1][2];

		for (int i = 1; i <= H; i++) {
			st = new StringTokenizer(br.readLine());
			hs[i][0] = Integer.parseInt(st.nextToken());
			hs[i][1] = Integer.parseInt(st.nextToken());
		}

		for (int i = 1; i <= G; i++) {
			st = new StringTokenizer(br.readLine());
			gs[i][0] = Integer.parseInt(st.nextToken());
			gs[i][1] = Integer.parseInt(st.nextToken());
		}

		br.close();

		int[][] dpH = new int[H + 1][G + 1];
		int[][] dpG = new int[H + 1][G + 1];
		
		for(int i = 0; i <= H; i++) {
			Arrays.fill(dpH[i], 2000000000);
			Arrays.fill(dpG[i], 2000000000);
		}
		
		dpH[1][0] = 0;
		
		for (int h = 1; h <= H; h++) {
			for (int g = 0; g <= G; g++) {
				if(h > 1) {
					dpH[h][g] = dpH[h - 1][g] + dist(hs[h][0], hs[h][1], hs[h - 1][0], hs[h - 1][1]);
					if(g > 0) dpH[h][g] = Math.min(dpH[h][g], dpG[h - 1][g] + dist(hs[h][0], hs[h][1], gs[g][0], gs[g][1]));
				}
				
				if(g > 0) {
					dpG[h][g] = dpH[h][g - 1] + dist(hs[h][0], hs[h][1], gs[g][0], gs[g][1]);
					if (g > 1) dpG[h][g] = Math.min(dpG[h][g], dpG[h][g - 1] + dist(gs[g][0], gs[g][1], gs[g - 1][0], gs[g - 1][1]));
				}
			}
		}


		// PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter(new
		// File(file + ".out"))));
		System.out.println(dpH[H][G]);
		// out.println(result);
		// out.close();
	}

	public static int dist(int x1, int y1, int x2, int y2) {
		return (x1 - x2) * (x1 - x2) + (y1 - y2) * (y1 - y2);
	}
}
