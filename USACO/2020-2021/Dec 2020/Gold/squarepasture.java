import java.util.*;
import java.io.*;

public class squarepasture {
	static int N;
	static Integer[][] C;
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		C = new Integer[N][2];
		
		for(int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			C[i][0] = Integer.parseInt(st.nextToken());
			C[i][1] = Integer.parseInt(st.nextToken());
		}
		
		Arrays.sort(C, (a, b) -> a[0] - b[0]);
		br.close();
		
		int res = 0;
		res += solve(false);
		for(Integer[] a : C) {
			int tmp = a[0];
			a[0] = a[1]; a[1] = tmp;
		}
		Arrays.sort(C, (a, b) -> a[0] - b[0]);
		res += solve(true);
		System.out.println(res + N + 1);
	}
	
	public static int solve(boolean s) {
		int res = 0;
		for(int i = 0; i < N; i++) {
			for(int j = i+1; j < N; j++) {
				int sz = C[j][0] - C[i][0];
				int mn = Math.max(C[j][1], C[i][1]) - sz, mx = Math.min(C[j][1], C[i][1]);
				if(mn > mx) continue;
				if(mn == mx && s) continue; // only second time
				ArrayList<Integer> ys = new ArrayList<>();
				TreeSet<Integer> dst = new TreeSet<>();
				for(int k = i; k <= j; k++) 
					if(C[k][1] >= mn && C[k][1] <= mx + sz) ys.add(C[k][1]);
				dst.addAll(ys); 
				ys.clear(); ys.addAll(dst);
				
				int l = 0, r = 0;
				while(r < ys.size()-1 && ys.get(r+1) <= mn+sz) r++;
				
				while(ys.get(l) <= mx) {
					res++;
					if(ys.get(r) - ys.get(l) == sz && s) res--;
					
					if(r < ys.size()-1 && ys.get(r+1)-sz < ys.get(l)+1) r++;
					else if(r < ys.size()-1 && ys.get(r+1)-sz == ys.get(l)+1) { r++; l++; }
					else l++; 
				}
			}
		}
		
		return res;
	}
}