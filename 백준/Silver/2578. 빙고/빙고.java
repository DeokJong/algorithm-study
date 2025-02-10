import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayDeque;
import java.util.HashMap;
import java.util.Map;
import java.util.Queue;

public class Main {
    private static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    private static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    private static int iter = 0;
    private static int bingo = 0;
    private static Map<String, int[]> bingoMapper = new HashMap<>();
    private static Queue<String> que = new ArrayDeque<>();
    private static int[][] bingoMap = new int[5][5];

    public static void main(String[] args) throws IOException {
        initializeProbem();
        solve();
        bw.append(String.valueOf(iter));
        bw.flush();
        br.close();
        bw.close();
    }

    private static void initializeProbem() throws IOException {
        for (int i = 0; i < 5; i++) {
            String[] inputs = br.readLine().trim().split(" ");
            for (int j = 0; j < 5; j++) {
                bingoMap[i][j] = Integer.parseInt(inputs[j]);
                bingoMapper.put(String.valueOf(bingoMap[i][j]), new int[] { i, j });
            }
        }

        for (int i = 0; i < 5; i++) {
            String[] inputs = br.readLine().trim().split(" ");
            for (int j = 0; j < 5; j++) {
                que.add(inputs[j]);
            }
        }
    }

    private static void solve() {
        while (bingo < 3) {
            String currentNum = que.poll();
            int[] loc = bingoMapper.get(currentNum);
            int r = loc[0];
            int c = loc[1];
            bingoMap[r][c] = 0;
            iter++;
            if (5 <= iter) {
                checkBingo();
            }
        }
    }
    
    private static void checkBingo() {
    	bingo=0;
    	// row
		for(int r =0;r<5;r++) {
			for(int c =0;c<5;c++) {
				if(bingoMap[r][c] !=0) break;
				if(c == 4) bingo++;
			}
		}
		
    	// column
		for(int c =0;c<5;c++) {
			for(int r =0;r<5;r++) {
				if(bingoMap[r][c] !=0) break;
				if(r == 4) bingo++;
			}
		}
		
		for(int di = 0;di<5;di++) {
			if(bingoMap[di][di] != 0) break;
			if (di==4) bingo++;
		}
		
		for(int di = 0;di<5;di++) {
			if(bingoMap[di][5-(di+1)] != 0) break;
			if (di==4) bingo++;
		}
	}
}