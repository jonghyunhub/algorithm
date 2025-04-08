package boj.dp;

import java.util.*;

/**
 * [점화식]
 * dp(n) = max(dp(i)) + 1(0<=i<n, arr[i] < arr[n])
 * if) arr[i] < arr[n] 인 i가 없으면 dp(n) = 1
 * dp를 모두 계산한 이후에 dp 배열 중 가장 큰 값을 리턴
 */
public class BOJ11053 {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        final int[] arr = new int[n];
        final int[] memo = new int[n];
        Arrays.fill(memo, -1);
        memo[0] = 1;
        for (int i = 0; i < n; i++) {
            arr[i] = sc.nextInt();
        }
        dp(n - 1, arr, memo);
        int answer = 0;
        for(int i=0; i<n; i++) { // O(N)
            if(memo[i] > answer) {
                answer = memo[i];
            }
        }
        System.out.println(answer);
        sc.close();
    }

    // O(N^2)
    public static int dp(int n, int[] arr, int[] memo){
        if(memo[n] != -1) return memo[n];

        // if) arr[i] < arr[n] 인 i가 없으면 dp(n) = 1
        int max = 1;
        for(int i=0; i<n; i++){ // O(N)
            int dp = dp(i, arr, memo) + 1; // O(N)
            if(arr[i] >= arr[n]) continue; // arr[i] < arr[n] 을 만족하지 않으면 skip
            max = Math.max(max, dp);
        }
        memo[n] = max;
        return max;
    }


}
