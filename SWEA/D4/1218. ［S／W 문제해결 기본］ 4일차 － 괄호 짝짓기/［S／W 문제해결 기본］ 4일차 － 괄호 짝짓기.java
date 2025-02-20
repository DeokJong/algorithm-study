import static java.lang.Integer.parseInt;

import java.util.*;
import java.io.*;

public class Solution {
	private static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	private static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

	public static void main(String[] args) throws IOException {
		for (int t = 1; t <= 10; t++) {
			char ans = solve() ? '1' : '0';
			bw.append('#').append(String.valueOf(t)).append(' ').append(ans).append('\n');
			bw.flush();
		}
		br.close();
		bw.close();

//		System.out.printf("%d %d %d %d %d %d %d %d", (int) '(', (int) ')', (int) '[', (int) ']', (int) '{', (int) '}',
//				(int) '<', (int) '>');
	}

	private static boolean solve() throws IOException {
		br.readLine();
		char[] inputs = br.readLine().toCharArray();
		ArrayDeque<Character> stack = new ArrayDeque<>();

		if (inputs.length % 2 == 1) {
			return false;
		}

		for (char e : inputs) {
			if (e == ')' || e == ']' || e == '}' || e == '>') {
				if (stack.isEmpty()) {
					return false;
				}

				if (stack.peek() == (e == ')' ? e - 1 : e - 2)) {
					stack.pop();
				} else {
					return false;
				}

			} else {
				stack.push(e);
			}
		}

		return stack.size() == 0;
	}

}
