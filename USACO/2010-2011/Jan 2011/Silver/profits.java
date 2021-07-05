// Profits

import java.util.*;
import java.io.*;

public class profits {
	public static void main(String[] args) throws IOException {
		String file = "";
		// BufferedReader br = new BufferedReader(new FileReader(new File(file + ".in")));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken());
		int[] profit = new int[N];
		for(int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			profit[i] = Integer.parseInt(st.nextToken());
		}
		br.close();

		int result = profit[0];
		int curr = 0;
		for(int i: profit) {
			curr += i;
			result = Math.max(result, curr);
			curr = Math.max(0, curr);
		}

		//PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter(new File(file + ".out"))));
		System.out.println(result);
		//out.println(result);
		//out.close();
	}
}


