import static java.lang.Integer.parseInt;
import java.io.*;
import java.util.*;

public class Solution {
	private static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	private static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
	private static int N;
	private static int limit;
	private static int[][] materials;
	private static int[] dp;

	public static void main(String[] args) throws NumberFormatException, IOException {
		int T = parseInt(br.readLine());
		for (int i = 1; i <= T; i++) {
			initializeProblem();
			int ans = solve(0, 0, 0);
			bw.append('#').append(String.valueOf(i)).append(' ').append(String.valueOf(ans)).append('\n');
			bw.flush();
		}
		bw.close();
		br.close();
	}

	private static void initializeProblem() throws IOException {
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = parseInt(st.nextToken());
		limit = parseInt(st.nextToken());
		materials = new int[N][2];

		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			materials[i][0] = parseInt(st.nextToken());
			materials[i][1] = parseInt(st.nextToken());
		}

		dp = new int[1 << N];
		Arrays.fill(dp, -1);
	}

	private static int solve(int mask, int calorie, int score) {
		if (calorie > limit) {
			return 0;
		}

		if (mask == (1 << N) - 1) {
			return score;
		}

		if (dp[mask] != -1) {
			return dp[mask];
		}

		int tempScore = score;
		for (int i = 0; i < N; i++) {
			int curBit = 1 << i;
			if ((mask & curBit) != 0)
				continue;

			int curCalorie = materials[i][1];
			int curScore = materials[i][0];

			tempScore = Math.max(tempScore, solve(mask | curBit, calorie + curCalorie, score + curScore));
		}

		dp[mask] = tempScore;
		return tempScore;
	}
}
