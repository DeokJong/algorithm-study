import java.util.ArrayDeque;
import java.util.Queue;
import java.util.Scanner;

public class Main {
  public static class Location {
    public int x, y;
    public int second;

    public Location(int x, int y) {
      second = 0;
      this.x = x;
      this.y = y;
    }

    public Location(int x, int y, int second) {
      this.second = second;
      this.x = x;
      this.y = y;
    }
  }

  private static Scanner sc = new Scanner(System.in);
  private static StringBuilder sb = new StringBuilder();
  private static int T;

  private static final String IMPOSSIBLE = "IMPOSSIBLE";
  private static final char WALL = '#';
  private static final char BLANK = '.';
  private static final char FIRE = '*';
  private static final char SEMI_FIRE = '+';
  private static final char PLAYER = '@';
  private static final int[] DR = { 0, 0, 1, -1 };
  private static final int[] DC = { 1, -1, 0, 0 };

  private static int second;
  private static int C, R;
  private static int[][] map;
  private static boolean[][] visited;
  private static Queue<Location> fireQue;
  private static Queue<Location> playerQue;

  public static void main(String[] args) {
    T = Integer.parseInt(sc.nextLine());
    for (int i = 0; i < T; i++) {
      initializeProblem();
      sovle();
    }
    System.out.println(sb);
    sc.close();
  } 

  private static void initializeProblem() {
    C = sc.nextInt();
    R = sc.nextInt();
    sc.nextLine();

    map = new int[R][C];
    visited = new boolean[R][C];
    second = 0;
    fireQue = new ArrayDeque<>();
    playerQue = new ArrayDeque<>();

    Queue<Location> tempFireQue = new ArrayDeque<>();

    for (int r = 0; r < R; r++) {
      String[] input = sc.nextLine().trim().split("");
      for (int c = 0; c < C; c++) {
        char ch = input[c].charAt(0);
        switch (ch) {
          case WALL:
            map[r][c] = WALL;
            break;
          case FIRE:
            map[r][c] = FIRE;
            fireQue.add(new Location(r, c));
            tempFireQue.add(new Location(r, c));
            break;
          case BLANK:
            map[r][c] = BLANK;
            break;
          case PLAYER:
            visited[r][c] = true;
            playerQue.add(new Location(r, c));
            map[r][c] = BLANK;
            break;
        }
      }
    }

    while (!tempFireQue.isEmpty()) {
      Location loc = tempFireQue.poll();
      setUpSemiFire(loc.x, loc.y);
    }
  }

  private static boolean isInBound(int r, int c) {
    return r > -1 && c > -1 && r < R && c < C;
  }

  private static boolean isContagionFireWithIsInBound(int r, int c) {
    if (!isInBound(r, c))
      return false;
    return map[r][c] == SEMI_FIRE;
  }

  private static boolean isMoveWithIsInBound(int r, int c) {
    if (!isInBound(r, c))
      return false;

    if (visited[r][c])
      return false;

    if (map[r][c] == BLANK)
      return true;

    return false;
  }

  private static boolean isEscape(int r, int c) {
    return !isInBound(r, c);
  }

  private static boolean isContagionSemiFireWithIsInBound(int r, int c) {
    if (!isInBound(r, c))
      return false;
    return map[r][c] == BLANK;
  }

  private static void setUpSemiFire(int r, int c) {
    for (int i = 0; i < 4; i++) {
      int roundR = r + DR[i];
      int roundC = c + DC[i];
      if (isContagionSemiFireWithIsInBound(roundR, roundC)) {
        map[roundR][roundC] = SEMI_FIRE;
      }
    }
  }

  private static void sovle() {
    while (!playerQue.isEmpty()) {
      // player move update
      if (playerQue.peek().second == second) {
        Location playLocation = playerQue.poll();

        for (int i = 0; i < 4; i++) {
          int nextR = playLocation.x + DR[i];
          int nextC = playLocation.y + DC[i];
          if (isEscape(nextR, nextC)) {
            sb.append(second + 1).append("\n");
            return;
          } else if (isMoveWithIsInBound(nextR, nextC)) {
            visited[nextR][nextC] = true;
            playerQue.add(new Location(nextR, nextC, second + 1));
          }
        }
      }
      // 다음 플레이어 위치가 현재 시간과 같지 않다면 불을 업데이트함.
      // 불 업데이트가 끝나면, 시간을 늘림
      else {
        while (!fireQue.isEmpty() && fireQue.peek().second == second) {
          Location fireLocation = fireQue.poll();

          for(int i =0;i<4;i++) {
            int nextR = fireLocation.x + DR[i];
            int nextC = fireLocation.y + DC[i];
            if(isContagionFireWithIsInBound(nextR, nextC)){
              map[nextR][nextC] = FIRE;
              setUpSemiFire(nextR, nextC);
              fireQue.add(new Location(nextR, nextC, second+1));
            }
          }
        }
        second++;
      }
    }
    sb.append(IMPOSSIBLE).append("\n");
  }
}