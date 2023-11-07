package algo_middle_2.dp2.BOJ10422;

import java.util.Scanner;

public class BOJ10422B {
    public static void main(String args[]) {
        Scanner sc = new Scanner(System.in);
        long[][] d = new long[5001][5001];
        long mod = 1000000007L;
        d[0][0] = 1;
        for (int i = 1; i <= 5000; i++) {
            for (int j = 0; j <= i; j++) {
                d[i][j] = 0;
                if (j + 1 <= i) {
                    d[i][j] += d[i - 1][j + 1];
                }
                if (j - 1 >= 0) {
                    d[i][j] += d[i - 1][j - 1];
                }
                d[i][j] %= mod;
            }
        }
        int t = sc.nextInt();
        while (t-- > 0) {
            int n = sc.nextInt();
            System.out.println(d[n][0]);
        }
    }
}
