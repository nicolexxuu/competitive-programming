import java.util.*;
import java.io.*;

public class times17 {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		String N = st.nextToken();
		br.close();
		String t16 = N + "0000";
		N = "0000" + N;
		String result = "";

		int carry = 0;
		for(int i = N.length() - 1; i >= 0; i--) {
			int i1 = Integer.parseInt(N.substring(i, i + 1));
			int i2 = Integer.parseInt(t16.substring(i, i+ 1));
			int sum = i1 + i2 + carry;
			if(sum > 1) {
				carry = sum / 2;
				sum %= 2;
			} else {
				carry = 0;
			}
			result = sum + result;
		}
		if(carry > 0) result = carry + result;

		System.out.println(result);
	}
}
