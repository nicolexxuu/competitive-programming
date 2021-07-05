// Help Yourself

import java.util.*;
import java.io.*;

public class help {
	static int MOD = 1000000007;
	public static void main(String[] args) throws IOException {
		String file = "help";
		BufferedReader br = new BufferedReader(new FileReader(new File(file + ".in")));
		//BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken());
		int[] pow = new int[N+1];
		pow[0] = 1;
		for(int i = 1; i <= N; i++) pow[i] = pow[i-1] * 2 % MOD;
		int[] ps = new int[N*2+1];
		for(int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			ps[Integer.parseInt(st.nextToken())]++;
			ps[Integer.parseInt(st.nextToken())]--;
		}
		br.close();
		
		for(int i = 1; i <= 2*N; i++) ps[i] += ps[i-1];
		int res = 0;
		for(int i = 1; i <= 2*N; i++) if(ps[i] > ps[i-1]) res = ( res + pow[N-1-ps[i-1]] ) % MOD;

		PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter(new File(file + ".out"))));
		System.out.println(res);
		out.println(res);
		out.close();
	}
}
