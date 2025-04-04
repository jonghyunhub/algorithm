package boj.dp;

import java.util.*;

public class BOJ11052 {
    public static void main(String[] args) {
        final Scanner sc = new Scanner(System.in);
        final int n = sc.nextInt();
        final Map<Integer, Integer> memo = new HashMap<>();
        memo.put(0, 0);
        final List<Integer> cardPacks = new ArrayList<>(n+1);
        cardPacks.add(0);
        for(int i = 1; i <= n; i++) {
            cardPacks.add(sc.nextInt());
        }
        int answer = dp(n, cardPacks, memo);
        System.out.println(answer);
        sc.close();
    }

    /**
     * N개의 카드를 구입하는 최대 금액을 구해야한다.
     * N개의 카드를 구입하는 최대 금액은
     * Max(
     * N-1 개의 카드를 구입 하는 금액 + P1 카드팩의 금액,
     * N-2 개의 카드를 구입 하는 금액 + P2 카드팩의 금액,
     * ...
     * 1 개의 카드를 구입 하는 금액 + P(N-1) 카드팩의 금액,
     * 0 개의 카드를 구입 하는 금액 + P(N) 카드팩의 금액
     * dp(N) = MAX(dp(n-1) + P1, dp(n-2) + P2,..., dp(1) + P(N-1), dp(0) + P(N))
     */
    public static int dp(int n,
                         List<Integer> cardPacks ,
                         Map<Integer, Integer> memo){
        if(memo.containsKey(n)){
            return memo.get(n);
        }

        int max  = 0;
        for(int i = 0; i < n; i++) {
            int dp = dp(i, cardPacks, memo) + cardPacks.get(n - i);
            max = Math.max(max, dp);
        }
        memo.put(n, max);
        return max;
    }
}
