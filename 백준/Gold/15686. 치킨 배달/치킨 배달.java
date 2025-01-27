import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {
  private static Scanner sc = new Scanner(System.in);

  public static class Location {
    private int r, c;

    public Location(int r, int c) {
      this.r = r;
      this.c = c;
    }

    public int getDistance(Location o) {
      return Math.abs(this.r - o.r) + Math.abs(this.c - o.c);
    }

    @Override
    public String toString() {
      return "Location [r=" + r + ", c=" + c + "]";
    }

  }

  private static int N, M;
  private static int minDistance = Integer.MAX_VALUE;
  private static Location[] house;
  private static Location[] bbq;
  private static Location[] selectedBbq;

  public static void main(String[] args) {
    initializeProblem();
    solve(0, -1);
    System.out.println(minDistance);
  }

  private static void initializeProblem() {
    N = sc.nextInt();
    M = sc.nextInt();
    sc.nextLine();

    selectedBbq = new Location[M];

    List<Location> tempHoustLi = new ArrayList<>();
    List<Location> tempBbqLi = new ArrayList<>();

    for (int r = 0; r < N; r++) {
      for (int c = 0; c < N; c++) {
        int item = sc.nextInt();
        if (item == 0)
          continue;
        if (item == 1) {
          tempHoustLi.add(new Location(r, c));
        } else if (item == 2) {
          tempBbqLi.add(new Location(r, c));
        }
      }
      sc.nextLine();
    }

    house = tempHoustLi.toArray(new Location[tempHoustLi.size()]);
    bbq = tempBbqLi.toArray(new Location[tempBbqLi.size()]);
  }

  private static void solve(int depth, int prevSelectedIdx) {
    if (depth == M) {
      int tempTotalDistance = 0;
      for (Location h : house) {
        int tempMinDistance = Integer.MAX_VALUE;
        for (Location b : selectedBbq) {
          tempMinDistance = Math.min(tempMinDistance, h.getDistance(b));
        }
        tempTotalDistance += tempMinDistance;
      }
      minDistance = Math.min(minDistance, tempTotalDistance);

      return;
    }

    for (int i = prevSelectedIdx + 1; i < bbq.length; i++) {
      selectedBbq[depth] = bbq[i];
      solve(depth + 1, i);
    }
  }

}