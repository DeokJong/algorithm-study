import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class Main {
    private static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    private static int[][] map = new int[9][9];
    private static int[][] notAllocatedArr;

    public static void main(String[] args) throws IOException {
        initializeProblem();
        if (solve(0)) {
            for (int[] r : map) {
                for (int e : r) {
                    System.out.print(e);
                }
                System.out.println();
            }
        } else {
            System.out.println("해결할 수 없는 스도쿠 퍼즐입니다.");
        }
    }

    private static boolean solve(int depth) {
        if (depth == notAllocatedArr.length) {
            return true;
        }
        int r = notAllocatedArr[depth][0];
        int c = notAllocatedArr[depth][1];
        for (int i = 1; i <= 9; i++) {
            map[r][c] = i;
            if (isAbleCol(c) && isAbleRow(r) && isAbleRectangle(r, c)) {
                if (solve(depth + 1)) {
                    return true;
                }
            }
            map[r][c] = 0;
        }
        return false;
    }

    private static void initializeProblem() throws IOException {
        List<List<Integer>> notAllocatedList = new ArrayList<>();
        for (int i = 0; i < 9; i++) {
            // 입력이 "530070000"과 같이 공백 없이 주어진다고 가정
            String[] inputs = br.readLine().trim().split("");
            for (int j = 0; j < 9; j++) {
                map[i][j] = Integer.parseInt(inputs[j]);
                if (map[i][j] == 0) {
                    List<Integer> temp = new ArrayList<>();
                    temp.add(i);
                    temp.add(j);
                    notAllocatedList.add(temp);
                }
            }
        }
        notAllocatedArr = notAllocatedList.stream()
                .map(x -> x.stream().mapToInt(Integer::intValue).toArray())
                .toArray(int[][]::new);
    }

    /**
     * 주어진 행(r)에서 중복 없이 숫자가 배치되었는지 비트마스크로 검사한다.
     */
    private static boolean isAbleRow(int r) {
        int mask = 0;
        for (int c = 0; c < 9; c++) {
            if (map[r][c] == 0)
                continue;
            int bit = 1 << (map[r][c] - 1);
            if ((mask & bit) != 0)
                return false;
            mask |= bit;
        }
        return true;
    }

    /**
     * 주어진 열(c)에서 중복 없이 숫자가 배치되었는지 비트마스크로 검사한다.
     */
    private static boolean isAbleCol(int c) {
        int mask = 0;
        for (int r = 0; r < 9; r++) {
            if (map[r][c] == 0)
                continue;
            int bit = 1 << (map[r][c] - 1);
            if ((mask & bit) != 0)
                return false;
            mask |= bit;
        }
        return true;
    }

    /**
     * 주어진 셀 (r, c)가 속한 3×3 박스에서 중복 없이 숫자가 배치되었는지 비트마스크로 검사한다.
     */
    private static boolean isAbleRectangle(int r, int c) {
        int mask = 0;
        int initR = (r / 3) * 3;
        int initC = (c / 3) * 3;
        for (int i = initR; i < initR + 3; i++) {
            for (int j = initC; j < initC + 3; j++) {
                if (map[i][j] == 0)
                    continue;
                int bit = 1 << (map[i][j] - 1);
                if ((mask & bit) != 0)
                    return false;
                mask |= bit;
            }
        }
        return true;
    }
}
