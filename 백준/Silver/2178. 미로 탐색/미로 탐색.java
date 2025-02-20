import static java.lang.Integer.parseInt;
import java.io.*;
import java.util.*;

public class Main {
	private static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	private static boolean[][] map;
	private static int height;
	private static int width;
	private static Queue<int[]> que = new ArrayDeque<>();

	private static int[] dr = { 0, 0, -1, 1 };
	private static int[] dc = { 1, -1, 0, 0 };

	public static void main(String[] args) throws IOException {
		initializeProblem();
		System.out.println(solve());
	}

	private static void initializeProblem() throws IOException {
		String[] inputs = br.readLine().trim().split(" ");
		height = parseInt(inputs[0]);
		width = parseInt(inputs[1]);

		map = new boolean[height][width];
		for (int r = 0; r < height; r++) {
			inputs = br.readLine().trim().split("");
			for (int c = 0; c < width; c++) {
				if (parseInt(inputs[c]) == 1) {
					map[r][c] = true;
				}
			}
		}
		que.add(new int[] { 0, 0, 1 });
	}

	private static int solve() {
		while (!que.isEmpty()) {
			int[] location = que.poll();
			int r = location[0];
			int c = location[1];
			int cost = location[2];
			if (!map[r][c])
				continue;
			map[r][c] = false;

			if (r == height - 1 && c == width - 1) {
				return cost;
			}

			for (int d = 0; d < 4; d++) {
				int nr = r + dr[d];
				int nc = c+dc[d];
				if(isInBound(nr, nc)) {
					que.add(new int[] {nr,nc,cost+1});
				}
			}

		}

		return -1;
	}

	private static boolean isInBound(int r, int c) {
		return r > -1 && c > -1 && height > r && width > c && map[r][c];
	}

}
