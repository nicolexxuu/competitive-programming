// Longest Prefix

import java.util.*;
import java.io.*;

public class prefix {
	public static void main(String[] args) throws IOException {
		String file = "";
		Scanner in = new Scanner(System.in);
		ArrayList<String> prims = new ArrayList<>();
		while(true) {
			String s = in.next();
			if(s.equals(".")) break;
			else prims.add(s);
		}
		
		String S = " ";
		
		while(in.hasNext()) {
			S += in.next();
		}
		int N = S.length() - 1;
		in.close();
		
		boolean[] dp = new boolean[N+1];
		dp[0] = true;
		
		int result=  0;
		
		for(int i = 1; i <= N; i++) {
//			System.out.println("i: " + i);
			for(String j: prims) {
				int start = i - j.length() + 1;
//				System.out.println(j + " start: " + start);
//				System.out.println("sub: " + S.substring(start, i+1));
				if(start >= 0 && j.equals(S.substring(start, i+1))) {
					if(dp[start - 1]) {
//						System.out.println("hi2");
						dp[i] = true;
						result = i;
						break;
					}
				}
			}
		}
		
//		for(int i = 0; i <= N; i++) {
//			System.out.print(dp[i] + " ");
//		}
//		System.out.println();
		
		//PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter(new File(file + ".out"))));
		System.out.println(result);
		//out.println(result);
		//out.close();
	}
}