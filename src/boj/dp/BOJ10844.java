package boj.dp;

import java.util.*;

/**
 * 계단수의 정의 : 인접한 숫자들의 차이가 1로만 이루어진 수
 * 길이가 N인 계단수 : 길이가 N-1인 계단수의 마지막 숫자를 기준으로 갯수가 달라짐
 * <p>
 * [점화식 유도]
 * dp(N-1, 0) => dp(N,1)
 * dp(N-1, 1) => dp(N,0), dp(N,2)
 * ...
 * dp(N-1, 8) => dp(N,7), dp(N,9)
 * dp(N-1, 9) => dp(N,8)
 * 이므로,
 * dp(N,0) = dp(N-1, 1)
 * dp(N,1) = dp(N-1, 0) + dp(N-1, 2)
 * dp(N,2) = dp(N-1, 1) + dp(N-1, 3)
 * dp(N,3) = dp(N-1, 2) + dp(N-1, 4)
 * ...
 * dp(N,7) = dp(N-1, 6) + dp(N-1, 8)
 * dp(N,8) = dp(N-1, 7) + dp(N-1, 9)
 * dp(N,9) = dp(N-1, 8)
 * dp(N) = dp(N,0) + dp(N,1) + dp(N,2) + ... + dp(N,9)
 * = dp(N-1,0) + 2dp(N-1, 1) + 2dp(N-1, 2) +  ... + 2dp(N-1, 8) + dp(N-1, 9)
 * <p>
 * [초기값]
 * ap(0,0) = dp(0,1) = dp(0,2) = dp(0,3) = ... dp(0,8) = dp(0,9) = 0
 * ap(1,0) = 0, dp(1,1) = 1, dp(1,2) = dp(1,3) = ... dp(1,8) = 2, dp(1,9) = 1
 * dp(2,0) = 1
 */
public class BOJ10844 {
    public static void main(String[] args) {
        final Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        // 메모이제이션 배열 초기화
        final long[][] memo = initMemo(n);
        long answer = dp(n, memo);
        answer = answer % 1000000000;
        System.out.println(answer);
        sc.close();
    }

    private static long[][] initMemo(int n) {
        final long[][] memo = new long[n + 1][10];
        for (long[] arr : memo) {
            Arrays.fill(arr, -1);
        }
        for (int i = 0; i < 10; i++) {
            memo[0][i] = 0;
        }
        memo[1][0] = 0;
        for (int i = 1; i < 10; i++) {
            memo[1][i] = 1;
        }
        return memo;
    }

    /**
     * [점화식]
     * dp(N) = dp(N-1,0) + 2dp(N-1, 1) + 2dp(N-1, 2) +  ... + 2dp(N-1, 8) + dp(N-1, 9)
     */
    public static long dp(int n, long[][] memo) {
        long dp = 0l;
        for (int i = 0; i <= 9; i++) {
            dp += (dpHelper(n, i, memo) % 1000000000);
        }
        return dp;
    }

    /**
     * dp(N,0) = dp(N-1, 1)
     * dp(N,1) = dp(N-1, 0) + dp(N-1, 2)
     * dp(N,2) = dp(N-1, 1) + dp(N-1, 3)
     * dp(N,3) = dp(N-1, 2) + dp(N-1, 4)
     * ...
     * dp(N,7) = dp(N-1, 6) + dp(N-1, 8)
     * dp(N,8) = dp(N-1, 7) + dp(N-1, 9)
     * dp(N,9) = dp(N-1, 8)
     * dp(N) = dp(N,0) + dp(N,1) + dp(N,2) + ... + dp(N,9)
     *
     * @return
     */
    public static long dpHelper(int n, int lastNum, long[][] memo) {
        if(lastNum < 0 || lastNum >= 10) return 0;
        if (memo[n][lastNum] != -1) {
            return memo[n][lastNum];
        }
        long dp = 0l;
        if (lastNum == 0) {
            dp += dpHelper(n - 1, 1, memo) % 1000000000;
        } else if (lastNum == 9) {
            dp += dpHelper(n - 1, 8, memo) % 1000000000;
        } else {
            dp += dpHelper(n - 1, lastNum - 1, memo) % 1000000000 + dpHelper(n - 1, lastNum + 1, memo) % 1000000000;
        }
        memo[n][lastNum] = dp;
        return dp;
    }
}
