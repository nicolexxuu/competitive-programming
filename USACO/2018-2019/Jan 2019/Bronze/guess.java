// Guess the Animal

import java.util.*;
import java.io.*;

public class guess {
	public static void main(String[] args) throws IOException {
		String file = "guess";
		BufferedReader br = new BufferedReader(new FileReader(new File(file + ".in")));
//		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken());
		
		String[][] traits = new String[N][];
		for(int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			st.nextToken();
			int K = Integer.parseInt(st.nextToken());
			traits[i] = new String[K];
			
			for(int j = 0; j < K; j++) {
				traits[i][j] = st.nextToken();
			}
		}
		br.close();
		
		int maxShared = 0;
		for(int i = 0; i < N; i++) {
			for(int j = i+1; j < N; j++) {
				int shared = 0;
				for(String t1 : traits[i])
					for(String t2 : traits[j])
						if(t1.equals(t2)) shared++;
				maxShared = Math.max(maxShared, shared);
			}
		}
		
		PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter(new File(file + ".out"))));
		System.out.println(maxShared+1);
		out.println(maxShared+1);
		out.close();
	}
}