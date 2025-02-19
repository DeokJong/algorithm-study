import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Deque;
import java.util.Stack;

public class Main {
	private static Deque<String> stack = new ArrayDeque<>();
	public static void main(String[] args) throws IOException {
		solve();
	}

	private static void solve() throws IOException {
		String[] inputs = (new BufferedReader(new InputStreamReader(System.in))).readLine().split("");
		for (String input : inputs) {
			if (stack.isEmpty()) {
				stack.push(input);
				continue;
			}

			if (!stack.peek().equals(input) && input.equals(")")) {
				stack.pop();
				continue;
			}

			stack.push(input);

		}
		System.out.println(stack.size());
	}
}