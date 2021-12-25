/*
ID: diudiux1
LANG: JAVA
TASK: dualpal
 */

import java.util.*;
import java.io.*;

public class dualpal {
	public static void main(String[] args) throws IOException {
		String file = "dualpal";
		BufferedReader br = new BufferedReader(new FileReader(new File(file + ".in")));
//		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken());
		int S =  Integer.parseInt(st.nextToken());
		br.close();
		
		PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter(new File(file + ".out"))));

		for(int i = S+1; N > 0; i++) {
			int count = 0;
			for(int b = 2; b <= 10; b++) {
				if(isPal(Integer.toString(Integer.parseInt(Integer.toString(i), 10), b)))
					count++;
			}
			
			if(count >= 2) {
//				System.out.println(i);
				out.println(i);
				N--;
			}
		}
		
		out.close();
	}
	
	public static boolean isPal(String str) {
		for(int i = 0; i < str.length()/2; i++) 
			if(str.charAt(i) != str.charAt(str.length()-1-i)) return false;
		return true;
	}
}