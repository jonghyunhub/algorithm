package boj.graph;

import java.io.*;
import java.util.*;

public class BOJ4963 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        while (true) {
            // 너비 w와 높이 h 입력 받기
            String line = br.readLine();
            StringTokenizer st = new StringTokenizer(line);
            int w = Integer.parseInt(st.nextToken());
            int h = Integer.parseInt(st.nextToken());

            // 0 0이 입력되면 프로그램 종료
            if (w == 0 && h == 0) {
                break;
            }

            // 지도 입력 받기
            int[][] map = new int[h][w];
            for (int i = 0; i < h; i++) {
                st = new StringTokenizer(br.readLine());
                for (int j = 0; j < w; j++) {
                    map[i][j] = Integer.parseInt(st.nextToken());
                }
            }
            boolean[][] visited = new boolean[h][w];

            int cnt = 0;
            for(int i=0; i<h;i++) {
                for(int j=0;j<w;j++) {
                    if(map[i][j] == 0) continue; // 바다인 경우 스킵
                    if(visited[i][j]) continue; // 이미 방문한 곳은 스킵
                    dfs(i,j, map, visited);
                    cnt++;
                }
            }
            System.out.println(cnt);

        }

        br.close();
    }

    public static void dfs(int startRow,
                           int startCol,
                           int[][] map,
                           boolean[][] visited) {
        final int[][] moves = {
                {1,0},
                {-1,0},
                {0,1},
                {0,-1},
                {-1,-1},
                {-1,1},
                {1,-1},
                {1,1}
        };

        Stack<Node> stack = new Stack<>();
        Node start = new Node(startRow, startCol);
        stack.push(start);

        while (!stack.isEmpty()) {
            Node now = stack.pop();
            if(visited[now.row][now.col]) continue;
            if(map[now.row][now.col] == 0) continue;
            for(int[] move : moves) {
                int nextRow = now.row + move[0];
                int nextCol = now.col + move[1];
                if(nextRow < 0 || nextRow >= map.length || nextCol < 0 || nextCol >= map[0].length) continue;
                if(map[nextRow][nextCol] == 0) continue;
                if(visited[nextRow][nextCol]) continue;
                stack.push(new Node(nextRow, nextCol));
            }
            visited[now.row][now.col] = true;
        }
    }

    static class Node{
        int row;
        int col;

        public Node(int row, int col) {
            this.row = row;
            this.col = col;
        }
    }
}
