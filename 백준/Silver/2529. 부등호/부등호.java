import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
	private static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	private static int[] arr;
	private static int LTSize;
	private static boolean[] LT;
	private static String MAX_S;
	private static String MIN_S;
	private static long MAX = Long.MIN_VALUE;
	private static long MIN = Long.MAX_VALUE;

	public static void main(String[] args) throws NumberFormatException, IOException {
		initializeProblem();
		solve(0, 0);
		System.out.println(MAX_S);
		System.out.println(MIN_S);
	}

	private static void initializeProblem() throws NumberFormatException, IOException {
		LTSize = Integer.parseInt(br.readLine());
		arr = new int[LTSize + 1];

		LT = new boolean[LTSize];
		StringTokenizer st = new StringTokenizer(br.readLine());
		for (int i = 0; i < LTSize; i++) {
			if (st.nextToken().equals("<"))
				LT[i] = true;
		}
	}

	private static void solve(int mask, int depth) {
		if (depth == LTSize + 1) {
			long temp = arrToInt();
			if (Long.compare(temp, MAX) > 0) {
				MAX = temp;
				MAX_S = arrToString();
			}
			if (Long.compare(MIN, temp) > 0) {
				MIN = temp;
				MIN_S = arrToString();
			}
			return;
		}

		for (int i = 0; i <= 9; i++) {
			int curBit = 1 << i;
			if ((mask & curBit) != 0)
				continue;

			if (depth == 0) {
				arr[depth] = i;
				solve(mask | curBit, depth + 1);
				continue;
			}

			if (LT[depth - 1] && arr[depth - 1] < i) {
				arr[depth] = i;
				solve(mask | curBit, depth + 1);
				continue;
			} else if (!LT[depth - 1] && arr[depth - 1] > i) {
				arr[depth] = i;
				solve(mask | curBit, depth + 1);
				continue;
			}

		}
	}

	private static long arrToInt() {
		StringBuilder sb = new StringBuilder();
		for (int e : arr) {
			sb.append(e);
		}

		return Long.parseLong(sb.toString());
	}

	private static String arrToString() {
		StringBuilder sb = new StringBuilder();
		for (int e : arr) {
			sb.append(e);
		}

		return sb.toString();
	}
}
