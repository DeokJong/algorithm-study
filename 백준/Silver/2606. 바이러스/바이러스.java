import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.List;
import java.util.Queue;

public class Main {
	private static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	private static int pcCount;
	private static int edgeCount;
	private static List<List<Integer>> graph;
	private static Queue<Integer> pcQueue = new ArrayDeque<>();
	private static int virusCount = -1;
	private static boolean[] visited;
	
	public static void main(String[] args) throws IOException {
    initializeProblem();
    sovle();
    System.out.println(virusCount != -1 ? virusCount : 0);
	}
	
	private static void initializeProblem() throws IOException {
		pcCount = Integer.parseInt(br.readLine());
		edgeCount = Integer.parseInt(br.readLine());
		
		visited = new boolean[pcCount];
		graph = new ArrayList<>();
		for (int i = 0; i < pcCount; i++) {
		    graph.add(new ArrayList<>());
		}
		
		for(int i =0;i<edgeCount; i++) {
      String[] input = br.readLine().split(" ");
      int p1 = Integer.parseInt(input[0])-1;
      int p2 = Integer.parseInt(input[1])-1;
      
      graph.get(p1).add(p2);
      graph.get(p2).add(p1);
		}
	}
	
	private static void sovle() {
		pcQueue.add(0);
		
		while (!pcQueue.isEmpty()) {
			int pc = pcQueue.poll();
			
			if (visited[pc]) {
				continue;
			} else {
				visited[pc] = true;
				virusCount++;
				
				for(int neighbor : graph.get(pc)) {
					if(!visited[neighbor]) {
						pcQueue.add(neighbor);
					}
				}
			}
			
		}
	}
}
