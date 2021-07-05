
// http://www.usaco.org/index.php?page=viewproblem2&cpid=259
// Cow Race

// This template code suggested by KT BYTE Computer Science Academy
//   for use in reading and writing files for USACO problems.

// https://content.ktbyte.com/problem.java

import java.util.*;
import java.io.*;

public class cowrace {
	public static void main(String[] args) throws FileNotFoundException {
		Scanner in = new Scanner(new File("cowrace.in"));
		int N = in.nextInt();
		int M = in.nextInt();
		
		int[] bessieTime = new int[N + 1];
		int[] bessieSpeed = new int[N + 1];
		int[] bessieDist = new int[N + 1];
		for(int i = 1; i <= N; i++) {
			int speed = in.nextInt();
			int time = in.nextInt();
			bessieTime[i] = bessieTime[i - 1] + time;
			bessieSpeed[i] = speed;
			bessieDist[i] = bessieDist[i - 1] + (time * speed);
		}
		int[] elsieTime = new int[M + 1];
		int[] elsieSpeed = new int[M + 1];
		int[] elsieDist = new int[M + 1];
		for(int i = 1; i <= M; i++) {
			int speed = in.nextInt();
			int time = in.nextInt();
			elsieTime[i] = elsieTime[i - 1] + time;
			elsieSpeed[i] = speed;
			elsieDist[i] = elsieDist[i - 1] + (time * speed);
		}
		in.close();

		int result = 0;
		boolean bessieAhead = false;
		
		
		int bessie = 1;
		int elsie = 1;
		
		while(bessie <= N && elsie <= M) {
			
			int bDist = bessieDist[bessie];
			int eDist = elsieDist[elsie];
			if(bessieTime[bessie] < elsieTime[elsie]) {
				eDist -= elsieSpeed[elsie] * (elsieTime[elsie] - bessieTime[bessie]);
			} else if(bessieTime[bessie] > elsieTime[elsie]) {
				bDist -= bessieSpeed[bessie] * (bessieTime[bessie] - elsieTime[elsie]);
			}
			
			if(bessieAhead && bDist < eDist) {
				bessieAhead = false;
				if(bessie != 1 && elsie != 1) {
					result++;
				}
			} else if(!bessieAhead && bDist > eDist) {
				bessieAhead = true;
				if(bessie != 1 && elsie != 1) {
					result++;
				}
			}
			
			if(bessieTime[bessie] < elsieTime[elsie]) {
				bessie++;
			} else if(bessieTime[bessie] > elsieTime[elsie]) {
				elsie++;
			} else {
				bessie++;
				elsie++;
			}
		}
		

		PrintWriter out = new PrintWriter(new File("cowrace.out"));
		System.out.println(result);
		out.println(result);
		out.close();
	}
}
