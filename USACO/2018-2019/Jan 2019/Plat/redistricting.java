// Redistricting

import java.util.*;
import java.io.*;

public class redistricting {
	public static void main(String[] args) throws IOException {
		String file = "";
		// BufferedReader br = new BufferedReader(new FileReader(new File(file + ".in")));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken());
		int K = Integer.parseInt(st.nextToken());
		st = new StringTokenizer(br.readLine());
		
		String str = st.nextToken();
		int[] prefix = new int[N+1];
		int[] dp = new int[N+1];
		for(int i = 1; i <= N; i++) {
			prefix[i] = prefix[i-1];
			if(str.charAt(i-1) == 'H') prefix[i]--;
			else prefix[i]++;
		}
		br.close();
		
		TreeMap<Integer, Integer> curr = new TreeMap<>();
		insert(curr, 0);
		ArrayList<TreeMap<Integer, Integer>> prefAt = new ArrayList<>();
		
		for(int i = 0; i <= N; i++) prefAt.add(new TreeMap<>((a, b) -> b - a));
		insert(prefAt.get(0), 0);
		
		for(int i = 1; i <= N; i++) {
			if(i > K) {
				int f = i - K - 1;
				erase(curr, dp[f]);
				erase(prefAt.get(dp[f]), prefix[f]);
			}
			
			int bVal = curr.firstKey();
			int bPref = prefAt.get(bVal).firstKey();
			
			if(bPref > prefix[i]) dp[i] = bVal;
			else dp[i] = bVal + 1;
			
			insert(curr, dp[i]);
			insert(prefAt.get(dp[i]), prefix[i]);
		}
		
		//PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter(new File(file + ".out"))));
		System.out.println(dp[N]);
		//out.println();
		//out.close();
	}
	
	public static void insert(TreeMap<Integer, Integer> map, int a) {
		if(map.containsKey(a)) map.replace(a, map.get(a), map.get(a) + 1);
		else map.put(a, 1);
	}
	
	public static void erase(TreeMap<Integer, Integer> map, int a) {
		if(map.get(a) > 1) map.replace(a, map.get(a) - 1);
		else map.remove(a);
	}
}
