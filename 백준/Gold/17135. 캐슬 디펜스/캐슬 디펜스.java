import static java.lang.Integer.parseInt;

import java.io.*;
import java.util.*;

public class Main {
	static class Location implements Comparable<Location> {
		int r, c, distance;

		@Override
		public int compareTo(Location o) {
			int comp = Integer.compare(this.distance, o.distance);

			if (comp != 0)
				return comp;

			return Integer.compare(this.c, o.c);
		}

		public Location(int r, int c, int distance) {
			super();
			this.r = r;
			this.c = c;
			this.distance = distance;
		}

		public int getDistance(Location o) {
			return Math.abs(this.r - o.r) + Math.abs(this.c - o.c);
		}

		public int getDistance(int r, int c) {
			return Math.abs(this.r - r) + Math.abs(this.c - c);
		}
	}

	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static int r, c, range;
	static int[][] globalMap;

	public static void main(String[] args) throws NumberFormatException, IOException {
		initializeProblem();
		System.out.println(solve(0, -1, 0));
	}

	static void initializeProblem() throws NumberFormatException, IOException {
		StringTokenizer st = new StringTokenizer(br.readLine());
		r = parseInt(st.nextToken());
		c = parseInt(st.nextToken());
		range = parseInt(st.nextToken());

		globalMap = new int[r][c];
		for (int i = 0; i < r; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < c; j++) {
				globalMap[i][j] = parseInt(st.nextToken());
			}
		}
	}

	static int solve(int depth, int prevIdx, int mask) {
		if (depth == 3) {
			return play(mask);
		}

		int ans = 0;

		for (int i = prevIdx + 1; i < c; i++) {
			int cutBit = 1 << i;
			ans = Math.max(ans, solve(depth + 1, i, mask | cutBit));
		}

		return ans;
	}

	static int play(int mask) {
		// 게임 초기화 작업.
		int[][] map = cloneMap();
		List<Location> archer = new ArrayList<>();
		int catchMonster = 0;
		for (int i = 0; i < 3; i++) {
			int smallest = mask & -mask;
			archer.add(new Location(r, Integer.numberOfTrailingZeros(smallest), 0));
			mask -= smallest;
		}

		// 턴수는 r만큼에 해당함
		for (int turn = 0; turn < r; turn++) {
			// 턴마다 타겟 큐를 새로 생성함.
			List<PriorityQueue<Location>> archerTargetQueue = new ArrayList<>();
			for (int atq = 0; atq < 3; atq++)
				archerTargetQueue.add(new PriorityQueue<>());

			// 맵 전체를 돌면서 각 아처의 타겟에다가 타겟 큐를 삽입.
			// 맨 아래부터 시작해서 탐색함.
			for (int i = r - 1; i >= turn; i--) {
				for (int j = 0; j < c; j++) {
					if (map[i][j] == 0)
						continue;
					for (int ar = 0; ar < 3; ar++) {
						archerTargetQueue.get(ar).add(new Location(i, j, archer.get(ar).getDistance(i, j)));
					}
				}
			}

			int emptyCount = 0;
			for (int ar = 0; ar < 3; ar++) {
				if (archerTargetQueue.get(ar).isEmpty())
					emptyCount++;
			}
			if (emptyCount == 3)
				return catchMonster;

			for (int ar = 0; ar < 3; ar++) {
				PriorityQueue<Location> tempPQ = archerTargetQueue.get(ar);
				if (!tempPQ.isEmpty() && tempPQ.peek().distance > range)
					continue;

				int r = tempPQ.peek().r;
				int c = tempPQ.peek().c;
				if (map[r][c] != 0) {
					catchMonster++;
					map[r][c] = 0;
				}

			}

			cleanUpTurn(map, turn);
		}

		return catchMonster;
	}

	private static int[][] cloneMap() {
		int[][] cloneMap = globalMap.clone();
		for (int i = 0; i < globalMap.length; i++) {
			cloneMap[i] = globalMap[i].clone();
		}
		return cloneMap;
	}

	/**
	 * 턴에 해당하는 row부터 밑으로 내림
	 * 
	 * @param map
	 * @param turn 턴은 0번째부터 시작
	 * @return
	 */
	private static void cleanUpTurn(int[][] map, int turn) {
		for (int i = r - 1; i > turn; i--) {
			map[i] = map[i - 1].clone();
		}

		map[turn] = new int[c];
	}
}
