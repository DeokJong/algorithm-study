import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Queue;

import static java.lang.Integer.parseInt;

public class Main {
	private static BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));

	private static int nodeCount, edgeCount, startNode;
	private static List<List<Integer>> adjacencyList;

	private static boolean[] visited;
	private static int[] visitedNodeTime;
	private static Queue<Integer> nodeQueue = new ArrayDeque<>();
	private static StringBuilder sb = new StringBuilder();
	private static int iter = 1;

	public static void main(String[] args) throws IOException {
		initializeProblem();
		solve(startNode);
		for(int e : visitedNodeTime) {
			sb.append(e).append("\n");
		}
		System.out.println(sb);
	}

	private static void initializeProblem() throws IOException {
		String[] input = bufferedReader.readLine().trim().split(" ");

		nodeCount = parseInt(input[0]);
		edgeCount = parseInt(input[1]);
		startNode = parseInt(input[2]) - 1;

		visited = new boolean[nodeCount];
		visitedNodeTime = new int[nodeCount];

		adjacencyList = new ArrayList<>();
		for (int i = 0; i < nodeCount; i++) {
			adjacencyList.add(new ArrayList<>());
		}

		for (int i = 0; i < edgeCount; i++) {
			input = bufferedReader.readLine().trim().split(" ");
			int n1 = parseInt(input[0]) - 1;
			int n2 = parseInt(input[1]) - 1;

			adjacencyList.get(n1).add(n2);
			adjacencyList.get(n2).add(n1);
		}
		
		for(List<Integer> list : adjacencyList) {
			Collections.sort(list);
		}
	}
	
	private static void solve(int startNode) {
		visited[startNode] = true;
		visitedNodeTime[startNode] =iter++;
		
		nodeQueue.add(startNode);
		while(!nodeQueue.isEmpty()) {
			int node = nodeQueue.poll();
			for (int e : adjacencyList.get(node)) {
				if(!visited[e]) {
					visited[e] = true;
					visitedNodeTime[e] = iter++;
					nodeQueue.add(e);
				}
			}
		}
	}
}
