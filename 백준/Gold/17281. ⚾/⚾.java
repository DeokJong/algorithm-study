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
    
    // idx: 현재 배치할 타순 인덱스, masking: 사용한 선수 표시 (비트마스크)
    static void solve(int idx, int masking) {
        if (idx == 9) {
            simulateGame();
            return;
        }
        if (idx == 3) { // 4번 타자는 이미 고정되어 있으므로 건너뛰기
            solve(idx + 1, masking);
            return;
        }
        
        for (int player = 1; player < 9; player++) {
            if ((masking & (1 << player)) != 0) continue;
            batterOrder[idx] = player;
            solve(idx + 1, masking | (1 << player));
        }
    }
    
    // 게임 시뮬레이션 (switch-case를 사용하여 오버헤드 최소화)
    static void simulateGame() {
        int curInning = 0;
        int score = 0;
        int curBatterIdx = 0;
        int outCount = 0;
        // 베이스: 0번, 1번, 2번 인덱스는 각각 1루, 2루, 3루의 상태 (0: 빈 상태, 1: 주자 있음)
        int[] base = new int[3];
        
        while (curInning < iningCount) {
            int player = batterOrder[curBatterIdx];
            int result = playerOperates[curInning][player];
            
            switch(result) {
                case 0: // 아웃
                    outCount++;
                    if (outCount == 3) {
                        outCount = 0;
                        base[0] = base[1] = base[2] = 0;
                        curInning++;
                    }
                    break;
                case 1: // 안타: 모든 주자 한 베이스씩 진루
                    score += base[2]; // 3루에 있던 주자는 득점
                    base[2] = base[1];
                    base[1] = base[0];
                    base[0] = 1;
                    break;
                case 2: // 2루타: 모든 주자 2베이스씩 진루
                    score += base[2] + base[1]; // 2루, 3루 주자는 득점
                    base[2] = base[0]; // 1루 주자는 3루로 이동
                    base[1] = 1;     // 타자는 2루에 위치
                    base[0] = 0;
                    break;
                case 3: // 3루타: 모든 주자 득점
                    score += base[2] + base[1] + base[0];
                    base[2] = 1; // 타자 3루에 위치
                    base[1] = 0;
                    base[0] = 0;
                    break;
                case 4: // 홈런: 모든 주자와 타자 득점
                    score += base[2] + base[1] + base[0] + 1;
                    base[0] = base[1] = base[2] = 0;
                    break;
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
