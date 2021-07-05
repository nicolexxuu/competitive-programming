// Cow Photography

import java.util.*;
import java.io.*;

public class photo {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken());
		ArrayList<Integer> result = new ArrayList<>();
		HashMap<Integer, ArrayList<Integer>> cows = new HashMap<>();
		
		for(int i = 0; i < 5; i++) {
			for(int j = 0; j < N; j++) {
				st = new StringTokenizer(br.readLine());
				int a = Integer.parseInt(st.nextToken());
				if(i == 0) result.add(a);
				if(cows.containsKey(a)) {
					ArrayList<Integer> locs = cows.get(a);
					locs.add(j);
				} else {
					ArrayList<Integer> b = new ArrayList<>();
					b.add(j);
					cows.put(a, b);
				}
			}
		}
		br.close();

		Collections.sort(result, new Comparator<Integer>() {
			public int compare(Integer a, Integer b) {
				int before = 0;
				ArrayList<Integer> as = cows.get(a);
				ArrayList<Integer> bs = cows.get(b);
				
				for(int i = 0; i < 5; i++) if(as.get(i) < bs.get(i)) before++;
				
				if(before >= 3) return -1;
				return 1;
			}
		});

		for(int i: result) {
			System.out.println(i);
		}
	}
	
}
