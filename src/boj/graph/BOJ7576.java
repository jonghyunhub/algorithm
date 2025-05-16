package boj.graph;

import java.io.*;
import java.util.*;

public class BOJ7576 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] split = br.readLine().split(" ");
        int column = Integer.parseInt(split[0]);
        int row = Integer.parseInt(split[1]);
        int [][] map = new int[row][column];
        for(int i = 0; i < row; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            for(int j = 0; j < column; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        int[][] moves = {
                {-1,0},
                {1,0},
                {0,-1},
                {0,1},
        };

        bfs(map, moves);

        int answer = 0;
        for(int i = 0; i < row; i++) {
            for(int j = 0; j < column; j++) {
                if(map[i][j] == 0) {
                    System.out.println(-1);
                    br.close();
                    return;
                }
                answer = Math.max(answer, map[i][j]);
            }
        }

        System.out.println(answer-1);
        br.close();
    }

    public static void bfs(int[][] map,
                          int[][] moves) {
        Queue<Node> queue = new ArrayDeque<>();
        // 처음에 들어있는 토마토를 bfs 시작점 처리
        for(int i = 0; i < map.length; i++) {
            for(int j = 0; j < map[0].length; j++) {
                if(map[i][j] == 1) {
                    queue.add(new Node(i, j));
                }
            }
        }

        while (!queue.isEmpty()) {
            Node now = queue.poll();
            for(int[] move : moves) {
                int nextRow = now.row + move[0];
                int nextCol = now.col + move[1];
                if(nextRow < 0 || nextRow >= map.length || nextCol < 0 || nextCol >= map[0].length) continue;
                if(map[nextRow][nextCol] == -1) continue;
                if(map[nextRow][nextCol] > 0) continue;
                map[nextRow][nextCol] = map[now.row][now.col] + 1;
                queue.add(new Node(nextRow, nextCol));
            }
        }
    }

    static class Node {
        int row;
        int col;

        public Node(int row, int col) {
            this.row = row;
            this.col = col;
        }
    }


}
