// Mooo

import java.util.*;
import java.io.*;

public class moooo {
	public static void main(String[] args) throws IOException {
		String file = "";
		// BufferedReader br = new BufferedReader(new FileReader(new File(file + ".in")));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken());
		Cow[] cows = new Cow[N];
		for(int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			cows[i] = new Cow(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()));
		}
		
		br.close();
		
		Stack<Integer> mooing = new Stack<>();
		//go up
		for(int cow = 0; cow < N; cow++) {
			while(!mooing.isEmpty() && cows[mooing.peek()].height < cows[cow].height) {
				cows[cow].heard += cows[mooing.pop()].volume;
			}
			mooing.add(cow);
		}
		
		mooing.clear();
		//now go down
		for(int cow = N-1; cow >= 0; cow--) {
			while(!mooing.isEmpty() && cows[mooing.peek()].height < cows[cow].height) {
				cows[cow].heard += cows[mooing.pop()].volume;
			}
			mooing.add(cow);
		}
		
		
		int result = 0;
		for(Cow c: cows) {
			result = Math.max(result, c.heard);
		}
		//PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter(new File(file + ".out"))));
		System.out.println(result);
		//out.println(result);
		//out.close();
	}
	
	public static class Cow {
		int height;
		int volume;
		int heard;
		
		Cow(int height, int volume){
			this.height = height;
			this.volume = volume;
		}
		
	}
}
