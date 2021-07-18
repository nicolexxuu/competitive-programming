// Sleeping Cow Herding

import java.util.*;
import java.io.*;

public class herding {
	public static void main(String[] args) throws IOException {
		String file = "herding";
		BufferedReader br = new BufferedReader(new FileReader(new File(file + ".in")));
//		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int[] cows = new int[3];
		for(int i = 0; i < 3; i++) cows[i] = Integer.parseInt(st.nextToken());
		br.close();
		
		Arrays.sort(cows);
		int max = Math.max(cows[2]-cows[1], cows[1]-cows[0])-1;
		int min = Math.min(cows[2]-cows[1], cows[1]-cows[0])-1;
		if(min == 0) min = max; // other gap - may be bigger
		if(min > 1) min = 2;
		PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter(new File(file + ".out"))));
		System.out.println(min);
		System.out.println(max);
		out.println(min);
		out.println(max);
		out.close();
	}
}