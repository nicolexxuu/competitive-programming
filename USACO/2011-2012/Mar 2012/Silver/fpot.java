// Flowerpot

import java.util.*;
import java.io.*;

public class fpot {
	public static void main(String[] args) throws IOException {
		String file = "";
		// BufferedReader br = new BufferedReader(new FileReader(new File(file + ".in")));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken());
		int D = Integer.parseInt(st.nextToken());
		
		int[][] drop = new int[N][2];
		
		for(int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			drop[i][0] = Integer.parseInt(st.nextToken());
			drop[i][1] = Integer.parseInt(st.nextToken());
		}
		
		Arrays.sort(drop, (a, b) -> a[0] - b[0]);
		br.close();

		int result = Integer.MAX_VALUE;
		int l = 0, r = 0;
		TreeMap<Integer, Integer> inRange = new TreeMap<>();
		inRange.put(drop[0][1], 1);
		
		while(true) {
			int max = inRange.lastKey(), min = inRange.firstKey();
			if(max - min < D) {
				if(++r == N) break;
				if(inRange.containsKey(drop[r][1])) inRange.replace(drop[r][1], inRange.get(drop[r][1]) + 1);
				else inRange.put(drop[r][1], 1);
			} else {
				if(drop[r][0] - drop[l][0] <= D) result = Math.min(result, drop[r][0] - drop[l][0]);
				if(inRange.get(drop[l][1]) > 1) inRange.replace(drop[l][1], inRange.get(drop[l][1]) - 1);
				else inRange.remove(drop[l][1]);
				if(++l == N) break;
			}
		}
		
		//PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter(new File(file + ".out"))));
		if(result == Integer.MAX_VALUE) System.out.println(-1);
		else System.out.println(result);
		//out.println(result);
		//out.close();
	}
}
