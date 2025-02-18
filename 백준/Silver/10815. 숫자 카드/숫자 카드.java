import static java.lang.Integer.parseInt;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;
import java.util.TreeSet;
import java.util.Set;

public class Main {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static int findCount;
    static int[] cards;
    static int[] finds;
    static int[] ans;

    public static void main(String[] args) throws NumberFormatException, IOException {
        initializeProblem();
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < findCount; i++) {
            binarySearch(finds[i], i);
            sb.append(ans[i]).append(" ");
        }
        System.out.print(sb);
    }

    static void initializeProblem() throws NumberFormatException, IOException {
        Set<Integer> temp = new TreeSet<>();
        int cardCount = parseInt(br.readLine());

        StringTokenizer st = new StringTokenizer(br.readLine());
        for (int i = 0; i < cardCount; i++) {
            temp.add(parseInt(st.nextToken()));
        }

        findCount = parseInt(br.readLine());
        finds = new int[findCount];
        ans = new int[findCount];
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < findCount; i++) {
            finds[i] = parseInt(st.nextToken());
        }

        cards = temp.stream().mapToInt(Integer::intValue).toArray();
    }

    static void binarySearch(int target, int ansIdx) {
        binarySearch(target, 0, cards.length - 1, ansIdx);
    }

    static void binarySearch(int target, int leftIdx, int rightIdx, int ansIdx) {
        while (leftIdx <= rightIdx) {
            int midIdx = (leftIdx + rightIdx) / 2;
            if (cards[midIdx] == target) {
                ans[ansIdx] = 1;
                return;
            } else if (cards[midIdx] > target) {
                rightIdx = midIdx - 1;
            } else {
                leftIdx = midIdx + 1;
            }
        }
    }
}
