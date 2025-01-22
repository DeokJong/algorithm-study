import java.util.Arrays;
import java.util.Comparator;
import java.util.Scanner;
import java.util.Set;
import java.util.TreeSet;

public class Main {
    static Scanner sc = new Scanner(System.in);

    static int N, M;
    static int[] integerArr;
    static Set<String> tempSet = new TreeSet<>(new Comparator<String>() {
      @Override
      public int compare(String o1, String o2) {
        o1.trim();
        o2.trim();

        String[] arr1 = o1.split(" ");
        String[] arr2 = o2.split(" ");

        for(int i =0;i< Math.min(arr1.length, arr2.length); i++){
          int num1 = Integer.parseInt(arr1[i]);
          int num2 = Integer.parseInt(arr2[i]);

          if(num1!=num2) {
            return num1-num2;
          }
        }

        return arr1.length - arr2.length;
      }
      
    });
    static int[] selectedIntegerArr;

    public static void main(String[] args) {
        initializeProblem();
        solve(0, -1);
        for (String s : tempSet) {
            System.out.println(s.trim());
        }

        sc.close();
    }

    private static void initializeProblem() {
        N = sc.nextInt();
        M = sc.nextInt();
        sc.nextLine();

        integerArr = new int[N];
        String[] integerString = sc.nextLine().trim().split(" ");
        for (int i = 0; i < N; i++) {
            integerArr[i] = Integer.parseInt(integerString[i]);
        }
        Arrays.sort(integerArr);

        selectedIntegerArr = new int[M];
    }

    private static void solve(int depth, int prevSelectedIdx) {
        if (depth == M) {
            tempSet.add(selectedArrToString());
            return;
        }

        for (int i = prevSelectedIdx + 1; i < N; i++) {
                selectedIntegerArr[depth] = integerArr[i];
                solve(depth + 1, i);
            }
        }

    private static String selectedArrToString() {
        StringBuilder sb = new StringBuilder();
        for (int item : selectedIntegerArr) {
            sb.append(item).append(" ");
        }
        return sb.toString();
    }
}
