import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
	private static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	private static int[] arr;

	public static void main(String[] args) throws NumberFormatException, IOException {
		initializeProblem();
		solve();
	}

	private static void initializeProblem() throws NumberFormatException, IOException {
		int n = Integer.parseInt(br.readLine().trim());
		arr = new int[n];
		StringTokenizer st = new StringTokenizer(br.readLine());
		for (int i = 0; i < n; i++) {
			arr[i] = Integer.parseInt(st.nextToken());
		}
	}

	private static void solve() {
		int[] lis = new int[arr.length];
		int length = 0;

		for (int num : arr) {
			int insertIdx = Arrays.binarySearch(lis, 0, length, num);

			if (insertIdx < 0) {
				insertIdx = -(insertIdx + 1);
			}

			lis[insertIdx] = num;

			if (insertIdx == length) {
				length++;
			}
		}

		System.out.println(length);
	}

}
