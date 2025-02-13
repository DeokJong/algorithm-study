import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;
import java.util.stream.Collectors;

public class Main {
    private static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    private static int[] arr;
    private static int[] LIS;
    private static int[] LDS;
    private static int maxLength = Integer.MIN_VALUE;

    public static void main(String[] args) throws IOException {
        initializeProblem();
        solve();
        System.out.println(maxLength);
        }

    private static void initializeProblem() throws IOException {
        int length = Integer.parseInt(br.readLine().trim());
        arr = new int[length];
        LIS = new int[length];
        LDS = new int[length];

        StringTokenizer st = new StringTokenizer(br.readLine());
        for (int i = 0; i < length; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
        }
    }

    private static void solve() {
        Arrays.fill(LIS, 1);
        Arrays.fill(LDS, 1);

        for (int i = 0; i < arr.length; i++) {
            for (int j = 0; j < i; j++) {
                if (arr[i] > arr[j]) {
                    LIS[i] = Math.max(LIS[i], LIS[j] + 1);
                }
            }
        }

        for (int i = arr.length - 1; i >= 0; i--) {
            for (int j = arr.length - 1; j > i; j--) {
                if (arr[i] > arr[j]) {
                    LDS[i] = Math.max(LDS[i], LDS[j] + 1);
                }
            }
        }

        for (int i = 0; i < arr.length; i++) {
            maxLength = Math.max(maxLength, LIS[i] + LDS[i] - 1);
        }
    }
}
