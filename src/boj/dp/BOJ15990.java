package boj.dp;

import java.util.*;

public class BOJ15990 {
    public static void main(String[] args) {
        final Scanner sc = new Scanner(System.in);
        final Map<DpParams, Long> memo = new HashMap<>();
        memo.put(new DpParams(0, 1), 0l);
        memo.put(new DpParams(0, 2), 0l);
        memo.put(new DpParams(0, 3), 0l);
        memo.put(new DpParams(1, 1), 1l);
        memo.put(new DpParams(1, 2), 0l);
        memo.put(new DpParams(1, 3), 0l);
        memo.put(new DpParams(2, 1), 0l);
        memo.put(new DpParams(2, 2), 1l);
        memo.put(new DpParams(2, 3), 0l);
        memo.put(new DpParams(3, 1), 1l);
        memo.put(new DpParams(3, 2), 1l);
        memo.put(new DpParams(3, 3), 1l);
        final int n = sc.nextInt();
        for(int i=0; i<n; i++) {
            int input = sc.nextInt();
            long answer = (dp(input, 1, memo) + dp(input, 2, memo) + dp(input, 3, memo)) %1000000009;
            System.out.println(answer);
        }
        sc.close();
    }

    /**
     * 리턴값 : int (부호 있는 int 최대 20억까지 가능)
     * 1,2,3을 더해서 N을 만드는 방법의 수를 구한다.
     * 단, 연속되는 숫자가 있으면 안된다.
     * dp(n) = dp(n,1) + dp(n,2) + dp(n,3)
     * dp(n,1) = dp(n-1, 2) + dp(n-1, 3)
     * dp(n,2) = dp(n-2, 1) + dp(n-2, 3)
     * dp(n,3) = dp(n-3, 1) + dp(n-3, 2)
     * dp(0,1) = dp(0,2) = dp(0,3) = 0
     */
    public static long dp(int n, int lastNum, Map<DpParams, Long> memo) {
        final DpParams key = new DpParams(n, lastNum);
        if(memo.containsKey(key)) {
            return memo.get(key);
        }

        if (lastNum == 1) {
            long dp = (dp(n-1, 2, memo) + dp(n-1, 3, memo))% 1000000009;
            memo.put(key, dp);
            return dp;
        }

        if (lastNum == 2) {
            long dp = (dp(n-2, 1, memo) + dp(n-2, 3, memo))% 1000000009;
            memo.put(key, dp);
            return dp;
        }

        // lastNum == 3
        long dp = (dp(n-3, 1, memo) + dp(n-3, 2, memo))% 1000000009;
        memo.put(key, dp);
        return dp;
    }

    /**
     * n : 만들어야 하는 정수
     * lastNum : 마지막 숫자
     */
    static class DpParams {
        int n;
        int lastNum;

        public DpParams(int n, int lastNum) {
            this.n = n;
            this.lastNum = lastNum;
        }

        @Override
        public boolean equals(Object o) {
            if (!(o instanceof DpParams)) return false;
            DpParams dpParams = (DpParams) o;
            return n == dpParams.n && lastNum == dpParams.lastNum;
        }

        @Override
        public int hashCode() {
            return Objects.hash(n, lastNum);
        }
    }
}
