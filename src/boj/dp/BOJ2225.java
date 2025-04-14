package boj.dp;

import java.util.*;

/**
 * [문제 정의]
 * dp[n][k] : 0 부터 n 까지의 정수 k개 더해서 그 합이 n이 되는 경우의 수
 * [점화식 도출]
 * - 마지막 수를 l이라고 할 때, l을 제외한 앞의 n-l을 k-1개의 정수의 합으로 나타낼 수 있다.
 * dp[n][k] = Sum(dp[n-l][k-1]) (0<= n-l <-> 0<= l <= n, 0<k<=n)
 * dp[1][1] = 1, dp[1][2] = 2, ...
 * dp[2][1] = 1, dp[2][2] = 3
 * dp[3][1] = 1, dp[3][2] = 1
 */
public class BOJ2225 {
    public static void main(String[] args) {
        final Scanner sc = new Scanner(System.in);
        final int n = sc.nextInt();
        final int k = sc.nextInt();
        final long[][] memo = new long[n + 1][k + 1];
        for (int i = 0; i <= n; i++) {
            Arrays.fill(memo[i], -1);
        }
        // memo[0][k] 초기화 단, memo[0][0] 은 X
        for (int i = 1; i <= k; i++) {
            memo[0][i] = 1;
        }
        // memo[k][1] 초기화 => dp[1][1] = dp[2][1] = dp[3][1] .... = 1
        for (int i = 1; i <= n; i++) {
            memo[i][1] = 1;
        }
        // memo[1][k] 초기화 => dp[1][1] = 1,  dp[1][2] = 2, dp[1][3] = 3, ...
        for (int i = 2; i <= k; i++) {
            memo[1][i] = i;
        }
        long answer = dp(n, k, memo) % 1000000000;
        System.out.println(answer);
        sc.close();
    }

    public static long dp(int n, int k, long[][] memo) {
        if (memo[n][k] > -1) return memo[n][k];
        long answer = 0;
        for (int l = 0; l <= n; l++) {
            answer += dp(n - l, k - 1, memo) % 1000000000;
        }
        memo[n][k] = answer % 1000000000;
        return answer;
    }

}
