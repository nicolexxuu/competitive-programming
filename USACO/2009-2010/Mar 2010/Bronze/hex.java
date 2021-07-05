import java.util.*;
import java.io.*;

public class hex {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		String h = st.nextToken();
		br.close();
		if(h.equals("0")) System.out.println(0);
		//from hex to binary
		String b = "";
		String[] bin = {"0000", "0001", "0010", "0011","0100", "0101", "0110", "0111", "1000", "1001", "1010", "1011", "1100", "1101", "1110", "1111"};
		char[] he = {'0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'A', 'B', 'C', 'D', 'E', 'F'};
		for(int i = 0; i < h.length(); i++) {
			char digit = h.charAt(i);
			for(int j = 0; j < he.length; j++) {
				if(he[j] == digit) {
					b += bin[j];
				}
			}
		}
		//from binary to octal
		String o = "";
		for(int i = b.length() - 1; i >= 0; i -= 3) {
			int oct = 0;
			for(int count = 0; count < 3 && i - count >= 0; count++) {
				oct += Integer.parseInt(b.substring(i - count, i - count + 1)) * (Math.pow(2, count));
			}
			o = oct + o;
		}
 		int i = 0;
 		while(i < o.length()) {
 			if(o.charAt(i) != '0') break;
 			i++;
 		}
 		o = o.substring(i);
		System.out.println(o);
	}
}
