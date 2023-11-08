package prepare_coding_test_problem.bruteforce3.BOJ16971;

import java.util.Scanner;

public class BOJ16971B {
    static int calc(int[][] a) {
        int n = a.length;
        int m = a[0].length;
        int ans = 0;
        for (int i = 0; i < n - 1; i++) {
            for (int j = 0; j < m - 1; j++) {
                ans += a[i][j] + a[i][j + 1] + a[i + 1][j] + a[i + 1][j + 1];
            }
        }
        return ans;
    }

    static void swap_row(int[][] a, int r1, int r2) {
        int m = a[0].length;
        for (int j = 0; j < m; j++) {
            int temp = a[r1][j];
            a[r1][j] = a[r2][j];
            a[r2][j] = temp;
        }
    }

    static void swap_col(int[][] a, int c1, int c2) {
        int n = a.length;
        for (int i = 0; i < n; i++) {
            int temp = a[i][c1];
            a[i][c1] = a[i][c2];
            a[i][c2] = temp;
        }
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int m = sc.nextInt();
        int[][] a = new int[n][m];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                a[i][j] = sc.nextInt();
            }
        }
        int ans = calc(a);
        int[] sum_row = new int[n];
        int[] sum_col = new int[m];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                sum_row[i] += a[i][j];
                sum_col[j] += a[i][j];
            }
        }
        int min_row = -1;
        for (int i = 1; i < n - 1; i++) {
            sum_row[i] = sum_row[i] * 4;
            sum_row[i] -= 2 * (a[i][0] + a[i][m - 1]);
            if (min_row == -1 || sum_row[min_row] > sum_row[i]) {
                min_row = i;
            }
        }
        int min_col = -1;
        for (int j = 1; j < m - 1; j++) {
            sum_col[j] = sum_col[j] * 4;
            sum_col[j] -= 2 * (a[0][j] + a[n - 1][j]);
            if (min_col == -1 || sum_col[min_col] > sum_col[j]) {
                min_col = j;
            }
        }
        if (min_row != -1) {
            swap_row(a, 0, min_row);
            int temp = calc(a);
            if (ans < temp) ans = temp;
            swap_row(a, 0, min_row);
            swap_row(a, n - 1, min_row);
            int temp2 = calc(a);
            if (ans < temp2) ans = temp2;
            swap_row(a, n - 1, min_row);
        }
        if (min_col != -1) {
            swap_col(a, 0, min_col);
            int temp = calc(a);
            if (ans < temp) ans = temp;
            swap_col(a, 0, min_col);
            swap_col(a, m - 1, min_col);
            int temp2 = calc(a);
            if (ans < temp2) ans = temp2;
            swap_col(a, m - 1, min_col);
        }
        System.out.println(ans);
    }
}
