package algo_middle_3.DP3;

import java.util.Arrays;
import java.util.Scanner;

public class BOJ3948 {
    static long[][] c = new long[22][22];
    static long[] d = new long[22];

    static long go(int n) {
        if (d[n] != -1) return d[n];
        long ans = d[n];
        ans = 0;
        for (int i = 1; i <= n; i += 2) {
            ans += c[n - 1][i - 1] * go(i - 1) * go(n - i);
        }
        d[n] = ans;
        return ans;
    }

    public static void main(String args[]) {
        Scanner sc = new Scanner(System.in);
        c[0][0] = 1;
        for (int i = 1; i <= 20; i++) {
            c[i][0] = c[i][i] = 1;
            for (int j = 1; j <= i - 1; j++) {
                c[i][j] = c[i - 1][j - 1] + c[i - 1][j];
            }
        }
        Arrays.fill(d, -1);
        d[0] = d[1] = d[2] = 1;
        int t = sc.nextInt();
        while (t-- > 0) {
            int n = sc.nextInt();
            if (n == 1) {
                System.out.println(1);
            } else {
                System.out.println(2L * go(n));
            }
        }
    }
}
