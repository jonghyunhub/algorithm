package boj.dp;

import java.util.*;

public class BOJ17404 {
    public static void main(String[] args) {
        final Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        final int[][] arr = new int[n][3];
        for(int i = 0; i < n; i++) {
            for(int j = 0; j < 3; j++) {
                arr[i][j] = sc.nextInt();
            }
        }
        // 최솟값 찾기
        int min = Integer.MAX_VALUE;

        // 첫번째 색을 각각의 색으로 고정하고 처리
        for(int firstColor = 0; firstColor < 3; firstColor++) {
            final int[][] memo = new int[n][3];
            for(int i=0; i<3; i++){
                memo[0][i] = arr[0][i];
            }
            for(int i = 1; i < n; i++) {
                Arrays.fill(memo[i], -1);
            }
            for(int otherColor = 0; otherColor < 3; otherColor++) {
                if(otherColor == firstColor) continue;
                memo[0][otherColor] = Integer.MAX_VALUE - 1001; // 첫번째로 지정한 색 말고 다른 색의 값을 매우 크게 설정
            }

            for(int lastColor = 0; lastColor < 3; lastColor++) {
                if(firstColor == lastColor) continue;
                dp(n-1,lastColor,arr,memo);
            }

            // 결과 값 저장
            for(int i = 0; i < 3; i++) {
                if(memo[n-1][i] == -1) continue;
                min = Math.min(memo[n - 1][i], min);
            }
        }

        System.out.println(min);
        sc.close();
    }

    public static int dp(int now, int nowColor, int[][] arr, int[][] memo) {
        if(memo[now][nowColor] != -1) return memo[now][nowColor];
        int min = Integer.MAX_VALUE;
        for (int beforeColor = 0; beforeColor < 3; beforeColor++) {
            if(beforeColor == nowColor) continue; // 이웃한 집은 색깔이 달라야함
            min = Math.min(dp(now-1, beforeColor, arr, memo), min);
        }
        memo[now][nowColor] = min + arr[now][nowColor];
        return memo[now][nowColor];
    }
}
