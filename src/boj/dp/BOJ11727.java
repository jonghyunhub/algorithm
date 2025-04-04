package boj.dp;

import java.util.*;

public class BOJ11727 {
    public static void main(String[] args) {
        final Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        final Map<Integer, Integer> memo = new HashMap<>();
        memo.put(1, 1);
        memo.put(2, 3);
        int answer = dp(n, memo);
        System.out.println(answer);
        sc.close();
    }

    // dp(n) = dp(n-1) + 2 * dp(n-2)
    public static int dp(int n, Map<Integer, Integer> memo){
        if(memo.containsKey(n)){
            return memo.get(n);
        }

        int answer = (dp(n - 1, memo) + 2 * dp(n - 2, memo)) % 10007;
        memo.put(n, answer);
        return answer;
    }
}
