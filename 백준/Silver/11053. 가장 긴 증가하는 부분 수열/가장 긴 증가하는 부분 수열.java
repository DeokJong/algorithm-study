import java.util.*;

public class Main {
	private static Scanner sc = new Scanner(System.in);
	private static int[] arr;

	public static void main(String[] args) {
		initializeProblem();
		solve();
	}

	private static void initializeProblem() {
		int n = Integer.parseInt(sc.nextLine().trim());
		arr = new int[n];

		for (int i = 0; i < n; i++) {
			arr[i] = sc.nextInt();
		}
	}

	private static void solve() {
		List<Integer> li = new ArrayList<>();

		for (int i = 0; i < arr.length; i++) {
			int insertIdx = Collections.binarySearch(li, arr[i]);

			if (insertIdx < 0) {
				insertIdx = -(insertIdx + 1);
			}

			if (insertIdx == li.size()) {
				li.add(arr[i]);
			} else {
				li.set(insertIdx, arr[i]);
			}
		}

		System.out.println(li.size());
	}

}
