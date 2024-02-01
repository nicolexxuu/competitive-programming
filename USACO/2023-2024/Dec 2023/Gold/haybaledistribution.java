import java.util.*;
import java.io.*;

public class haybaledistribution {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken());
		int[] x = new int[N];
		st = new StringTokenizer(br.readLine());
		long[] dl = new long[N], dr = new long[N];
		for(int i = 0; i < N; i++) x[i] = Integer.parseInt(st.nextToken());
		Arrays.sort(x);
		for(int i = 1; i < N; i++) dl[i] = dl[i-1] + (x[i] - x[i-1]) * i;
		for(int i = N-2; i >= 0; i--) dr[i] = dr[i+1] + (x[i+1] - x[i]) * (N-i-1);

		st = new StringTokenizer(br.readLine());
		int Q = Integer.parseInt(st.nextToken());
		for(int i = 0; i < Q; i++) {
			st = new StringTokenizer(br.readLine());
			long a = Long.parseLong(st.nextToken()), b = Long.parseLong(st.nextToken());
			int j = (int)((b * N) / (a + b));
			System.out.println(dl[j] * a + dr[j] * b);
		}
		br.close();
	}
}