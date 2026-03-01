package programmers.dp;

import java.util.*;

public class Programmers42895 {
    public static void main(String[] args) {
        int N1 = 5;
        int number1 = 12;
        int answer1 = 4;

        int N2 = 2;
        int number2 = 11;
        int answer2 = 3;

        Solution solution = new Solution();
        int solution1 = solution.solution2(N1, number1);
        System.out.println(solution1 == answer1);
    }

    static class Solution {

        /**
         * N으로 표현
         * 선택지 : +,-,*,/ 사용해서 표현
         * dp(x) : N을 x번 사용해서 만들 수 있는 모든 숫자들의 집합
         * dp(x) = Min( dp(x - N) + 1, dp(x - 1) + 2 , )
         * dp(0) = 0
         */
        // 이렇게 하면 답이 안나옴 why? 중간에 N을 넘어가는 경우는 커버를 못함
        // 예시 : 12 = 55 / 5 + 5 / 5 <- 55 때문에 커버 안됨
        public int solution(int N, int number) {
            int[] dp = new int[number + 1];
            dp[0] = 0;
            for(int i=1; i<=N; i++){
                // 1 = N/N
                // 2 = N/N + N/N
                dp[i] = i * 2;
            }

            for(int i = N+ 1; i<=number; i++){
                dp[i] = Math.min(dp[i - N] + 1, dp[i - 1] + 2);
            }

            int answer = dp[number];

            if (answer > 8) {
                return -1;
            }

            return answer;
        }


        /**
         * idea :
         */
        public int solution2(int N, int number) {
            // 예외 처리: N 자체가 number인 경우
            if (N == number) {
                return 1;
            }

            // S[k] = N을 정확히 k번 사용해서 만들 수 있는 모든 숫자들의 집합
            List<Set<Integer>> S = new ArrayList<>();

            // 인덱스 맞추기 위해 S[0]은 빈 집합으로
            for (int i = 0; i <= 8; i++) {
                S.add(new HashSet<>());
            }

            // 이어붙인 숫자를 계산하기 위한 변수 (5, 55, 555, ...)
            int concat = 0;

            for (int k = 1; k <= 8; k++) {
                // 1) 이어붙이기: N을 k번 이어붙인 수 추가
                concat = concat * 10 + N;
                S.get(k).add(concat);

                // 2) 조합: S[i]와 S[k-i]의 모든 원소 쌍에 사칙연산 적용
                for (int i = 1; i < k; i++) {
                    for (int a : S.get(i)) {
                        for (int b : S.get(k - i)) {
                            S.get(k).add(a + b);
                            S.get(k).add(a - b);
                            S.get(k).add(a * b);
                            if (b != 0) {
                                S.get(k).add(a / b);
                            }
                        }
                    }
                }

                // 3) 확인: number를 만들 수 있으면 k 반환
                if (S.get(k).contains(number)) {
                    return k;
                }
            }

            return -1;
        }
    }
}
