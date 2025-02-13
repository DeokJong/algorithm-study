import static java.lang.Integer.parseInt;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
	private static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	private static int[] arr;
	private static int[] dp;
	private static int MAX;

	public static void main(String[] args) throws NumberFormatException, IOException {
		initializeProblem();
		solve();
		System.out.println(MAX);
	}

	public static void initializeProblem() throws NumberFormatException, IOException {
		int length = parseInt(br.readLine().trim());

		arr = new int[length];
		dp = new int[length];

		StringTokenizer st = new StringTokenizer(br.readLine().trim());
		for (int i = 0; i < length; i++) {
			arr[i] = dp[i] = parseInt(st.nextToken());
		}
	}

	public static void solve() {
		for (int i = 0; i < arr.length; i++) {
			for (int j = 0; j < i; j++) {
				if (arr[i] > arr[j]) {
					dp[i] = Math.max(dp[i], dp[j] + arr[i]);
				}
			}
			MAX = Math.max(MAX, dp[i]);
		}
	}
}