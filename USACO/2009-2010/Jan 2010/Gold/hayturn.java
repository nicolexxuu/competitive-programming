// Taking Turns

import java.util.*;
import java.io.*;

public class hayturn {
	public static void main(String[] args) throws IOException {
		String file = "";
		// BufferedReader br = new BufferedReader(new FileReader(new File(file + ".in")));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken());
		int[] weights = new int[N];
		
		for(int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			weights[i] = Integer.parseInt(st.nextToken());
		}
		
		br.close();
		
		long maxF = weights[N-1], maxS = 0;
		long prevF = weights[N-1], prevS = 0;
		
		for(int i = N-2; i >= 0; i--) {
			if(weights[i] + prevS >= prevF) {
				long temp = prevF;
				prevF = weights[i] + prevS;
				prevS = temp;
			}
			
			if(prevF >= maxF) {
				maxF = prevF;
				maxS = prevS;
			}
		}
		
		//PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter(new File(file + ".out"))));
		System.out.println(maxF + " " + maxS);
		//out.println();
		//out.close();
	}
}
