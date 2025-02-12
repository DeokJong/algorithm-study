import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class Main {
    private static final String YES = "YES";
    private static final String NO = "NO";

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        
        int N = Integer.parseInt(br.readLine().trim());
        int[] trees = Arrays.stream(br.readLine().split(" "))
                            .mapToInt(Integer::parseInt)
                            .toArray();

        long sum = 0;
        long capacity = 0;   // 각 나무에 +2 물뿌리개를 사용할 수 있는 '최대 횟수'들의 합

        for (int h : trees) {
            sum += h;
            capacity += (h / 2);  // floor(h/2)
        }

        // 1. 전체 합이 3의 배수가 아니면 불가능
        if (sum % 3 != 0) {
            System.out.println(NO);
            return;
        }

        // 2. (+2) 물뿌리개를 sum/3 번 사용해야 하는데, 그만큼 배분 가능?
        long need = sum / 3;
        if (capacity >= need) {
            System.out.println(YES);
        } else {
            System.out.println(NO);
        }
    }
}
