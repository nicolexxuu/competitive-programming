// cpp "multiset" with Java TreeMap Implementation 
// key -> element, value -> frequency

import java.util.*;
import java.io.*;

public class MultiSet {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken());
		TreeMap<Integer, Integer> map = new TreeMap<>();
		
		for(int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			insert(map, Integer.parseInt(st.nextToken()));
		}
		br.close();
		
		int first = map.firstKey();
		erase(map, first);
	}
	
	public static class MultiSet {
		TreeMap<Integer, Integer> map = new TreeMap<>();
		
		// add 1 to the frequency of element a
		// O(log N)
		public void insert(int a) {
			map.putIfAbsent(a, 0);
           		map.put(a, map.get(a)+1);
		}
		
		// remove 1 from the frequency of element a, remove key from map if needed
		// O(log N)
		public void erase(int a) {
			if(map.get(a) > 1) map.replace(a, map.get(a) - 1);
			else map.remove(a);
		}
	}
}
