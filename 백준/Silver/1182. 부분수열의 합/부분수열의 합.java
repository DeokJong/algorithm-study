import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int N = sc.nextInt();
        int S = sc.nextInt();
        int[] arr = new int[N];

        for(int i = 0; i < N; i++) {
            arr[i] = sc.nextInt();
        }

        int count = 0;

        // 부분 수열은 공집합을 제외해야 하므로 mask는 1부터 시작
        // (mask & (1 << i))를 통해 i번째 원소를 부분 수열에 포함하는지 확인
        for(int mask = 1; mask < (1 << N); mask++) {
            int sum = 0;
            for(int i = 0; i < N; i++) {
                // mask의 i번째 비트가 1이면 arr[i]를 포함
                if((mask & (1 << i)) != 0) {
                    sum += arr[i];
                }
            }
            if(sum == S) {
                count++;
            }
        }

        System.out.println(count);
    }
}
