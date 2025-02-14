import java.util.Scanner;

public class Main {
	private static Scanner sc = new Scanner(System.in);
	private static int[][] dp;
	private static int digits;
	private static int sum = 17;

	public static void main(String[] args) {
		initializeProblem();
		if (digits == 1) {
			System.out.println(9);
		} else if (digits == 2) {
			System.out.println(17);
		} else {
			solve();
			System.out.println(sum);
		}
	}

	private static void solve() {
		for (int i = 2; i < digits; i++) {
			for (int j = 0; j < 10; j++) {
				if (j == 0) {
					dp[i][0] = dp[i - 1][1];
				} else if (j == 9) {
					dp[i][9] = dp[i - 1][8];
				} else {
					dp[i][j] = (dp[i - 1][j - 1] + dp[i - 1][j + 1]) % 1_000_000_000;
				}
			}
		}
		sum=0;
		for(int i =1;i<10;i++) {
			sum = (sum+ dp[digits-1][i])% 1_000_000_000;
		}
		
	}

	private static void initializeProblem() {
		digits = sc.nextInt();
		dp = new int[digits][10];
		if (digits > 2) {
			for (int i = 0; i < 10; i++) {
				if (i == 0) {
					dp[0][i] = 0;
				} else {
					dp[0][i] = 1;
				}
			}

			for (int i = 0; i < 10; i++) {
				if (i == 0) {
					dp[1][i] = 1;
				} else if (i == 9) {
					dp[1][i] = 1;
				} else {
					dp[1][i] = 2;
				}
			}
		}
	}
}