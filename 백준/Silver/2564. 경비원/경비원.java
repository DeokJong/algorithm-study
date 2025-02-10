import static java.lang.Integer.parseInt;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

public class Main {
	public static class Location {
		// 상대 위치
		int compass, distance;
		// 절대 위치
		int x, y;
		// 반대 상대 위치
		int oppasite;
		int minDistance;

		public Location(int compass, int distance, int w, int h) {
			super();
			this.compass = compass;
			this.distance = distance;

			if (compass == 1) {
				x=distance;
				y=h;
				minDistance = Math.min(x, w-x);
				oppasite=2;
			} else if (compass == 2) {
				x=distance;
				y=0;
				minDistance = Math.min(x, w-x);
				oppasite=1;
			} else if (compass == 3) {
				x=0;
				y=h-distance;
				minDistance = Math.min(y, h-y);
				oppasite=4;
			} else {
				x=w;
				y=h-distance;
				minDistance = Math.min(y, h-y);
				oppasite=3;
			}
		}

		public int getMinDistance(Location l) {
			int euclideanDistance = Math.abs(this.x - l.x) + Math.abs(this.y - l.y);
			if(l.compass == oppasite) {
				return euclideanDistance + Math.min(this.minDistance, l.minDistance)*2;
			} else {
				return euclideanDistance;
			}
		}
	}

	private static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	private static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
	private static int minDistance;
	private static int storeCount;
	private static Location[] stores;
	private static Location officeLoc;
	private static int w, h;

	public static void main(String[] args) throws IOException {
		initializeProblem();
		sovle();
		bw.append(String.valueOf(minDistance));
		bw.flush();
		br.close();
		bw.close();
	}
	
	private static void initializeProblem() throws IOException {
		String[] inputs = br.readLine().trim().split(" ");
		w = parseInt(inputs[0]);
		h = parseInt(inputs[1]);
		storeCount = parseInt(br.readLine().trim());
		
		stores = new Location[storeCount];
		for(int i =0;i<storeCount;i++) {
			inputs = br.readLine().trim().split(" ");
			stores[i] = new Location(parseInt(inputs[0]), parseInt(inputs[1]), w, h);
		}
		inputs = br.readLine().trim().split(" ");
		officeLoc = new Location(parseInt(inputs[0]), parseInt(inputs[1]), w, h);
		
	}
	
	private static void sovle() {
		for(int i =0;i<storeCount;i++) {
			minDistance += officeLoc.getMinDistance(stores[i]);
		}
	}
}