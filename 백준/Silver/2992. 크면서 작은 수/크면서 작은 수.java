import java.util.Arrays;
import java.util.Scanner;

public class Main {
    private static Scanner sc = new Scanner(System.in);
    private static int initNumber;
    private static int result;
    private static int[] numberArray;
    private static boolean[] used;

    public static void main(String[] args) {
        initialize();
        findNextLargerNumber(0, new StringBuilder());
        System.out.println(result == Integer.MAX_VALUE ? "0" : result);
        sc.close();
    }

    private static void initialize() {
        String input = sc.nextLine();
        initNumber = Integer.parseInt(input);
        numberArray = input.chars().map(c -> c - '0').toArray();
        Arrays.sort(numberArray);  // 오름차순 정렬하여 최소값부터 탐색
        used = new boolean[numberArray.length];
        result = Integer.MAX_VALUE;
    }

    private static void findNextLargerNumber(int depth, StringBuilder current) {
        if (depth == numberArray.length) {
            int formedNumber = Integer.parseInt(current.toString());
            if (formedNumber > initNumber) {
                result = Math.min(result, formedNumber);
            }
            return;
        }

        for (int i = 0; i < numberArray.length; i++) {
            if (!used[i]) {
                if (i > 0 && numberArray[i] == numberArray[i - 1] && !used[i - 1]) {
                    continue;  // 중복 제거
                }
                used[i] = true;
                current.append(numberArray[i]);
                findNextLargerNumber(depth + 1, current);
                current.deleteCharAt(current.length() - 1);
                used[i] = false;
            }
        }
    }
}
