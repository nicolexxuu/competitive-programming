// Clumsy Cows

import java.util.*;
import java.io.*;

public class clumsy {
	public static void main(String[] args) throws IOException {
		String file = "";
		// BufferedReader br = new BufferedReader(new FileReader(new File(file + ".in")));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		String S = st.nextToken();
		br.close();

		int result = 0;
		int count = 0;
		for(char c: S.toCharArray()) {
			if(c == '(') {
				count++;
			} else {
				if(count == 0) {
					result++;
					count++;
				} else {
					count--;
				}
			}
		}
		//PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter(new File(file + ".out"))));
		System.out.println(result + count/2);
		//out.println(result);
		//out.close();
	}
}


