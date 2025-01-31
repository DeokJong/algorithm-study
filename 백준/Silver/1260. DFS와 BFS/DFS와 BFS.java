import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Queue;
import java.util.Stack;
import static java.lang.Integer.parseInt;

public class Main {
	private static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	private static StringBuilder sb = new StringBuilder();

	private static int nodeCount, edgeCount, startNode;
	private static List<List<Integer>> graph;

	private static Queue<Integer> bfsQueue = new ArrayDeque<>();
	private static boolean[] visitedBfs;
	private static Stack<Integer> dfsStack = new Stack<>();
	private static boolean[] visitedDfs;

	public static void main(String[] args) throws IOException {
		initializeProblem();
		solve();
		System.out.println(sb);
	}

	private static void initializeProblem() throws IOException {
		String[] input = br.readLine().trim().split(" ");
		nodeCount = parseInt(input[0]);
		edgeCount = parseInt(input[1]);
		startNode = parseInt(input[2]) - 1;
		
		visitedBfs = new boolean[nodeCount];
		visitedDfs = new boolean[nodeCount];

		graph = new ArrayList<>();
		for (int i = 0; i < nodeCount; i++) {
			graph.add(new ArrayList<>());
		}
		
		for(int i =0;i<edgeCount;i++) {
			input = br.readLine().trim().split(" ");
			int n1 = parseInt(input[0])-1;
			int n2 = parseInt(input[1])-1;
			graph.get(n1).add(n2);
			graph.get(n2).add(n1);
		}
		
		for (int i = 0; i < nodeCount; i++) {
			Collections.sort(graph.get(i));
			Collections.reverse(graph.get(i));
		}

	}
	
	private static void solve() {
		dfs();
		for (int i = 0; i < nodeCount; i++) {
			Collections.reverse(graph.get(i));
		}
		bfs();
	}
	
	private static void dfs() {
		dfsStack.add(startNode);
		
		while (!dfsStack.isEmpty()) {
			int node = dfsStack.pop();
			
			if(visitedDfs[node]) continue;
			else {
				visitedDfs[node] = true;
				sb.append(node+1).append(" ");
				
				for(int neighbor : graph.get(node)) {
					if(!visitedDfs[neighbor]) dfsStack.add(neighbor);
				}
			}
		}
		sb.append("\n");
	}
	
	private static void bfs() {
		bfsQueue.add(startNode);
		
		while (!bfsQueue.isEmpty()){
			int node = bfsQueue.poll();
			
			if(visitedBfs[node]) continue;
			else {
				 visitedBfs[node] = true;
				 
				 sb.append(node+1).append(" ");
					for(int neighbor : graph.get(node)) {
						if(!visitedBfs[neighbor]) bfsQueue.add(neighbor);
					}
			}
			
		}
		sb.append("\n");
	}
}
