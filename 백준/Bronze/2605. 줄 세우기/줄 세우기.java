import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;

public class Main {
	private static Scanner sc = new Scanner(System.in);
	private static int studentCount = 0;
	private static int[] insertArr;
	private static List<Integer> studentList = new ArrayList<>();
	private static StringBuilder sb = new StringBuilder();

	public static void main(String[] args) {
		initializeProblem();
		solve();
		System.out.println(sb);
	}

	private static void initializeProblem() {
		studentCount = sc.nextInt();
		sc.nextLine();

		insertArr = new int[studentCount];
		for (int i = 0; i < studentCount; i++) {
			insertArr[i] = sc.nextInt();
		}
	}
	
	private static void solve() {
		int studentNumber = 1;
		
		for(int i =0;i<studentCount; i++) {
				studentList.add(insertArr[i], studentNumber++);
		}
		
		Collections.reverse(studentList);
		
		for(int e : studentList) {
			sb.append(e).append(" ");
		}
	}
}
