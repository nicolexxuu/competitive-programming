// Empty Stalls

import java.util.*;
import java.io.*;

public class empty {
	public static void main(String[] args) throws IOException {
		String file = "";
		// BufferedReader br = new BufferedReader(new FileReader(new File(file + ".in")));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken());
		int K = Integer.parseInt(st.nextToken());
		
		int[] cows = new int[N];
		for(int i = 0; i < K; i++) {
			st = new StringTokenizer(br.readLine());
			int X = Integer.parseInt(st.nextToken());
			int Y = Integer.parseInt(st.nextToken());
			int A = Integer.parseInt(st.nextToken());
			int B = Integer.parseInt(st.nextToken());
			
			for(int j = 1; j <= Y; j++) cows[(int)(((long)A * j + B) % N)] += X;
		}
		br.close();

		int res = -1, curr = 0;
		
		for(int s = 0; s < 2; s++) {
			for(int i = 0; i < N; i++) {
				if(cows[i] == 0) {
					if(curr == 0) {
						if(s == 1 && res == -1) res = i;
					} else {
						cows[i] = 1;
						curr--;
					}
				} else {
					curr += cows[i] - 1;
					cows[i] = 1;
				}
			}
		}
		//PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter(new File(file + ".out"))));
		System.out.println(res);
		//out.println();
		//out.close();
	}
}
