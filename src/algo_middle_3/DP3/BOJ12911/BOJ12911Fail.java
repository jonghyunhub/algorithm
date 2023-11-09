package algo_middle_3.DP3.BOJ12911;

import java.util.Scanner;

public class BOJ12911Fail {
    public static final long mod = 1000000007L;

    public static void main(String args[]) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int k = sc.nextInt();
        long[][] d = new long[n + 1][k + 1];
        for (int j = 1; j <= k; j++) {
            d[1][j] = 1;
        }
        for (int i = 2; i <= n; i++) {
            for (int j = 1; j <= k; j++) {
                // 길이 i, 첫 수 j
                // j 다음에 오는 수: l
                // j <= l 인 경우는 다 더해주면 된다
                for (int l = j; l <= k; l++) {
                    d[i][j] += d[i - 1][l];
                    d[i][j] %= mod;
                }
                // j % l != 0 인 조건
                for (int l = 1; l < j; l++) {
                    if (j % l != 0) {
                        d[i][j] += d[i - 1][l];
                        d[i][j] %= mod;
                    }
                }
            }
        }
        long ans = 0;
        for (int i = 1; i <= k; i++) {
            ans += d[n][i];
            ans %= mod;
        }
        System.out.println(ans);
    }
}
