/*
ID: diudiux1
LANG: JAVA
TASK: crypt1
 */

import java.util.*;
import java.io.*;

public class crypt1 {
	static int solutions = 0;
	static HashSet<String> set;
	static String[] digits;
	public static void main(String[] args) throws IOException {
		String file = "crypt1";
		BufferedReader br = new BufferedReader(new FileReader(new File(file + ".in")));
//		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken());
		
		st = new StringTokenizer(br.readLine());
		set = new HashSet<>();
		for(int i = 0; i < N; i++) set.add(st.nextToken());
		
		int idx = 0;
		digits = new String[set.size()];
		for(String s : set) digits[idx++] = s;
		br.close();
		
		int[] crypt = new int[5];
		permute(0, crypt);
		PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter(new File(file + ".out"))));
		System.out.println(solutions);
		out.println(solutions);
		out.close();
	}
	
	public static void permute(int i, int[] crypt) {
		if(i == 5) {
			int p1 = Integer.parseInt(digits[crypt[0]] + digits[crypt[1]] + digits[crypt[2]]) * Integer.parseInt(digits[crypt[4]]),
				p2 = Integer.parseInt(digits[crypt[0]] + digits[crypt[1]] + digits[crypt[2]]) * Integer.parseInt(digits[crypt[3]]);
			int sum = p1 + 10 * p2;
			if(p1 >= 1000 || p2 >= 1000 || sum >= 10000) return;
			if(!valid(Integer.toString(p1)) || !valid(Integer.toString(p2)) || !valid(Integer.toString(sum))) return;
			solutions++;
			return;
		}
		
		for(int j = 0; j < digits.length; j++) {
			crypt[i] = j;
			permute(i+1, crypt);
		}
	}
	
	public static boolean valid(String s) {
		for(int i = 0; i < s.length(); i++) 
			if(!set.contains("" + s.charAt(i))) return false;
		return true;
	}
}
