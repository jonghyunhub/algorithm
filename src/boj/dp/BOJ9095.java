package boj.dp;

import java.util.*;

public class BOJ9095 {
    public static void main(String[] args) {
        final Scanner sc = new Scanner(System.in);
        final Map<Integer, Integer> memo = new HashMap<>();
        int n = sc.nextInt();
        memo.put(1, 1);
        memo.put(2, 2);
        memo.put(3, 4);
        for(int i=0; i<n; i++){
            int input = sc.nextInt();
            int answer = dp(input, memo);
            System.out.println(answer);
        }
        sc.close();
    }

    public static int dp(int n, Map<Integer, Integer> memo) {
        if(memo.containsKey(n)){
            return memo.get(n);
        }

        int answer = dp(n - 3, memo) + dp(n - 2, memo) + dp(n - 1, memo);
        memo.put(n, answer);
        return answer;
    }
}
