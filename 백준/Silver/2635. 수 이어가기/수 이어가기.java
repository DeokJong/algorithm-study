import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main{
	private static Scanner sc = new Scanner(System.in);
	private static int[] ans;
	public static void main(String[] args) {
		solve();
		System.out.printf("%d\n",ans.length);
		for(int e : ans) {
			System.out.print(e + " ");
		}
		sc.close();
	}
	
	private static void solve() {
		ans = new int[] {};
		int num = sc.nextInt();
		
		for(int initDiff = 1; initDiff<=num; initDiff++ ) {
			List<Integer> li = new ArrayList<Integer>();
			li.add(num);
			li.add(initDiff);
			
			for(int j =2; ;j++) {
				int nextNum = li.get(j-2) - li.get(j-1);
				if(nextNum < 0) break;
				li.add(nextNum);
			}
			if(li.size()>ans.length) {
				ans = li.stream().mapToInt(Integer::intValue).toArray();
			}
		}
	}
}