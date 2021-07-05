import java.util.*;
import java.io.*;

public class radar {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int brand = Integer.parseInt(st.nextToken());
		br.close();

		int step = 1;
		while(true) {
			int sum = brand + reverse(brand);
			if(isP(sum)) {
				System.out.println(step + " " + sum);
				break;
			}
			step++;
			brand = sum;
		}
	}
	
	public static int reverse(int brand) {
		String b = String.valueOf(brand);
		String res = "";
		for(int i = b.length() - 1; i >= 0; i--) {
			res += b.charAt(i);
		}
		
		return Integer.parseInt(res);
	}
	
	public static boolean isP(int sum) {
		String s = String.valueOf(sum);
		for(int i = 0; i < s.length() / 2; i++) {
			if(s.charAt(i) != s.charAt(s.length() - 1 - i)) return false;
		}
		return true;
	}
}
