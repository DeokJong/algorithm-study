import java.util.Scanner;

public class Main {
    private static Scanner sc = new Scanner(System.in);
    private static int[] wines;
    private static int[] dp;
    private static int wineCount;

    public static void main(String[] args) {
        initializeProblem();
        solve();
        System.out.println(dp[wineCount]);
    }

    private static void initializeProblem() {
        wineCount = Integer.parseInt(sc.nextLine());
        wines = new int[wineCount + 1];
        dp = new int[wineCount + 1];

        for (int i = 1; i <= wineCount; i++) {
            wines[i] = Integer.parseInt(sc.nextLine());
        }
    }

    private static void solve() {
        if (wineCount == 0) return;

        dp[1] = wines[1];

        if (wineCount >= 2) {
            dp[2] = wines[1] + wines[2];
        }

        for (int i = 3; i <= wineCount; i++) {
            dp[i] = Math.max(dp[i - 1], Math.max(dp[i - 2] + wines[i], dp[i - 3] + wines[i - 1] + wines[i]));
        }
    }
}
