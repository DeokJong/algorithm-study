import java.util.Scanner;

public class Main {
  private static Scanner sc = new Scanner(System.in);
  private static int w, h;
  private static int t;
  private static int[] location = new int[2];
  private static int[] vector = new int[2];
  static {
    vector[0] = 1;
    vector[1] = 1;
  }

  public static void main(String[] args) {
    initializeProblem();
    solve();
    System.out.printf("%d %d",location[0],location[1]);
    sc.close();
  }

  private static void initializeProblem() {
    w = sc.nextInt();
    h = sc.nextInt();
    sc.nextLine();

    location[0] = sc.nextInt();
    location[1] = sc.nextInt();
    sc.nextLine();

    t = sc.nextInt();
  }

  private static void solve() {
	  t = t % (w*h*2);
    while (t > 0) {
      int vectorMaxDistance = getMaxMoveDistanceByVector();
      if(t>vectorMaxDistance) {
        t-=vectorMaxDistance;
        modifyLocation(vectorMaxDistance);
        modifyVector();
      } else {
        modifyLocation(t);
        t=0;
      }
    }
  }

  private static void modifyVector() {
    int x = location[0];
    int y = location[1];
    if(x == 0 || x  == w) {
      vector[0] = -vector[0];
    }

    if(y == 0 || y  == h) {
      vector[1] = -vector[1];
    }
  }

  private static void modifyLocation(int distance){
    int xVector = vector[0];
    int yVector = vector[1];
    if(xVector>0) {
      location[0]+=distance;
    } else {
      location[0]-=distance;
    }

    if(yVector>0) {
      location[1]+=distance;
    } else {
      location[1]-=distance;
    }
  }

  private static int getMaxMoveDistanceByVector(){
    int x = vector[0];
    int y = vector[1];
    int xDiff = x>0 ? w-location[0] : location[0];
    int yDiff = y>0 ? h-location[1] : location[1];
    return Math.min(xDiff, yDiff);
  }
}