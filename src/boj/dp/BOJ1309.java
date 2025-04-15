package boj.dp;

import java.util.*;

/**
 * [문제 요구사항 정리]
 * 2xN 칸에 배치시 가로,세로 이웃하게 배치는 x
 * <p>
 * [점화식 유도]
 * dp(n) : N개의 칸에 배치하는 경우의 수
 * dp[n][0] : N번째 행에 아무것도 배치하지 않는 경우
 * dp[n][1] : N번째 행 1열에 배치하는 경우
 * dp[n][2] : N번째 행 2열에 배치하는 경우
 * dp(n) = dp[n][0] + dp[n][1] + dp[n][1]
 * dp[n][0] = dp[n-1][0] + dp[n-1][1] + dp[n-1][2]
 * dp[n][1] = dp[n-1][0] + dp[n-1][2]
 * dp[n][2] = dp[n-1][0] + dp[n-1][1]
 * <p>
 * [초기 값]
 * dp[1][0]=1, dp[1][1]=1, dp[1][2]=1
 * dp[2][0]=3, dp[2][1]=2, dp[2][2]=2
 */
public class BOJ1309 {
    public static void main(String[] args) {
        final Scanner sc = new Scanner(System.in);
        final int n = sc.nextInt();

        // 2차원 배열 사용시 N = 100,000 일때 힙 메모리 초과로 OOME 터짐 -> 변수 3개로 처리
        int prev0 = 1;
        int prev1 = 1;
        int prev2 = 1;

        for (int i = 2; i <= n; i++) {
            int cur0 = (prev0 + prev1 + prev2) % 9901;
            int cur1 = (prev0 + prev2) % 9901;
            int cur2 = (prev0 + prev1) % 9901;

            prev0 = cur0;
            prev1 = cur1;
            prev2 = cur2;
        }

        int answer = (prev0 + prev1 + prev2) % 9901;
        System.out.println(answer % 9901);
        sc.close();
    }

    // 재귀사용시 메모리 초과... -> 반복문으로 해결
    public static int dp(int n, int col, int[][] memo) {
        if (n <= 0) return 0;
        if (memo[n][col] != -1) return memo[n][col];

        if (col == 0) {
            memo[n][col] = (dp(n - 1, 0, memo) % 9901 + dp(n - 1, 1, memo) % 9901 + dp(n - 1, 2, memo) % 9901) % 9901;
            return memo[n][col];
        }

        if (col == 1) {
            memo[n][col] = (dp(n - 1, 0, memo) % 9901 + dp(n - 1, 2, memo) % 9901) % 9901;
            return memo[n][col];
        }

        // col == 2 인 경우
        memo[n][col] = (dp(n - 1, 0, memo) % 9901 + dp(n - 1, 1, memo) % 9901) % 9901;
        return memo[n][col];
    }

}
