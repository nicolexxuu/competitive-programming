// Catch That Cow

import java.util.*;
import java.io.*;

public class catchcow {
	public static void main(String[] args) throws IOException {
		String file = "catchcow";
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken());
		int K = Integer.parseInt(st.nextToken());
		br.close();
		
		int[] visited = new int[100001];
		ArrayDeque<Integer> toVisit = new ArrayDeque<>();
		visited[N] = 1;
		toVisit.add(N);
		
		while(!toVisit.isEmpty()) {
			int curr = toVisit.remove();
			//System.out.println(curr);
			if(curr == K) {
				System.out.println(visited[K]-1);
				break;
			}
			
			if(curr+1 != N && curr+1 <= 100000 && visited[curr+1] == 0) {
				visited[curr+1] = visited[curr] + 1;
				toVisit.add(curr+1);
			}
			if(curr-1 != N && curr-1 >= 0 && visited[curr-1] == 0) {
				visited[curr-1] = visited[curr] + 1;
				toVisit.add(curr-1);
			}
			if(curr*2 != N && curr*2 <= 100000 && visited[curr*2] == 0) {
				visited[curr*2] = visited[curr] + 1;
				toVisit.add(curr*2);
			}
		}
		
		//PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter(new File(file + ".out"))));
		//out.println(result);
		//out.close();
	}
}

