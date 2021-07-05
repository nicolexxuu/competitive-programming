import java.util.*;
import java.io.*;

public class rollers {
	public static void main(String[] args) throws IOException {
		Scanner in = new Scanner(System.in);
		int N = in.nextInt();
		Roller [] rollers = new Roller[N];
		for(int i = 0; i < N; i++) {
			rollers[i] = new Roller(in);
		}
		
		int[] rollersTouched = new int[N];
		for(int r1 = 0; r1 < N; r1++) {
			for(int r2 = 0; r2 < N; r2++) {
				if(r2 == r1) continue;
				if(touch(rollers[r1], rollers[r2])) {
					rollersTouched[r1]++;
				}
			}
		}
		
		for(int i = 0; i < N; i++) {
			if(!(rollers[i].x == 0 && rollers[i].y == 0) && rollersTouched[i] < 2) {
				System.out.println(rollers[i].x + " " + rollers[i].y);
				break;
			}
		}
	}
	
	public static class Roller{
		int x;
		int y;
		int r;
		
		Roller(Scanner in){
			x = in.nextInt();
			y = in.nextInt();
			r = in.nextInt();
		}
		
		
	}
	
	public static boolean touch(Roller r1, Roller r2) {
		int d = (r1.x - r2.x)*(r1.x - r2.x) + (r1.y - r2.y)*(r1.y - r2.y);  
		int r = (r1.r + r2.r) * (r1.r + r2.r);
		if(d == r || d < r) return true;
		return false;
	}
}
