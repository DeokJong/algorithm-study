import static java.lang.Integer.parseInt;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.stream.Stream;

/**
 * 토마토 문제 BOJ 7569 (멋쟁이 토마토~)
 * 
 * 문제 풀이 요약해보기
 * 1. dx dy dd 정의 해두기
 * 2. 좌표에 대한 클래스와 함께 second도 함께 초기화 해주기
 * 3. 박스 초기화시 queue에다가 토마토 넣기
 * 4. 전역 second를 선언해 peek과 함께 활용해서 시간이 지난것 측정하기
 * 5. 큐가 비었으면 맨 마지막에 -1이 들어간 좌표의 모든 토마토 확인해서 해당 위치가 익었는지 확인하기
 */

public class Main {
  public static class Location {
    int r, c, d, second;

    public Location(int d, int r, int c) {
      this(d, r, c, 0);
    }

    public Location(int d, int r, int c, int second) {
      this.r = r;
      this.c = c;
      this.d = d;
      this.second = second;
    }

  }

  private static int[] dr = { 0, 0, -1, 1, 0, 0 };
  private static int[] dc = { 1, -1, 0, 0, 0, 0 };
  private static int[] dd = { 0, 0, 0, 0, -1, 1 };
  private static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
  private static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

  private static Queue<Location> tomatoQueue = new ArrayDeque<>();
  private static Queue<Location> initUnRipeTomato = new ArrayDeque<>();
  private static int D, R, C;
  private static int second;
  private static int[][][] box;

  public static void main(String[] args) throws IOException {
    initializeProblem();
    sovle();
    bw.append(String.format("%d", second == -1 ? -1 : second-1));
    bw.flush();
    br.close();
    bw.close();
  }

  private static void initializeProblem() throws IOException {
    int[] inputs = Stream.of(br.readLine().trim().split(" ")).mapToInt(x -> parseInt(x)).toArray();
    C = inputs[0];
    R = inputs[1];
    D = inputs[2];

    box = new int[D][R][C];

    for (int d = 0; d < D; d++) {
      for (int r = 0; r < R; r++) {
        inputs = Stream.of(br.readLine().trim().split(" ")).mapToInt(x -> parseInt(x)).toArray();
        for (int c = 0; c < C; c++) {
          if (inputs[c] == -1) {
            box[d][r][c] = -1;
          } else if (inputs[c] == 1) {
            box[d][r][c] = 1;
            tomatoQueue.add(new Location(d, r, c));
          } else {
            initUnRipeTomato.add(new Location(d, r, c));
          }
        }
      }
    }
  }

  private static void sovle() {
    while (!tomatoQueue.isEmpty()) {
      if(tomatoQueue.peek().second != second) {
        Location loc = tomatoQueue.poll();
        
        for(int d =0;d<6;d++) {
          int nd = loc.d + dd[d];
          int nr = loc.r + dr[d];
          int nc = loc.c + dc[d];
          if(isInBound(nd, nr, nc) && isRipe(nd, nr, nc)) {
            box[nd][nr][nc] = 1;
            tomatoQueue.add(new Location(nd, nr, nc,loc.second+1));
          }
        }
      } else {
        second++;
      }
    }

    while (!initUnRipeTomato.isEmpty()) {
      Location loc = initUnRipeTomato.poll();
      if(box[loc.d][loc.r][loc.c] == 0) {
        second = -1;
        return;
      }
    }
  }

  private static boolean isInBound(int d, int r, int c){
    return d>-1 && r> -1 && c>-1 && D>d && R>r && C>c;
  }
  private static boolean isRipe(int d,int r, int c) {
    return box[d][r][c] == 0;
  }
}