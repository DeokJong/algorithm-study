import static java.lang.Integer.parseInt;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class Main {
	private static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	private static List<List<Integer>> graph;
	private static List<List<Integer>> reversedGraph;
	private static int studentCount;
	private static int edgeCount;

	public static void main(String[] args) throws IOException {
		initializeProblem();
		System.out.println(solve());
	}

	private static void initializeProblem() throws IOException {
		String[] inputs = br.readLine().split(" ");
		studentCount = parseInt(inputs[0]);
		edgeCount = parseInt(inputs[1]);

		graph = new ArrayList<>();
		reversedGraph = new ArrayList<>();
		for (int i = 0; i < studentCount; i++) {
			graph.add(new ArrayList<>());
			reversedGraph.add(new ArrayList<>());
		}

		for (int i = 0; i < edgeCount; i++) {
			inputs = br.readLine().split(" ");
			int from = parseInt(inputs[0]) - 1;
			int end = parseInt(inputs[1]) - 1;
			graph.get(from).add(end);
			reversedGraph.get(end).add(from);
		}
	}

	private static int solve() {
		int count = 0;

		for (int i = 0; i < studentCount; i++) {
			boolean[] visited = new boolean[studentCount];
			visited[i] = true;
			DFS(i, visited);
			reverseDFS(i,visited);
			int visitedCount=0;
			for (int j = 0; j < studentCount; j++) {
				if (visited[j]) visitedCount++;
			}
			
			if(visitedCount == studentCount) count++;
		}

		return count;
	}

	private static void DFS(int from, boolean[] visited) {

		for (int e : graph.get(from)) {
			if (visited[e]) {
				continue;
			}
			visited[e] = true;
			DFS(e, visited);
		}
	}
	
	private static void reverseDFS(int from, boolean[] visited) {

		for (int e : reversedGraph.get(from)) {
			if (visited[e]) {
				continue;
			}
			visited[e] = true;
			reverseDFS(e, visited);
		}
	}
}