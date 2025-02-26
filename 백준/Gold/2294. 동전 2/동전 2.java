import static java.lang.Integer.parseInt;
import java.io.*;
import java.util.*;

public class Main {

	private static final BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	private static final BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
	private static final StringBuilder sb = new StringBuilder();
	private static int coinCount;
	private static int targetSum;
	private static int[] coins;
	private static int[] dp;

	public static void main(String[] args) throws IOException {
		initializeProblem();
		solve();
		System.out.println(sb);
		bw.close();
		br.close();
	}

	private static void initializeProblem() throws NumberFormatException, IOException {
		String[] sa = br.readLine().split(" ");
		coinCount = parseInt(sa[0]);
		targetSum = parseInt(sa[1]);

		coins = new int[coinCount];
		for (int i = 0; i < coinCount; i++) {
			coins[i] = parseInt(br.readLine());
		}

		dp = new int[targetSum + 1];
		Arrays.fill(dp, targetSum + 1);  
		dp[0] = 0;

	}

	private static void solve() throws IOException {
		for (int coinIdx = 0; coinIdx < coinCount; coinIdx++) {
			for (int i = coins[coinIdx]; i <= targetSum; i++) {
				if (dp[i - coins[coinIdx]] != targetSum + 1) { 
					dp[i] = Math.min(dp[i], dp[i - coins[coinIdx]] + 1);
				}
			}
		}
		sb.append(dp[targetSum] == targetSum + 1 ? -1 : dp[targetSum]);
	}
}
