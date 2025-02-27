import java.io.*;
import java.util.*;

public class Main {
    private static final int[] dr = { -1, 0, 1 };
    private static final int[] dc = { 1, 1, 1 };

    private static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    private static StringBuilder sb = new StringBuilder();

    private static int R, C;
    private static int[][] map;
    private static int maxPipeLine = 0;

    public static void main(String[] args) throws IOException {
        initializeProblem();
        solve();
        System.out.println(maxPipeLine);
    }

    private static void initializeProblem() throws IOException {
        String[] sa = br.readLine().trim().split(" ");
        R = Integer.parseInt(sa[0]);
        C = Integer.parseInt(sa[1]);

        map = new int[R][C];
        for (int i = 0; i < R; i++) {
            sa = br.readLine().trim().split("");
            for (int j = 0; j < C; j++) {
                if (sa[j].equals("x")) {
                    map[i][j] = -1;
                }
            }
        }
    }

    private static void solve() {
        for (int i = 0; i < R; i++) {
            if (dfs(i, 0)) {
                maxPipeLine++;
            }
        }
    }

    private static boolean dfs(int r, int c) {
        if (c == C - 1) {
            return true;
        }

        for (int d = 0; d < 3; d++) {
            int nr = r + dr[d];
            int nc = c + dc[d];

            if (isInBound(nr, nc) && map[nr][nc] == 0) {
                map[nr][nc] = 1;
                if (dfs(nr, nc)) {
                    return true;
                }
            }
        }
        return false;
    }

    private static boolean isInBound(int r, int c) {
        return r >= 0 && c >= 0 && r < R && c < C;
    }
}
