import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

public class Main {

    public static void main(String[] args) throws NumberFormatException, IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        int count = Integer.parseInt(br.readLine());
        int bit = 0;
        int MAX_IDX = 20;

        for (int i = 0; i < count; i++) {
            String[] inputs = br.readLine().trim().split(" ");
            String command = inputs[0];
            int curbit = (inputs.length > 1) ? (1 << Integer.parseInt(inputs[1])) : 0;

            if ("add".equals(command)) {
                bit |= curbit;
            } else if ("remove".equals(command)) {
                bit &= ~curbit;
            } else if ("check".equals(command)) {
                sb.append((bit & curbit) != 0 ? 1 : 0).append('\n');
            } else if ("toggle".equals(command)) {
                bit ^= curbit;
            } else if ("all".equals(command)) {
                bit = (1 << (MAX_IDX + 1)) - 1;  // 0 ~ 20번 비트를 모두 켬
            } else if ("empty".equals(command)) {
                bit = 0;
            }
        }

        bw.write(sb.toString());
        bw.flush();
        bw.close();
        br.close();
    }
}
