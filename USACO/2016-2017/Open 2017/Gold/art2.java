// Modern Art 2

import java.util.*;
import java.io.*;

public class art2 {
	public static void main(String[] args) throws IOException {
		String file = "art2";
		BufferedReader br = new BufferedReader(new FileReader(new File(file + ".in")));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken());
		int[] painting = new int[N+2];
		int[][] color = new int[N+2][2];
		color[0][1] = N+1;
		
		for(int i = 1; i <= N; i++) {
			st = new StringTokenizer(br.readLine());
			int c = Integer.parseInt(st.nextToken());
			painting[i] = c;
			if(color[c][0] == 0 && c != 0) color[c][0] = i;
			color[c][1] = Math.max(color[c][1], i);
		}
		

		br.close();

		int result = 0;
		
		int[] ps = new int[N+1];
		ps[0] = 1;
		for(int i = 1; i <= N; i++) {
			ps[i] = ps[i-1];
			
			int c = painting[i];
			if(i == color[c][0]) ps[i]++;
			result = Math.max(result, ps[i]);
			
			if(i == color[c][1]) {
				if(ps[i] != ps[color[c][0]]) {
					result = 0;
					break;
				}
				ps[i]--;
			}
		}
		PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter(new File(file + ".out"))));
		out.println(result-1);
		out.close();
	}
}
