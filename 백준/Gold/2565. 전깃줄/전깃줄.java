import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Comparator;
import java.util.StringTokenizer;

public class Main {
	private static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	private static int[] arr;
	private static int[] dp;
	private static int maxLength;

	public static void main(String[] args) throws NumberFormatException, IOException {
		initializeProblem();
		solve();
		System.out.println(arr.length-maxLength);
	}

	private static void solve() {
		for (int i = 1; i < arr.length; i++) {
			for (int j = 0; j < i; j++) {
				if (arr[i] > arr[j]) {
					dp[i] = Math.max(dp[i], dp[j]+1);
				}
			}
			maxLength = Math.max(dp[i], maxLength);
		}

	}

	private static void initializeProblem() throws NumberFormatException, IOException {
		int n = Integer.parseInt(br.readLine().trim());

		dp = new int[n];
		Arrays.fill(dp, 1);
		arr = new int[n];

		int[][] tempArr = new int[n][2];
		StringTokenizer st;
		for (int i = 0; i < n; i++) {
			st = new StringTokenizer(br.readLine().trim());
			tempArr[i][0] = Integer.parseInt(st.nextToken());
			tempArr[i][1] = Integer.parseInt(st.nextToken());
		}
		Arrays.sort(tempArr, new Comparator<int[]>() {

			@Override
			public int compare(int[] o1, int[] o2) {
				return o1[0] - o2[0];
			}
		});

		for (int i = 0; i < n; i++) {
			arr[i] = tempArr[i][1];
		}
	}
}