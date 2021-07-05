// Word Power

import java.util.*;
import java.io.*;

public class wordpow {
	public static void main(String[] args) throws IOException {
		String file = "";
		// BufferedReader br = new BufferedReader(new FileReader(new File(file + ".in")));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());
		
		String[] cows = new String[N];
		String[] goods = new String[M];
		
		for(int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			cows[i] = st.nextToken();
		}
		for(int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine());
			goods[i] = st.nextToken();
		}
		br.close();

		for(int i = 0; i < N; i++) {
			String cow = cows[i].toLowerCase();
			int count = 0;
			for(int j = 0; j < M; j++) {
				String good = goods[j].toLowerCase();
				int k = 0;
				for(int m = 0; m < cow.length() && k < good.length(); m++) {
					if(cow.charAt(m) == good.charAt(k)) k++;
				}
				
				if(k == good.length()) count++;
			}
			System.out.println(count);
		}
		
		//PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter(new File(file + ".out"))));
		//out.println(result);
		//out.close();
	}
}



