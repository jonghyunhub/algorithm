package boj.dp;

import java.util.*;

/**
 * [문제 요구사항]
 * - 연속한 집은 같은 색깔 x
 * - 모든 집을 칠할떄 색칠하는 최소 비용 구하기
 *
 * [점화식]
 * dp(n) : n번째 집을 칠할때 칠하는 전체 최소 비용
 * dp(n,0) : n번째 집을 빨간색으로 칠하는 비용
 * dp(n,1) : n번째 집을 초록색으로 칠하는 비용
 * dp(n,2) : n번째 집을 파랑색으로 칠하는 비용
 *
 * dp(n) = min(N번째 집을 빨강으로 칠하는 비용, N번째 집을 초록으로 칠하는 비용, N번째 집을 파랑으로 칠하는 비용)
 * = min(
 * dp(n-1, 1) + color[n][0],
 * dp(n-1, 2) + color[n][0],
 * dp(n-1, 0) + color[n][1],
 * dp(n-1, 2) + color[n][1],
 * dp(n-1, 0) + color[n][2],
 * dp(n-1, 1) + color[n][2]
 * )
 */
public class BOJ1149 {
    public static void main(String[] args) {
        final Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        final long[][] memo = new long[n][3];
        for (int i = 0; i < n; i++) {
            Arrays.fill(memo[i], Integer.MAX_VALUE);
        }
        final int[][] color = new int[n][3];
        for (int i = 0; i < n; i++) {
            color[i][0] = sc.nextInt();
            color[i][1] = sc.nextInt();
            color[i][2] = sc.nextInt();
        }

        long answer = Integer.MAX_VALUE;
        for(int i=0; i<3; i++){
            long dp = dp(n - 1, i, memo, color);
            if(answer > dp) answer = dp;
        }
        System.out.println(answer);
        sc.close();
    }

    public static long dp(int n, int nowColor, long[][] memo, int[][] color) {
        if(n<0) return 0;
        if(memo[n][nowColor] < Integer.MAX_VALUE) return memo[n][nowColor];

        // 빨강
        if(nowColor == 0) {
            long dp1 = dp(n - 1, 1, memo, color) + color[n][0];
            long dp2 = dp(n - 1, 2, memo, color) + color[n][0];
            long min = Math.min(dp1, dp2);
            memo[n][nowColor] = min;
            return min;
        }

        // 초록
        if(nowColor == 1) {
            long dp1 = dp(n - 1, 0, memo, color) + color[n][1];
            long dp2 = dp(n - 1, 2, memo, color) + color[n][1];
            long min = Math.min(dp1, dp2);
            memo[n][nowColor] = min;
            return min;
        }

        // 파랑 : color == 2
        long dp1 = dp(n - 1, 0, memo, color) + color[n][2];
        long dp2 = dp(n - 1, 1, memo, color) + color[n][2];
        long min = Math.min(dp1, dp2);
        memo[n][nowColor] = min;
        return min;
    }
}
