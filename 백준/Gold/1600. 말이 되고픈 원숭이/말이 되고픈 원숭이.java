import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;
import java.util.StringTokenizer;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.Arrays;

public class Main {
    // 나이트(말) 이동 방향
    private static final int[] drHorse = { -2, -1, 1, 2, 2, 1, -1, -2 };
    private static final int[] dcHorse = { 1, 2, 2, 1, -1, -2, -2, -1 };
    // 상하좌우 이동 방향
    private static final int[] drCross = { 0, 0, -1, 1 };
    private static final int[] dcCross = { -1, 1, 0, 0 };

    private static int initK, w, h;
    private static int[][] map;
    // 각 좌표를 방문할 때 남은 k의 최대값을 기록합니다.
    private static int[][] maxK;

    public static void main(String[] args) throws IOException {
        // BufferedReader와 StringTokenizer를 사용하여 빠른 입출력 수행
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        // 첫 줄: k
        initK = Integer.parseInt(br.readLine().trim());

        // 두 번째 줄: w h
        StringTokenizer st = new StringTokenizer(br.readLine());
        w = Integer.parseInt(st.nextToken());
        h = Integer.parseInt(st.nextToken());

        // 지도 배열 초기화 (h행, w열)
        map = new int[h][w];
        maxK = new int[h][w];
        for (int i = 0; i < h; i++) {
            Arrays.fill(maxK[i], -1); // 아직 방문하지 않은 상태로 -1을 설정
        }

        // 세 번째 줄부터 h개의 줄에 걸쳐 지도 정보를 입력 (0: 이동 가능, 1: 장애물)
        for (int i = 0; i < h; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < w; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        System.out.println(solve());
    }

    private static int solve() {
        // 각 상태를 int 배열 {r, c, 남은 k, 이동 횟수 m}로 표현
        Queue<int[]> queue = new ArrayDeque<>();
        queue.add(new int[] { 0, 0, initK, 0 });
        // 시작점 (0,0)은 initK 만큼의 기회를 가지고 방문했다고 기록
        maxK[0][0] = initK;

        // BFS 수행
        while (!queue.isEmpty()) {
            int[] cur = queue.poll();
            int r = cur[0], c = cur[1], k = cur[2], m = cur[3];

            // 도착점 도달 시 이동 횟수를 반환
            if (r == h - 1 && c == w - 1) {
                return m;
            }

            // 1. 나이트(말) 이동 (남은 k가 있을 경우)
            if (k > 0) {
                int nextK = k - 1;
                for (int d = 0; d < 8; d++) {
                    int nr = r + drHorse[d];
                    int nc = c + dcHorse[d];
                    // 경계 체크와 장애물(1)이 아닌지, 그리고 더 나은(더 많은 k를 가진) 상태인 경우만 진행
                    if (nr >= 0 && nr < h && nc >= 0 && nc < w &&
                        map[nr][nc] == 0 && nextK > maxK[nr][nc]) {
                        maxK[nr][nc] = nextK;
                        queue.add(new int[] { nr, nc, nextK, m + 1 });
                    }
                }
            }

            // 2. 상하좌우 이동 (남은 k 변화 없음)
            for (int d = 0; d < 4; d++) {
                int nr = r + drCross[d];
                int nc = c + dcCross[d];
                if (nr >= 0 && nr < h && nc >= 0 && nc < w &&
                    map[nr][nc] == 0 && k > maxK[nr][nc]) {
                    maxK[nr][nc] = k;
                    queue.add(new int[] { nr, nc, k, m + 1 });
                }
            }
        }
        // 도착점에 도달할 수 없으면 -1 반환
        return -1;
    }
}
