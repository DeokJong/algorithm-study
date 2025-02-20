import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;
import java.util.*;

public class Main {
	private static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	private static int mapSize;
	private static int[][] map;
	private static Map<Integer, List<Integer>> studentMap = new HashMap<>();
	private static List<Integer> order = new ArrayList<>();

	private static final int[] dr = { -1, 1, 0, 0 };
	private static final int[] dc = { 0, 0, -1, 1 };

	public static void main(String[] args) throws IOException {
		initializeProblem();
		solve();
		System.out.println(computeSatisfaction());
	}

	private static void initializeProblem() throws IOException {
		mapSize = Integer.parseInt(br.readLine());
		map = new int[mapSize][mapSize];
		int totalStudents = mapSize * mapSize;
		for (int i = 0; i < totalStudents; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			int student = Integer.parseInt(st.nextToken());
			order.add(student);
			List<Integer> favList = new ArrayList<>();
			for (int j = 0; j < 4; j++) {
				favList.add(Integer.parseInt(st.nextToken()));
			}
			studentMap.put(student, favList);
		}
	}

	private static void solve() {

		for (int idx = 0; idx < order.size(); idx++) {
			int student = order.get(idx);
			int bestLike = -1;
			int bestEmpty = -1;
			int bestRow = -1;
			int bestCol = -1;

			for (int i = 0; i < mapSize; i++) {
				for (int j = 0; j < mapSize; j++) {
					if (map[i][j] != 0)
						continue;

					int likeCount = 0;
					int emptyCount = 0;
					for (int d = 0; d < 4; d++) {
						int ni = i + dr[d];
						int nj = j + dc[d];
						if (ni < 0 || ni >= mapSize || nj < 0 || nj >= mapSize)
							continue;
						if (map[ni][nj] == 0) {
							emptyCount++;
						} else if (studentMap.get(student).contains(map[ni][nj])) {
							likeCount++;
						}
					}

					if (likeCount > bestLike || (likeCount == bestLike && emptyCount > bestEmpty)
							|| (likeCount == bestLike && emptyCount == bestEmpty && i < bestRow)
							|| (likeCount == bestLike && emptyCount == bestEmpty && i == bestRow && j < bestCol)) {
						bestLike = likeCount;
						bestEmpty = emptyCount;
						bestRow = i;
						bestCol = j;
					}
				}
			}
			map[bestRow][bestCol] = student;
		}
	}

	private static int computeSatisfaction() {
		int total = 0;
		for (int r = 0; r < mapSize; r++) {
			for (int c = 0; c < mapSize; c++) {
				int student = map[r][c];
				int likeCount = 0;
				for (int d = 0; d < 4; d++) {
					int nr = r + dr[d];
					int nc = c + dc[d];
					if (!isInBound(nr, nc))
						continue;
					if (studentMap.get(student).contains(map[nr][nc])) {
						likeCount++;
					}
				}

				if (likeCount > 0) {
					total += Math.pow(10, likeCount - 1);
				}
			}
		}
		return total;
	}

	private static boolean isInBound(int r, int c) {
		return r > -1 && c > -1 && mapSize > r && mapSize > c;
	}
}
