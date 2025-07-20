package boj.dp;

import java.io.*;
import java.util.Arrays;

public class BOJ12865 {
    /**
     * 점화식 dp[n][k] : n 번째 물품까지 포함했을 때, j 무게만큼 담은 최대 가치
     * dp[n][k] = Math.max(dp[n-1][k], dp[n-1][k-weight[n]] + value[n])
     * dp[n-1][k-1] : 현재 물품 안넣을 때,
     * dp[n-1][k-weight[n]] + value[n] : 현재 물품 넣을 때
     */
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String input = br.readLine();
        String[] split = input.split(" ");
        int n = Integer.parseInt(split[0]);
        int k = Integer.parseInt(split[1]);
        int[] weights = new int[n];
        int[] values = new int[n];
        for (int i = 0; i < n; i++) {
            input = br.readLine();
            split = input.split(" ");
            weights[i] = Integer.parseInt(split[0]);
            values[i] = Integer.parseInt(split[1]);
        }

        int[][] dp = new int[n][k+1];
        for (int i = 0; i < n; i++)
            Arrays.fill(dp[i], 0);
        // 초기값 설정
        for(int i=0; i<=k; i++) {
            int nowWeight = weights[0];
            int nowValue = values[0];
            if(nowWeight <= i){
                dp[0][i]  = nowValue;
            }
        }

        for(int i = 1; i < n; i++) {
            int nowWeight = weights[i];
            int nowValue = values[i];
            for (int j = 0; j <= k; j++) {
                dp[i][j] = dp[i-1][j];
                if(nowWeight <= j){
                    dp[i][j] = Math.max(dp[i][j], dp[i-1][j-nowWeight] + nowValue);
                }
            }
        }

        System.out.println(dp[n-1][k]);
        br.close();
    }
}
