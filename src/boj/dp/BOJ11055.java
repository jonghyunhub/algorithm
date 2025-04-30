package boj.dp;

import java.util.Arrays;
import java.util.Scanner;

public class BOJ11055 {
    public static void main(String[] args) {
        final Scanner sc = new Scanner(System.in);
        final int n = sc.nextInt();
        final int[] arr = new int[n];
        final int[] memo = new int[n];
        Arrays.fill(memo, -1);
        for (int i = 0; i < n; i++) {
            arr[i] = sc.nextInt();
        }
        memo[0] = arr[0];
        for(int i = 0; i < n; i++)
            dp(i, arr, memo);
        int max = 0;
        for(int i = 0; i < n; i++) {
            if(memo[i] > max) max = memo[i];
        }
        System.out.println(max);
        sc.close();
    }

    /**
     * dp[i] : 마지막 수가 arr[i]인 가장 큰 증가하는 수열
     * dp[i] = Max(dp[i-1], dp[i-2],dp[i-3],....) + arr[i] (arr[i-1] < arr[i])
     * dp[i] = dp[i-1] (arr[i-1] >= arr[i])
     */
    public static int dp(int n, int[] arr, int[] memo) {
        if(n<0) return 0;
        if (memo[n] != -1) return memo[n];
        int max = 0;
        for(int i = 0; i < n; i++) {
            if(arr[i] >= arr[n]) continue;
            int dp = dp(i, arr, memo) + arr[n];
            max = Math.max(max,dp);
        }
        if(max == 0) max = arr[n]; // 길이가 1인 부분수열이 되는 경우
        memo[n] = max;
        return max;
    }
}
