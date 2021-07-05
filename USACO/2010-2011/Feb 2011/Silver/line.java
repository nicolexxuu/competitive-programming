// Cow Line

import java.util.*;
import java.io.*;

public class line {
	static long[] fact = new long[21];
	public static void main(String[] args) throws IOException {
		String file = "";
		// BufferedReader br = new BufferedReader(new FileReader(new File(file + ".in")));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken());
		int K = Integer.parseInt(st.nextToken());
		
		fact[0] = 1; fact[1] = 1;
		for(int i = 2; i <= 20; i++) fact[i] = fact[i-1] * i;
		
		for(int i = 0; i < K; i++) {
			st = new StringTokenizer(br.readLine());
			String s = st.nextToken();
			st = new StringTokenizer(br.readLine());
			if(s.equals("P")) {
				int A = Integer.parseInt(st.nextToken());
				int[] res = findLine(A, N);
				for(int j: res) System.out.print(j + " ");
				System.out.println();
			} else {
				
			}
		}
		br.close();
	}
	
	public static int[] findLine(int A, int N) {
		boolean[] used = new boolean[N];
		int[] ret = new int[N];
		int q = A - 1;
		for(int i = 0; i < N; i++) {
			long p = q / fact[N-i-1];
			q %= p;
			System.out.println("p: " + p);
			System.out.println("fact: " + fact[N-i-1]);
			
			int ind = 0, found = 0;
			while(found < p+1) {
				System.out.println(found + " ind: " + ind);
				if(!used[ind]) found++;
				ind++;
			}
			ret[i] = ind;
			used[ind-1] = true;
				
			p = q;
		}
		return ret;
	}
}
