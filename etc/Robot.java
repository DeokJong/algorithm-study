import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Robot {
    private record Location(int x, int y) {}

    private static Scanner sc = new Scanner(System.in);
    private static StringBuilder sb = new StringBuilder();
    private static int T;

    private static boolean[][] matrix;
    private static int matrixSize;
    private static List<Location> aRobots;
    private static List<Location> bRobots;
    private static List<Location> cRobots;
    private static int count;

    // 이동 방향 정의 (상, 하, 좌, 우)
    private static final int[] dx = {-1, 1, 0, 0};  // 행(row) 이동 (위, 아래)
    private static final int[] dy = {0, 0, -1, 1};  // 열(column) 이동 (좌, 우)

    public static void main(String[] args) {
        T = sc.nextInt();
        sc.nextLine();

        for (int i = 1; i <= T; i++) {
            sb.append(String.format("#%d ", i));
            initCase();
            solve();
            sb.append(count).append("\n");
        }

        System.out.println(sb.toString().trim());
    }

    private static void initCase() {
        count = 0;
        matrixSize = sc.nextInt();
        sc.nextLine();

        matrix = new boolean[matrixSize][matrixSize];
        aRobots = new ArrayList<>();
        bRobots = new ArrayList<>();
        cRobots = new ArrayList<>();

        for (int i = 0; i < matrixSize; i++) {
            String[] ele = sc.nextLine().trim().split(" ");
            for (int j = 0; j < matrixSize; j++) {
                switch (ele[j]) {
                    case "A":
                        aRobots.add(new Location(i, j));
                        matrix[i][j] = true;
                        break;
                    case "B":
                        bRobots.add(new Location(i, j));
                        matrix[i][j] = true;
                        break;
                    case "C":
                        cRobots.add(new Location(i, j));
                        matrix[i][j] = true;
                        break;
                    case "W":
                        matrix[i][j] = true;
                        break;
                }
            }
        }
    }

    private static void solve() {
        for (Location loc : aRobots) {
            move(loc.x, loc.y, new int[]{3});  // 오른쪽만
        }

        for (Location loc : bRobots) {
            move(loc.x, loc.y, new int[]{2, 3});  // 좌우
        }

        for (Location loc : cRobots) {
            move(loc.x, loc.y, new int[]{0, 1, 2, 3});  // 상하좌우
        }
    }

    private static void move(int x, int y, int[] directions) {
        for (int dir : directions) {
            int nx = x + dx[dir];
            int ny = y + dy[dir];

            while (isInBounds(nx, ny) && !matrix[nx][ny]) {
                count++;
                nx += dx[dir];
                ny += dy[dir];
            }
        }
    }

    private static boolean isInBounds(int x, int y) {
        return x >= 0 && x < matrixSize && y >= 0 && y < matrixSize;
    }
}
