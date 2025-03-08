import static java.lang.Integer.parseInt;
import java.io.*;
import java.util.*;

public class Main {

	private static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	private static int dp[];
	private static int num;

	public static void main(String[] args) throws NumberFormatException, IOException {
		int T = parseInt(br.readLine());
		for (int t = 0; t < T; t++) {
			initializeProblem();
			solve();
		}

	}

	private static void initializeProblem() throws NumberFormatException, IOException {
		num = parseInt(br.readLine());
		dp = new int[num + 1];
		dp[0] = 1;
	}

	private static void solve() {
		int[] coins = {1, 2, 3};
    for (int coin : coins) {
        for (int i = coin; i <=  num; i++) {
            dp[i] += dp[i - coin];
        }
    }

		System.out.println(dp[num]);
	}

}
