// Time Travel - persistent stack

import java.util.*;
import java.io.*;

public class ttravel {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken());
		
		int[]val = new int[N+1], parent = new int[N+1];
		
		val[0] = -1;
		for(int i = 1; i <= N; i++) {
			st = new StringTokenizer(br.readLine());
			String c = st.nextToken();
			
			if(c.equals("a")) {
				val[i] = Integer.parseInt(st.nextToken());
				parent[i] = i-1;
			} else if(c.equals("s")) {
				val[i] = val[parent[i-1]];
				parent[i] = parent[parent[i-1]];
			} else if(c.equals("t")) {
				int q = Integer.parseInt(st.nextToken());
				val[i] = val[q-1];
				parent[i] = parent[q-1];
			}
			System.out.println(val[i]);
		}

		br.close();
	}
}
