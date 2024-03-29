package algo_middle_3.DP3.BOJ16195;

import java.util.Scanner;

public class BOJ16195B {
    static final long mod = 1000000009L;
    static final int limit = 1000;
    static long[][] d = new long[limit + 1][limit + 1];

    public static void main(String args[]) {
        Scanner sc = new Scanner(System.in);
        d[0][0] = 1;
        for (int i = 1; i <= limit; i++) {
            d[0][i] = 1;
        }
        for (int i = 1; i <= limit; i++) {
            for (int j = 1; j <= limit; j++) {
                if (i - 1 >= 0) {
                    d[i][j] += d[i - 1][j - 1];
                }
                if (i - 2 >= 0) {
                    d[i][j] += d[i - 2][j - 1];
                }
                if (i - 3 >= 0) {
                    d[i][j] += d[i - 3][j - 1];
                }
                d[i][j] %= mod;
            }
        }
        int t = sc.nextInt();
        while (t-- > 0) {
            int n = sc.nextInt();
            int m = sc.nextInt();
            long ans = d[n][m];
            System.out.println(ans);
        }
    }
}
