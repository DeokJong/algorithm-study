import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import static java.lang.Integer.parseInt;

public class Main {
	private static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	private static int nodeCount, edgeCount, startNode;
	private static boolean[] visited;
	private static List<List<Integer>> graph;
	private static int[] visitedNodeTime;
	private static int iter=1;

	public static void main(String[] args) throws IOException {
		initializeProblem();
		solve(startNode);
		for(int e : visitedNodeTime) {
			System.out.println(e);
		}
	}

	private static void initializeProblem() throws IOException {
		String[] input = br.readLine().trim().split(" ");
		nodeCount = parseInt(input[0]);
		edgeCount = parseInt(input[1]);
		startNode = parseInt(input[2]) - 1;

		visited = new boolean[nodeCount];
		visitedNodeTime = new int[nodeCount];
		
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
		
		for(int i =0;i<nodeCount;i++) {
			Collections.sort(graph.get(i));
		}
	}
	
	private static void solve(int startNode) {
		visited[startNode] = true;
		visitedNodeTime[startNode] = iter++;
		
		for (int neighbor : graph.get(startNode)) {
			if(!visited[neighbor]) {
				solve(neighbor);
			}
		}
	}

}
