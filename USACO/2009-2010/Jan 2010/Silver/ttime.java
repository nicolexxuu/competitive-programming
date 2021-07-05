import java.util.*;
import java.io.*;

public class ttime {
	public static void main(String[] args) throws IOException {
		String file = "";
		// BufferedReader br = new BufferedReader(new FileReader(new File(file + ".in")));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());
		int Q = Integer.parseInt(st.nextToken());
		
		ArrayList<ArrayList<Integer>> adj = new ArrayList<>();
		
		for(int i = 0; i < N; i++) adj.add(new ArrayList<>());
		for(int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine());
			int a = Integer.parseInt(st.nextToken())-1;
			int b = Integer.parseInt(st.nextToken())-1;
			adj.get(a).add(b);
			adj.get(b).add(a);
		}
		
		int num = 1;
		int[] id = new int[N];
		for(int i = 0; i < N; i++) {
			if(id[i] != 0) continue;
			
			ArrayDeque<Integer> toVisit = new ArrayDeque<>();
			toVisit.add(i);
			id[i] = num;
			
			while(!toVisit.isEmpty()) {
				int curr = toVisit.remove();
				for(int j: adj.get(curr)) {
					if(id[j] == 0) {
						id[j] = num;
						toVisit.add(j);
					}
				}
			}
			num++;
		}
		
		for(int i = 0; i < Q; i++) {
			st = new StringTokenizer(br.readLine());
			int a = Integer.parseInt(st.nextToken())-1;
			int b = Integer.parseInt(st.nextToken())-1;
			if(id[a] == id[b]) System.out.println("Y");
			else System.out.println("N");
		}
		br.close();
	}
}
