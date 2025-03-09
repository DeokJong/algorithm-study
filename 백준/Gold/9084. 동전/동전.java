import static java.lang.Integer.parseInt;
import java.io.*;
import java.util.*;

public class Main {

	private static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	private static int dp[];
	private static int coins[];
	private static int target;
	private static int coinCount;

	public static void main(String[] args) throws NumberFormatException, IOException {
		int T = parseInt(br.readLine());
		for (int t = 0; t < T; t++) {
			initializeProblem();
			solve();
		}

	}

	private static void initializeProblem() throws NumberFormatException, IOException {
		StringTokenizer st = new StringTokenizer(br.readLine());
		coinCount = parseInt(st.nextToken());
		coins = new int[coinCount];
		st = new StringTokenizer(br.readLine());
		for (int i = 0; i < coinCount; i++) {
			coins[i] = parseInt(st.nextToken());
		}
		target = parseInt(br.readLine());

		dp = new int[target + 1];
		dp[0] = 1;
	}

	private static void solve() {
		for (int coin : coins) {
			for (int i = coin; i <= target; i++) {
				dp[i] += dp[i - coin];
			}
		}
		System.out.println(dp[target]);
	}

}
