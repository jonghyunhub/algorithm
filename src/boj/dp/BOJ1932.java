package boj.dp;

import java.util.*;

/**
 * [점화식]
 * dp[n][i] = Max(dp[n-1][i-1], dp[n-1][i]) + arr[n][i]
 */
public class BOJ1932 {
    public static void main(String[] args) {
        final Scanner sc = new Scanner(System.in);
        final int n = sc.nextInt();
        final int[][] arr = new int[n][n];
        final int[][] memo = new int[n][n];
        for (int i = 0; i < n; i++) {
            Arrays.fill(memo[i], -1);
        }
        for (int i = 0; i < n; i++) {
            for (int j = 0; j <= i; j++) {
                arr[i][j] = sc.nextInt();
            }
        }
        for(int i=0; i<n; i++) {
            dp(n - 1, i, arr, memo);
        }
        int answer= 0;
        for(int i = 0; i < n; i++) {
            if(answer < memo[n-1][i]) answer = memo[n-1][i];
        }
        System.out.println(answer);
        sc.close();
    }

    public static int dp(int row, int col, int[][] arr, int[][] memo) {
        if(row < 0 || col < 0) return 0;
        if(memo[row][col] != -1) return memo[row][col];

        memo[row][col] = Math.max(dp(row-1, col-1, arr, memo), dp(row-1, col, arr, memo)) + arr[row][col];
        return memo[row][col];
    }

}
