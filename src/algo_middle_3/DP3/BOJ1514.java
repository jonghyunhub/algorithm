package algo_middle_3.DP3;

import java.util.Scanner;

public class BOJ1514 {
    public static int n;
    public static String a, b;
    public static int[][][] d;
    public static int[] turn = {0, 1, 1, 1, 2, 2, 2, 1, 1, 1};

    public static int go(int i, int j, int k) {
        if (i == n) return 0;
        if (d[i][j][k] != -1) return d[i][j][k];
        int original = a.charAt(i) - '0';
        original = (original + j) % 10;
        int to = b.charAt(i) - '0';
        for (int two = 0; two < 10; two++) {
            for (int three = 0; three < 10; three++) {
                int from = (original + two + three) % 10;
                int one = to - from;
                if (one < 0) one += 10;
                int cost = turn[one] + turn[two] + turn[three];
                cost += go(i + 1, (k + two + three) % 10, three);
                if (d[i][j][k] == -1 || d[i][j][k] > cost) {
                    d[i][j][k] = cost;
                }
            }
        }
        return d[i][j][k];
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        n = sc.nextInt();
        sc.nextLine();
        a = sc.nextLine();
        b = sc.nextLine();
        d = new int[n][10][10];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < 10; j++) {
                for (int k = 0; k < 10; k++) {
                    d[i][j][k] = -1;
                }
            }
        }
        System.out.println(go(0, 0, 0));
    }
}
