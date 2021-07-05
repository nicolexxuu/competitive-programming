import java.util.*;
import java.io.*;

public class geteven {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken());
		int [][] letters = new int[7][2];
		for(int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			char letter = st.nextToken().charAt(0);
			int index = 0;
			if(letter == 'B')		index = 0;
			else if(letter == 'E')	index = 1;
			else if(letter == 'S')	index = 2;
			else if(letter == 'I')	index = 3;
			else if(letter == 'G')	index = 4;
			else if(letter == 'O')	index = 5;
			else 					index = 6;
			if(Integer.parseInt(st.nextToken()) % 2 == 0) {
				letters[index][0]++;
			} else {
				letters[index][1]++;
			}
		}
		
		//(B+I)(G+O+E+S)(M)
		//BESIGOM
		//0123456
		//1234567
		
		br.close();
		int result = 0;
		
		for(int i1 = 0; i1 < 2; i1++) {
			for(int i2 = 0; i2 < 2; i2++) {
				for(int i3 = 0; i3 < 2; i3++) {
					for(int i4 = 0; i4 < 2; i4++) {
						for(int i5 = 0; i5 < 2; i5++) {
							for(int i6 = 0; i6 < 2; i6++) {
								for(int i7 = 0; i7 < 2; i7++) {
									if(((i1 + i4) * (i5 + i6 + i2 + i3) * i7) % 2 == 0) {
										result += (letters[0][i1] * letters[1][i2] * letters[2][i3] * letters[3][i4] * letters[4][i5] * letters[5][i6] * letters[6][i7]);
									}
								}
							}
						}
					}
				}
			}
		}
		System.out.println(result);
	}
}


/*
 * o*e*o = e
 * o*e*e = e
 * e*e*e = e;
 * add all together
 */
