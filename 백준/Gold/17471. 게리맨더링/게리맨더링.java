import static java.lang.Integer.parseInt;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
	private static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	private static int[] pop;
	private static int[] section;
	private static boolean initNotAble;
	private static int nodeCount;
	private static List<List<Integer>> adjacencyList;
	private static int oneSectionCount;
	private static int twoSectionCount;
	private static int totalOneSection;
	private static int totalTwoSection;
	private static int minDiff = Integer.MAX_VALUE;
	private static boolean isPrune;

	public static void main(String[] args) throws NumberFormatException, IOException {
		initializeProblem();
		if (initNotAble) {
			System.out.println("-1");
		} else {
			solve(0);
			System.out.println(minDiff != Integer.MAX_VALUE ? minDiff : -1);
		}
	}

	private static void initializeProblem() throws NumberFormatException, IOException {
		nodeCount = parseInt(br.readLine().trim());
		pop = new int[nodeCount];
		section = new int[nodeCount];
		adjacencyList = new ArrayList<>();
		for (int i = 0; i < nodeCount; i++) {
			adjacencyList.add(new ArrayList<>());
		}

		StringTokenizer st = new StringTokenizer(br.readLine().trim());
		for (int i = 0; i < nodeCount; i++) {
			pop[i] = parseInt(st.nextToken());
		}

		for (int i = 0; i < nodeCount; i++) {
			st = new StringTokenizer(br.readLine().trim());
			int edgeCount = parseInt(st.nextToken());
			for (int j = 0; j < edgeCount; j++) {
				int neighbor = parseInt(st.nextToken()) - 1;
				adjacencyList.get(i).add(neighbor);
			}
		}
	}
	
	private static void solve(int depth) {
		if(isPrune) {
			return ;
		}
		if(depth == nodeCount) {
			if(oneSectionCount == 0 || twoSectionCount == 0) {
				return;
			}
			if(!isAbleContidion()) {
				return;
			}
			minDiff = Math.min(minDiff, Math.abs(totalOneSection-totalTwoSection));
			if(minDiff == 0) {
				isPrune = true;
			}
			return;
		}
		
		
		totalOneSection += pop[depth];
		oneSectionCount++;
		section[depth] = 1;
		solve(depth+1);
		oneSectionCount--;
		totalOneSection -= pop[depth];
		
		totalTwoSection += pop[depth];
		twoSectionCount++;
		section[depth]=2;
		solve(depth+1);
		twoSectionCount--;
		totalTwoSection -= pop[depth];
		
		
	}
	
	private static boolean isAbleContidion() {
		boolean[] visited = new boolean[nodeCount];
		int sectionOneNode=-1;
		int sectionTwoNode=-1;
		int sectionOneCount =0;
		int sectionTwoCount = 0;
		
		for(int i =0;i<nodeCount;i++) {
			if(section[i] == 1) {
				sectionOneNode=i;
			} else {
				sectionTwoNode=i;
			}
			
			if(sectionOneNode != -1 && sectionTwoNode != -1) break;
		}
		
		Queue<Integer> oneQue = new ArrayDeque<>();
		Queue<Integer> twoQue = new ArrayDeque<>();
		oneQue.add(sectionOneNode);
		twoQue.add(sectionTwoNode);
		
		while(!oneQue.isEmpty()) {
			int node = oneQue.poll();
			if(visited[node]) continue;
			visited[node]= true;
			sectionOneCount++;
			
			for(int n : adjacencyList.get(node)) {
				if(!visited[n] && section[n] == 1) {
					oneQue.add(n);
				}
			}
		}
		
		while(!twoQue.isEmpty()) {
			int node = twoQue.poll();
			if(visited[node]) continue;
			visited[node]= true;
			sectionTwoCount++;
			
			for(int n : adjacencyList.get(node)) {
				if(!visited[n] && section[n] == 2) {
					twoQue.add(n);
				}
			}
		}
		
		return nodeCount == sectionOneCount+sectionTwoCount;
	}
}