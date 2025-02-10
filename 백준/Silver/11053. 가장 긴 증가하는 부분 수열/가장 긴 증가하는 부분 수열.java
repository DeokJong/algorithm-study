import java.util.*;

public class Main {
  private static Scanner sc = new Scanner(System.in);
  private static int[] arr;
  
  public static void main(String[] args) {
    initializeProblem();
    solve();
  }

  private static void initializeProblem() {
    int n = Integer.parseInt(sc.nextLine().trim());
    arr = new int[n];

    for (int i = 0; i < n; i++) {
      arr[i] = sc.nextInt();
    }
  }

  private static void solve() {
    int n = arr.length;
    int[] dp = new int[n];
    int[] prev = new int[n];

    Arrays.fill(dp, 1);
    Arrays.fill(prev, -1);

    int maxLen = 1; 
    int maxIndex = 0;
    for (int i = 1; i < n; i++) {
      for (int j = 0; j < i; j++) {
        if (arr[j] < arr[i] && dp[i] < dp[j] + 1) {
          dp[i] = dp[j] + 1;
          prev[i] = j;
        }
      }
      if (dp[i] > maxLen) {
        maxLen = dp[i];
        maxIndex = i;
      }
    }
    
    while (maxIndex != -1) {
      maxIndex = prev[maxIndex];
    }
    System.out.println(maxLen);
  }
}
