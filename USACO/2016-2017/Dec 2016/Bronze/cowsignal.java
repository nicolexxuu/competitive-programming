import java.util.*;
import java.io.*;
public class cowsignal {

	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int M = Integer.parseInt(st.nextToken());
		int N = Integer.parseInt(st.nextToken());
		char[][] original = new char[M][N];
		int K = Integer.parseInt(st.nextToken());
		
		for(int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine());
			String str = st.nextToken();
			original[i] = str.toCharArray();
		}
		
		for(int i = 0; i < M * K; i++) {
			for(int j = 0; j < N * K; j++) {
					System.out.print(original[i/K][j/K]);
			}
			System.out.println();
		}

	}

}
