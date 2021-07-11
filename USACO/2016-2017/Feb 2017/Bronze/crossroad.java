// Why Did the Cow Cross the Road

import java.util.*;
import java.io.*;

public class crossroad {
	public static void main(String[] args) throws IOException {
		String file = "crossroad";
		BufferedReader br = new BufferedReader(new FileReader(new File(file + ".in")));
//		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken());
		int[] lastSeen = new int[11];
		Arrays.fill(lastSeen, -1);
		
		int crossings = 0;
		for(int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			int id = Integer.parseInt(st.nextToken());
			int side = Integer.parseInt(st.nextToken());
			if(lastSeen[id] != -1 && lastSeen[id] != side) crossings++;
			lastSeen[id] = side;
		}
		
		br.close();

		PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter(new File(file + ".out"))));
		System.out.println(crossings);
		out.println(crossings);
		out.close();
	}
}