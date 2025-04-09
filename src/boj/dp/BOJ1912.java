package boj.dp;

import java.util.*;

/**
 * [문제 정의]
 * dp(i) : i 번째 수로 끝나는 '가장 큰 연속합'
 * [점화식]
 * dp(i) = Max(dp(i-1) + arr[i] , arr[i])
 * [초기화]
 * dp(0) = arr[0]
 */
public class BOJ1912 {
    public static void main(String[] args) {
        final Scanner sc = new Scanner(System.in);
        final int n = sc.nextInt();
        final int[] arr = new int[n];
        final int[] memo = new int[n];
        Arrays.fill(memo, -1001);
        for (int i = 0; i < n; i++) {
            arr[i] = sc.nextInt();
        }
        memo[0] = arr[0];
        dp(n-1, arr, memo);
        int answer = -1001;
        for(int i=0; i<n; i++) {
            if(memo[i] > answer){
                answer = memo[i];
            }
        }
        System.out.println(answer);
        sc.close();
    }

    // dp(i) = Max(dp(i-1) + arr[i] , arr[i])
    public static int dp(int n, int[] arr, int[] memo){
        if(memo[n] > -1001) return memo[n];

        int max = Math.max(dp(n - 1, arr, memo) + arr[n], arr[n]);
        memo[n] = max;
        return max;
    }

}
