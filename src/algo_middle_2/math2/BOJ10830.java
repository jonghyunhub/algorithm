package algo_middle_2.math2;

import java.util.Scanner;

public class BOJ10830 {
    final static int mod = 1000;

    static int[][] multiplication(int[][] a, int[][] b) {
        int n = a.length;
        int[][] c = new int[n][n];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                c[i][j] = 0;
                for (int k = 0; k < n; k++) {
                    c[i][j] += a[i][k] * b[k][j];
                }
                c[i][j] %= mod;
            }
        }
        return c;
    }

    static void move(int[][] a, int[][] b) {
        int n = a.length;
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                a[i][j] = b[i][j];
            }
        }
    }

    public static void main(String args[]) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        long m = sc.nextLong();
        int[][] a = new int[n][n];
        int[][] ans = new int[n][n];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                a[i][j] = sc.nextInt();
                ans[i][j] = 0;
            }
            ans[i][i] = 1;
        }
        int[][] temp;
        while (m > 0) {
            if (m % 2 == 1) {
                temp = multiplication(ans, a);
                move(ans, temp);
            }
            temp = multiplication(a, a);
            move(a, temp);
            m /= 2;
        }
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                System.out.print(ans[i][j] + " ");
            }
            System.out.println();
        }
    }
}
