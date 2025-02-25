import static java.lang.Integer.parseInt;

import java.util.*;
import java.io.*;

public class Main {
	private static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	private static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
	private static int[][] matrix;
	private static int[][] dp;

	public static void main(String[] args) throws IOException {
		solve();

		br.close();
		bw.close();
	}

	private static void solve() throws IOException {
		StringTokenizer st = new StringTokenizer(br.readLine());
		int matrixSize = parseInt(st.nextToken());
		int M = parseInt(st.nextToken());

		matrix = new int[matrixSize + 1][matrixSize + 1];
		dp = new int[matrixSize + 1][matrixSize + 1];

		for (int i = 1; i <= matrixSize; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 1; j <= matrixSize; j++) {
				matrix[i][j] = parseInt(st.nextToken());
			}
		}

		for (int i = 1; i <= matrixSize; i++) {
			for (int j = 1; j <= matrixSize; j++) {
				dp[i][j] = matrix[i][j] + dp[i - 1][j] + dp[i][j - 1] - dp[i - 1][j - 1];
			}
		}

		for (int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine());
			int x1 = parseInt(st.nextToken());
			int y1 = parseInt(st.nextToken());
			int x2 = parseInt(st.nextToken());
			int y2 = parseInt(st.nextToken());

			bw.append(String.valueOf(dp[x2][y2] - dp[x1 - 1][y2] - dp[x2][y1 - 1] + dp[x1 - 1][y1 - 1])).append('\n');
		}
	}
}
