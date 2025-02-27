import static java.lang.Integer.parseInt;
import java.util.*;
import java.io.*;

public class Solution {
	private static final int[] dr = { 0, 0, -1, 1 };
	private static final int[] dc = { -1, 1, 0, 0 };
	private static final BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	private static final BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

	private static int mapSize;
	private static int[][][] map;

	private static int minLocation;
	private static int maxMove;

	public static void main(String[] args) throws Exception {
		int T = Integer.parseInt(br.readLine().trim());
		for (int t = 1; t <= T; t++) {
			initializeProblem();
			solve();
			bw.append('#').append(String.valueOf(t)).append(' ').append(String.valueOf(minLocation)).append(' ')
					.append(String.valueOf(maxMove)).append(' ').append('\n');
		}
		br.close();
		bw.close();
	}

	private static void initializeProblem() throws IOException {
		mapSize = parseInt(br.readLine());

		map = new int[mapSize][mapSize][2];
		StringTokenizer st;
		for (int i = 0; i < mapSize; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < mapSize; j++) {
				map[i][j][0] = parseInt(st.nextToken());
				map[i][j][1] = -1;
			}
		}

		minLocation = Integer.MAX_VALUE;
		maxMove = Integer.MIN_VALUE;
	}

	private static void solve() {
		for (int i = 0; i < mapSize; i++) {
			for (int j = 0; j < mapSize; j++) {
				if (map[i][j][1] == -1) {
					bfs(i, j);
				}
			}
		}

		for (int i = 0; i < mapSize; i++) {
			for (int j = 0; j < mapSize; j++) {
				if (maxMove < map[i][j][1]) {
					maxMove = Math.max(maxMove, map[i][j][1]);
					minLocation = map[i][j][0];
				} else if(maxMove == map[i][j][1]) {
					minLocation = Math.min(minLocation, map[i][j][0]);
				}
			}
		}
	}

	private static void bfs(int r, int c) {
		ArrayDeque<int[]> stack = new ArrayDeque<>();
		stack.push(new int[] { r, c });

		while (true) {
			int curData = map[r][c][0];
			int nr = r;
			int nc = c;
			if (isInbound(r + dr[0], c + dc[0]) && map[r + dr[0]][c + dc[0]][0] == curData + 1) {
				nr += dr[0];
				nc += dc[0];
				stack.push(new int[] { nr, nc });
			} else if (isInbound(r + dr[1], c + dc[1]) && map[r + dr[1]][c + dc[1]][0] == curData + 1) {
				nr += dr[1];
				nc += dc[1];
				stack.push(new int[] { nr, nc });
			} else if (isInbound(r + dr[2], c + dc[2]) && map[r + dr[2]][c + dc[2]][0] == curData + 1) {
				nr += dr[2];
				nc += dc[2];
				stack.push(new int[] { nr, nc });
			} else if (isInbound(r + dr[3], c + dc[3]) && map[r + dr[3]][c + dc[3]][0] == curData + 1) {
				nr += dr[3];
				nc += dc[3];
				stack.push(new int[] { nr, nc });
			}

			if (nr == r && nc == c)
				break;

			r = nr;
			c = nc;
		}

		int count = 1;
		while (!stack.isEmpty()) {
			int[] loc = stack.pop();
			map[loc[0]][loc[1]][1] = count++;
		}
	}

	private static boolean isInbound(int i, int j) {
		return i > -1 && j > -1 && mapSize > i && mapSize > j;
	}
}
