// Soda Machine

import java.util.*;
import java.io.*;

public class soda {
	public static void main(String[] args) throws IOException {
		String file = "";
		// BufferedReader br = new BufferedReader(new FileReader(new File(file + ".in")));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken());
		int[] S = new int[N];
		int[] E = new int[N];
		
		for(int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			S[i] = Integer.parseInt(st.nextToken());
			E[i] = Integer.parseInt(st.nextToken());
		}
		Arrays.sort(S);
		Arrays.sort(E);
		br.close();
		
		int start = 0;
		int end = 0;
		int result = 0;
		int curr = 0;
		
		while(start < N || end < N) {
			if(start < N && S[start] <= E[end]) {
				curr++;
				start++;
				
			} else {
				result = Math.max(result, curr);
				curr--;
				end++;
			}

		}
		
		//PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter(new File(file + ".out"))));
		System.out.println(result);
		//out.println(result);
		//out.close();
	}
}
