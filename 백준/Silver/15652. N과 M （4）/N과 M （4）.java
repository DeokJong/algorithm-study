import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class Main {
    public static Scanner sc = new Scanner(System.in);

    static int M;
    public static void main(String[] args) {
        int N = sc.nextInt();
        M = sc.nextInt();
        List<Integer> numberBox = IntStream.range(1, N+1).boxed().collect(Collectors.toList());
        List<Integer> printNumberBox = new ArrayList<>();
        StringBuilder result = new StringBuilder();
        backtracking(numberBox, M, printNumberBox, result);
        System.out.println(result.toString());
        sc.close();
    }

    public static void backtracking(List<Integer> orgNumberBox, int depth, List<Integer> orgPrintNumberBox, StringBuilder result) {
        if (depth == 0) {
            // 완성된 수열을 StringBuilder에 추가
            for (int n : orgPrintNumberBox) {
                result.append(n).append(" ");
            }
            result.append("\n");
            return;
        }

        for (int n: orgNumberBox) {
            orgPrintNumberBox.add(n);  // 숫자를 추가
            
            int nIdx = orgNumberBox.indexOf(Integer.valueOf(n));

            // 재귀적으로 다음 깊이로 진행
            backtracking(orgNumberBox.subList(nIdx, orgNumberBox.size()), depth - 1, orgPrintNumberBox, result);

            orgPrintNumberBox.remove(orgPrintNumberBox.size() - 1);  // 이전에 추가한 숫자 제거
        }
    }
}
