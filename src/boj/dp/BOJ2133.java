package boj.dp;


import java.util.*;

public class BOJ2133 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int[] memo = new int[n+1];
        Arrays.fill(memo, -1);
        memo[0] = 1;
        memo[1] = 0;
        memo[2] = 3;
        int answer = dp(n, memo);
        System.out.println(answer);
        sc.close();
    }

    /**
     * 점화식
     * dp(n) = 3* dp(n-2) + 2*dp(n-4) + 2*dp(n-6) + ...
     */
    public static int dp(int n, int[] memo){
        if(n%2 != 0) return 0;
        if(n<0) return 0;
        if(memo[n] != -1) return memo[n];
        int dp =  dp(n-2, memo) * 3;
        for(int i=n-4; i>=0; i-=2) {
            dp += dp(i, memo) * 2;
        }
        memo[n] = dp;
        return memo[n];
    }
}
