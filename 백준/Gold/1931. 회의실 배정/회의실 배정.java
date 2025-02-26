import java.io.*;
import static java.lang.Integer.parseInt;
import java.util.*;

public class Main {
	static class Meeting implements Comparable<Meeting> {
		int startTime;
		int endTime;

		public Meeting(int startTime, int endTime) {
			super();
			this.startTime = startTime;
			this.endTime = endTime;
		}

		@Override
		public int compareTo(Meeting o) {
			if(Integer.compare(this.endTime, o.endTime) == 0) {
				return Integer.compare(this.startTime, o.startTime);
			} else {
				return Integer.compare(this.endTime, o.endTime);
			}
		}
		
	}

	private static final BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	private static final BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

	private static int meetingCount;
	private static Meeting[] meetings;
	private static int ableMeetingCount;
	private static int curTime;
	
	public static void main(String[] args) throws IOException {
		initializeProblem();
		solve();
		System.out.println(ableMeetingCount);
	}

	private static void initializeProblem() throws NumberFormatException, IOException {
		meetingCount = parseInt(br.readLine());
		meetings = new Meeting[meetingCount];
		
		
		StringTokenizer st;
		for(int i =0;i<meetingCount;i++) {
			st = new StringTokenizer(br.readLine());
			meetings[i] = new Meeting(parseInt(st.nextToken()), parseInt(st.nextToken()));
		}
		Arrays.sort(meetings);
		
		
		ableMeetingCount = 0;
		curTime = 0;
	}

	private static void solve() throws IOException {
		for(Meeting m : meetings) {
			if(m.startTime >= curTime) {
				curTime = m.endTime;
				ableMeetingCount++;
			}
		}
	}
}
