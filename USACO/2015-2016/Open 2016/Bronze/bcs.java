import java.util.*;
import java.io.*;

public class bcs {
	public static void main(String[] args) throws IOException {
		Scanner in = new Scanner(System.in);
		int N = in.nextInt();
		int K = in.nextInt();
		boolean[][] figure = new boolean[N][N];
		for(int i = 0; i < N; i++) {
			String ln = in.next();
			for(int j = 0; j < N; j++) {
				if(ln.charAt(j) == '#') figure[i][j] = true;
			}
		}
		
		boolean[][][] figures = new boolean[K][][];
		for(int i = 0; i < K; i++) {
			figures[i] = new boolean[N][N];
			for(int l = 0; l < N; l++) {
				String ln = in.next();
				for(int j = 0; j < N; j++) {
					if(ln.charAt(j) == '#') figures[i][l][j] = true;
				}
			}
		}
		
		boolean matches = false;
		for(int f1 = 0; f1 < K; f1++) {
			for(int f2 = f1 + 1; f2 < K; f2++) {
				System.out.println("checking figures " + f1 + " and " + f2);
				boolean good = false;
				for(int sr1 = -N + 1; sr1 <= N - 1; sr1++) {
					for(int sc1 = -N + 1; sc1 <= N + 1; sc1++) {
						System.out.println("rows/cols of fig one shifted by " + sr1 + ", and " + sc1);
						for(int sr2 = -N + 1; sr2 <= N - 1; sr2++) {
							for(int sc2 = -N + 1; sc2 <= N + 1; sc2++) {
								System.out.println("rows/cols of fig one shifted by " + sr2 + ", and " + sc2);
								for(int i = 0; i < N; i++) {
									matches = true;
									for(int j = 0; j < N; i++) {
										System.out.println(i + " " + j);
										int r = i + sr1;
										int c = j + sc1;
										if(r >= 0 && r < N && c >= 0 && c < N) {
											if(figure[i][j] != figures[f1][r][c]) {
												matches = false;
												break;
											}
										}
									}
									if(!matches) continue;
									for(int j = 0; j < N; i++) {
										int r = i + sr2;
										int c = j + sc2;
										if(r >= 0 && r < N && c >= 0 && c < N) {
											if(figure[i][j] != figures[f2][r][c]) {
												matches = false;
												break;
											}
										}
									}
									if(matches) good = true;
								}
							}
						}
					}
				}
				if(good) {
					System.out.println((Math.min(f1, f2) + 1) + " " + (Math.max(f1, f2) + 1));
				}
			}
		}
	}
}

