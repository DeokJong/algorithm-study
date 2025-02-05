import java.util.Scanner;

public class Main {
    public static class Rectangle {
        int x1, y1, x2, y2;

        public Rectangle(int x1, int y1, int x2, int y2) {
            this.x1 = x1;
            this.y1 = y1;
            this.x2 = x2;
            this.y2 = y2;
        }

        public char getRelation(Rectangle rec) {
            if (this.x2 < rec.x1 || rec.x2 < this.x1 || this.y2 < rec.y1 || rec.y2 < this.y1) {
                return 'd';
            }

            if ((this.x1 == rec.x2 && this.y1 == rec.y2) || 
                (this.x1 == rec.x2 && this.y2 == rec.y1) || 
                (this.x2 == rec.x1 && this.y1 == rec.y2) || 
                (this.x2 == rec.x1 && this.y2 == rec.y1)) {
                return 'c';
            }

            if (this.x1 == rec.x2 || this.x2 == rec.x1 || this.y1 == rec.y2 || this.y2 == rec.y1) {
                return 'b';
            }

            return 'a';
        }
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        StringBuilder sb = new StringBuilder();

        for (int i = 0; i < 4; i++) {
            int x1 = sc.nextInt();
            int y1 = sc.nextInt();
            int x2 = sc.nextInt();
            int y2 = sc.nextInt();
            int x3 = sc.nextInt();
            int y3 = sc.nextInt();
            int x4 = sc.nextInt();
            int y4 = sc.nextInt();

            Rectangle rec1 = new Rectangle(x1, y1, x2, y2);
            Rectangle rec2 = new Rectangle(x3, y3, x4, y4);

            sb.append(rec1.getRelation(rec2)).append("\n");
        }

        System.out.print(sb);
        sc.close();
    }
}
