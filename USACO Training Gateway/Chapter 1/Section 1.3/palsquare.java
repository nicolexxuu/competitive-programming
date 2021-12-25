/*
ID: diudiux1
LANG: JAVA
TASK: palsquare
*/

import java.util.*;
import java.io.*;

public class palsquare {
	public static void main(String[] args) throws IOException {
		String file = "palsquare";
		BufferedReader br = new BufferedReader(new FileReader(new File(file + ".in")));
		//BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int B = Integer.parseInt(st.nextToken());
		br.close();
		
		PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter(new File(file + ".out"))));
		for(int N = 1; N <= 300; N++) {
			String converted = Integer.toString(Integer.parseInt(Integer.toString(N*N), 10), B);
			boolean palindrome = true;
			for(int i = 0; i < converted.length()/2; i++) {
				if(converted.charAt(i) != converted.charAt(converted.length()-1-i)) palindrome = false;
			}
			
			if(palindrome) out.println(Integer.toString(Integer.parseInt(Integer.toString(N), 10), B).toUpperCase() + " " + converted.toUpperCase());
		}
		out.close();
	}
}
