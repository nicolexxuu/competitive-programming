/*
LANG: JAVA
TASK: milk2             
*/

// This template code suggested by KT BYTE Computer Science Academy
//   for use in reading and writing files for USACO problems.

// https://content.ktbyte.com/problem.java
// https://train.usaco.org/usacoprob2?a=rFLmmBde2zN&S=milk2

import java.util.*;
import java.io.*;

public class milk2 {
	public static void main(String[] args) throws IOException {
		String file = "milk2";
		BufferedReader br = new BufferedReader(new FileReader(new File(file + ".in")));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken());
		int[] milk = new int[1000000];
		int minStart = 1000000;
		int maxEnd = 0;
		for(int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			int start = Integer.parseInt(st.nextToken())-1;
			int end = Integer.parseInt(st.nextToken());
			maxEnd = Math.max(maxEnd, end);
			minStart = Math.min(minStart, start);
			milk[start+1]++;
			milk[end]--;
		}
		br.close();

		int maxNC = 0;
		int maxC = 0;
		int currNC = 0;
		int currC = 0;
		int curr = 0;
		
		for(int time = minStart+1; time < maxEnd; time++) {
			//System.out.println("time " + time + ": " + milk[time]);
			curr += milk[time];
			if(curr > 0) {
				currNC = 0;
				currC++;
			} else {
				currC = 0;
				currNC++;
			}
			maxNC = Math.max(maxNC, currNC);
			maxC = Math.max(maxC, currC);
		}
		PrintWriter out = new PrintWriter(new File(file + ".out"));
		System.out.println(maxC + " " + maxNC);
		out.println(maxC + " " + maxNC);
		out.close();
	}
}

