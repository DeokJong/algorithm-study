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
		int bit=0;
		int MAX_IDX = 20;
		for (int i = 0; i < count; i++) {
			String[] inputs = br.readLine().trim().split(" ");
			String command = inputs[0];
			int curbit = inputs.length > 1 ? 1 << Integer.parseInt(inputs[1]) : 0;

			switch (command) {
			case "add":
				bit |= curbit;
				break;
			case "remove":
				bit &= ~curbit;
				break;
			case "check":
				sb.append((bit & curbit) != 0 ? 1 : 0).append('\n');
				break;
			case "toggle":
				bit ^= curbit;
				break;
			case "all":
				bit = (1<<MAX_IDX+1)-1;
				break;
			case "empty":
				bit = 0;
				break;
			}
		}
		bw.append(sb.toString());
		bw.close();
		br.close();
	}
}
