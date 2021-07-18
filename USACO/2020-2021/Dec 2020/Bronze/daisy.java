// Daisy Chains

import java.util.*;
import java.io.*;

public class daisy {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken());
		int[] p = new int[N];
		
		st = new StringTokenizer(br.readLine());
		for(int i = 0; i < N; i++) p[i] = Integer.parseInt(st.nextToken());
		br.close();
		
		
		int count = 0;
		for(int i = 0; i < N; i++) {
			double sum = 0;
			for(int j = i; j < N; j++) {
				sum += p[j];
				
				boolean avg = false;
				for(int k = i; k <= j; k++) 
					if(p[k] == sum / (j-i+1)) avg = true;
				if(avg) count++;
			}
		}
		
		System.out.println(count);
		
	}
}