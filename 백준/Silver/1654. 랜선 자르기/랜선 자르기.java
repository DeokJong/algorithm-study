import static java.lang.Integer.parseInt;
import java.io.*;
import java.util.*;

public class Main {

	private static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	private static int cableCount;
	private static int target;
	private static long[] cables;

	public static void main(String[] args) throws NumberFormatException, IOException {
		initializeProblem();
		solve();
	}

	private static void initializeProblem() throws NumberFormatException, IOException {
		StringTokenizer st = new StringTokenizer(br.readLine());
		cableCount = parseInt(st.nextToken());
		target = parseInt(st.nextToken());
		cables = new long[cableCount];
		for (int i = 0; i < cableCount; i++) {
			cables[i] = Long.parseLong(br.readLine());
		}
	}

	private static void solve() {
		long low = 1;
		long high = 0;
		for (long cable : cables) {
			if (cable > high) {
				high = cable;
			}
		}
		long answer = 0;
		while (low <= high) {
			long mid = (low + high) / 2;
			long count = 0;
			for (long cable : cables) {
				count += cable / mid;
			}
			if (count >= target) {
				answer = mid;
				low = mid + 1;
			} else {
				high = mid - 1;
			}
		}
		System.out.println(answer);
	}
}
