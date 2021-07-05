import java.io.*;
import java.util.*;

public class dream {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int M = Integer.parseInt(st.nextToken());
		int N = Integer.parseInt(st.nextToken());
		int[] nums = new int[10];
		for(int i = M; i <= N; i++) {
			String num = Integer.toString(i);
			for(int digit = 0; digit < num.length(); digit++) {
				int index = (num.charAt(digit)) - '0';
				nums[index]++;
			}
		}
		System.out.println(nums[0] + " " + nums[1] + " " + nums[2] + " " + nums[3] + " " + nums[4] + " " + nums[5] + " " + nums[6] + " " + nums[7] + " " + nums[8] + " " + nums[9]);
    }
}
