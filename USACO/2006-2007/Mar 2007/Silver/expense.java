// Monthly Expense

import java.util.*;
import java.io.*;

public class expense {
	static int[] dollars;
	public static void main(String[] args) throws IOException {
		String file = "";
		// BufferedReader br = new BufferedReader(new FileReader(new File(file + ".in")));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());
		int l = 1, h = 0;
		dollars = new int[N];
		
		for(int i = 0; i < N; i++) { 
			st = new StringTokenizer(br.readLine());
			dollars[i] = Integer.parseInt(st.nextToken());
			h += dollars[i];
		}
		br.close();

		while(l < h) {
			int m = (l+h)/2;
			//System.out.println(m);
			if(possible(M, m)) h = m;
			else l = m + 1;
		}
		//PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter(new File(file + ".out"))));
		System.out.println(l);
		//out.println(result);
		//out.close();
	}
	
	public static boolean possible(int M, int lim) {
		int curr = 0;
		int count = 1;
		
		for(int d: dollars) {
			if(d > lim) return false;
			if(curr + d > lim) {
				curr = d;
				count++;
			} else {
				curr += d;
			}
		}

		return count <= M;
	}
}

