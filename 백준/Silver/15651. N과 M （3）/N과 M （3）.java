import java.util.Scanner;
import java.util.StringJoiner;

public class Main {
    static int N, M;
    static int[] sequence;

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        N = sc.nextInt();
        M = sc.nextInt();
        sequence = new int[M];
        
        StringBuilder result = new StringBuilder();
        backtrack(0, result);
        System.out.print(result.toString()); // 한 번에 출력
        sc.close();
    }

    public static void backtrack(int depth, StringBuilder result) {
        if (depth == M) {
            // 결과를 StringBuilder에 추가
            for (int i = 0; i < M; i++) {
                result.append(sequence[i]).append(" ");
            }
            result.append("\n");
            return;
        }

        for (int i = 1; i <= N; i++) {
            sequence[depth] = i;
            backtrack(depth + 1, result);
        }
    }
}
