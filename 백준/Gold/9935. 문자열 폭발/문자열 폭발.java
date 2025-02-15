import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

public class Main {
    private static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    private static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

    public static void main(String[] args) throws IOException {
        solve();
    }

    private static void solve() throws IOException {
        String org = br.readLine();
        String regex = br.readLine();
        int regexLength = regex.length();

        StringBuilder sb = new StringBuilder();

        for (char c : org.toCharArray()) {
            sb.append(c);

            if (sb.length() >= regexLength) {
                boolean match = true;
                for (int i = 0; i < regexLength; i++) {
                    if (sb.charAt(sb.length() - regexLength + i) != regex.charAt(i)) {
                        match = false;
                        break;
                    }
                }
                if (match) {
                    sb.setLength(sb.length() - regexLength);
                }
            }
        }

        if (sb.length() == 0) {
            bw.write("FRULA");
        } else {
            bw.write(sb.toString());
        }

        bw.flush();
        br.close();
        bw.close();
    }
}
