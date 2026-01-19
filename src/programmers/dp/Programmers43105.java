package programmers.dp;

// https://school.programmers.co.kr/learn/courses/30/lessons/43105
public class Programmers43105 {
    public static void main(String[] args) {
        int[][] triangle1 = {{7}, {3, 8}, {8, 1, 0}, {2, 7, 4, 4}, {4, 5, 2, 6, 5}};
        int answer1 = 30;
        int result = new Solution().solution(triangle1);
        System.out.println(result == answer1);
    }


    /**
     이동 가능 : [i][j] => [i+1][j] or [i+1][j+1]
     점화식 : dp[i][j] => (i,j) 위치까지 도달했을 때 거쳐간 숫자의 최대 합
     dp[i][j] = Max(dp[i-1][j-1], dp[i-1][j]) + arr[i][j]
     */
    static class Solution {
        public int solution(int[][] triangle) {
            int answer = 0;
            int[][] dp = new int[triangle.length][triangle[triangle.length-1].length];
            dp[0][0] = triangle[0][0];
            for(int i = 1; i < triangle.length; i++) {
                dp[i][0] = triangle[i][0] + dp[i-1][0]; // 삼각형 왼쪽
            }

            for(int i=1; i<triangle.length; i++) {
                for(int j=1; j<triangle[i].length; j++) {
                    dp[i][j] = Math.max(dp[i-1][j-1], dp[i-1][j]) +  triangle[i][j];
                }
            }


            for(int i=0; i<triangle[triangle.length-1].length; i++) {
                answer = Math.max(answer, dp[triangle.length-1][i]);
            }

            return answer;
        }
    }
}
