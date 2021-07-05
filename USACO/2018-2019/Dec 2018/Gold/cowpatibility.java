// Cowpatibility

import java.util.*;
import java.io.*;

public class cowpatibility {
	static long[] inc_exc = {-1, 1, -1, 1, -1, 1};
	public static void main(String[] args) throws IOException {
		String file = "cowpatibility";
		BufferedReader br = new BufferedReader(new FileReader(new File(file + ".in")));
		//BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken());
		int[][] flavors = new int[N][5];
		HashMap<String, Integer> subsets = new HashMap<>();
		for(int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			for(int j = 0; j < 5; j++) flavors[i][j] = Integer.parseInt(st.nextToken());
		}
		br.close();
		
		for(int i = 0; i < N; i++) {
			Arrays.sort(flavors[i]);
			// generate all subsets including fav colors
			for(int j = 1; j < 32; j++) {
				int len = 0;
				String hash = "";
				for(int k = 0; k < 5; k++) {
					if(((1 << k) & j) > 0) { // if subset contains kth element
						hash += flavors[i][k] + ".";
						len++;
					}
				}
				hash += len;
				insert(subsets, hash);
			}
		}
		
		long res = (long)N * (N-1);
		for(Map.Entry<String, Integer> me : subsets.entrySet()) {
			String key = me.getKey();
			int val = me.getValue();
			res -= inc_exc[Integer.parseInt(key.substring(key.length()-1, key.length()))] * val * (val-1);
		}
		
		PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter(new File(file + ".out"))));
		//System.out.println(res/2);
		out.println(res/2);
		out.close();
	}
	
	public static void insert(HashMap<String, Integer> map, String a) {
		if(map.containsKey(a)) map.replace(a, map.get(a), map.get(a) + 1);
		else map.put(a, 1);
	}

}
