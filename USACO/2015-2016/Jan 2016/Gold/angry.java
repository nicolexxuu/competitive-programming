// Angry Cows

import java.util.*;
import java.io.*;
import java.text.DecimalFormat;

public class angry {
	public static void main(String[] args) throws IOException {
		String file = "";
		// BufferedReader br = new BufferedReader(new FileReader(new File(file + ".in")));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken());
		int[] loc = new int[N];
		for(int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			loc[i] = Integer.parseInt(st.nextToken())*2;
		}
		
		Arrays.sort(loc);
		br.close();
		
		int[] maxL = new int[N];
		int[] maxR = new int[N];
		Arrays.fill(maxL, 2000000000);
		Arrays.fill(maxR, 2000000000);
		maxL[0] = 0;
		maxR[N-1] = 0;
		
		int ind = 0; // needs max radius to cover
		
		for(int i = 1; i < N; i++) {
			while(ind < i-1 && (loc[i] - loc[ind+1]) > maxL[ind+1] + 2) ind++;
			maxL[i] = Math.min(loc[i] - loc[ind], maxL[ind+1] + 2);
		}
		
		ind = N-1;
		for(int i = N-2; i >= 0; i--) {
			while(ind > i+1 && (loc[ind-1] - loc[i]) > maxR[ind-1] + 2) ind--;
			maxR[i] = Math.min(loc[ind] - loc[i], maxR[ind-1] + 2);
		}
		int result = Integer.MAX_VALUE;

		int i = 0, j = N-1;
		while(i < j) {
			result = Math.min(result, Math.max(((loc[j]-loc[i])/2), Math.max(maxL[i], maxR[j]) + 2));
			if(maxL[i+1] < maxR[j-1]) i++;
			else j--;
		}

		//PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter(new File(file + ".out"))));
		System.out.println((result/2) + "." + (result%2 == 0 ? 0 : 5));
		//System.out.println(result);
		//out.println(result);
		//out.close();
	}

}
