import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
    private static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

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
            
            if (sb.length() >= regexLength && sb.substring(sb.length() - regexLength).equals(regex)) {
                sb.setLength(sb.length() - regexLength);
            }
        }

        if (sb.length() == 0) {
            System.out.println("FRULA");
        } else {
            System.out.println(sb.toString());
        }
    }
}
