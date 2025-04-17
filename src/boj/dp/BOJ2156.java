package boj.dp;

import java.util.*;

/**
 * [문제 요구사항]
 * dp(N) : N 번쨰까지 마신 포도주의 합의 회대값
 * <p>
 * [점화식]
 * dp(N) = Max(dp[N][0], dp[N][1])
 * dp[N][0] : N번째 포도주를 마시는 경우
 * dp[N][1] : N번째 포도주를 안마시는 경우
 * <p>
 * dp[n][0] = Max(dp[N-2][1] + arr[N-1], dp[N-2][0]) + arr[N]
 * dp[n][1] = Max(dp[N-1][0], dp[N-1][1])
 */
public class BOJ2156 {

    public static void main(String[] args) {
        final Scanner sc = new Scanner(System.in);
        final int n = sc.nextInt();
        final int[] arr = new int[n];
        for (int i = 0; i < n; i++) {
            arr[i] = sc.nextInt();
        }
        final int[][] memo = new int[n][2];
        for (int i = 0; i < n; i++)
            Arrays.fill(memo[i], -1);
        memo[0][0] = arr[0];
        memo[0][1] = 0;
        dp(n - 1, 0, memo, arr);
        dp(n - 1, 1, memo, arr);
        int answer = 0;
        for (int i = 0; i < n; i++) {
            if (answer < memo[i][0])
                answer = memo[i][0];
            if (answer < memo[i][1])
                answer = memo[i][1];
        }
        System.out.println(answer);
        sc.close();
    }

    /**
     * [점화식]
     * * dp(N) = Max(dp[N][0], dp[N][1])
     * * dp[N][0] : N번째 포도주를 마시는 경우
     * * dp[N][1] : N번째 포도주를 안마시는 경우
     * * <p>
     * * dp[n][0] = Max(dp[N-2][1] + arr[N-1], dp[N-2][0]) + arr[N]
     * * dp[n][1] = Max(dp[N-1][0], dp[N-1][1])
     */
    public static int dp(int n, int drinkNow, int[][] memo, int[] arr) {
        if (n < 0) return 0;
        if (memo[n][drinkNow] != -1) return memo[n][drinkNow];

        if (drinkNow == 0) {
            memo[n][drinkNow] = Math.max(dp(n - 2, 1, memo, arr) + arr[n - 1], dp(n - 2, 0, memo, arr)) + arr[n];
            return memo[n][drinkNow];
        }

        // drinkNow == 1
        memo[n][drinkNow] = Math.max(dp(n - 1, 0, memo, arr), dp(n - 1, 1, memo, arr));
        return memo[n][drinkNow];
    }

}
