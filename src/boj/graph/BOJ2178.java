package boj.graph;

import java.io.*;
import java.util.*;


public class BOJ2178 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());
        int[][] map = new int[N][M];
        for (int i = 0; i < N; i++) {
            String[] split = br.readLine().split("");
            for (int j = 0; j < M; j++) {
                map[i][j] = Integer.parseInt(split[j]);
            }
        }

        Node start = new Node(0, 0);
        Node end = new Node(N-1, M-1);
        int answer = bfs(start, end, map);
        System.out.println(answer);

        br.close();
    }

    public static int bfs(Node start,
                          Node end,
                          int[][] map) {
        Queue<Node> queue = new ArrayDeque<>();
        int[][] visited = new int[map.length][map[0].length];
        for (int i = 0; i < map.length; i++) {
            Arrays.fill(visited[i], -1);
        }
        int[][] moves = {
                {1,0},
                {-1,0},
                {0,1},
                {0,-1},
        };
        queue.add(start);
        visited[start.row][start.col] = 1;
        while (!queue.isEmpty()) {
            Node now = queue.poll();
            if(now.row == end.row && now.col == end.col)
                return visited[now.row][now.col];

            for(int[] move : moves) {
                int nextRow = now.row + move[0];
                int nextCol = now.col + move[1];
                if(nextRow < 0 || nextRow >= map.length || nextCol < 0 || nextCol >= map[0].length) continue;
                if(visited[nextRow][nextCol] > -1) continue;
                if(map[nextRow][nextCol] == 0) continue; // 이동할수 없는 칸
                queue.add(new Node(nextRow, nextCol));
                visited[nextRow][nextCol] = visited[now.row][now.col] + 1;
            }
        }
        return -1; // 마지막 노드에 도달할 수 없는 경우
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
