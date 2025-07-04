package boj.dp;

import java.util.Arrays;
import java.util.Scanner;

public class BOJ1230 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String firstStr = sc.nextLine();
        String secondStr = sc.nextLine();
        int[][] memo = new int[firstStr.length()+1][secondStr.length()+1];
        for (int i = 0; i < firstStr.length(); i++) {
            Arrays.fill(memo[i], -1);
        }
        /**
         * 첫 번째 행 (DP[0][j])
         * 의미: 빈 문자열 ""을 두 번째 문자열의 처음 j개 문자로 변환
         * 값: DP[0][j] = j (j번의 삽입 필요)
         */
        for(int j = 0; j < secondStr.length(); j++)
            memo[0][j] = j;

        /**
         * 첫 번째 열 (DP[i][0])
         * 의미: 첫 번째 문자열의 처음 i개 문자를 빈 문자열 ""로 변환
         * 값: DP[i][0] = i (i번의 삭제 필요)
         */
        for (int i = 0; i < firstStr.length(); i++)
            memo[i][0] = i;
        /**
         * 시작점 (DP[0][0])
         * 의미: 빈 문자열 ""을 빈 문자열 ""로 변환
         * 값: DP[0][0] = 0 (변환 불필요)
         */
        memo[0][0] = 0;
        int answer = dp(firstStr.length()-1, secondStr.length()-1, memo, firstStr, secondStr);
        System.out.println(answer);
    }

    /**
     * LevenStein(문자열 거리) [점화식]
     * 삽입 : dp[i][j] = dp[i][j-1] + 1
     * dp[i][j] 첫번째 문자열 i개의 문자와 두번째 문자열 j개의 거리
     * dp[i][j-1] 첫번째 문자열 i개의 문자와 두번째 문자열 j-1개의 거리
     * -> 첫번째 문자열 i개의 문자와 두번째 문자열 j-1개의 거리에서 하나를 추가한 j개의 문자와 i개의 문자의 거리
     *  첫 번째 문자열에 j번째 문자(두번째 문자열의) 추가
     *
     * 삭제 : dp[i][j] = dp[i-1][j] + 1
     * dp[i][j] 첫번째 문자열 i개의 문자와 두번째 문자열 j개의 거리
     * dp[i][j-1] 첫번째 문자열 i개의 문자와 두번째 문자열 j-1개의 거리
     * -> 첫번째 문자열 i개의 문자와 두번째 문자열 j-1개의 거리에서 하나를 삭제한 j개의 문자와 i개의 문자의 거리
     * 첫 번째 문자열에서 i번째 문자 제거
     *
     * 변환 : dp[i][j] = dp[i-1][j-1] + cost (i-1, j-1 문자가 같으면 cost = 0, 다르면 cost = 1) (해당 인덱스의 문자만 비교)
     */
    public static int dp(int i, int j, int[][] memo, String firstStr,String secondStr) {
        if(memo[i][j] != -1) return memo[i][j];

        int dist = Integer.MAX_VALUE;
        // 삽입
        dist = Math.min(dp(i, j-1, memo, firstStr, secondStr) +1, dist);

        // 삭제
        dist = Math.min(dp(i-1, j, memo, firstStr, secondStr) +1, dist);

        // 치환
        int convert = dp(i-1, j-1, memo, firstStr, secondStr);
        if(firstStr.charAt(i-1) != secondStr.charAt(j-1)) convert +=1; // i-1, j-1 같지 않으면 1 추가 , 같으면 추가 x (cost)
        dist = Math.min(convert, dist);

        memo[i][j] = dist;
        return memo[i][j];
    }
}
