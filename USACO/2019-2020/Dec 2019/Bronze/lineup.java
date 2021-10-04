// Livestock Lineup

import java.util.*;
import java.io.*;

public class lineup {
	static Cow[] cows;
	public static void main(String[] args) throws IOException {
		String file = "lineup";
		BufferedReader br = new BufferedReader(new FileReader(new File(file + ".in")));
//		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken());
		
		cows = new Cow[8];
		cows[0] = new Cow("Beatrice");
		cows[1] = new Cow("Belinda");
		cows[2] = new Cow("Bella");
		cows[3] = new Cow("Bessie");
		cows[4] = new Cow("Betsy");
		cows[5] = new Cow("Blue");
		cows[6] = new Cow("Buttercup");
		cows[7] = new Cow("Sue");
		
		for(int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			String name1 = st.nextToken();
			for(int skip = 0; skip < 4; skip++) st.nextToken(); // "must be milked beside"
			String name2 = st.nextToken();
			int id1 = find(name1), id2 = find(name2);
			
			cows[id1].beside.add(id2);
			cows[id2].beside.add(id1);
		}
		br.close();
		
		ArrayList<Integer> result = new ArrayList<>();
		while(result.size() < 8) {
			int id = 0;
			while(result.contains(id) || cows[id].beside.size() > 1) id++;
			
			boolean keepAdding = true;
			while(keepAdding) {
				result.add(id);
				keepAdding = false;
				for(int i = 0; i < 8; i++) {
					if(!result.contains(i) && cows[i].beside.contains(id)) {
						id = i;
						keepAdding = true;
						break;
					}
				}
			}
		}
		
		PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter(new File(file + ".out"))));
		for(int i : result) {
			System.out.println(cows[i].name);
			out.println(cows[i].name);
		}
		out.close();
	}
	
	public static class Cow {
		String name;
		ArrayList<Integer> beside;
		
		Cow(String name) {
			this.name = name;
			beside = new ArrayList<>();
		}
	}
	
	public static int find(String name) {
		for(int i = 0; i < 8; i++) 
			if(cows[i].name.equals(name)) return i;
		return -1;
	}
}