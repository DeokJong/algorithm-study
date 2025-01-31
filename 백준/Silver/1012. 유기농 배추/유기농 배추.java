import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;

import static java.lang.Integer.parseInt;

public class Main {
	public static class Coordinate {
		int r, c;
		public Coordinate(int r, int c) {
			super();
			this.r = r;
			this.c = c;
		}

	}

	private static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	private static StringBuilder sb = new StringBuilder();
	private static int T;

	private static int N, M, cabbageCount;
	private static boolean[][] notVistedMatrix;
	private static int minEarthworm;
	private static Queue<Coordinate> queue = new ArrayDeque<>();
	private static int[] dr = { 0, 0, 1, -1 };
	private static int[] dc = { 1, -1, 0, 0 };

	public static void main(String[] args) throws IOException {
		T = parseInt(br.readLine().trim());
		for (int i = 0; i < T; i++) {
			initializeProblem();
			solve();
		}
    System.out.println(sb);
	}

	private static void initializeProblem() throws IOException {
		String[] input = br.readLine().trim().split(" ");
		N = parseInt(input[0]);
		M = parseInt(input[1]);
		cabbageCount = parseInt(input[2]);

		notVistedMatrix = new boolean[N][M];
		minEarthworm = 0;

		for (int i = 0; i < cabbageCount; i++) {
			input = br.readLine().trim().split(" ");
			notVistedMatrix[parseInt(input[0])][parseInt(input[1])] = true;
		}
	}

	private static void solve() {
		for (int r = 0; r < N; r++) {
			for (int c = 0; c < M; c++) {
				if(notVistedMatrix[r][c]) {
					queue.add(new Coordinate(r, c));
					notVistedMatrix[r][c] = false;
					bfs();
				}
			}
		}
		sb.append(minEarthworm).append("\n");
	}
	
	private static void bfs() {
		minEarthworm++;
		while (!queue.isEmpty()) {
			Coordinate coordinate = queue.poll();
			int r = coordinate.r;
			int c = coordinate.c;

			
			for(int i =0;i<4;i++) {
				if(isInBound(r+dr[i], c + dc[i]) &&
						notVistedMatrix[r+dr[i]][c + dc[i]]) {
					notVistedMatrix[r+dr[i]][c + dc[i]] = false;
					queue.add(new Coordinate(r+dr[i], c + dc[i]));
				}
			}
		}
	}

	private static boolean isInBound(int r, int c) {
		return r > -1 && c > -1 && r < N && c < M;
	}
}
