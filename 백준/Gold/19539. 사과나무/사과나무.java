import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class Main {
	private static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	private static int[] appleTrees;
	private static String YES = "YES";
	private static String NO = "NO";
	private static int treeCount;

	public static void main(String[] args) throws IOException {
		initializeProblem();
		System.out.println(solve(0,0,0) ? YES : NO);
	}

	private static void initializeProblem() throws IOException {
		br.readLine();
		appleTrees = Arrays.stream(br.readLine().trim().split(" ")).mapToInt(Integer::parseInt).filter(x -> x!=0).sorted().toArray();
		treeCount=appleTrees.length;
	}

	private static boolean solve(int idx, int remainOneCount, int remainTwoCount ) {
		if(idx == treeCount) {
			if(remainOneCount==0 && remainTwoCount == 0) {
				return true;
			} else {
				return false;
			}
		}
		
		int value = appleTrees[idx];
		
		if(remainOneCount==0 && remainTwoCount == 0) {
			int remainValue = value%3;
			if(remainValue == 1) {
				return solve(idx+1, 0, 1);
			} else if(remainValue == 2) {
				return solve(idx+1, 1, 0);
			} else {
				return solve(idx+1, 0, 0);
			}
		} else {
			int ableTwoCount = value/2;
			int remainValue = value;
			
			if(ableTwoCount >= remainTwoCount) {
				remainValue -= remainTwoCount*2;
				remainTwoCount=0;
			} else {
				remainValue -= ableTwoCount*2;
				remainTwoCount-=ableTwoCount;
			}
			
			
			int ableOneCount = remainValue;
			if(ableOneCount >= remainOneCount) {
				remainValue -= remainOneCount;
				remainOneCount=0;
			} else {
				remainValue -= ableOneCount;
				remainOneCount-=ableOneCount;
			}
			
			
			remainValue = remainValue%3;
			if(remainValue == 1) {
				return solve(idx+1, remainOneCount, remainTwoCount+1);
			} else if(remainValue == 2) {
				return solve(idx+1, remainOneCount+1, remainTwoCount+0);
			} else {
				return solve(idx+1, remainOneCount, remainTwoCount);
			}
		}
	}
}