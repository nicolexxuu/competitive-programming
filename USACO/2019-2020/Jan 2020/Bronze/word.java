// Word Processor

import java.util.*;
import java.io.*;

public class word {
	public static void main(String[] args) throws IOException {
		String file = "word";
		BufferedReader br = new BufferedReader(new FileReader(new File(file + ".in")));
//		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken());
		int K = Integer.parseInt(st.nextToken());
		String[] words = new String[N];
		st = new StringTokenizer(br.readLine());
		for(int i = 0; i < N; i++) words[i] = st.nextToken();
		br.close();
		
		PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter(new File(file + ".out"))));
		int curr = 0;
		for(int i = 0; i < N; i++) {
			System.out.print(words[i]);
			out.print(words[i]);
			curr += words[i].length();
			if(i < N - 1 && curr + words[i+1].length() <= K) {
				System.out.print(" ");
				out.print(" ");
			} else {
				curr = 0;
				System.out.println();
				out.println();
			}
		}
		out.close();
	}
}