import java.util.ArrayDeque;
import java.util.Queue;
import java.util.Scanner;

public class Main {
    private static Scanner sc = new Scanner(System.in);

    public static class Location {
        int r, c, k, m;
        public Location(int r, int c, int k, int m) {
            this.r = r;
            this.c = c;
            this.k = k;
            this.m = m;
        }
    }

    public static int[] drHorse = { -2, -1, 1, 2, 2, 1, -1, -2 };
    public static int[] dcHorse = { 1, 2, 2, 1, -1, -2, -2, -1 };
    public static int[] drCross = { 0, 0, -1, 1 };
    public static int[] dcCross = { -1, 1, 0, 0 };
    public static int initK;
    public static int[][] map;
    public static boolean[][][] visited;
    public static int w, h;
    public static Queue<Location> que;

    public static void main(String[] args) {
        initializeProblem();
        System.out.println(solve());
        sc.close();
    }

    private static void initializeProblem() {
        initK = sc.nextInt();
        w = sc.nextInt();
        h = sc.nextInt();
        map = new int[h][w];
        visited = new boolean[h][w][initK + 1];

        for (int i = 0; i < h; i++) {
            for (int j = 0; j < w; j++) {
                map[i][j] = sc.nextInt();
            }
        }
        
        que = new ArrayDeque<>();
        que.add(new Location(0, 0, initK, 0));
        visited[0][0][initK] = true;
    }

    private static int solve() {


        while (!que.isEmpty()) {
            Location cur = que.poll();
            int r = cur.r, c = cur.c, k = cur.k, m = cur.m;

            if (r == h - 1 && c == w - 1)
                return m;

            if (k > 0) {
                for (int d = 0; d < 8; d++) {
                    int nr = r + drHorse[d];
                    int nc = c + dcHorse[d];
                    if (isInBound(nr, nc) && map[nr][nc] != 1 && !visited[nr][nc][k - 1]) {
                        visited[nr][nc][k - 1] = true;
                        que.add(new Location(nr, nc, k - 1, m + 1));
                    }
                }
            }

            for (int d = 0; d < 4; d++) {
                int nr = r + drCross[d];
                int nc = c + dcCross[d];
                if (isInBound(nr, nc) && map[nr][nc] != 1 && !visited[nr][nc][k]) {
                    visited[nr][nc][k] = true;
                    que.add(new Location(nr, nc, k, m + 1));
                }
            }
        }
        return -1;
    }

    private static boolean isInBound(int r, int c) {
        return r >= 0 && c >= 0 && r < h && c < w;
    }
}
