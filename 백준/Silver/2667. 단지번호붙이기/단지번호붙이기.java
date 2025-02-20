import static java.lang.Integer.parseInt;
import java.io.*;
import java.util.*;

public class Main {
	private static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	private static List<Integer> li = new ArrayList<>();
	private static boolean[][] map;
	private static int mapSize;

	private static int[] dr = { 0, 0, -1, 1 };
	private static int[] dc = { 1, -1, 0, 0 };

	public static void main(String[] args) throws IOException {
		initializeProblem();
		solve();
		Collections.sort(li);
		System.out.println(li.size());
		for (int e : li) {
			System.out.println(e);
		}
	}

	private static void initializeProblem() throws IOException {
		mapSize = parseInt(br.readLine());
		map = new boolean[mapSize][mapSize];

		for (int i = 0; i < mapSize; i++) {
			String[] inputs = br.readLine().trim().split("");
			for (int j = 0; j < mapSize; j++) {
				if (parseInt(inputs[j]) == 1) {
					map[i][j] = true;
				}
			}
		}
	}

	private static void solve() {
		for (int i = 0; i < mapSize; i++) {
			for (int j = 0; j < mapSize; j++) {
				if (map[i][j] == true) {
					Queue<int[]> temp = new ArrayDeque<>();
					temp.add(new int[] { i, j });
					li.add(bfs(temp));
				}
			}
		}
	}

	private static int bfs(Queue<int[]> que) {
		int count = 0;
		while (!que.isEmpty()) {
			int[] location = que.poll();
			int r = location[0];
			int c = location[1];
			if (!map[r][c])
				continue;
			map[r][c] = false;
			count++;

			for (int d = 0; d < 4; d++) {
				int nr = r + dr[d];
				int nc = c + dc[d];
				if (isInBound(nr, nc)) {
					que.add(new int[] { nr, nc });
				}
			}
		}

		return count;
	}

	private static boolean isInBound(int r, int c) {
		return r > -1 && c > -1 && mapSize > r && mapSize > c && map[r][c];
	}

}
