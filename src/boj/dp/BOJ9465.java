package boj.dp;

import java.util.*;

/**
 * [점화식]
 * dp(i,j) : 마지막 스티커가 memo[i][j]
 * dp(i,j)
 * 1. dp(0,j) = Max(dp(1, j-1), dp(1, j-2)) + memo[0][j]
 * 2. dp(1,j) = Max(dp(0, j-1), dp(0, j-2)) + memo[1][j]
 * case 1) 대각선 떼기
 * case 2) 왼쪽 열은 뗴지않고 그 이전 열 뗴기
 */
public class BOJ9465 {
    public static void main(String[] args) {
        final Scanner sc = new Scanner(System.in);
        final int caseNum = sc.nextInt();
        for (int c = 0; c < caseNum; c++) {
            final int n = sc.nextInt();
            final int[][] arr = new int[2][n];
            for (int i = 0; i < 2; i++) {
                for(int j = 0; j < n; j++) {
                    arr[i][j] = sc.nextInt();
                }
            }
            final int[][] memo = new int[2][n];
            for (int i = 0; i < 2; i++)
                Arrays.fill(memo[i], -1);
            memo[0][0] = arr[0][0];
            memo[1][0] = arr[1][0];
            dp(0, n - 1, memo, arr);
            dp(1, n - 1, memo, arr);
            int max = 0;
            for (int row = 0; row < 2; row++) {
                for (int col = 0; col < n; col++) {
                    if (max < memo[row][col]) max = memo[row][col];
                }
            }
            System.out.println(max);
        }
        sc.close();
    }

    public static int dp(int row, int col, int[][] memo, int[][] arr) {
        if (col < 0) return 0;
        if (memo[row][col] != -1) return memo[row][col];

        if (row == 0) {
            int max = Math.max(dp(1, col - 1, memo, arr), dp(1, col - 2, memo, arr));
            memo[row][col] = max + arr[row][col];
            return memo[row][col];
        }

        // row == 1
        int max = Math.max(dp(0, col - 1, memo, arr), dp(0, col - 2, memo, arr));
        memo[row][col] = max + arr[row][col];
        return memo[row][col];
    }
}
