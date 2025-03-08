import static java.lang.Integer.parseInt;
import java.io.*;
import java.util.*;

public class Main {
	private static class Location implements Comparable<Location> {
		// 무조건 준영의 타입은0, 불의 타입은 1
		int r, c, time, type;

		public Location(int r, int c, int time, int type) {
			super();
			this.r = r;
			this.c = c;
			this.time = time;
			this.type = type;
		}

		@Override
		public int compareTo(Location o) {
			int comp = Integer.compare(this.time, o.time);
			if (comp != 0)
				return comp;

			return Integer.compare(this.type, o.type);
		}
	}

	private static final int[] dr = { 0, 0, 1, -1 };
	private static final int[] dc = { 1, -1, 0, 0 };

	private static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	private static int height, width;
	private static int[][] map;

	private static PriorityQueue<Location> que;

	public static void main(String[] args) throws NumberFormatException, IOException {
		initializeProblem();
		int ans = solve();
		System.out.println(ans == -1 ? "IMPOSSIBLE" : ans);
	}

	private static void initializeProblem() throws NumberFormatException, IOException {
		StringTokenizer st = new StringTokenizer(br.readLine());
		height = parseInt(st.nextToken());
		width = parseInt(st.nextToken());

		que = new PriorityQueue<>();
		map = new int[height][width];
		for (int r = 0; r < height; r++) {
			char[] inputs = br.readLine().toCharArray();
			for (int c = 0; c < width; c++) {
				int item = inputs[c];
				map[r][c] = item;
				if (map[r][c] == 'F') {
					que.add(new Location(r, c, 0, 1));
				} else if (map[r][c] == 'J') {
					que.add(new Location(r, c, 0, 0));
				}

			}
		}
	}

	private static int solve() {
		while (!que.isEmpty()) {
			Location loc = que.poll();
			int r = loc.r;
			int c = loc.c;
			int time = loc.time;
			int type = loc.type;

			if (type == 0 && map[r][c] == 'F') {
				continue;
			}

			if (type == 0) {
				for (int d = 0; d < 4; d++) {
					int nr = r + dr[d];
					int nc = c + dc[d];

					if (!isInBound(nr, nc)) {
						return time + 1;
					}

					if (isMove(nr, nc)) {
						que.add(new Location(nr, nc, time + 1, type));
						map[nr][nc] = 'J';
					}
				}
			} else {
				for (int d = 0; d < 4; d++) {
					int nr = r + dr[d];
					int nc = c + dc[d];
					
					if(isInBound(nr, nc) && isContamination(nr, nc)) {
						que.add(new Location(nr, nc, time+1, type));
						map[nr][nc] = 'F';
					}
				}
			}
		}

		return -1;
	}

	private static boolean isInBound(int r, int c) {
		return r > -1 && c > -1 && height > r && width > c;
	}

	private static boolean isMove(int r, int c) {
		return map[r][c] != 'F' && map[r][c] != '#' && map[r][c] != 'J';
	}

	private static boolean isContamination(int r, int c) {
		return map[r][c] != '#' && map[r][c] != 'F';
	}

}
