import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Maze {
  private static Scanner sc = new Scanner(System.in);
  private static StringBuilder sb = new StringBuilder();
  private static int[] dr = { -1, 0, 1, 0 };
  private static int[] dc = { 0, 1, 0, -1 };

  private record DirectionCommands(int d, int count) {
  }

  private record Location(int row, int col) {
  }

  private static int T;
  private static int matrixSize;
  private static boolean[][] matrix;
  private static int jumperCount;
  private static int commandCount;
  private static List<DirectionCommands> commandLi;
  private static Location startLoc;

  public static void main(String[] args) {
    T = Integer.parseInt(sc.nextLine());
    for (int i = 1; i <= T; i++) {
      sb.append(String.format("#%d ", i));
      initProblems();
      solve();
    }

    System.out.println(sb.toString());

    sc.close();
  }

  private static void initProblems() {
    matrixSize = sc.nextInt();
    startLoc = new Location(sc.nextInt() - 1, sc.nextInt() - 1);
    jumperCount = sc.nextInt();
    matrix = new boolean[matrixSize][matrixSize];
    sc.nextLine();

    // 점퍼 좌표 받기기
    for (int i = 0; i < jumperCount; i++) {
      int x = sc.nextInt() - 1;
      int y = sc.nextInt() - 1;

      matrix[x][y] = true;
    }
    sc.nextLine();

    commandCount = sc.nextInt();
    sc.nextLine();

    commandLi = new ArrayList<>();
    for (int i = 0; i < commandCount; i++) {
      commandLi.add(new DirectionCommands(sc.nextInt(), sc.nextInt()));
    }
    sc.nextLine();
  }

  private static void solve() {
    int curRow = startLoc.row;
    int curCol = startLoc.col;

    for (DirectionCommands command : commandLi) {
      if (curRow == 0 && curCol == 0)
        break;
      int directIdx = command.d - 1;
      int count = command.count;

      for (int i = 0; i < count; i++) {
        int nextRow = curRow + dr[directIdx];
        int nextCol = curCol + dc[directIdx];
        if (isInBounds(nextRow, nextCol) && !matrix[nextRow][nextCol]) {
          curCol = nextCol;
          curRow = nextRow;
        } else {
          curCol = 0;
          curRow = 0;
          break;
        }
      }
    }

    sb.append(String.format("%d %d\n", curRow, curCol));
  }

  private static boolean isInBounds(int row, int col) {
    return row >= 0 && col >= 0 && row < matrixSize && col < matrixSize;
  }

}
