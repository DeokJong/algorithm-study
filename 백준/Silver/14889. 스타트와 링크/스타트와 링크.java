import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {
    static Scanner sc = new Scanner(System.in);
    static int matrixSize;
    static int[][] matrix;
    static int min = Integer.MAX_VALUE;

    public static void main(String[] args) {
        setMatrixSize();
        setMatrix();
        solve();
        System.out.println(min);
    }

    public static void setMatrixSize() {
        matrixSize = Integer.parseInt(sc.nextLine().trim());
    }

    public static void setMatrix() {
        matrix = new int[matrixSize][matrixSize];
        for (int i = 0; i < matrixSize; i++) {
            String[] row = sc.nextLine().trim().split(" ");
            for (int j = 0; j < matrixSize; j++) {
                matrix[i][j] = Integer.parseInt(row[j]);
            }
        }
    }

    public static void solve() {
        boolean[] visited = new boolean[matrixSize];
        backtracking(0, 0, visited);
    }

    public static void backtracking(int depth, int start, boolean[] visited) {
        if (depth == matrixSize / 2) {
            calculateTeamDifference(visited);
            return;
        }

        for (int i = start; i < matrixSize; i++) {
            if (!visited[i]) {
                visited[i] = true;
                backtracking(depth + 1, i + 1, visited);
                visited[i] = false;
            }
        }
    }

    public static void calculateTeamDifference(boolean[] visited) {
        List<Integer> teamStart = new ArrayList<>();
        List<Integer> teamLink = new ArrayList<>();

        for (int i = 0; i < matrixSize; i++) {
            if (visited[i]) {
                teamStart.add(i);
            } else {
                teamLink.add(i);
            }
        }

        int startScore = calculateScore(teamStart);
        int linkScore = calculateScore(teamLink);

        min = Math.min(min, Math.abs(startScore - linkScore));
    }

    public static int calculateScore(List<Integer> team) {
        int score = 0;
        for (int i = 0; i < team.size(); i++) {
            for (int j = 0; j < team.size(); j++) {
                if (i != j) {
                    score += matrix[team.get(i)][team.get(j)];
                }
            }
        }
        return score;
    }
}
