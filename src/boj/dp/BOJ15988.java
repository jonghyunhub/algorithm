package boj.dp;

import java.util.*;

/**
 * [점화식]
 * dp(N) = dp(N-1) (마지막수 1인경우) + dp(N-2)(마지막수 2인 경우) + dp(N-3)(마지막수 3인경우)
 * dp(1) = 1, dp(2) = 2, dp(3) = 4
 */
public class BOJ15988 {
    public static void main(String[] args) {
        final Scanner sc = new Scanner(System.in);
        final int n = sc.nextInt();
        final long[] memo = new long[1000001];
        Arrays.fill(memo, -1);
        memo[0] = 0;
        memo[1] = 1;
        memo[2] = 2;
        memo[3] = 4;
        for(int i=0; i<n; i++){
            int m = sc.nextInt();
            dp(m, memo);
            System.out.println(memo[m] % 1000000009);
        }
        sc.close();
    }

    public static long dp(int n, long[] memo) {
        if(memo[n] != -1) {
            return memo[n];
        }

        memo[n] = (dp(n-1, memo) + dp(n-2, memo) + dp(n-3, memo) ) % 1000000009;
        return memo[n];
    }
}
