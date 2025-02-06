import java.io.*;
import java.util.*;

public class Main {
    static int[] dr = {0, 0, -1, 1, 0, 0};
    static int[] dc = {1, -1, 0, 0, 0, 0};
    static int[] dd = {0, 0, 0, 0, -1, 1};

    static class Coord {
        int d, r, c;
        public Coord(int d, int r, int c) {
            this.d = d;
            this.r = r;
            this.c = c;
        }
    }

    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

    static Queue<Coord> queue = new ArrayDeque<>();
    static int D, R, C;
    static int[][][] box;
    static int totalCnt;

    public static void main(String[] args) throws IOException {
        initializeProblem();
        int result = solve();
        bw.write(Integer.toString(result));
        bw.flush();
        br.close();
        bw.close();
    }

    private static void initializeProblem() throws IOException {
        StringTokenizer st = new StringTokenizer(br.readLine());
        C = Integer.parseInt(st.nextToken());
        R = Integer.parseInt(st.nextToken());
        D = Integer.parseInt(st.nextToken());

        box = new int[D][R][C];
        totalCnt = 0;

        for (int d = 0; d < D; d++) {
            for (int r = 0; r < R; r++) {
                st = new StringTokenizer(br.readLine());
                for (int c = 0; c < C; c++) {
                    box[d][r][c] = Integer.parseInt(st.nextToken());
                    if (box[d][r][c] == 0) {
                        totalCnt++;
                    } else if (box[d][r][c] == 1) {
                        queue.offer(new Coord(d, r, c));
                    }
                }
            }
        }
    }

    private static int solve() {
        if (totalCnt == 0) return 0;

        int days = -1;

        while (!queue.isEmpty()) {
            int size = queue.size();
            while (size-- > 0) {
                Coord loc = queue.poll();
                int d = loc.d, r = loc.r, c = loc.c;

                for (int i = 0; i < 6; i++) {
                    int nd = d + dd[i];
                    int nr = r + dr[i];
                    int nc = c + dc[i];

                    if (nd >= 0 && nd < D && nr >= 0 && nr < R && nc >= 0 && nc < C && box[nd][nr][nc] == 0) {
                        box[nd][nr][nc] = 1;
                        queue.offer(new Coord(nd, nr, nc));
                        totalCnt--;
                    }
                }
            }
            days++;
        }

        return totalCnt == 0 ? days : -1;
    }
}
