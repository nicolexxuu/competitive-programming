// Chocolate Buying

import java.util.*;
import java.io.*;
import java.math.BigInteger;

public class cbuying {
	public static void main(String[] args) throws IOException {
		String file = "";
		// BufferedReader br = new BufferedReader(new FileReader(new File(file + ".in")));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken());
		long B = Long.parseLong(st.nextToken());
		long[][] chocos = new long[N][2];
		for(int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			chocos[i][0] = Long.parseLong(st.nextToken());
			chocos[i][1] = Long.parseLong(st.nextToken());
		}
		Arrays.sort(chocos, new Comparator<long[]>() {
			public int compare(long[] a, long[] b) {
				if(a[0] < b[0]) return -1;
				if(b[0] < a[0]) return 1;
				return 0;
			}
		});

		br.close();
		
		long remaining = B;
		long cows = 0;
		boolean finished = false;
		for(int i = 0; i < N && !finished; i++) {
			long max = remaining/chocos[i][0];
			if(max >= chocos[i][1]) {
				cows += chocos[i][1];
				remaining -= chocos[i][0] * chocos[i][1];
			} else {
				finished = true;
				cows += max;
			}
		}
		//PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter(new File(file + ".out"))));
		System.out.println(cows);
		//out.println(result);
		//out.close();
	}
}


