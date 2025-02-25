import static java.lang.Integer.parseInt;

import java.util.*;
import java.io.*;

public class Main {
	private static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	private static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
	private static int[] dp;

	public static void main(String[] args) throws IOException {
		initializeProblem();

		br.close();
		bw.close();
	}

	private static void initializeProblem() throws IOException {
		String[] inputs = br.readLine().trim().split(" ");
		int N = parseInt(inputs[0]);
		int M = parseInt(inputs[1]);

		dp = new int[N + 1];
		StringTokenizer st = new StringTokenizer(br.readLine());
		for (int i = 1; i <= N; i++) {
			dp[i] = dp[i - 1] + parseInt(st.nextToken());
		}

		for (int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine());
			bw.append(String.valueOf((-dp[parseInt(st.nextToken()) - 1]) + dp[parseInt(st.nextToken())])).append('\n');
		}
	}
}
