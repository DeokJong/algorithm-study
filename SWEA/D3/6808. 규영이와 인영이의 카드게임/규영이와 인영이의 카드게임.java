import static java.lang.Integer.parseInt;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Solution {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static int leftCtn;
    static int rightCtn;
    static int[][] cards = new int[2][];

    public static void main(String[] args) throws IOException {
        int T = parseInt(br.readLine());
        for (int t = 1; t <= T; t++) {
            leftCtn = 0;
            rightCtn = 0;
            initializeProblem();
            solve(0, 0, 0, 0);
            System.out.println("#" + t + " " + leftCtn + " " + rightCtn);
        }
    }

    private static void initializeProblem() throws IOException {
        int[] left = new int[9];
        StringTokenizer st = new StringTokenizer(br.readLine());
        for (int j = 0; j < 9; j++) {
            left[j] = parseInt(st.nextToken());
        }
        cards[0] = left.clone();
        boolean[] used = new boolean[19];
        for (int card : left) {
            used[card] = true;
        }
        int[] right = new int[9];
        int idx = 0;
        for (int num = 1; num <= 18; num++) {
            if (!used[num]) {
                right[idx++] = num;
            }
        }
        cards[1] = right;
    }

    private static void solve(int masks, int depth, int leftSum, int rightSum) {
        if (depth == 9) {
            if (leftSum > rightSum)
                leftCtn++;
            else if (leftSum < rightSum)
                rightCtn++;
            return;
        }
        int leftCard = cards[0][depth];
        for (int r = 0; r < 9; r++) {
            int rightBit = 1 << r;
            if ((masks & rightBit) != 0)
                continue;
            masks |= rightBit;
            int rightCard = cards[1][r];
            int score = leftCard + rightCard;
            if (leftCard > rightCard)
                solve(masks, depth + 1, leftSum + score, rightSum);
            else
                solve(masks, depth + 1, leftSum, rightSum + score);
            masks &= ~rightBit;
        }
    }

}
