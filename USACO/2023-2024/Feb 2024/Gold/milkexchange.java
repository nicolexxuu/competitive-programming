import java.util.*;
import java.io.*;

public class milkexchange {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken());
		int[] a = new int[3*N];
		st = new StringTokenizer(br.readLine());
		long sum = 0;
		for(int i = 0; i < N; i++) {
			a[i] = a[i+N] = a[i+2*N] = Integer.parseInt(st.nextToken());
			sum += a[i];
		}
		long[] delt = new long[N+1];
		br.close();
		
		ArrayDeque<Integer> stk = new ArrayDeque<>();
		int[] l = new int[N*2], r = new int[N*2];
		for(int i = 0; i < N*2; i++) {
			while(!stk.isEmpty() && a[stk.peek()] > a[i]) stk.pop(); 
			if(i >= N) l[i] = stk.isEmpty() ? -1 : stk.peek();
			stk.addFirst(i);
		}
		stk.clear();
		for(int i = N*3-1; i >= N; i--) {
			while(!stk.isEmpty() && a[stk.peek()] >= a[i]) stk.pop(); 
			if(i < 2*N) r[i] = stk.isEmpty() ? -1 : stk.peek();
			stk.addFirst(i);
		}
		
		for(int i = N; i < N*2; i++) {
			int len = i-l[i];
			delt[0] += a[i];
			delt[len] -= a[i];
			if(r[i] != -1) {
				delt[r[i]-i] -= a[i];
				delt[r[i]-i+len] += a[i];
			}
		}
		
		for(int i = 1; i <= N; i++) {
			delt[i] += delt[i-1];
			sum += delt[i];
			System.out.println(sum);
		}
	}
}