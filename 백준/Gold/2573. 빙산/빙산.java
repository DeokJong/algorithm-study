import static java.lang.Integer.parseInt;

import java.io.*;
import java.util.*;

public class Main {
	private static int[] dr = { 0, 0, -1, 1 };
	private static int[] dc = { -1, 1, 0, 0 };

	static class Location {
		int r, c, time, size;

		public Location(int r, int c, int time, int size) {
			super();
			this.r = r;
			this.c = c;
			this.time = time;
			this.size = size;
		}

	}

	private static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

	private static int N, M;
	private static ArrayDeque<Location> que;
	private static int[][] map;

	public static void main(String[] args) throws Exception {
		initializeProblem();
		System.out.println(solve());
	}

	private static void initializeProblem() throws IOException {
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = parseInt(st.nextToken());
		M = parseInt(st.nextToken());

		que = new ArrayDeque<>();
		map = new int[N][M];
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < M; j++) {
				map[i][j] = parseInt(st.nextToken());
				if (map[i][j] != 0) {
					que.add(new Location(i, j, 0, map[i][j]));
				}
			}
		}
	}

	private static int solve() {

		while (true) {
			if (que.isEmpty()) {
				return 0;
			}

			if (BFS(que.peek().r, que.peek().c) != que.size()) {
				return que.peek().time;
			}
			melt();
		}
	}

	private static void melt() {
		if (que.isEmpty()) {
			return;
		}

		int curTime = que.peek().time;
		Deque<Location> updateQue = new ArrayDeque<Main.Location>();
		while (!que.isEmpty() && que.peek().time == curTime) {
			Location l = que.poll();
			int size = l.size;

			for (int d = 0; d < 4; d++) {
				int nr = l.r + dr[d];
				int nc = l.c + dc[d];

				if (map[nr][nc] == 0) {
					size--;
				}
			}

			l.time += 1;
			l.size = size;
			if (size > 0) {
				que.add(l);
			}

			updateQue.add(l);
		}

		while (!updateQue.isEmpty()) {
			Location l = updateQue.poll();
			int r = l.r;
			int c = l.c;
			int size = l.size;

			map[r][c] = size <= 0 ? 0 : size;
		}

//		for(int[] r : map) {
//			for(int c : r) {
//				System.out.printf("%d ",c);
//			}
//			System.out.println();
//		}
//		System.out.println();

	}

	private static int BFS(int r, int c) {
		boolean[][] visited = new boolean[N][M];
		int count = 0;
		Deque<Location> traceQue = new ArrayDeque<Main.Location>();
		traceQue.add(new Location(r, c, 0, 0));

		while (!traceQue.isEmpty()) {
			Location l = traceQue.poll();
			int x = l.r;
			int y = l.c;

			if (visited[x][y])
				continue;
			visited[x][y] = true;
			count++;

			for (int d = 0; d < 4; d++) {
				int nr = x + dr[d];
				int nc = y + dc[d];

				if (map[nr][nc] != 0 && !visited[nr][nc]) {
					traceQue.add(new Location(nr, nc, 0, 0));
				}
			}

		}

		return count;
	}
}
