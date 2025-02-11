import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Arrays;

public class Main {
	private static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	private static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
	private static int[] orderDp;
	private static int[] unOrderDp;
	private static int[] arr;
	private static int length;
	private static int maxLength;

	public static void main(String[] args) throws NumberFormatException, IOException {
		initializeProblem();
		solve();
		bw.append(String.valueOf(maxLength));
		bw.flush();
		br.close();
		bw.close();
	}

	public static void initializeProblem() throws NumberFormatException, IOException {
		length = Integer.parseInt(br.readLine().trim());
		orderDp = new int[length];
		unOrderDp = new int[length];
		arr = new int[length];

		String[] inputs = br.readLine().trim().split(" ");
		for (int i = 0; i < length; i++) {
			arr[i] = Integer.parseInt(inputs[i]);
		}
	}

	public static void solve() {
		Arrays.fill(orderDp, 1);
		Arrays.fill(unOrderDp, 1);
		int orderMaxLength = 1;
		int unOrderMaxLength = 1;
		for(int i =1;i<length;i++) {
			if(arr[i-1] <= arr[i]) {
				orderDp[i] = orderDp[i-1]+1;
			}
			
			orderMaxLength = Math.max(orderMaxLength, orderDp[i]);
		}
		
		for(int i =1;i<length;i++) {
			if(arr[i-1] >= arr[i]) {
				unOrderDp[i] = unOrderDp[i-1]+1;
			}
			unOrderMaxLength= Math.max(unOrderMaxLength, unOrderDp[i]);
		}
		
		maxLength = Math.max(orderMaxLength, unOrderMaxLength);
	}
}