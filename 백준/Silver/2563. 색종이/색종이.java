import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

public class Main {
	private static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	private static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
	private static int colorPaperCount;
	private static int[][] whitePaper = new int[101][101];
	private static int colorPointCount;
	
	public static void main(String[] args) throws IOException {
		solve();
		bw.append(String.valueOf(colorPointCount));
		bw.flush();
		br.close();
		bw.close();
	}
	
	private static void solve() throws NumberFormatException, IOException {
		colorPaperCount = Integer.parseInt(br.readLine().trim());
		for(int i =0;i<colorPaperCount;i++) {
			String[] inputs = br.readLine().trim().split(" ");
			paintWhitePaper(Integer.parseInt(inputs[0]), Integer.parseInt(inputs[1]));
		}
		
		for(int i =1;i<=100;i++) {
			for(int j =1;j<=100;j++) {
				if(whitePaper[i][j]==1) colorPointCount++;
			}
		}
	}
	
	private static void paintWhitePaper(int x ,int y) {
		for(int i =0;i<10;i++) {
			for(int j =0;j<10;j++) {
				whitePaper[i+x][j+y]=1;
			}
		}
	}
}