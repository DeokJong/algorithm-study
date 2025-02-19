import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringBuilder sb = new StringBuilder();
    static int N;
    static int size;
    public static void main(String[] args) throws IOException {
    	initializeProblem();
    	solve(0,0, new int[size]);
    	System.out.println(sb);
    }

    private static void initializeProblem() throws IOException {
    	String[] inputs = br.readLine().trim().split(" ");
    	N = Integer.parseInt(inputs[0]);
    	size = Integer.parseInt(inputs[1]);
    }
    
    private static void solve(int mask,int depth, int[] arr) {
    	if(depth == size) {
    		for(int e : arr) {
    			sb.append(e).append(" ");
    		}
    		sb.append("\n");
    		return ;
    	}
        
    	for(int i =0;i<N;i++) {
    		int curBit = 1<<i;
    		if((mask & curBit) == 0 ) {
    			arr[depth]=i+1;
    			solve(mask | curBit, depth+1,arr);
    		}
    	}
    }

}
