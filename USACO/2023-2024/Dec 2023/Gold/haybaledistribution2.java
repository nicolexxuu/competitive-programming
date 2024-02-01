import java.util.*;
import java.io.*;

public class haybaledistribution2 {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken());
		st = new StringTokenizer(br.readLine());
		int[] X = new int[N];
		
		for(int i = 0; i < N; i++) X[i] = Integer.parseInt(st.nextToken());
		Arrays.sort(X);
		
		long[] DL = new long[N], DR = new long[N];
		for(int i = 1; i < N; i++) DL[i] = DL[i-1] + (X[i] - X[i-1]) * i;
		for(int i = N-2; i >= 0; i--) DR[i] = DR[i+1] + (X[i+1] - X[i]) * (N-i-1);
		
		ArrayList<Integer> x = new ArrayList<>();
		ArrayList<Long> dl = new ArrayList<>(), dr = new ArrayList<>();
		for(int i = 0; i < N; i++) {
			if(i == 0 || X[i] != X[i-1]) {
				x.add(X[i]);
				dl.add(DL[i]);
				dr.add(DR[i]);
			} else {
				dl.set(dl.size()-1, DL[i]);
			}
		}
		N = x.size();;
		
		st = new StringTokenizer(br.readLine());
		int Q = Integer.parseInt(st.nextToken());
		for(int i = 0; i < Q; i++) {
			st = new StringTokenizer(br.readLine());
			long a = Long.parseLong(st.nextToken()), b = Long.parseLong(st.nextToken());
			long best = Long.MAX_VALUE;
			int lo = 0, hi = N-1;
			while(lo < hi) {
				int mid = (lo + hi) / 2;
				best = Math.min(best, a * dl.get(mid) + b * dr.get(mid));
				if(a * dl.get(mid) + b * dr.get(mid) < a * dl.get(mid+1) + b * dr.get(mid+1)) hi = mid;
				else lo = mid + 1;
			}
			
			System.out.println(best);
		}
		br.close();
	}
}