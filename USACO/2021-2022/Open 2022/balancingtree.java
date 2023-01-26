// 

import java.util.*;
import java.io.*;

public class balancingtree {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int T = Integer.parseInt(st.nextToken());
		int B = Integer.parseInt(st.nextToken());
		
		for(int t = 0; t < T; t++) {
			st = new StringTokenizer(br.readLine());
			int N = Integer.parseInt(st.nextToken());
			
			int[] P = new int[N];
			int[] L = new int[N], R = new int[N];
			int[] mn = new int[N], mx = new int[N];
			
			
			st = new StringTokenizer(br.readLine());
			for(int i = 1; i < N; i++) 
				P[i] = Integer.parseInt(st.nextToken())-1;
			
			int res = 0;
			int gMn = Integer.MAX_VALUE, gMx = Integer.MIN_VALUE;
			for(int i = 0; i < N; i++) {
				st = new StringTokenizer(br.readLine());
				L[i] = Integer.parseInt(st.nextToken());
				R[i] = Integer.parseInt(st.nextToken());
				
				if(i == 0) {
					mn[i] = R[i];
					mx[i] = L[i];
				}
				
				mn[i] = Math.min(mn[P[i]], R[i]);
				mx[i] = Math.max(mx[P[i]], L[i]);
				gMn = Math.min(mn[i], gMn);
				gMx = Math.max(gMx, mx[i]);
				
				res = Math.max(res, mx[i] - mn[i]);
			}
			
			res = Math.max(res, (gMx-gMn+1)/2);
			System.out.println(res);

			if(B == 1) {
				for(int i = 0; i < N; i++) {
					System.out.print(Math.max(Math.min(res + gMn, R[i]), L[i]));
					if(i < N-1) System.out.print(" ");
				}
				System.out.println();
			}
		}
		br.close();
		
		
	}
}