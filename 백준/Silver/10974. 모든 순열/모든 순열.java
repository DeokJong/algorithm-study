import java.util.Scanner;
import java.util.stream.IntStream;

public class Main {
  private static Scanner sc = new Scanner(System.in);
  private static StringBuilder sb = new StringBuilder();
  private static int integerSize;
  private static int[] selectedArr;
  private static int[] integerArr;
  private static boolean[] usedIntegerArr;

  public static void main(String[] args) {
    initializeProblem();
    solve(0);
    System.out.println(sb);
  }

  private static void initializeProblem() {
    integerSize = sc.nextInt();
    integerArr = IntStream.range(1, integerSize+1).toArray();
    selectedArr = new int[integerSize];
    usedIntegerArr = new boolean[integerSize];
  }

  private static void solve(int depth) {
    if(depth == integerSize) {
      for(int item : selectedArr) sb.append(item).append(" ");
      sb.append("\n");
    }

    for(int i = 0 ;i< integerSize; i++) {
      if(!usedIntegerArr[i]) {
        usedIntegerArr[i] = true;
        selectedArr[depth] = integerArr[i];
        solve(depth+1);
        usedIntegerArr[i] = false;
      }
    }
  }
}
