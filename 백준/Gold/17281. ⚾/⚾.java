import static java.lang.Integer.parseInt;
import java.io.*;
import java.util.*;

public class Main {
    static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    
    static int iningCount;
    static int[][] playerOperates;
    static int[] batterOrder;
    static int maxScore;
    
    public static void main(String[] args) throws IOException {
        initializeProblem();
        
        // 타순 배열 초기화: 1번 선수(내부적으로 0번)를 4번 타자(인덱스 3)에 고정
        batterOrder = new int[9];
        batterOrder[3] = 0;
        maxScore = 0;
        solve(0, 1 << 0);
        
        bw.write(String.valueOf(maxScore));
        bw.close();
        br.close();
    }
    
    // idx: 현재 타순 배치 인덱스, masking: 이미 배치한 선수 표시 (비트마스크)
    static void solve(int idx, int masking) {
        if (idx == 9) {
            simulateGame();
            return;
        }
        if (idx == 3) { // 4번 타자는 이미 고정되어 있으므로 건너뜀
            solve(idx + 1, masking);
            return;
        }
        
        for (int player = 1; player < 9; player++) {
            if ((masking & (1 << player)) != 0) continue;
            batterOrder[idx] = player;
            solve(idx + 1, masking | (1 << player));
        }
    }
    
    // 게임 시뮬레이션: 베이스 상태를 정수(비트마스크)로 관리
    static void simulateGame() {
        int curInning = 0;
        int score = 0;
        int outCount = 0;
        int baseState = 0;  // 비트 0: 1루, 1: 2루, 2: 3루 (값 0이면 빈 베이스)
        
        int curBatterIdx = 0;
        
        while (curInning < iningCount) {
            int player = batterOrder[curBatterIdx];
            int result = playerOperates[curInning][player];
            
            if (result == 0) { // 아웃
                outCount++;
                if (outCount == 3) {
                    outCount = 0;
                    baseState = 0;
                    curInning++;
                }
            } else if (result >= 1 && result <= 3) { // 안타, 2루타, 3루타
                // 기존 주자들이 result만큼 진루: baseState를 왼쪽으로 result비트 시프트
                int shifted = baseState << result;
                // 3루를 넘어간 주자들은 득점
                score += Integer.bitCount(shifted & (~7));
                // 3루 이하의 비트만 남김
                baseState = shifted & 7;
                // 타자: result에 따라 해당 베이스(1루:result=1, 2루:result=2, 3루:result=3)에 배치
                baseState |= (1 << (result - 1));
            } else if (result == 4) { // 홈런: 모든 주자와 타자 득점
                score += Integer.bitCount(baseState) + 1;
                baseState = 0;
            }
            
            curBatterIdx = (curBatterIdx + 1) % 9;
        }
        
        maxScore = Math.max(maxScore, score);
    }
    
    static void initializeProblem() throws IOException {
        iningCount = parseInt(br.readLine());
        playerOperates = new int[iningCount][9];
        for (int i = 0; i < iningCount; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            for (int j = 0; j < 9; j++) {
                playerOperates[i][j] = parseInt(st.nextToken());
            }
        }
    }
}
