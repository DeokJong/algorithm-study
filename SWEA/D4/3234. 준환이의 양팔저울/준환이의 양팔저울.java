import static java.lang.Integer.parseInt;
import java.io.*;
import java.util.*;

public class Solution {
	private static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	private static int[] arr;
	private static boolean[] visited;

    public static void main(String[] args) throws NumberFormatException, IOException {
        int T = Integer.parseInt(br.readLine());
        for (int tc = 1; tc <= T; tc++) {
        		initializeProblem();
            int permutationCount = solve(0, 0, 0, arr, visited);
            System.out.printf("#%d %d\n", tc, permutationCount);
        }
    }
    
  	private static void initializeProblem() throws NumberFormatException, IOException {
  		int count = parseInt(br.readLine());
  		arr = new int[count];
  		visited = new boolean[count];
  		StringTokenizer st = new StringTokenizer(br.readLine());
  		for (int i = 0; i < count; i++) {
  			arr[i] = parseInt(st.nextToken());
  		}
  	}

    /**
     * @param depth     현재까지 선택한 숫자의 개수
     * @param leftSum   왼쪽에 더해진 값의 합
     * @param rightSum  오른쪽에 더해진 값의 합
     * @param arr       사용할 숫자 배열
     * @param visited   각 숫자의 사용 여부
     * @return          현재 상태에서 올바른 경우의 수
     */
    private static int solve(int depth, int leftSum, int rightSum, int[] arr, boolean[] visited) {
        // 왼쪽의 합이 오른쪽의 합보다 작으면 더 이상 진행할 필요가 없음
        if (leftSum < rightSum) {
            return 0;
        }
        // 모든 숫자를 사용했다면 하나의 경우를 찾은 것
        if (depth == arr.length) {
            return 1;
        }
        
        int count = 0;
        // 아직 사용하지 않은 숫자에 대해 모든 경우를 탐색
        for (int i = 0; i < arr.length; i++) {
            if (visited[i]) continue;
            visited[i] = true;
            // 왼쪽에 숫자를 더하는 경우
            count += solve(depth + 1, leftSum + arr[i], rightSum, arr, visited);
            // 오른쪽에 숫자를 더하는 경우
            count += solve(depth + 1, leftSum, rightSum + arr[i], arr, visited);
            visited[i] = false;
        }
        return count;
    }
}
