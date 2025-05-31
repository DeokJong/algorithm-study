import java.io.IOException;

public class Main {
    private static final int OFFSET = 10_000_000;
    private static int[] counts;

    public static void main(String[] args) throws IOException {
        initializeProblem();
        solve();
    }

    private static void initializeProblem() throws IOException {
        counts = new int[OFFSET * 2 + 1];
        int n = nextInt();
        for (int i = 0; i < n; i++) {
            counts[nextInt() + OFFSET]++;
        }
    }

    private static void solve() throws IOException {
        int count = nextInt();
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < count; i++) {
            sb.append(counts[nextInt() + OFFSET]).append(' ');
        }
        System.out.write(sb.toString().getBytes());
        System.out.flush();
    }

    private static int nextInt() throws IOException {
        int sum = 0;
        int c;
        boolean isNegative = false;

        while ((c = System.in.read()) != -1 && (c == ' ' || c == '\n' || c == '\r'));

        if (c == '-') {
            isNegative = true;
            c = System.in.read();
        }

        do {
            sum = sum * 10 + (c - '0');
        } while ((c = System.in.read()) != -1 && c >= '0' && c <= '9');

        return isNegative ? -sum : sum;
    }
}
