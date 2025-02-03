import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

public class Main {
  private static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
  private static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
  private static int[] wines;
  private static int wineCount;

  private static int prev3;
  private static int prev2;
  private static int prev1;
  private static int current;

  public static void main(String[] args) throws IOException {
    initializeProblem();
    solve();
    bw.write(current + "\n");
    
    bw.flush();
    bw.close();
    br.close();
  }

  private static void initializeProblem() throws IOException {
    wineCount = Integer.parseInt(br.readLine());
    wines = new int[wineCount + 1];

    for (int i = 1; i <= wineCount; i++) {
      wines[i] = Integer.parseInt(br.readLine());
    }
  }

  private static void solve() {
    if (wineCount == 0)
      return;

    prev3 = 0;
    prev2 = 0;
    prev1 = 0;
    current = wines[1];

    if (wineCount >= 2) {
      prev1 = current;
      current = wines[1] + wines[2];
    }

    if (wineCount >= 3) {
      prev2 = prev1;
      prev1 = current;
      current = Math.max(wines[1] + wines[3], Math.max(wines[2] + wines[3], wines[1] + wines[2]));
    }

    for (int i = 4; i <= wineCount; i++) {
      prev3 = prev2;
      prev2 = prev1;
      prev1 = current;
      current = Math.max(prev1, Math.max(prev2 + wines[i], prev3 + wines[i - 1] + wines[i]));
    }
  }
}
