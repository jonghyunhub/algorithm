package prepare_coding_test_problem.DP.BOJ1695;

import java.util.Scanner;

public class BOJ1695A {
    public static void main(String args[]) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int[] a = new int[n + 1];
        int[] b = new int[n + 1];
        for (int i = 1; i <= n; i++) {
            a[i] = sc.nextInt();
            b[n - i + 1] = a[i];
        }
        int[][] d = new int[n + 1][n + 1];
        for (int i = 1; i <= n; i++) {
            for (int j = 1; j <= n; j++) {
                if (a[i] == b[j]) {
                    d[i][j] = d[i - 1][j - 1] + 1;
                } else {
                    d[i][j] = Math.max(d[i - 1][j], d[i][j - 1]);
                }
            }
        }
        System.out.println(n - d[n][n]);
    }
}
