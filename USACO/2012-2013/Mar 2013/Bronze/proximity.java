// Breed Proximity

import java.util.*;
import java.io.*;

public class proximity {
	public static void main(String[] args) throws IOException {
		String file = "";
		// BufferedReader br = new BufferedReader(new FileReader(new File(file + ".in")));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken());
		int K = Integer.parseInt(st.nextToken());
		int[] lastSeen = new int[1000001];
		int res = 0;
		for(int i = 1; i <= N; i++) {
			st = new StringTokenizer(br.readLine());
			int id = Integer.parseInt(st.nextToken());
			if(lastSeen[id] != 0 && i - lastSeen[id] <= K) res = Math.max(res, id);
			lastSeen[id] = i;
		}
		br.close();

		//PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter(new File(file + ".out"))));
		System.out.println(res);
		//out.println(result);
		//out.close();
	}
}
