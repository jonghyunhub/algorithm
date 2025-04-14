package boj.dp;

import java.util.*;

public class BOJ1699 {
    public static void main(String[] args) {
        final Scanner sc = new Scanner(System.in);
        final int n = sc.nextInt();
        final int[] memo = new int[n + 1];
        Arrays.fill(memo, Integer.MAX_VALUE);
        // memo[1] = 1, memo[4]=1, memo[9] = 1, ....
        for (int i = 1; i * i <= n; i++) {
            memo[i * i] = 1;
        }
        int answer = dp(n, memo);
        System.out.println(answer);
        sc.close();
    }


    // dp(N) : N을 제곱수의 합으로 나타낼 때, 제곱수의 항의 갯수의 최소값
    public static int dp(int n, int[] memo) {
        if (memo[n] < Integer.MAX_VALUE) {
            return memo[n];
        }
        int answer = Integer.MAX_VALUE;
        for (int i = 1; i * i <= n; i++) {
            int dp = dp(n - i*i, memo);
            if(answer > dp) answer = dp;
        }
        answer++; // 마지막 수의 갯수 1 더하기
        memo[n] = answer;
        return answer;
    }
}
