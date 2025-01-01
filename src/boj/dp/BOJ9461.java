package boj.dp;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

//https://www.acmicpc.net/problem/9461
public class BOJ9461 {

    public static void main(String[] args) {
        long[] memo = new long[101];
        memo[1] = 1;
        memo[2] = 1;
        memo[3] = 1;
        memo[4] = 2;
        memo[5] = 2;
        List<Long> answers = new ArrayList<>();
        Scanner sc = new Scanner(System.in);

        int t = sc.nextInt();

        for (int i = 0; i < t; i++) {
            int num = sc.nextInt();
            answers.add(solutionWithLoop(num, memo));
        }

        for (Long answer : answers) {
            System.out.println(answer);
        }
        sc.close();
    }

    public static long solutionWithRecursive(int n, long[] memo) {
        if (n <= 5) return memo[n];
        if (memo[n] != 0) return memo[n];
        memo[n] = solutionWithRecursive(n - 1, memo) + solutionWithRecursive(n - 5, memo);
        return memo[n];
    }

    public static long solutionWithLoop(int n, long[] memo) {
        if (n <= 5) return memo[n];
        for (int i = 6; i <= n; i++) {
            memo[i] = memo[i - 5] + memo[i - 1];
        }
        return memo[n];
    }

}
