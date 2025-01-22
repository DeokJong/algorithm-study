import java.util.Arrays;
import java.util.Scanner;

public class Main {
	private static Scanner sc = new Scanner(System.in);
	private static int[] height = new int[9];
	private static final int TARGET = 100;
	private static int TOTAL;
	private static StringBuilder sb = new StringBuilder();
	public static void main(String[] args) {
		initializeProblem();
		solve();
		
		for(int item : height) {
			if (item != 0) sb.append(item + "\n");
		}
		
		System.out.println(sb.toString());
		
		sc.close();
	}

	private static void initializeProblem() {
		for (int i = 0; i < height.length; i++) {
			height[i] = sc.nextInt();
			sc.nextLine();
		}

		height = Arrays.stream(height).sorted().toArray();
		TOTAL = Arrays.stream(height).sum();
	}

	private static void solve() {
		for (int i = 0; i < 9; i++) {
			for (int j = 0; j < 9; j++) {
				if(i == j) continue;
				
				if(TOTAL - height[i] - height[j] ==TARGET) {
					height[i] = 0;
					height[j] = 0;
					return ;
				}
			}
		}
	}
}
