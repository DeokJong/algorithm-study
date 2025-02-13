import static java.lang.Integer.parseInt;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.StringTokenizer;

public class Solution {
	private static int[] dr = { 0, 0, -1, 1 };
	private static int[] dc = { 1, -1, 0, 0 };

	private static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	private static int[][] map;
	private static int remainBrick;
	private static int minRemainBrick;
	private static int shotCount;
	private static int width;
	private static int height;

	public static void main(String[] args) throws NumberFormatException, IOException {
		int T = parseInt(br.readLine().trim());
		for (int i = 1; i <= T; i++) {
			initializeProblem();
			solve(shotCount);
			System.out.printf("#%d %d\n", i, minRemainBrick);
		}
	}

	private static void initializeProblem() throws IOException {
		StringTokenizer st = new StringTokenizer(br.readLine().trim());
		remainBrick = 0;
		minRemainBrick = Integer.MAX_VALUE;
		shotCount = parseInt(st.nextToken());
		width = parseInt(st.nextToken());
		height = parseInt(st.nextToken());
		map = new int[height][width];

		for (int r = 0; r < height; r++) {
			st = new StringTokenizer(br.readLine().trim());
			for (int c = 0; c < width; c++) {
				map[r][c] = parseInt(st.nextToken());
				if (map[r][c] != 0)
					remainBrick++;
			}
		}
	}

	private static void solve(int shotCount) {
		if (remainBrick == 0) {
			minRemainBrick = 0;
			return;
		}
		if (shotCount == 0) {
			minRemainBrick = Math.min(minRemainBrick, remainBrick);
			return;
		}

		for (int c = 0; c < width; c++) {
			int[][] storedMap = map.clone();
			for(int l =0;l<storedMap.length;l++) {
				storedMap[l] = map[l].clone();
			}
			int storedRemainbrick = remainBrick;
			
			int targetR = -1;
			for (int r = 0; r < height; r++) {
				if (map[r][c] == 0)
					continue;
				targetR = r;
				break;
			}
			if (targetR == -1)
				continue;

			int brickNumber = map[targetR][c];
			removeBrick(targetR, c, brickNumber);
			shiftDownEmptySpaces();
			// show();
			solve(shotCount - 1);

			map = storedMap;
			remainBrick = storedRemainbrick;
		}
	}

	/**
	 * 위치와 벽돌 숫자를 받음. 해당 위치는 일단 제거. 벽돌 숫자가 2 이상이면 사방 제거. 벽돌 숫자가 1이면 단일 제거
	 * 
	 * @param r
	 * @param c
	 * @param brickNumber
	 */
	private static void removeBrick(int r, int c, int brickNumber) {
		if (brickNumber == 0)
			return;
		map[r][c] = 0;
		remainBrick--;
		if (brickNumber >= 2) {
			removeCrossBrick(r, c, brickNumber - 1);
		}
	}

	/**
	 * 주워진 점의 4방향 제거. 주워진 점은 제거 안함. count 만큼 주변을 제거
	 * 
	 * @param r
	 * @param c
	 * @param count
	 */
	private static void removeCrossBrick(int r, int c, int count) {
		for (int dir = 0; dir < 4; dir++) {
			removeOneLineBrick(r, c, dir, count);
		}
	}

	/**
	 * 주워진 점의 다음 방향만 제거. 주워진 점은 제거 안함
	 * 
	 * @param r
	 * @param c
	 * @param dir
	 * @param count
	 */
	private static void removeOneLineBrick(int r, int c, int dir, int count) {
		int nr = r;
		int nc = c;
		while (count != 0) {
			nr += dr[dir];
			nc += dc[dir];
			if (!isInBound(nr, nc))
				return;
			removeBrick(nr, nc, map[nr][nc]);
			count--;
		}
	}

	/**
	 * 빈 공간 없이 나머지를 밑으로 내리는 함수
	 */
	private static void shiftDownEmptySpaces() {
		Queue<Integer> tempQue = new ArrayDeque<>();
		for (int c = 0; c < width; c++) {
			tempQue.clear();
			for (int r = height - 1; r > -1; r--) {
				if (map[r][c] != 0) {
					tempQue.add(map[r][c]);
					map[r][c] = 0;
				}
			}
			for (int r = height - 1; r > -1; r--) {
				if (tempQue.isEmpty()) {
					break;
				} else {
					map[r][c] = tempQue.poll();
				}
			}
		}
	}

	private static boolean isInBound(int r, int c) {
		return r > -1 && c > -1 && height > r && width > c;
	}
	
	private static void show() {
		System.out.println("===========================");
		for(int r =0;r<height;r++) {
			for(int c = 0;c<width;c++) {
				System.out.printf("%d ",map[r][c]);
			}
			System.out.println();
		}
		System.out.println("\n");
	}
}