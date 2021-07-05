import java.io.*;
import java.util.*;

public class charms {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine()); //reads one line
		
		int L = Integer.parseInt(st.nextToken());
		int C = Integer.parseInt(st.nextToken());
		int N = Integer.parseInt(st.nextToken());
		
		int [][] charms = new int[C][2];
		
		for(int i = 0; i < C; i++) {
			st = new StringTokenizer(br.readLine());
			charms[i][0] = Integer.parseInt(st.nextToken());
			charms[i][1] = Integer.parseInt(st.nextToken());
			System.out.println(Math.abs(N - charms[i][1]) + charms[i][0]);
		}
		

    }
}
