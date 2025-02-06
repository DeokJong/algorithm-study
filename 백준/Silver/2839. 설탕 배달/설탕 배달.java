import static java.lang.Integer.parseInt;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

public class Main {
	private static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	private static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
	private static int kg;
	private static int[] initArr = new int[] {0,0,0,1,0,1,2,0,2,3,2,3,4,3,4,3};

	public static void main(String[] args) throws IOException {
		kg = parseInt(br.readLine());

		if (kg <= 15) {
			bw.append(String.valueOf(initArr[kg] == 0 ? -1 :initArr[kg]));
		} else {
			int[] tempArr = new int[kg+1];
			
			for(int i =0;i<initArr.length;i++) {
				tempArr[i] = initArr[i];
			}
			
			for(int i =16;i<=kg;i++) {
				tempArr[i] = tempArr[i-5] +1;
			}
			bw.append(String.valueOf(tempArr[kg]));
		}

		bw.flush();

		br.close();
		bw.close();
	}
	
	private static int dp() {
		return 0;
	}
}
