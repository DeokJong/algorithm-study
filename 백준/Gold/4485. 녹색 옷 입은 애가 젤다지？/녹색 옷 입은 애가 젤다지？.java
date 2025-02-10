import java.util.PriorityQueue;
import java.util.Scanner;

public class Main {
	public static class Location implements Comparable<Location> {
		int x, y, rupee;

		public Location(int x, int y, int rupee) {
			this.x = x;
			this.y = y;
			this.rupee = rupee;
		}

		@Override
		public int compareTo(Location o) {
			return Integer.compare(this.rupee, o.rupee);
		}
	}

	private static Scanner sc = new Scanner(System.in);
	private static int mapSize;
	private static int[][] map;
	private static boolean[][] visited;
	private static PriorityQueue<Location> pq;
	private static int[] dr = { 0, 0, -1, 1 };
	private static int[] dc = { -1, 1, 0, 0 };

	public static void main(String[] args) {
		int iter = 1;
		while (true) {
			mapSize = sc.nextInt();
			if (mapSize == 0)
				break;
			initializeProblem();
			System.out.printf("Problem %d: %d\n", iter++, solve());
		}
	}

	private static void initializeProblem() {
		map = new int[mapSize][mapSize];
		visited = new boolean[mapSize][mapSize];
		pq = new PriorityQueue<>();

		for (int i = 0; i < mapSize; i++) {
			for (int j = 0; j < mapSize; j++) {
				map[i][j] = sc.nextInt();
			}
		}

		pq.add(new Location(0, 0, map[0][0]));
	}

	private static int solve() {
		while (!pq.isEmpty()) {
			Location loc = pq.poll();
			int x = loc.x;
			int y = loc.y;
			int cost = loc.rupee;

			if (visited[x][y])
				continue;

			visited[x][y] = true;

			if (x == mapSize - 1 && y == mapSize - 1) {
				return cost;
			}

			for (int d = 0; d < 4; d++) {
				int nr = x + dr[d];
				int nc = y + dc[d];

				if (isInBound(nr, nc)) {
					int nextCost = cost + map[nr][nc];
					pq.add(new Location(nr, nc, nextCost));
				}
			}
		}
		return -1;
	}

	private static boolean isInBound(int r, int c) {
		return r >= 0 && c >= 0 && r < mapSize && c < mapSize && !visited[r][c];
	}
}
