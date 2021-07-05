// This template code suggested by KT BYTE Computer Science Academy
//   for use in reading and writing files for USACO problems.

// https://content.ktbyte.com/problem.java

// http://usaco.org/index.php?page=viewproblem2&cpid=397
// "Auto-complete", 2014 February Silver Division

import java.util.*;
import java.io.*;

public class auto {
	public static void main(String[] args) throws IOException {
		String file = "auto";
		BufferedReader br = new BufferedReader(new FileReader(new File(file + ".in")));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int W = Integer.parseInt(st.nextToken());
		int N = Integer.parseInt(st.nextToken());
		
		Word[] dict = new Word[W];
		for(int i = 0; i < W; i++) {
			st = new StringTokenizer(br.readLine());
			dict[i] = new Word(st.nextToken(), i + 1);
		}
		Arrays.sort(dict);

		PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter(new File(file + ".out"))));		//System.out.println(lowerBound(dict, "ac"));
		for(int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			int K = Integer.parseInt(st.nextToken());
			String partial = st.nextToken();
			int lower = lowerBound(dict, partial);
			if(lower != -1 && lower + K - 1 < W && compare(dict[lower+K-1], partial) == 0) {
				out.println(dict[lower + K - 1].pos);
			} else {
				out.println(-1);
			}
		}
		out.close();
		br.close();	
	}
	
	public static int lowerBound(Word[] dict, String partial) {

		if (compare(dict[0], partial) < 0) return -1;
		if (compare(dict[dict.length - 1], partial) > 0) return -1;
		int low = 0;
		int high = dict.length - 1;
		while (low < high) {
			int mid = (low + high) / 2;
			if (compare(dict[mid], partial) <= 0)  {
				high = mid;
			} else {
				low = mid + 1;
			}
		}

		return low;
	}

	public static int compare(Word dictionary, String partial) {
		int i = 0;
		String dict = dictionary.text;
		while(i < partial.length() && i < dict.length()) {
			if((int)partial.charAt(i) < (int)dict.charAt(i)) return -1;
			else if ((int)partial.charAt(i) > (int)dict.charAt(i)) return 1;
			i++;
		}
		if(dict.length() < partial.length()) return 1;
		else return 0;
	}
	
	public static class Word implements Comparable<Word> {
		String text;
		int pos;
		
		Word(String text, int pos){
			this.text = text;
			this.pos = pos;
		}
		
		public int compareTo(Word other) {
			return this.text.compareTo(other.text);
		}
	}
}
