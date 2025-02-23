import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        // 입력을 위한 BufferedReader와 StringTokenizer 사용
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int K = Integer.parseInt(st.nextToken());
        
        final int MAX = 100001;
        // 각 위치까지 도달하는 최소 시간 (방문하지 않은 곳은 -1)
        int[] dist = new int[MAX];
        // 각 위치에 도달하는 최단 경로의 수
        int[] ways = new int[MAX];
        Arrays.fill(dist, -1);
        
        Queue<Integer> queue = new LinkedList<>();
        // 시작 위치 초기화
        dist[N] = 0;
        ways[N] = 1;
        queue.offer(N);
        
        while (!queue.isEmpty()) {
            int x = queue.poll();
            // 세 가지 이동: x-1, x+1, 2*x
            int[] next = {x - 1, x + 1, x * 2};
            for (int nx : next) {
                if (nx >= 0 && nx < MAX) {
                    // 처음 방문하는 경우
                    if (dist[nx] == -1) {
                        dist[nx] = dist[x] + 1;
                        ways[nx] = ways[x];
                        queue.offer(nx);
                    }
                    // 이미 방문했지만, 현재 경로가 최단 경로인 경우
                    else if (dist[nx] == dist[x] + 1) {
                        ways[nx] += ways[x];
                    }
                }
            }
        }
        
        // 동생 위치 K까지 도달하는 최소 시간과 방법의 수 출력
        System.out.println(dist[K]);
        System.out.println(ways[K]);
    }
}
