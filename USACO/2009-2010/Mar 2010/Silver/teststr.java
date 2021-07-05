// Test Taking

import java.util.*;
import java.io.*;

public class teststr {
	public static void main(String[] args) throws IOException {
		String file = "";
		// BufferedReader br = new BufferedReader(new FileReader(new File(file + ".in")));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken());
		int K = Integer.parseInt(st.nextToken());
		
		int[] A = new int[K];
		
		for(int i = 0; i < K; i++) {
			st = new StringTokenizer(br.readLine());
			A[i] = Integer.parseInt(st.nextToken());
		}
		br.close();
		
		Arrays.sort(A);
		int result = Math.max(A[0], N - A[K-1]);
		for(int i = 0; i < K-1; i++) {
			result = Math.max(result, (A[i+1] - A[i]) / 2);
		}
		//PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter(new File(file + ".out"))));
		System.out.println(result);
		//out.println(result);
		//out.close();
	}
}

