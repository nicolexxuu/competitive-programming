// Water Slides

import java.util.*;
import java.io.*;

public class slides {
	public static void main(String[] args) throws IOException {
		String file = "";
		// BufferedReader br = new BufferedReader(new FileReader(new File(file + ".in")));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());
		int[] pos = new int[N];
		for(int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			pos[i] = Integer.parseInt(st.nextToken());
		}
		
		int[] inDegrees = new int[N];
		int[] outDegrees = new int[N];
		
		for(int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine());
			int a = Integer.parseInt(st.nextToken())-1;
			int b = Integer.parseInt(st.nextToken())-1;
			inDegrees[b]++;
			outDegrees[a]++;
		}
		
		br.close();
		
		ArrayList<Integer> in = new ArrayList<>();
		ArrayList<Integer> out = new ArrayList<>();
		
		for(int i = 0; i < N; i++) {
			if(inDegrees[i] == outDegrees[i]) continue;
			if(inDegrees[i] > outDegrees[i]) { // walking away
				for(int j = 0; j < Math.abs(inDegrees[i]-outDegrees[i]); j++) out.add(pos[i]);
			} else { // walking to
				for(int j = 0; j < Math.abs(inDegrees[i]-outDegrees[i]); j++) in.add(pos[i]);
			}
		}
		
		Collections.sort(in);
		Collections.sort(out);
		int result = 0;
		for(int i = 0; i < in.size(); i++) result += Math.abs(in.get(i) - out.get(i));
		//PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter(new File(file + ".out"))));
		System.out.println(result);
		//out.println(result);
		//out.close();
	}
}

