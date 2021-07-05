// Tallest Cow

import java.util.*;
import java.io.*;

public class tallest {
	public static void main(String[] args) throws IOException {
		String file = "";
		// BufferedReader br = new BufferedReader(new FileReader(new File(file + ".in")));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken()); //num of cows
		int I = Integer.parseInt(st.nextToken())-1; //index of tallest
		int H = Integer.parseInt(st.nextToken()); //height of tallest
		int R = Integer.parseInt(st.nextToken()); //relationships
		
		HashSet<String> seen = new HashSet<>();
		int[] intervals = new int[N];
		
		for(int i = 0; i < R; i++) {
			st = new StringTokenizer(br.readLine());
			int a = Integer.parseInt(st.nextToken())-1;
			int b = Integer.parseInt(st.nextToken())-1;
			int A = Math.min(a, b);
			int B = Math.max(a, b);
			if(!seen.contains(""+A+B)) {
				intervals[A]++;
				intervals[B-1]--;
				seen.add(""+A+B);
			}
		}
		br.close();

		int[] result = new int[N];
		int curr = 0;
		for(int i = 0; i < N; i++) {
			result[i] = H-curr;
			curr+=intervals[i];
		}
		//PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter(new File(file + ".out"))));
		for(int i: result) System.out.println(i);
		//out.println(result);
		//out.close();
	}
}
