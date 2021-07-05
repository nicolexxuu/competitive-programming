/*
 * http://usaco.org/index.php?page=viewproblem2&cpid=487
 * "Marathon," 2014 December Bronze Contest
 */

import java.util.*;
import java.io.*;
public class marathon {

	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new FileReader("marathon.in"));
        PrintWriter pw = new PrintWriter(new BufferedWriter(new FileWriter("marathon.out")));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int [][] points = new int[N + 1][2];
        int [] distanceTo = new int[N + 1];
        for(int i = 1; i <= N; i++) {
        	st = new StringTokenizer(br.readLine());
        	points[i][0] = Integer.parseInt(st.nextToken());
        	points[i][1] = Integer.parseInt(st.nextToken());
        	distanceTo[i] = distance(points[i - 1][0], points[i - 1][1], points[i][0], points[i][1]) + distanceTo[i - 1];
        }
        
        int maxSkipped = 0;
        for(int skip = 2; skip < N; skip++) {
        	maxSkipped = distanceTo[skip + 1] - distanceTo[skip - 1]
        			- distance(points[skip - 1][0], points[skip-1][1], points[skip + 1][0], points[skip + 1][0]);
        }
        
        System.out.println(distanceTo[N] - maxSkipped);
        pw.close();

	}
	
	public static int distance(int x1, int y1, int x2, int y2) {
		return Math.abs(x1 - x2) + Math.abs(y1 - y2);
	}

}
