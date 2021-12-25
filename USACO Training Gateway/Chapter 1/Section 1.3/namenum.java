/*
LANG: JAVA
TASK: namenum              
*/

// This template code suggested by KT BYTE Computer Science Academy
//   for use in reading and writing files for USACO problems.

// https://content.ktbyte.com/problem.java
// https://train.usaco.org/usacoprob2?a=rFLmmBde2zN&S=namenum

import java.util.*;
import java.io.*;

public class namenum {
	static String[][] phone = new String[10][3];
	static PrintWriter out;
	static HashSet<String> dict;
	static boolean output;
	public static void main(String[] args) throws IOException {
		String file = "namenum";
		BufferedReader br = new BufferedReader(new FileReader(new File(file + ".in")));
		StringTokenizer st = new StringTokenizer(br.readLine());
		String N = st.nextToken();
		br.close();
		
		dict = new HashSet<>();
		Scanner in = new Scanner(new File("dict.txt"));
		while(in.hasNext()) dict.add(in.next());
		
		phone[2] = new String[]{"A", "B", "C"};
		phone[3] = new String[]{"D", "E", "F"};
		phone[4] = new String[]{"G", "H", "I"};
		phone[5] = new String[]{"J", "K", "L"};
		phone[6] = new String[]{"M", "N", "O"};
		phone[7] = new String[]{"P", "R", "S"};
		phone[8] = new String[]{"T", "U", "V"};
		phone[9] = new String[]{"W", "X", "Y"};
		
		out = new PrintWriter(new File(file + ".out"));
		output = false;
		recur(N, "", 0);
		if(!output) out.println("NONE");
		out.close();
	}
	
	public static void recur(String N, String curr, int index) {
		if(index >= N.length()) {
			if(dict.contains(curr)) {
				out.println(curr);
				System.out.println(curr);
				output = true;
			}
		} else {
			String[] arr = phone[Integer.parseInt(N.substring(index, index + 1))];
			for(int i = 0; i < 3; i++) {
				recur(N, curr + arr[i], index + 1);
			}
		}
		
	}
}
