import java.io.*;
import java.util.*;

public class swap {

	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new FileReader("swap.in"));
		PrintWriter pw = new PrintWriter(new File("swap.out"));

		StringTokenizer st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());
		int K = Integer.parseInt(st.nextToken());
		
		int [] cows = new int[N+1];
		for(int i = 1; i <= N; i++) {
			cows[i] = i;
		}

		int[][] swaps = new int[M][2]; 
		for(int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine());
			swaps[i][0] = Integer.parseInt(st.nextToken());
			swaps[i][1] = Integer.parseInt(st.nextToken());
		}
		
		
		for(int i = 0; i < M; i++) {
			int a = swaps[i][0];
			int b = swaps[i][1];
			for(int j = 0; j < (b - a + 1) / 2 ; j++) {
				int temp = cows[j + a];
				cows[j + a] = cows[b - j];
				cows[b - j] = temp;
			}
		}
		
		ArrayList<ArrayList<Integer>> cycles = new ArrayList<>();
		int[][] reference = new int[N + 1][2];
		//reference[i][0]: cycle ID
		//reference[i][1]: cycle location
		
		boolean [] visited = new boolean[N + 1];
		
		int cID = 0;
		for(int i = 1; i <= N; i++) {
			if(visited[i]) continue;
			int loc = 0;
			ArrayList<Integer> c = new ArrayList<>();
			
			int toVisit = i;
			while(!visited[toVisit]) {
				visited[toVisit] = true;
				c.add(toVisit);
				reference[toVisit][0] = cID;
				reference[toVisit][1] = loc;
				loc++;
				toVisit = cows[toVisit];
			}
			
			cycles.add(c);
			cID++;
		}
		
		
		//output
		for(int i = 1; i <= N; i++) {
			int ID = reference[i][0];
			int loc = reference[i][1];
			ArrayList<Integer> cycle = cycles.get(ID);
			pw.println(cycle.get((loc + K) % cycle.size()));
		}
		
		pw.close();

	}
}
