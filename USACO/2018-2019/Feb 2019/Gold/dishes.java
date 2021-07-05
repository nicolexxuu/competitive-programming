// This template code suggested by KT BYTE Computer Science Academy
//   for use in reading and writing files for USACO problems.

// https://content.ktbyte.com/problem.java

// http://www.usaco.org/index.php?page=viewproblem2&cpid=922
// "Dish Washing," 2019 Feb Gold

import java.util.*;
import java.io.*;

public class dishes {
	public static void main(String[] args) throws IOException {
		String file = "dishes";
		BufferedReader br = new BufferedReader(new FileReader(new File(file + ".in")));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken());
		int[] plates = new int[N];
		ArrayList<Stack<Integer>> stacks = new ArrayList<>();
		int max = -1;
		for(int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			plates[i] = Integer.parseInt(st.nextToken())-1;
			stacks.add(new Stack<>());
		}
		int[] placeIn = new int[N];
		br.close();

		int plate = 0;
		while(plate < N) {
			int placing = plates[plate];
			if(placing < max) break;
			
			for(int i = placing-1; i>=0 && placeIn[i]==0; i--) placeIn[i] = placeIn[placing];
			
			Stack<Integer> curr = stacks.get(placing);
			while(!curr.isEmpty() && curr.peek() < placing) max = curr.pop();
			curr.push(placing);
			plate++;
		}
		
		PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter(new File(file + ".out"))));
		System.out.println(plate);
		out.println(plate);
		out.close();
	}
}
