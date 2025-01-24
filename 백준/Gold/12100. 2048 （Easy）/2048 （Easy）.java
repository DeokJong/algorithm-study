import java.util.Arrays;
import java.util.Scanner;
import java.util.Stack;

public class Main {
  @FunctionalInterface
  private interface PushFunction {
    public void push();
  }

  private static PushFunction[] pushFuncs = {
      Main::pushDown,
      Main::pushUp,
      Main::pushLeft,
      Main::pushRight
  };
  private static Scanner sc = new Scanner(System.in);
  private static Stack<Integer> stack = new Stack<>();
  private static int boardSize;
  private static int[][] matrix;
  private static int max = Integer.MIN_VALUE;

  public static void main(String[] args) {
    initializeProblem();
    solve(0);
    System.out.println(max);
  }

  private static void initializeProblem() {
    boardSize = sc.nextInt();

    matrix = new int[boardSize][boardSize];

    for (int i = 0; i < boardSize; i++) {
      for (int j = 0; j < boardSize; j++) {
        matrix[i][j] = sc.nextInt();
      }
      sc.nextLine();
    }
  }

  private static void solve(int depth) {
    if (depth == 5) {
      setMax();
      return;
    }

    for (PushFunction f : pushFuncs) {
      int[][] tempMatrix = new int[boardSize][boardSize];
      for (int i = 0; i < boardSize; i++) {
        tempMatrix[i] = matrix[i].clone();
      }
      f.push();
      solve(depth + 1);
      matrix = tempMatrix;
    }
  }

  private static void pushRight() {
    for (int r = 0; r < boardSize; r++) {
      // left index 부터 queue에 담기기
      for (int c = 0; c < boardSize; c++) {
        if (matrix[r][c] != 0) {
          stack.add(matrix[r][c]);
          matrix[r][c] = 0;
        }
      }

      // left index부터 queue에 담긴것 뽑아서 할당
      // 이후, 그 다음것과 현재 것이 같으면 두배로 할당.

      for (int c = boardSize - 1; -1 < c; c--) {
        if (stack.isEmpty()) {
          break;
        }

        int item = stack.pop();

        if (!stack.isEmpty() && stack.peek() == item) {
          matrix[r][c] = item + stack.pop();
        } else {
          matrix[r][c] = item;
        }
      }
    }
  }

  private static void pushLeft() {
    for (int r = 0; r < boardSize; r++) {
      // left index 부터 queue에 담기기
      for (int c = boardSize - 1; -1 < c; c--) {
        if (matrix[r][c] != 0) {
          stack.add(matrix[r][c]);
          matrix[r][c] = 0;
        }
      }

      // left index부터 queue에 담긴것 뽑아서 할당
      // 이후, 그 다음것과 현재 것이 같으면 두배로 할당.
      for (int c = 0; c < boardSize; c++) {
        if (stack.isEmpty()) {
          break;
        }

        int item = stack.pop();

        if (!stack.isEmpty() && stack.peek() == item) {
          matrix[r][c] = item + stack.pop();
        } else {
          matrix[r][c] = item;
        }
      }
    }
  }

  private static void pushUp() {
    for (int c = 0; c < boardSize; c++) {
      for (int r = boardSize - 1; -1 < r; r--) {
        if (matrix[r][c] != 0) {
          stack.add(matrix[r][c]);
          matrix[r][c] = 0;
        }
      }

      for (int r = 0; r < boardSize; r++) {
        if (stack.isEmpty()) {
          break;
        }

        int item = stack.pop();

        if (!stack.isEmpty() && stack.peek() == item) {
          matrix[r][c] = item + stack.pop();
        } else {
          matrix[r][c] = item;
        }
      }
    }
  }

  private static void pushDown() {
    for (int c = 0; c < boardSize; c++) {
      for (int r = 0; r < boardSize; r++) {
        if (matrix[r][c] != 0) {
          stack.add(matrix[r][c]);
          matrix[r][c] = 0;
        }
      }
      for (int r = boardSize - 1; -1 < r; r--) {
        if (stack.isEmpty()) {
          break;
        }

        int item = stack.pop();

        if (!stack.isEmpty() && stack.peek() == item) {
          matrix[r][c] = item + stack.pop();
        } else {
          matrix[r][c] = item;
        }
      }

    }
  }

  private static void setMax() {
    for (int[] r : matrix) {
      max = Math.max(max, Arrays.stream(r).max().getAsInt());
    }
  }
}