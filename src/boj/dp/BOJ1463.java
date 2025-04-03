package boj.dp;

import java.util.*;

public class BOJ1463 {

    /**
     * 문제 : D[N] : N을 1로 만드는 가지수
     D[N] = min(1 + D[N/3], 1 + D[N/2] , 1 + D[N-1])
     D[1] = 0, D[2] = 1, D[3] = 1
     */
    public static void main(String[] args) {
        final Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        final Map<Integer, Integer> memo = new HashMap<>();
        memo.put(1, 0);
        memo.put(2, 1);
        memo.put(3, 1);
        System.out.println(dp(n, memo));
        sc.close();
    }

    public static int dp(int n, Map<Integer, Integer> memo){
        if(memo.containsKey(n)){
            return memo.get(n);
        }
        int dp1;
        if(n%3 == 0){
            dp1 = dp(n / 3, memo);
        } else {
            dp1 = n;
        }
        int dp2;
        if(n%2 == 0){
            dp2 = dp(n / 2, memo);
        } else {
            dp2 = n;
        }
        int dp3 = dp(n - 1, memo);
        int min = Math.min(dp1, dp2);
        int answer = Math.min(min, dp3) + 1;
        memo.put(n, answer);
        return answer;
    }
}
