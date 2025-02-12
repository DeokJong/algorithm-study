import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
	private static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	private static StringTokenizer st;
	private static int C, R, N;
	private static boolean[][] map;
	private static int[] dr = { 1, 0, -1, 0 };
	private static int[] dc = { 0, 1, 0, -1 };

	public static void main(String[] args) throws IOException {
		initializeProblem();
		solve();
	}

	private static void initializeProblem() throws IOException {
		st = new StringTokenizer(br.readLine());
		C = Integer.parseInt(st.nextToken());
		R = Integer.parseInt(st.nextToken());
		N = Integer.parseInt((new StringTokenizer(br.readLine())).nextToken());
		map = new boolean[R][C];
	}

	private static void solve() {
		if (C * R < N) {
			System.out.println(0);
			return;
		}
		
		if(N == 1) {
			System.out.printf("%d %d",1,1);
			return ;
		}

		int iter = 1;
		int d = 0;
		int r = 0;
		int c = 0;
		map[r][c]=true;
		while (iter < C * R) {		
			int nr = r+dr[d];
			int nc = c+dc[d];
			if(isInBound(nr, nc)&&!map[nr][nc]) {
				r=nr;
				c=nc;
				iter++;
				map[r][c] = true;
				if(iter==N) {
					System.out.printf("%d %d",c+1,r+1);
					return ;
				}
			} else {
				d = (d + 1) % 4;
			}
			
		}

	}

	private static boolean isInBound(int r, int c) {
		return r > -1 && c > -1 && R > r && C > c;
	}
}