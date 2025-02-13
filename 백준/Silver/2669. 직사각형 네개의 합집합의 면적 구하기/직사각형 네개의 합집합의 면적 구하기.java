import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
	private static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	private static int[][] map = new int[100][100];
	private static int area;

	public static void main(String[] args) throws IOException {
		initializeProblem();
		sovle();
		System.out.println(area);
	}

	private static void initializeProblem() throws IOException {
		StringTokenizer st;
		for (int i = 0; i < 4; i++) {
			st = new StringTokenizer(br.readLine());
			int c = Integer.parseInt(st.nextToken()) - 1;
			int r = Integer.parseInt(st.nextToken()) - 1;
			int w = (Integer.parseInt(st.nextToken()) - 1) - c;
			int h = (Integer.parseInt(st.nextToken()) - 1) - r;

			for (int j = r; j < r + h; j++) {
				for (int k = c; k < c + w; k++) {
					map[j][k] = 1;
				}
			}
		}
	}

	private static void sovle() {
		for (int i = 0; i < 100; i++) {
			for (int j = 0; j < 100; j++) {
				if (map[i][j] == 1)
					area++;
			}
		}
	}
}