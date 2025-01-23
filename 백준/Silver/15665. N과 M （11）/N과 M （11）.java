import java.util.Scanner;
import java.util.Set;
import java.util.TreeSet;

public class Main {
  private static Scanner sc = new Scanner(System.in);

  private static int N, M;
  private static int[] integerArr;
  private static int[] selectedArr;
  private static StringBuilder sb = new StringBuilder();

  public static void main(String[] args) {
    initializeProblem();
    solve(0);
    System.out.println(sb);
  }

  private static void initializeProblem(){
    N = sc.nextInt();
    M = sc.nextInt();
    sc.nextLine();

    Set<Integer> integerSet = new TreeSet<>();
    for(int i=0;i<N;i++) {
      integerSet.add(sc.nextInt());
    }

    integerArr = integerSet.stream().mapToInt(Integer::valueOf).toArray();
    selectedArr = new int[M];
  }

  private static void solve(int depth){
    if(depth == M) {
      for(int item : selectedArr) {
        sb.append(item + " ");
      }
      sb.append("\n");
      return ;
    }

    for(int i =0;i<integerArr.length;i++) {
        selectedArr[depth] = integerArr[i];
        solve(depth+1);
    }
  }
}
