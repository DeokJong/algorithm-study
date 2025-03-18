import static java.lang.Integer.parseInt;

import java.io.*;
import java.util.*;

public class Main {
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static StringBuilder sb = new StringBuilder();

	static int N, M;
	static int[] arr;
	static int[] prefixSum;

	public static void main(String[] args) throws Exception {
		initializeProblem();
		System.out.println(solve());
	}

	private static void initializeProblem() throws IOException {
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = parseInt(st.nextToken());
		M = parseInt(st.nextToken());

		arr = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();

		prefixSum = new int[N + 1];
		for (int i = 1; i <= N; i++) {
			prefixSum[i] = prefixSum[i - 1] + arr[i - 1];
		}
	}

	private static int solve() {
		int left = 0;
		int right = 0;

		for (int length : arr) {
			left = Math.max(left, length);
			right += length;
		}

		int ans = right;

		while (left <= right) {
			int mid = (left + right) / 2;

			if (isDivide(mid)) {
				ans = mid;
				right = mid - 1;
			} else {
				left = mid + 1;
			}
		}

		return ans;
	}

	private static boolean isDivide(int blueRaySize) {
		int count = 1;
		int sum = 0;

		for (int i = 0; i < N; i++) {
			if (sum + arr[i] > blueRaySize) {
				count++;
				sum = arr[i];
				if (count > M)
					return false;
			} else {
				sum += arr[i];
			}
		}

		return true;
	}
}
