import static java.lang.Integer.parseInt;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;
import java.util.*;

public class Main {
	private static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	private static int hegith;
	private static int width;
	private static int[][] map;
	private static int[][] dp;

	public static void main(String[] args) throws IOException {
		initializeProblem();
		System.out.println(solve());
	}

	private static void initializeProblem() throws IOException {
		StringTokenizer st = new StringTokenizer(br.readLine());
		hegith = parseInt(st.nextToken());
		width = parseInt(st.nextToken());
		map = new int[hegith][width];

		for (int r = 0; r < hegith; r++) {
			String[] inputs = br.readLine().trim().split("");
			for (int c = 0; c < width; c++) {
				map[r][c] = parseInt(inputs[c]);
			}
		}

		dp = new int[hegith + 1][width + 1];

		for (int i = 1; i <= hegith; i++) {
			for (int j = 1; j <= width; j++) {
				dp[i][j] = dp[i - 1][j] + dp[i][j - 1] - dp[i - 1][j - 1] + map[i - 1][j - 1];
			}
		}
	}

	private static int solve() {
		int low = 1;
		int high = Math.min(width, hegith);
		int maxSize = Integer.MIN_VALUE;

		while (low <= high) {
			int mid = (low + high) / 2;
			if (isFindRectangle(mid)) {
				maxSize = Math.max(maxSize, mid);
				low = mid + 1;
			} else {
				high = mid - 1;
			}
		}

		return maxSize*maxSize;
	}

	private static boolean isFindRectangle(int size) {
		for (int i = size; i <= hegith; i++) {
			for (int j = size; j <= width; j++) {
				if (size * size == dp[i][j] - dp[i][j - size] - dp[i - size][j] + dp[i - size][j - size])
					return true;
			}
		}
		return false;
	}
}
