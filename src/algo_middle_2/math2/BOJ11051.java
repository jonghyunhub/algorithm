package algo_middle_2.math2;

import java.util.Scanner;

public class BOJ11051 {
    static final int mod = 10007;

    public static void main(String args[]) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int m = sc.nextInt();
        int[][] d = new int[n + 1][n + 1];
        for (int i = 0; i <= n; i++) {
            d[i][0] = d[i][i] = 1;
            for (int j = 1; j <= i - 1; j++) {
                d[i][j] = d[i - 1][j - 1] + d[i - 1][j];
                d[i][j] %= mod;
            }
        }
        int ans = d[n][m];
        System.out.println(ans);
    }
}
