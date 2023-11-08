package prepare_coding_test_problem.DP.BOJ1695;

import java.util.Arrays;
import java.util.Scanner;

public class BOJ1695B {
    static int[] a = new int[5000];
    static int[][] d = new int[5000][5000];

    static int go(int i, int j) {
        if (i >= j) return 0;
        if (d[i][j] != -1) return d[i][j];
        if (a[i] == a[j]) {
            return d[i][j] = go(i + 1, j - 1);
        } else {
            return d[i][j] = Math.min(go(i + 1, j), go(i, j - 1)) + 1;
        }
    }

    public static void main(String args[]) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        for (int i = 0; i < n; i++) {
            a[i] = sc.nextInt();
            Arrays.fill(d[i], -1);
        }
        System.out.println(go(0, n - 1));
    }
}
