package boj.graph;

import java.util.*;

public class BOJ1520 {
    static int[][] dp;  // 메모이제이션을 위한 배열
    static int[][] graph;
    static  Map<String, Move> moveMap;
    static int n;
    static int m;

    public static void main(String[] args) {
        final Scanner scanner = new Scanner(System.in);
        n = scanner.nextInt();
        m = scanner.nextInt();
        graph = new int[n][m];
        dp = new int[n][m];  // dp 배열 초기화
        for (int row = 0; row < n; row++)
            for (int col = 0; col < m; col++) {
                graph[row][col] = scanner.nextInt();
                dp[row][col] = -1;
            }
        dp[n-1][m-1] = 1;
        moveMap = Map.of(
                "DOWN", new Move(1, 0),
                "RIGHT", new Move(0, 1),
                "LEFT", new Move(0, -1),
                "UP", new Move(-1, 0)
        );
        dfs( 0, 0);
        System.out.println(dp[0][0]);
        scanner.close();
    }

    public static int dfs(int row, int col) {
        // 끝점에 도달한 경우
        if (row == n - 1 && col == m - 1) {
            return 1;
        }

        // 이미 계산된 경우 해당 값 반환
        if (dp[row][col] > -1) {
            return dp[row][col];
        }

        // 해당 지점 0으로 초기화
        dp[row][col] = 0;

        for (Move move : moveMap.values()) {
            int nextRow = row + move.row;
            int nextCol = col + move.col;

            // 벽인 경우 skip
            if (nextRow < 0 || nextRow > n - 1 || nextCol < 0 || nextCol > m - 1) {
                continue;
            }

            // 이동하려는 노드의 높이가 현재 노드의 높이 보다 크거나 같으면 skip
            if (graph[row][col] <= graph[nextRow][nextCol])
                continue;

            dp[row][col] += dfs(nextRow, nextCol);
        }

        return dp[row][col];
    }


    public static class Move {
        int row;
        int col;

        public Move(int row, int col) {
            this.row = row;
            this.col = col;
        }
    }

}
