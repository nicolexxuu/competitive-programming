// Sleepy Cow Herding

import java.util.*;
import java.io.*;

public class herding {
	public static void main(String[] args) throws IOException {
		String file = "herding";
		BufferedReader br = new BufferedReader(new FileReader(new File(file + ".in")));
//		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken());
		int[] L = new int[N];
		for(int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			L[i] = Integer.parseInt(st.nextToken());
		}
		
		Arrays.sort(L);
		br.close();

		int min = N;
		if((L[N-2]-L[0]+1 == N-1 && L[N-1]-L[N-2]-1 > 1) || (L[N-1]-L[1]+1 == N-1 && L[1]-L[0]-1 > 1)) {
			min = 2;
		} else {
			for(int i = 0, j = 0; i < N; i++) {
				while(j < N-1 && L[j+1]-L[i]+1 <= N) j++;
				min = Math.min(min, N-j+i-1);
			}
		}

		int max = L[N-1] - L[0] + 1 - N - Math.min(L[1]-L[0]-1, L[N-1]-L[N-2]-1);
		PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter(new File(file + ".out"))));
		System.out.println(min);
		System.out.println(max);
		out.println(min);
		out.println(max);
		out.close();
	}
}