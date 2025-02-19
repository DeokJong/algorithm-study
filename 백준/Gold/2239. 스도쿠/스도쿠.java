import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class Main {
	private static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	private static int map[][]=new int[9][9];
	private static int[][] notAllocatedArr;

	public static void main(String[] args) throws IOException {
		initializeProblem();
		solve(0);
		for(int[] r:map) {
			for(int e:r) {
				System.out.print(e);
			}
			System.out.println();
		}
	}
	
	private static boolean solve(int depth) {
		if(depth == notAllocatedArr.length) {
			return true ;
		}
		
		int r = notAllocatedArr[depth][0];
		int c = notAllocatedArr[depth][1];
		for(int i =1;i<=9;i++) {
			map[r][c] = i;
			if(isAbleCol(c) && isAbleRow(r) && isAbleRectangle(r, c)) {
				if(solve(depth+1)) {
					return true;
				}
			}
			map[r][c]=0;
		}
		return false;
	}

	private static void initializeProblem() throws IOException {
		List<List<Integer>> notAllocatedList = new ArrayList<>();
		for(int i=0;i<9;i++) {
			String[] inputs = br.readLine().trim().split("");
			for(int j =0;j<9;j++) {
				map[i][j] = Integer.parseInt(inputs[j]);
				if(map[i][j] == 0) {
					List<Integer> temp = new ArrayList<>();
					temp.add(i);
					temp.add(j);
					notAllocatedList.add(temp);
				}
			}
		}
		notAllocatedArr = notAllocatedList.stream().map(x -> x.stream().mapToInt(Integer::intValue).toArray()).toArray(int[][]::new);
	}

	/**
	 * row와 column이 주워지면 해당 위치의 사각형에서 유효한 스도쿠인지 확인한다.
	 * 
	 * @param r
	 * @param c
	 * @return 유효한 사각형인경우 true 아니라면 false
	 */
	private static boolean isAbleRectangle(int r, int c) {
		boolean visited[] = new boolean[9];
		int initR = (r / 3) * 3;
		int initC = (c / 3) * 3;

		for (int i = initR; i < initR + 3; i++) {
			for (int j = initC; j < initC+3; j++) {
				if(map[i][j] == 0)
					continue;
				if(visited[map[i][j]-1])
					return false;
				visited[map[i][j]-1]=true;
			}
		}

		return true;
	}

	/**
	 * column의 idx를 받아서 해당 column이 가능한 스도쿠인 경우
	 * 
	 * @param c
	 * @return 가능한 스도쿠라면 true 아니라면 false
	 */
	private static boolean isAbleCol(int c) {
		boolean visited[] = new boolean[9];
		for (int r = 0; r < 9; r++) {
			if (map[r][c] == 0)
				continue;
			if (visited[map[r][c]-1])
				return false;
			visited[map[r][c]-1] = true;
		}
		return true;
	}

	/**
	 * row의 idx를 받아서 해당 row이 가능한 스도쿠인 경우
	 * 
	 * @param r
	 * @return 가능한 스도쿠라면 true 아니라면 false
	 */
	private static boolean isAbleRow(int r) {
		boolean visited[] = new boolean[9];
		for (int c = 0; c < 9; c++) {
			if (map[r][c] == 0)
				continue;
			if (visited[map[r][c]-1])
				return false;
			visited[map[r][c]-1] = true;
		}
		return true;
	}
}