import java.util.Scanner;

public class Main {
	private static Scanner scanner = new Scanner(System.in);
	private static int[] tiles = new int[1000001];
	public static void main(String[] args) {
		int n = scanner.nextInt();
		tiles[1] = 1;
		tiles[2] = 2;
		for(int i =3; i <=n; i++) {
			tiles[i] = (tiles[i-1] + tiles[i-2]) %15746 ;
		}
		System.out.println(tiles[n]);
	}
}
