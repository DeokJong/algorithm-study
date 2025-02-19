import static java.lang.Integer.parseInt;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.StringTokenizer;

public class Solution {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    
    // 왼쪽 카드와 오른쪽 카드 배열
    static int[] leftCards;
    static int[] rightCards;
    
    // dp 메모이제이션: key = "mask,diff", value = {왼쪽 승리 횟수, 오른쪽 승리 횟수}
    static HashMap<String, int[]> dp;
    
    public static void main(String[] args) throws IOException {
        int T = parseInt(br.readLine());
        for (int t = 1; t <= T; t++) {
            initializeProblem();
            dp = new HashMap<>();
            // 초기 상태: mask=0, depth=0, diff=0
            int[] result = solve(0, 0, 0);
            System.out.println("#" + t + " " + result[0] + " " + result[1]);
        }
    }
    
    // 문제 초기화: 왼쪽 카드 입력 및 오른쪽 카드 생성(1~18 중 사용되지 않은 카드)
    private static void initializeProblem() throws IOException {
        leftCards = new int[9];
        StringTokenizer st = new StringTokenizer(br.readLine());
        for (int j = 0; j < 9; j++) {
            leftCards[j] = parseInt(st.nextToken());
        }
        boolean[] used = new boolean[19];  // 1부터 18까지
        for (int card : leftCards) {
            used[card] = true;
        }
        rightCards = new int[9];
        int idx = 0;
        for (int num = 1; num <= 18; num++) {
            if (!used[num]) {
                rightCards[idx++] = num;
            }
        }
    }
    
    /**
     * dp(메모이제이션)을 적용한 재귀 함수
     * 
     * @param mask  오른쪽 카드 사용 여부를 비트마스크로 표현 (9자리)
     * @param depth 현재까지 진행한 라운드 (0~9; 사실 mask에 설정된 비트 개수와 동일)
     * @param diff  현재까지의 점수 차 (leftSum - rightSum)
     * @return int[] 배열 {왼쪽 승리 경우의 수, 오른쪽 승리 경우의 수}
     */
    private static int[] solve(int mask, int depth, int diff) {
        // 모든 라운드가 끝난 경우: 승리 여부에 따라 경우의 수를 반환
        if (depth == 9) {
            if (diff > 0)
                return new int[]{1, 0};
            else if (diff < 0)
                return new int[]{0, 1};
            else
                return new int[]{0, 0};  // 문제에 따라 무승부 처리 (여기서는 0으로 처리)
        }
        
        // dp 메모이제이션 키 구성 (diff가 음수일 수 있으므로 문자열로 구성)
        String key = mask + "," + diff;
        if (dp.containsKey(key)) {
            return dp.get(key);
        }
        
        int leftWins = 0;
        int rightWins = 0;
        int leftCard = leftCards[depth];
        
        // 오른쪽 카드 중 아직 사용하지 않은 카드에 대해 모든 경우의 수 탐색
        for (int r = 0; r < 9; r++) {
            int bit = 1 << r;
            if ((mask & bit) != 0)
                continue;
            int newMask = mask | bit;
            int rightCard = rightCards[r];
            int score = leftCard + rightCard;
            int newDiff = diff;
            if (leftCard > rightCard)
                newDiff += score;
            else
                newDiff -= score;
            
            int[] subResult = solve(newMask, depth + 1, newDiff);
            leftWins += subResult[0];
            rightWins += subResult[1];
        }
        
        int[] result = new int[]{leftWins, rightWins};
        dp.put(key, result);
        return result;
    }
}
