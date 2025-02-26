import static java.lang.Integer.parseInt;

import java.io.*;
import java.lang.reflect.Array;
import java.util.*;

public class Main {

	private static final BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	private static final BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
	private static final StringBuilder sb = new StringBuilder();
	private static int vertexCount;
	private static int edgeCount;
	private static List<List<Integer>> graph;

	private static int[] remainCount;
	private static int[] lectureSemester;
	// 0: 후수과목 번호 ; 1: 선수과목을 들은 학기
	private static Queue<int[]> que;

	public static void main(String[] args) throws IOException {
		initializeProblem();
		solve();
		System.out.println(sb);
		bw.close();
		br.close();
	}

	private static void initializeProblem() throws NumberFormatException, IOException {
		StringTokenizer st = new StringTokenizer(br.readLine());
		vertexCount = parseInt(st.nextToken());
		edgeCount = parseInt(st.nextToken());

		graph = new ArrayList<>();
		for (int i = 0; i < vertexCount; i++) {
			graph.add(new ArrayList<Integer>());
		}
		
		remainCount = new int[vertexCount];
		lectureSemester = new int[vertexCount];
		
		for (int i = 0; i < edgeCount; i++) {
			st = new StringTokenizer(br.readLine());
			int pre = parseInt(st.nextToken()) - 1;
			int post = parseInt(st.nextToken()) - 1;
			graph.get(pre).add(post);
			remainCount[post]++;
		}
		
		Arrays.fill(lectureSemester, 1);

		que = new ArrayDeque<>();		
		for(int i =0;i<vertexCount;i++) {
			if(remainCount[i] == 0){
				for(int post : graph.get(i)) {
					que.add(new int[] {post,1});
				}
			}
		}
	}

	private static void solve() throws IOException {
		while(!que.isEmpty()) {
			int[] info = que.poll();
			int postLectureNumber = info[0];
			int preLectureSemester= info[1];
			
			remainCount[postLectureNumber]--;
			lectureSemester[postLectureNumber] = Math.max(lectureSemester[postLectureNumber], preLectureSemester+1);
			
			if(remainCount[postLectureNumber] == 0) {
				for(int post : graph.get(postLectureNumber)) {
					que.add(new int[] {post,lectureSemester[postLectureNumber]});
				}
			}
		}
		
		for(int e : lectureSemester) {
			sb.append(e).append(' ');
		}
	}
}
