import java.util.Scanner;

public class Main {
  private static Scanner sc = new Scanner(System.in);
  private static int[] wines;
  private static int wineCount;

  private static int prev3;
  private static int prev2;
  private static int prev1;
  private static int current;

  public static void main(String[] args) {
    initializeProblem();
    solve();
    System.out.println(current);
  }

  private static void initializeProblem() {
    wineCount = Integer.parseInt(sc.nextLine());
    wines = new int[wineCount + 1];

    for (int i = 1; i <= wineCount; i++) {
      wines[i] = Integer.parseInt(sc.nextLine());
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
      current = Math.max(wines[1]+ wines[3],  Math.max(wines[2]+ wines[3], wines[1]+wines[2])) ;
    }

    for (int i = 4; i <= wineCount; i++) {
      prev3 = prev2;
      prev2 = prev1;
      prev1 = current;
      current = Math.max(prev1, Math.max(prev2 + wines[i], prev3 + wines[i - 1] + wines[i]));
    }
  }
}
