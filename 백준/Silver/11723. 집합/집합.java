import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

public class Main {
	private static int bit;
	private static final String ADD = "add";
	private static final String REMOVE = "remove";
	private static final String CHECK = "check";
	private static final String TOGGLE = "toggle";
	private static final String ALL = "all";
	private static final String EMPTY = "empty";
	private static final int MAX_IDX = 20;

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		int count = Integer.parseInt(br.readLine());
		for (int i = 0; i < count; i++) {
			String[] inputs = br.readLine().trim().split(" ");
			String command = inputs[0];
			int curbit = inputs.length > 1 ? 1 << Integer.parseInt(inputs[1]) : 0;

			switch (command) {
			case ADD:
				bit |= curbit;
				break;
			case REMOVE:
				bit &= ~curbit;
				break;
			case CHECK:
				bw.append(String.valueOf((bit & curbit) != 0 ? 1 : 0)).append('\n');
				break;
			case TOGGLE:
				bit ^= curbit;
				break;
			case ALL:
				bit = (1<<MAX_IDX+1)-1;
				break;
			case EMPTY:
				bit = 0;
				break;
			}
		}
		
		bw.close();
		br.close();
	}
}
