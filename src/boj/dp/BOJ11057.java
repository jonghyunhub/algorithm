package boj.dp;

import java.util.*;

/**
 * [문제 정의]
 * dp(n) : 길이가 N인 오르막 수의 갯수
 *
 * [점화식 정의]
 * dp(n,i) : 길이가 N이고 마지막 수가 i인 오르막 수의 갯수
 * dp(n) = dp(n,0) + dp(n,1) + ... + dp(n,9)
 * dp(n,i) = dp(n-1, 0) + dp(n-1,i) + ... + dp(n-1,i)
 */
public class BOJ11057 {
    public static void main(String[] args) {
        final Scanner sc = new Scanner(System.in);
        final int n = sc.nextInt();
        final long[][] memo = new long[n+1][10];
        for (int i = 0; i <= n; i++) {
            Arrays.fill(memo[i], -1);
        }
        for(int i=0; i<10; i++){
            memo[1][i] = 1;
        }
        long answer = 0;
        for (int i = 0; i < 10; i++) {
            answer += dp(n, i, memo);
        }
        System.out.println(answer % 10007);
        sc.close();
    }

    public static long dp(int n, int lastNum, long[][] memo) {
        if(n<=0) return 0;
        if(memo[n][lastNum] != -1) return memo[n][lastNum];

        long answer = 0;
        for(int i=0; i<=lastNum; i++) {
            answer += dp(n-1, i, memo);
        }
        memo[n][lastNum] = answer % 10007;
        return answer;
    }
}
