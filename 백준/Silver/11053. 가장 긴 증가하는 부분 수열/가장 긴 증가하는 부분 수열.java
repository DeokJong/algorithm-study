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
    int[] dp = new int[arr.length];
    int maxLength = 1;
    Arrays.fill(dp, 1);

    for(int i =1; i< arr.length; i++) {
      for(int j = 0;j<i;j++) {
        if(arr[i] > arr[j]) {
          dp[i] = Math.max(dp[i], dp[j]+1);
        }
      }
      maxLength = Math.max(maxLength, dp[i]);
    }

    System.out.println(maxLength);
  }
}
