package boj.dp;

import java.util.Scanner;

public class BOJ11722 {
    public static void main(String[] args) {
        final Scanner sc = new Scanner(System.in);
        final int n = sc.nextInt();
        final int[] arr = new int[n];
        for (int i = 0; i < n; i++) {
            arr[i] = sc.nextInt();
        }
        final int[] memo = new int[n];
        memo[0] = 1;
        for (int i = 1; i < n; i++) {
            dp(i, arr, memo);
        }
        int max = 0;
        for (int i = 0; i < n; i++) {
            max = Math.max(max, memo[i]);
        }
        System.out.println(max);
        sc.close();
    }

    public static int dp(int n, int[] arr, int[] memo) {
        if (memo[n] != 0) return memo[n];
        int len = 1;
        for(int i=0; i<n; i++){
            if(arr[i] > arr[n]){
                int dp = dp(i, arr, memo) + 1;
                len = Math.max(len, dp);
            }
        }
        memo[n] = len;
        return len;
    }
}
