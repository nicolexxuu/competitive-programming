// Forgotten Password

import java.util.*;
import java.io.*;

public class forgot {
	public static void main(String[] args) throws IOException {
		String file = "";
		// BufferedReader br = new BufferedReader(new FileReader(new File(file + ".in")));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int L = Integer.parseInt(st.nextToken());
		int NW = Integer.parseInt(st.nextToken());
		st = new StringTokenizer(br.readLine());
		char[] pass = st.nextToken().toCharArray();
		String[] dict = new String[NW];
		for(int i = 0; i < NW; i++) {
			st = new StringTokenizer(br.readLine());
			dict[i] = st.nextToken();
		}
		br.close();

		String[] dp = new String[L];
		for(int i = 0; i < L; i++) {
			if(i > 0 && dp[i-1] == null) continue;
			String curr = "";
			if(i > 0) curr = dp[i-1];
			for(String s: dict) {
				if(i + s.length() - 1 < L && fits(i, s, pass)) {
					String temp = curr + s;
					if(dp[i + s.length() - 1] == null || temp.compareTo(dp[i+s.length()-1]) < 0) {
						dp[i+s.length() - 1] = temp;
					}
				}
			}
		}
		//PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter(new File(file + ".out"))));
		System.out.println(dp[L-1]);
		//out.println(result);
		//out.close();
	}
	
	public static boolean fits(int i, String s, char[] pass) {
		for(int j = 0; j < s.length(); j++) {
			if(pass[j+i] != '?' && pass[j+i] != s.charAt(j)) return false;
		}
		return true;
	}
}
