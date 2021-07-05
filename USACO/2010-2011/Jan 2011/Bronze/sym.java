import java.io.*;
import java.util.*;

public class sym {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());
		int multiple = 0;
		int cows = 0;
		while(N >= 1 && M >= 1 && N%2 == 1 && M%2 == 1) {
			cows += Math.pow(4, multiple);
			multiple++;
			N /= 2;
			M /= 2;
		}
		System.out.println(cows);
    }
}
