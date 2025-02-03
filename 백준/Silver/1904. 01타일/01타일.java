import java.io.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        int n = Integer.parseInt(br.readLine());
        if (n == 1) {
            bw.write("1\n");
        } else if (n == 2) {
            bw.write("2\n");
        } else {
            int prev2 = 1, prev1 = 2, current = 0;
            for (int i = 3; i <= n; i++) {
                current = (prev1 + prev2) % 15746;
                prev2 = prev1;
                prev1 = current;
            }
            bw.write(current + "\n");
        }
        
        bw.flush();
        br.close();
        bw.close();
    }
}
