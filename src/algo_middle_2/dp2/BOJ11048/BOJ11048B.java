package algo_middle_2.dp2.BOJ11048;

import java.util.Scanner;

public class BOJ11048B {
    public static void main(String args[]) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int m = sc.nextInt();
        int[][] a = new int[n + 1][m + 1];
        int[][] d = new int[n + 1][m + 1];
        for (int i = 1; i <= n; i++) {
            for (int j = 1; j <= m; j++) {
                a[i][j] = sc.nextInt();
            }
        }
        d[1][1] = a[1][1];
        for (int i = 1; i <= n; i++) {
            for (int j = 1; j <= m; j++) {
                if (j + 1 <= m && d[i][j + 1] < d[i][j] + a[i][j + 1]) {
                    d[i][j + 1] = d[i][j] + a[i][j + 1];
                }
                if (i + 1 <= n && d[i + 1][j] < d[i][j] + a[i + 1][j]) {
                    d[i + 1][j] = d[i][j] + a[i + 1][j];
                }
                if (i + 1 <= n && j + 1 <= m && d[i + 1][j + 1] < d[i][j] + a[i + 1][j + 1]) {
                    d[i + 1][j + 1] = d[i][j] + a[i + 1][j + 1];
                }
            }
        }
        System.out.println(d[n][m]);
    }
}
