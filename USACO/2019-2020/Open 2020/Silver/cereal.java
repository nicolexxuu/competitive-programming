// This template code suggested by KT BYTE Computer Science Academy
//   for use in reading and writing files for USACO problems.

// https://content.ktbyte.com/problem.java

// http://www.usaco.org/index.php?page=viewproblem2&cpid=1039
// "Cereal", 2020 US Open Silver Division

import java.util.*;
import java.io.*;

public class cereal {
	public static void main(String[] args) throws IOException {
		String file = "cereal";
		BufferedReader br = new BufferedReader(new FileReader(new File(file + ".in")));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());
		
		int[][] favs = new int[N][2];
		for(int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			favs[i][0] = Integer.parseInt(st.nextToken());
			favs[i][1] = Integer.parseInt(st.nextToken());
		}
		br.close();
		
		int[] cereal = new int[N+1]; // which cow has this cereal
		Arrays.fill(cereal, -1);
		
		int[] result = new int[N];
		int count = 0;
		for(int add = N-1; add >= 0; add--) {
			
			int curr = add;
			int currC = favs[add][0];
			
			while(true) {
				if(cereal[currC] == -1) { //no one has taken this yet
					cereal[currC] = curr;
					count++;
					break;
				} else if (cereal[currC] < curr) {
					break;
				} else { //cereal is stolen
					int temp = cereal[currC];
					cereal[currC] = curr;
					if(currC == favs[temp][1]) break; // no choices left
					currC = favs[temp][1];
					curr = temp;
				}
			}
			result[add] = count;
		}
		
		PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter(new File(file + ".out"))));
//		Scanner in = new Scanner(new File(file + ".out"));
		for(int i = 0; i < N; i++) {
			out.println(result[i]);
		}
		out.close();
	}
}
