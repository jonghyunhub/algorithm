package boj.dp;

import java.util.*;

public class BOJ2193 {

    /**
     * [이친수의 정의]
     * dp(n) : 길이가 n 인 이친수의 갯수
     * dp(n,0) : 길이가 n이고 마지막 숫자가 0인 이친수의 갯수
     * dp(n,1) : 길이가 n이고 마지막 숫자가 1인 이친수의 갯수
     *
     * [점화식]
     * dp(n) = dp(n,0) + dp(n,1)
     * dp(n,0) = dp(n-1,0) + dp(n-1,1)
     * dp(n,1) = dp(n-1,0)
     * => dp(n) = 2 * dp(n-1,0) + dp(n-1,1)
     *
     * [초기화]
     * dp(1,0) = 0, dp(1,1) = 1
     * dp(2,0) = 1, dp(2,1) = 0
     */
    public static void main(String[] args) {
        final Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        long[][] memo = new long[91][2];
        for(long[] arr: memo) {
            Arrays.fill(arr, -1);
        }
        memo[1][0] = 0;
        memo[1][1] = 1;
        memo[2][0] = 1;
        memo[2][1] = 0;
        long answer = dp(n , 0, memo) + dp(n, 1, memo);
        System.out.println(answer);
        sc.close();
    }

    public static long dp(int n, int lastNum,  long[][]memo){
        if(n<=0) return 0;
        if(memo[n][lastNum] != -1){
            return memo[n][lastNum];
        }

        // 마지막 숫자 0인 경우
        if(lastNum == 0){
            long dp = dp(n - 1, 0, memo) + dp(n - 1, 1, memo);
            memo[n][lastNum] = dp;
            return dp;
        }

        // 마지막 숫자 1인 경우
        long dp = dp(n - 1, 0, memo);
        memo[n][lastNum] = dp;
        return dp;
    }
}
