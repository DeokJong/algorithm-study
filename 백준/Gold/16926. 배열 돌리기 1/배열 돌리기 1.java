import java.io.*;
import static java.lang.Integer.parseInt;
import java.util.*;

public class Main {
	private static int[] dr = { 1, 0, -1, 0 };
	private static int[] dc = { 0, 1, 0, -1 };

	private static final BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	private static final BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

	private static int hegiht, width, totalRotationCount;
	private static int[][] map;
	private static int[] layerRotationCount;

	public static void main(String[] args) throws IOException {
		initializeProblem();
		solve();

		bw.close();
		br.close();
	}

	private static void initializeProblem() throws NumberFormatException, IOException {
		StringTokenizer st = new StringTokenizer(br.readLine());
		hegiht = parseInt(st.nextToken());
		width = parseInt(st.nextToken());
		totalRotationCount = parseInt(st.nextToken());

		map = new int[hegiht][width];
		for (int i = 0; i < hegiht; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < width; j++) {
				map[i][j] = parseInt(st.nextToken());
			}
		}

		int r = hegiht;
		int c = width;
		List<Integer> tempMultiLi = new ArrayList<>();
		while (true) {
			if (r * c <= 0) {
				tempMultiLi.add(0);
				break;
			}
			tempMultiLi.add(r * c);
			r -= 2;
			c -= 2;
		}

		layerRotationCount = new int[tempMultiLi.size() - 1];
		for (int i = 0; i < tempMultiLi.size() - 1; i++) {
			layerRotationCount[i] = tempMultiLi.get(i) - tempMultiLi.get(i + 1);
		}
	}

	private static void solve() throws IOException {
		for (int i = 0; i < layerRotationCount.length; i++) {
			int curRotationCount = totalRotationCount % layerRotationCount[i];
			for (int rotationCount = 0; rotationCount < curRotationCount; rotationCount++) {
				rotationArr(i, i);
			}
		}
		
		for(int[] r : map) {
			for(int c: r) {
				bw.append(String.valueOf(c)).append(' ');
			}
			bw.append('\n');
		}
	}

	private static void rotationArr(int i, int j) {
		int lowerI = i - 1;
		int lowerJ = j - 1;
		int upperI = hegiht - i;
		int upperJ = width - j;

		// down
		int temp = map[i][j];
		int nr = i + dr[0];
		int nc = j + dc[0];
		while(isInBound(lowerI, lowerJ, upperI, upperJ, nr, nc)) {
			int whileTemp = map[nr][nc];
			map[nr][nc] = temp;
			temp = whileTemp;
			nr += dr[0];
			nc += dc[0];
		}
		nr -= dr[0];
		nc -= dc[0];
		
		// right
		nr += dr[1];
		nc += dc[1];
		while(isInBound(lowerI, lowerJ, upperI, upperJ, nr, nc)) {
			int whileTemp = map[nr][nc];
			map[nr][nc] = temp;
			temp = whileTemp;
			nr += dr[1];
			nc += dc[1];
		}
		nr -= dr[1];
		nc -= dc[1];
		
		// up
		nr+= dr[2];
		nc += dc[2];
		while(isInBound(lowerI, lowerJ, upperI, upperJ, nr, nc)) {
			int whileTemp = map[nr][nc];
			map[nr][nc] = temp;
			temp = whileTemp;
			nr += dr[2];
			nc += dc[2];
		}
		nr -= dr[2];
		nc -= dc[2];
		
		//left
		nr += dr[3];
		nc += dc[3];
		while(isInBound(lowerI, lowerJ, upperI, upperJ, nr, nc)) {
			int whileTemp = map[nr][nc];
			map[nr][nc] = temp;
			temp = whileTemp;
			nr += dr[3];
			nc += dc[3];
		}
	}

	private static boolean isInBound(int lowerI, int lowerJ, int upperI, int upperJ, int i, int j) {
		return i > lowerI && j > lowerJ && upperI > i && upperJ > j;
	}
}
