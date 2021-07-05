// Best Parenthesis
import java.util.*;
import java.io.*;

public class paren {
	
	static long MOD = 12345678910L;
	public static void main(String[] args) throws IOException {
		String file = "";
		// BufferedReader br = new BufferedReader(new FileReader(new File(file + ".in")));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken());
		long[] totals = new long[N/2+1];
		
		int currLevel = 0;
		for(int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			if(Integer.parseInt(st.nextToken()) == 0) { //(
				currLevel++;
			} else { //)
				if(totals[currLevel] == 0) totals[currLevel-1]++;
				else totals[currLevel-1] += totals[currLevel]*2;
				totals[currLevel-1] %= MOD;
				totals[currLevel] = 0;
				currLevel--;
			}
		}
		br.close();
		

		//PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter(new File(file + ".out"))));
		System.out.println(totals[0]);
		//out.println(result);
		//out.close();
	}
}

