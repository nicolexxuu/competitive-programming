import java.io.*;
import java.util.*;

public class billboard2 {
	 public static void main(String[] args) throws IOException {
	        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
			StringTokenizer st = new StringTokenizer(br.readLine());
			int lx1 = Integer.parseInt(st.nextToken());
			int ly1 = Integer.parseInt(st.nextToken());
			int lx2 = Integer.parseInt(st.nextToken());
			int ly2 = Integer.parseInt(st.nextToken());
			
			st = new StringTokenizer(br.readLine());
			int cx1 = Integer.parseInt(st.nextToken());
			int cy1 = Integer.parseInt(st.nextToken());
			int cx2 = Integer.parseInt(st.nextToken());
			int cy2 = Integer.parseInt(st.nextToken());
			
			if(cx1 <= lx1 && cx2 >= lx2 && cy1 <= ly1 && cy2 >= ly2){ 
			    System.out.println(0);
			}
			else if(cx2 <= lx1 || cx1 >= lx2 || cy1 >= ly2 || cy2 <= ly1) {
				System.out.println((lx2 - lx1) * (ly2 - ly1));
			} else {
				if(cx1 <= lx1 && cx2 >= lx2) {
					if(cy2 <= ly2 && cy1 >= ly1) {
						System.out.println((ly2 - ly1) * (lx2 - lx1));
					} else 
						if(cy2 >= ly2) {
							System.out.println((lx2 - lx1) * (cy1 - ly1));
						} else {
							System.out.println((lx2 - lx1) * (cy2 - ly2));
						}
						
				} else if(cy1 <= ly1 && cy2 >= ly2) {
					if(cx2 <= lx2 && cx1 >= lx1) {
						System.out.println((ly2 - ly1) * (lx2 - lx1));
					} else 
						if(cx2 >= lx2) {
							System.out.println((lx2 - cx1) * (ly2 - ly1));
						} else {
							System.out.println((lx2 - cx2) * (ly2 - ly1));
						}
				} else {
					System.out.println((lx2 - lx1) * (ly2 - ly1));
				}
			}
			
			
	    }
}
