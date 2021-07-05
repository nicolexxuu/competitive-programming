import java.io.*;
import java.util.*;

public class lostcow {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int X = Integer.parseInt(st.nextToken());
		int Y = Integer.parseInt(st.nextToken());
		
		boolean cowFound = false;
		int distance = 0;
		int multiple = 1;
		boolean add = true;
		int prevLocation = X;
		
		while(!cowFound) {
			int temp = X + multiple;
			if((temp <= Y && Y < X) || (temp >= Y && Y > X)) {
				cowFound = true;
				distance += Math.abs(prevLocation - Y);
				System.out.println(distance);
			} else {
			    multiple *= -2;
				distance += Math.abs(temp - prevLocation);
				prevLocation = temp;
			}
		}
    }

}
