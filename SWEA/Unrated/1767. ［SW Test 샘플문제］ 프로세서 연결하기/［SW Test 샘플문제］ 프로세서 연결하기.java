import static java.lang.Integer.parseInt;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.StringTokenizer;

public class Solution {
	private static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

	static class Location {
		int r, c;

		public Location(int r, int c) {
			super();
			this.r = r;
			this.c = c;
		}
	}

	private static int[] dr = { 0, 0, -1, 1 };
	private static int[] dc = { -1, 1, 0, 0 };
	
	private static int minWire;
	private static int selectedWire;
	private static int maxSelectedCoreCount;
	private static int selectedCoreCount;
	private static int coreCount;
	private static int mapSize;
	
	private static Location[] cores;
	private static int[][] map;

	public static void main(String[] args) throws NumberFormatException, IOException {
		int T = parseInt(br.readLine());
		for (int i = 1; i <= T; i++) {
			initialzeProblem();
			solve(0);
			System.out.printf("#%d %d\n", i, minWire);
		}
	}

	private static void initialzeProblem() throws NumberFormatException, IOException {
		minWire = Integer.MAX_VALUE;
		selectedWire = 0;
		maxSelectedCoreCount = Integer.MIN_VALUE;
		selectedCoreCount = 0;
		coreCount = 0;
		Queue<Location> tempQue = new ArrayDeque<>();

		mapSize = parseInt(br.readLine());
		map = new int[mapSize][mapSize];
		for (int i = 0; i < mapSize; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine().trim());
			for (int j = 0; j < mapSize; j++) {
				map[i][j] = parseInt(st.nextToken());
				if (map[i][j] == 1) {
					if (!isOutLinerCore(i, j)) {
						coreCount++;
						tempQue.add(new Location(i, j));
					}
				}
			}
		}

		cores = tempQue.stream().toArray(Location[]::new);
	}

	private static void solve(int depth) {
		if (depth == coreCount) {
			if (maxSelectedCoreCount < selectedCoreCount) {
				maxSelectedCoreCount = selectedCoreCount;
				minWire = selectedWire;
				return;
			} else if(maxSelectedCoreCount ==selectedCoreCount ) {
				minWire = Math.min(minWire, selectedWire);
				return ;
			}
			return ;
		}

			Location loc = cores[depth];
			for (int d = 0; d < 4; d++) {
				if (isPutWire(loc.r, loc.c, d)) {
					putWire(loc.r, loc.c, d);
					selectedCoreCount++;
					solve(depth + 1);
					selectedCoreCount--;
					removeWire(loc.r, loc.c, d);
				}
			}
			solve(depth + 1);
	}

	private static boolean isOutLinerCore(int r, int c) {
		return r == 0 || c == 0 || r == mapSize - 1 || c == mapSize - 1;
	}

	/**
	 * core 기준으로 주워진 방향에 선을 깔음.
	 * 
	 * @param r
	 * @param c
	 * @param dir
	 */
	private static void putWire(int r, int c, int dir) {
		int nr = r + dr[dir];
		int nc = c + dc[dir];
		int wireCount = 0;
		while (true) {
			if (!isInBound(nr, nc)) {
				selectedWire+=wireCount;
				return;
			}
			map[nr][nc] = -1;
			wireCount++;
			nr += dr[dir];
			nc += dc[dir];
		}
	}

	/**
	 * core 기준으로 주워진 방향에 선을 제거함.
	 * 
	 * @param r
	 * @param c
	 * @param dir
	 */
	private static void removeWire(int r, int c, int dir) {
		int nr = r + dr[dir];
		int nc = c + dc[dir];
		int wireCount = 0;
		while (true) {
			if (!isInBound(nr, nc)) {
				selectedWire-=wireCount;
				return;
			}
			map[nr][nc] = 0;
			wireCount++;
			nr += dr[dir];
			nc += dc[dir];
		}
	}

	/**
	 * core 기준 주워진 방향으로 선을 깔 수 있는지 확인함.
	 * 
	 * @param r
	 * @param c
	 * @param dir
	 * @return
	 */
	private static boolean isPutWire(int r, int c, int dir) {
		int nr = r + dr[dir];
		int nc = c + dc[dir];
		while (true) {
			if (!isInBound(nr, nc))
				return true;
			if (map[nr][nc] != 0)
				return false;
			nr += dr[dir];
			nc += dc[dir];
		}
	}

	private static boolean isInBound(int r, int c) {
		return r > -1 && c > -1 && mapSize > r && mapSize > c;
	}
}