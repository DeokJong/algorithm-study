import static java.lang.Integer.parseInt;
import java.util.*;
import java.io.*;

public class Main {
    private static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    private static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    private static int[] permutation;
    private static int permutationSize;

    public static void main(String[] args) throws IOException {
        solve();

        br.close();
        bw.close();
    }

    private static void solve() throws IOException {
        permutationSize = parseInt(br.readLine());
        permutation = new int[permutationSize];

        StringTokenizer st = new StringTokenizer(br.readLine());
        for (int i = 0; i < permutationSize; i++) {
            permutation[i] = parseInt(st.nextToken());
        }

        if (!setNextPermutation()) {
            bw.write("-1\n");
        } else {
            for (int num : permutation) {
                bw.write(num + " ");
            }
            bw.write("\n");
        }
        bw.flush();
    }

    private static boolean setNextPermutation() {
        int largestIdx = -1;

        for (int i = permutationSize - 2; i >= 0; i--) {
            if (permutation[i] < permutation[i + 1]) {
                largestIdx = i;
                break;
            }
        }

        if (largestIdx == -1) return false;

        int secondLargestIdx = -1;
        for (int i = permutationSize - 1; i > largestIdx; i--) {
            if (permutation[largestIdx] < permutation[i]) {
                secondLargestIdx = i;
                break;
            }
        }

        swap(largestIdx, secondLargestIdx);

        reverse(largestIdx + 1, permutationSize - 1);

        return true;
    }

    private static void swap(int i, int j) {
        int temp = permutation[i];
        permutation[i] = permutation[j];
        permutation[j] = temp;
    }

    private static void reverse(int start, int end) {
        while (start < end) {
            swap(start, end);
            start++;
            end--;
        }
    }
}
