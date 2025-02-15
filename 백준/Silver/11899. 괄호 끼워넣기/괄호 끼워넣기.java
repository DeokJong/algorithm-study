import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Stack;

public class Main {
	private static Stack<String> stack = new Stack<>();

	public static void main(String[] args) throws IOException {
		solve();
	}

	private static void solve() throws IOException {
		String[] inputs = (new BufferedReader(new InputStreamReader(System.in))).readLine().split("");
		for (String input : inputs) {
			if (stack.isEmpty()) {
				stack.add(input);
				continue;
			}

			if (!stack.peek().equals(input) && input.equals(")")) {
				stack.pop();
				continue;
			}

			stack.add(input);

		}
		System.out.println(stack.size());
	}
}