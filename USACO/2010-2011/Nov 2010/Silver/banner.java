// Banner

import java.util.*;
import java.io.*;

public class banner {
	public static void main(String[] args) throws IOException {
		String file = "";
		// BufferedReader br = new BufferedReader(new FileReader(new File(file + ".in")));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int W = Integer.parseInt(st.nextToken());
		int H = Integer.parseInt(st.nextToken());
		int L1 = Integer.parseInt(st.nextToken());
		int L2 = Integer.parseInt(st.nextToken());
		br.close();

		long result = 0;
		if(L1 == 1) result = (W * (H+1)) + (H * (W+1));
		for(int w = 1; w <= L2 && w <= W; w++) {
			for(int h = 1; h <= L2 && h <= H; h++) {
//				System.out.println(w + " " + h);
				if(GCD(Math.max(w, h), Math.min(w,  h)) != 1) continue;
				if(Math.sqrt(w*w+h*h) < L1 || Math.sqrt(w*w+h*h) > L2) continue;
//				System.out.println("good");
				result += (W-w+1) * (H-h+1) * 2;
			}
		}
		//PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter(new File(file + ".out"))));
		System.out.println(result);
		//out.println(result);
		//out.close();
	}
	
	public static int GCD (int a, int b) {
		if(b == 0) return a;
		return GCD(b, a%b);
	}
}

