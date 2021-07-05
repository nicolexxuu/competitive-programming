import java.io.*;

public class irrigation {
	static int[] x;
	static int[] y;
	public static void main(String[] args) throws IOException {
		String file = "";
		// BufferedReader br = new BufferedReader(new FileReader(new File(file + ".in")));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken());
		int C = Integer.parseInt(st.nextToken());
		x = new int[N];
		y = new int[N];
		
		for(int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			x[i] = Integer.parseInt(st.nextToken())-1;
			y[i] = Integer.parseInt(st.nextToken())-1;
			
		}
		br.close();

		int result = 0;
		int[] dist = new int[N];
		Arrays.fill(dist, Integer.MAX_VALUE);
		dist[0] = 0;
		boolean[] visited = new boolean[N];
		int inTree = 0;
		
		while(inTree < N) {
			int minInd = -1;
			int minVal = Integer.MAX_VALUE;
			
			for(int i = 0; i < N; i++) {
				if(!visited[i] && dist[i] < minVal) {
					minVal = dist[i];
					minInd = i;
				}
			}
			
			if(minInd == -1) {
				result = -1;
				break;
			}
			
			visited[minInd] = true;
			result += minVal;
			
			for(int j = 0; j < N; j++) {
				if(visited[j]) continue;
				int d = distance(minInd, j);
				if(d >= C) dist[j] = Math.min(dist[j], d);
			}
			inTree++;
		}
		
		System.out.println(result);
	}
	
	public static int distance(int p1, int p2) {
		return (x[p1] - x[p2]) * (x[p1] - x[p2]) + (y[p1] - y[p2]) * (y[p1] - y[p2]);
	}
}
