import java.util.*;
import java.io.*;

public class subarrays {
	static int N;
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		long[] a = new long[N];
		st = new StringTokenizer(br.readLine());
		for(int i = 0; i < N; i++) a[i] = Long.parseLong(st.nextToken());
		
		ArrayList<Subarray> sums = new ArrayList<>();
		for(int l = 0; l < N; l++) {
			long sum = 0;
			for(int r = l; r < N; r++) {
				sum += a[r];
				sums.add(new Subarray(l, r, sum));
			}
		}
		Collections.sort(sums, (u, v) -> Long.compare(u.sum, v.sum));
		br.close();
		
		
		solve1(sums);
//		solve2(sums);
		
	}
	
	public static void solve1(ArrayList<Subarray> sums) {
		for(int i = 0; i < N; i++) {
			long min = Long.MAX_VALUE;
			for(int l = 0; l < sums.size()-1; l++) {
				Subarray f = sums.get(l), s = sums.get(l+1);
				if(i >= f.l && i <= f.r) {
					if(i < s.l || i > s.r) min = Math.min(min, Math.abs(f.sum - s.sum));
				} else {
					if(i >= s.l && i <= s.r) min = Math.min(min, Math.abs(f.sum - s.sum));
				}
			}
			
			System.out.println(min);
		}
	}
	
	public static void solve2(ArrayList<Subarray> sums) {
		long[][] dp = new long[N][N];
		for(int i = 0; i < N; i++) Arrays.fill(dp[i], Long.MAX_VALUE);
		for(int i = 0; i < sums.size()-1; i++) {
			Subarray f = sums.get(i), s = sums.get(i+1);
			long diff = Math.abs(f.sum - s.sum);
			
			int l = Math.min(f.l, s.l);
			int r = Math.min(Math.max(f.l, s.l)-1, Math.min(f.r, s.r));
			if(l <= r) dp[l][r] = Math.min(dp[l][r], diff);
			
			r = Math.max(f.r, s.r);
			l = Math.max(Math.min(f.r, s.r)+1, Math.max(f.l, s.l));
			if(l <= r) dp[l][r] = Math.min(dp[l][r], diff);
		}
		
		for(int len = N-1; len >= 1; len--) {
			for(int l = 0; l+len-1 < N; l++) {
				int r = l+len-1;
				if(l > 0) dp[l][r] = Math.min(dp[l][r], dp[l-1][r]);
				if(r < N-1) dp[l][r] = Math.min(dp[l][r], dp[l][r+1]);
			}
		}
		
		for(int i = 0; i < N; i++) System.out.println(dp[i][i]);
	}
	
	public static class Subarray {
		int l, r;
		long sum;
		
		Subarray(int l, int r, long sum) {
			this.l = l;
			this.r = r;
			this.sum = sum;
		}
	}
}