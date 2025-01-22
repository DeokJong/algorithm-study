import java.util.Arrays;
import java.util.Scanner;
import java.util.stream.IntStream;

public class Main {
	private static Scanner sc = new Scanner(System.in);
	private static int initNumbers;
	private static int maxMiniNumber;
	private static int[] reversIntegerArray;
	private static boolean[] isUsedReversIntegerArray;
	private static int[] selectedIntegerArray;
	
	public static void main(String[] args) {
		initializeProblems();
		if (initNumbers == intArrayToInt(reversIntegerArray)) {
			System.out.println("0");
		} else {
			solve(0);
			System.out.println(maxMiniNumber);
		}
		
		sc.close();
	}
	
	private static void initializeProblems() {
		String line = sc.nextLine();
		
		maxMiniNumber= Integer.MAX_VALUE;
		initNumbers = Integer.parseInt(line);
		selectedIntegerArray = new int[line.length()];
		isUsedReversIntegerArray = new boolean[line.length()];
		
		reversIntegerArray = new int[line.length()];
		for(int i =0;i<line.length();i++) {
			reversIntegerArray[i] = line.charAt(i) - '0';
		}
		
		
		reversIntegerArray = IntStream.of(reversIntegerArray)
				.boxed()
				.sorted((a,b) -> b-a)
				.mapToInt(Integer::intValue)
				.toArray();
	}
	
	private static void solve(int depth) {
		if (depth == reversIntegerArray.length) {
			int temp = Math.min(maxMiniNumber, intArrayToInt(selectedIntegerArray));
			
			if (temp > initNumbers) {
				maxMiniNumber = temp;
			}
		}
		
		for(int i =0;i<reversIntegerArray.length;i++) {
			if(!isUsedReversIntegerArray[i]) {
				isUsedReversIntegerArray[i] = true;
				selectedIntegerArray[depth] = reversIntegerArray[i]; 
				solve(depth+1);
				selectedIntegerArray[depth] = 0; 
				isUsedReversIntegerArray[i] = false;
			}
		}
		
	}
	
	private static int intArrayToInt(int[] arr) {
		StringBuilder sb = new StringBuilder();
		for(int item : arr) {
			sb.append(item);
		}
		
		return Integer.parseInt(sb.toString());
	}
}
