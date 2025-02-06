import java.io.*;
import java.util.*;

public class Main {
    private static int[] dr = { 0, 0, -1, 1, 0, 0 };
    private static int[] dc = { 1, -1, 0, 0, 0, 0 };
    private static int[] dd = { 0, 0, 0, 0, -1, 1 };

    private static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    private static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

    private static Queue<int[]> arrTomatoQueue = new ArrayDeque<>();
    private static int D, R, C;
    private static int[][][] box;
    private static int totalCnt; // 안 익은 토마토 개수

    public static void main(String[] args) throws IOException {
        initializeProblem();
        int result = solve();
        bw.write(String.valueOf(result));
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
                        arrTomatoQueue.offer(new int[]{d, r, c});
                    }
                }
            }
        }
    }

    private static int solve() {
        if (totalCnt == 0) return 0;

        int days = -1;

        while (!arrTomatoQueue.isEmpty()) {
            int size = arrTomatoQueue.size();
            while (size-- > 0) {
                int[] loc = arrTomatoQueue.poll();
                int d = loc[0], r = loc[1], c = loc[2];

                for (int i = 0; i < 6; i++) {
                    int nd = d + dd[i];
                    int nr = r + dr[i];
                    int nc = c + dc[i];

                    if (nd >= 0 && nd < D && nr >= 0 && nr < R && nc >= 0 && nc < C && box[nd][nr][nc] == 0) {
                        box[nd][nr][nc] = 1;
                        arrTomatoQueue.offer(new int[]{nd, nr, nc});
                        totalCnt--;
                    }
                }
            }
            days++;
        }

        return totalCnt == 0 ? days : -1;
    }
}
