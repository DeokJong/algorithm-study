import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

public class Main {
	private static Scanner sc = new Scanner(System.in);
	
	public static class Edge {
		int start;
		int end;
		public Edge(int start, int end) {
			super();
			this.start = start;
			this.end = end;
		}
		public int getStart() {
			return start;
		}
		public void setStart(int start) {
			this.start = start;
		}
		public int getEnd() {
			return end;
		}
		public void setEnd(int end) {
			this.end = end;
		}
	}
	
	private static int pcCount;
	private static int edgeCount;
	private static Edge[] edges;
	private static boolean[] visited;
	private static Queue<Integer> queue = new LinkedList<>();
	private static int viruscount = -1;
	
	public static void main(String[] args) {
		initializeProblem();
		solve();
		System.out.println(viruscount != -1 ? viruscount : 0);
	}
	
	private static void initializeProblem() {
		pcCount = sc.nextInt();
		sc.nextLine();
		visited = new boolean[pcCount];
		
		edgeCount = sc.nextInt();
		sc.nextLine();
		edges = new Edge[edgeCount];
		
		for(int i =0;i<edgeCount;i++) {
			edges[i] = new Edge(sc.nextInt()-1, sc.nextInt()-1);
			sc.nextLine();
		}
	}
	
	private static void solve() {
		queue.add(0);
		
		while (!queue.isEmpty()){
			int pc = queue.poll();
			
			if(visited[pc]) {
				continue ;
			} else {
				visited[pc] = true;
				viruscount++;
				for(Edge edge : edges) {
					if (edge.getStart() == pc && !visited[edge.getEnd()]) {
						queue.add(edge.getEnd());
					} else if (edge.getEnd() == pc && !visited[edge.getStart()]) {
						queue.add(edge.getStart());
					}
				}
			}
			
		}
	}
}
