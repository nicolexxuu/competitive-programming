// Milk Pails

import java.util.*;
import java.io.*;

public class pails {
	public static void main(String[] args) throws IOException {
		String file = "pails";
		BufferedReader br = new BufferedReader(new FileReader(new File(file + ".in")));
//		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int X = Integer.parseInt(st.nextToken());
		int Y = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());
		br.close();
		
		int max = 0;
		for(int i = 0; i*X <= M; i++) {
			for(int j = 0; i*X + j*Y <= M; j++) {
				max = Math.max(max, i*X + j*Y);
			}
		}
		
		PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter(new File(file + ".out"))));
		System.out.println(max);
		out.println(max);
		out.close();
	}
}