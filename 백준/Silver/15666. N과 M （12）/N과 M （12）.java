import java.util.Scanner;
import java.util.Set;
import java.util.TreeSet;

public class Main {
	private static Scanner sc = new Scanner(System.in);
	private static int N, M;
	private static int[] integer;
	private static int[] selectedInteger;
	private static StringBuilder sb = new StringBuilder();

	public static void main(String[] args) {
		initializeProblem();
		solve(0, 0);
		System.out.println(sb.toString());
	}

	private static void initializeProblem() {
		N = sc.nextInt();
		M = sc.nextInt();
		sc.nextLine();

		Set<Integer> set = new TreeSet<>();
		for (int i = 0; i < N; i++) {
			set.add(sc.nextInt());
		}

		integer = set.stream().mapToInt(Integer::intValue).toArray();
		selectedInteger = new int[M];
	}

	private static void solve(int depth, int startIdx) {
		if (depth == M) {
			for (int item : selectedInteger) {
				sb.append(item).append(' ');
			}
			sb.append('\n');
			return;
		}

		for (int i = startIdx; i < integer.length; i++) {
			selectedInteger[depth] = integer[i];
			solve(depth + 1, i); // 같은 숫자 중복 가능
		}
	}
}
