import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

public class Main {
  private static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
  private static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    private static int[] wines;
    private static int[] dp;
    private static int wineCount;

    public static void main(String[] args) throws IOException {
        initializeProblem();
        solve();
        bw.write(dp[wineCount]+"\n");
        bw.flush();
        bw.close();
        br.close();
    }

    private static void initializeProblem() throws IOException {
        wineCount = Integer.parseInt(br.readLine());
        wines = new int[wineCount + 1];
        dp = new int[wineCount + 1];

        for (int i = 1; i <= wineCount; i++) {
            wines[i] = Integer.parseInt(br.readLine());
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
