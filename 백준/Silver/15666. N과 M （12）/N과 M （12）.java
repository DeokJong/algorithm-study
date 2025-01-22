import java.util.HashSet;
import java.util.Scanner;
import java.util.Set;

public class Main {
	private static Scanner sc = new Scanner(System.in);
	private static int N,M;
	private static int[] integer;
	private static int[] selectedInteger;
	private static StringBuilder sb = new StringBuilder();
	
	public static void main(String[] args) {
		initializeProblem();
		solve(0,-1);
		System.out.println(sb.toString());
	}

	private static void initializeProblem() {
		N = sc.nextInt();
		M = sc.nextInt();
		sc.nextLine();
		
		Set<Integer> set = new HashSet<>();
		for(int i =0;i<N;i++) {
			set.add(sc.nextInt());
		}
		
		integer = set.stream().mapToInt(Integer::valueOf).sorted().toArray();
		selectedInteger = new int[M];
	}
	
	private static void solve(int depth, int prevNumber) {
		if(depth == M) {
			for(int item: selectedInteger) {
				sb.append(String.format("%d ", item));
			}
			sb.append("\n");
			return ;
		}
		
		for(int i =0;i<integer.length; i++) {
			if(prevNumber <= integer[i]) {
				selectedInteger[depth] = integer[i];
				solve(depth+1,integer[i]);
				selectedInteger[depth] =0;
			}
		}
	}
}
