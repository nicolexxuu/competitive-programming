import java.util.*;
import java.io.*;

public class BerlandMusic {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int T = Integer.parseInt(st.nextToken());
		for(int t = 0; t < T; t++) {
			st = new StringTokenizer(br.readLine());
			int n = Integer.parseInt(st.nextToken());
			
			int[] res = new int[n];
			int[] p = new int[n];
			st = new StringTokenizer(br.readLine());
			for(int i = 0; i < n; i++) p[i] = Integer.parseInt(st.nextToken());
			st = new StringTokenizer(br.readLine());
			boolean[] like = new boolean[n];
			String str = st.nextToken();
			ArrayList<Pair> l = new ArrayList<>(), d = new ArrayList<>();
			for(int i = 0; i < n; i++) {
				like[i] = str.charAt(i) == '1';
				if(like[i]) l.add(new Pair(p[i], i));
				else d.add(new Pair(p[i], i));
			}
			
			Collections.sort(l); Collections.sort(d);
			for(int i = l.size(); i > 0; i--) res[l.get(l.size()-i).id] = n - (l.size()-i);
			for(int i = d.size(); i > 0; i--) res[d.get(d.size()-i).id] = i;
			for(int i : res) System.out.print(i + " ");
		}
		br.close();
	}
	
	public static class Pair implements Comparable<Pair>{
		int orig, id;
		Pair(int orig, int id) {
			this.orig = orig;
			this.id = id;
		}
		
		public int compareTo(Pair other) {
			return other.orig - orig;
		}
	}
}