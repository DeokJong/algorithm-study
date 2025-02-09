import java.util.Scanner;

public class Main {
  static Scanner sc = new Scanner(System.in);
  static int[] dp;
  public static void main(String[] args) {
    int n = sc.nextInt();
    if(n >=4 ){
      dp = new int[n+1];
    } else {
      dp = new int[4];
    }
    dp[1]=1;
    dp[2]=2;
    dp[3]=3;

    for(int i =4;i<=n;i++) {
      dp[i] = (dp[i-1] + dp[i-2]) % 10007;
    }

    System.out.println(dp[n]);
  }
}
