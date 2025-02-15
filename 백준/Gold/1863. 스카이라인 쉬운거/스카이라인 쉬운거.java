import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Deque;

public class Main{
	private static int minBuildings;
	private static Deque<Integer> stack = new ArrayDeque<Integer>();
	private static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	public static void main(String[] args) throws NumberFormatException, IOException {
		solve();
		System.out.println(minBuildings);
	}
	
	private static void solve() throws NumberFormatException, IOException {
		int n = Integer.parseInt(br.readLine());
		
		for(int i =0;i<n;i++) {
			int buildingSize = Integer.parseInt(br.readLine().trim().split(" ")[1]);
			if(stack.isEmpty()) {
				if(buildingSize != 0) {
					stack.push(buildingSize);
				}
				continue;
			}
			
			// clean up
			if(!stack.isEmpty() && buildingSize == 0) {
				minBuildings += stack.size();
				stack.clear();
				continue;
			}
			
			if(stack.peek() < buildingSize) {
				stack.push(buildingSize);
			} else {
				while(!stack.isEmpty() && stack.peek() > buildingSize) {
					stack.pop();
					minBuildings++;
				}
				if(!stack.isEmpty() && stack.peek() != buildingSize) {
					stack.push(buildingSize);
				} else if (stack.isEmpty()) {
					stack.push(buildingSize);
				}
			}
		}
		
		// clean up
		minBuildings += stack.size();
		stack.clear();
	}
	
	
}