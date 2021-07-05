/*
LANG: JAVA
TASK: gift1              
*/

// This template code suggested by KT BYTE Computer Science Academy
//   for use in reading and writing files for USACO problems.

// https://content.ktbyte.com/problem.java
// https://train.usaco.org/usacoprob2?a=rFLmmBde2zN&S=gift1

import java.util.*;
import java.io.*;

public class gift1 {
	public static void main(String[] args) throws IOException {
		String file = "gift1";
		BufferedReader br = new BufferedReader(new FileReader(new File(file + ".in")));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken());
		String[] names = new String[N];
		
		for(int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			names[i] = st.nextToken();
		}
		
		int[] amt = new int[N];
		
		for(int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			String giving = st.nextToken();
			st = new StringTokenizer(br.readLine());
			int money = Integer.parseInt(st.nextToken());
			int G = Integer.parseInt(st.nextToken());
			if(G==0) {
				amt[findFriend(names, giving)] += money;
				continue;
			}
			amt[findFriend(names, giving)] += money%G - money;
			int dist = money/G;
			for(int j = 0; j < G; j++) {
				st = new StringTokenizer(br.readLine());
				String receive = st.nextToken();
				amt[findFriend(names, receive)] += dist;
			}
		}
		br.close();
		
		PrintWriter out = new PrintWriter(new File(file + ".out"));
		
		for(int i = 0; i < N; i++) {
			//System.out.println(names[i] + " " + (amt[i]));
			out.println(names[i] + " " + (amt[i]));
		}
		out.close();
	}
	
	public static int findFriend(String[] friends, String friend) {
		for(int i = 0; i < friends.length; i++) {
			if(friends[i].equals(friend)) return i;
		}
		return -1;
	}
}
