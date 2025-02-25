import static java.lang.Integer.parseInt;
import java.util.*;
import java.io.*;

public class Solution {
	private static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	private static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
	private static int numberSize;
	private static int[] nums;
	private static int[] remainOperators;
	private static int min;
	private static int max;
	private static MathOperators[] op = {
			Integer::sum,
			(a, b) -> a - b,
			(a, b) -> a * b,
			(a, b) -> b != 0 ? a / b : Integer.MAX_VALUE };
	
	public static void main(String[] args) throws Exception {
		int T = Integer.parseInt(br.readLine());
		for (int t = 1; t <= T; t++) {
			initializeProblem();
			solve(1,nums[0]);
			bw.append('#').append(String.valueOf(t)).append(' ').append(String.valueOf(max-min)).append('\n');
		}
		br.close();
		bw.close();
	}

	private static void initializeProblem() throws IOException {
		min = Integer.MAX_VALUE;
		max = Integer.MIN_VALUE;
		numberSize = parseInt(br.readLine());
		nums = new int[numberSize];
		remainOperators = new int[4];
		
		StringTokenizer st = new StringTokenizer(br.readLine());
		for(int i =0;i<4;i++) {
			remainOperators[i] = parseInt(st.nextToken());
		}

		
		
		st = new StringTokenizer(br.readLine());
		for(int i =0;i<numberSize;i++) {
			nums[i] = parseInt(st.nextToken());
		}
	}

	private static void solve(int depth, int result) {
		if(depth == numberSize) {
			max = Math.max(result, max);
			min = Math.min(result, min);
			return;
		}
		
		for(int i =0;i<4;i++) {
			if(remainOperators[i] != 0) {
				remainOperators[i]--;
				solve(depth+1, op[i].operate(result, nums[depth]));
				remainOperators[i]++;
			}
		}
	}

	
	@FunctionalInterface
	private interface  MathOperators{
		int operate(int a,int b);
	}
}
