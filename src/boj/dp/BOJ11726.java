package boj.dp;

import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

// https://www.acmicpc.net/problem/11726
public class BOJ11726 {
//    public static void main(String[] args) {
//        Scanner sc = new Scanner(System.in);
//        int n = sc.nextInt();
//        long[] memo = new long[n + 1];
//        long answer = solutionWithLoop(n, memo);
//        System.out.println(answer);
//        sc.close();
//    }
//
//    public static long solutionWithRecursion(int n, long[] memo) {
//        if (n <= 2) return n;
//        if (memo[n] != 0) return memo[n];  // 메모이제이션 확인
//
//        // 각 단계에서 나머지 연산 적용
//        memo[n] = (solutionWithRecursion(n - 1, memo) + solutionWithRecursion(n - 2, memo)) % 10007;
//        return memo[n];
//    }
//
//    // 반복문을 실행해서 n이 될때까지 값을 더하면서 계산후 n이 되면 결과를 리턴한다.
//    public static long solutionWithLoop(int n, long[] memo) {
//        if (n <= 2) return n;
//        memo[1] = 1;
//        memo[2] = 2;
//        for (int i = 3; i <= n; i++)
//            memo[i] = (memo[i - 1] + memo[i - 2]) % 10007;
//        return memo[n];
//    }

    public static void main(String[] args){
        final Scanner sc = new Scanner(System.in);
        final int n = sc.nextInt();
        final Map<Integer, Integer> memo = new HashMap<>();
        memo.put(1,1);
        memo.put(2,2);
        memo.put(3,3);
        int answer = dp(n, memo);
        System.out.println(answer);
        sc.close();
    }

    // dp(n) = dp(n-1) + dp(n-2)
    public static int dp(int n, Map<Integer, Integer> memo){
        if(memo.containsKey(n)){
            return memo.get(n);
        }

        int answer = (dp(n-1,memo) + dp(n-2,memo)) % 10007;
        memo.put(n, answer);
        return answer;
    }
}
