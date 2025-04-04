package boj.dp;

import java.util.*;

public class BOJ16194 {
    public static void main(String[] args) {
        final Scanner sc = new Scanner(System.in);
        final Map<Integer, Integer> memo = new HashMap<>();
        final List<Integer> cardPacks = new ArrayList<>();
        memo.put(0, 0);
        int n = sc.nextInt();
        cardPacks.add(0);
        for (int i = 0; i < n; i++) {
            cardPacks.add(sc.nextInt());
        }
        int answer = dp(n, cardPacks, memo);
        System.out.println(answer);
        sc.close();
    }

    /**
     * dp(n) = N개의 카드팩을 구입하는 최소금액
     * dp(n) = min(dp(n-1) + p(1), dp(n-2) + p(2), ... , dp(1) + p(n-1), dp(0)+p(n))
     * dp(0) = 0
     */
    public static int dp(int n,
                         List<Integer> cardPacks,
                         Map<Integer, Integer> memo) {
        if(memo.containsKey(n)){
            return memo.get(n);
        }

        int min = Integer.MAX_VALUE;
        for(int i=1; i<=n; i++){
            min = Math.min(min, dp(n - i, cardPacks, memo) + cardPacks.get(i));
        }
        memo.put(n,min);
        return min;
    }
}
