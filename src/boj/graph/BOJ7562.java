package boj.graph;

import java.io.*;
import java.util.*;

public class BOJ7562 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());
        for (int i = 0; i < n; i++) {
            int l = Integer.parseInt(br.readLine());
            String[] startSplit = br.readLine().split(" ");
            int startRow = Integer.parseInt(startSplit[0]);
            int startCol = Integer.parseInt(startSplit[1]);
            String[] split = br.readLine().split(" ");
            int endRow = Integer.parseInt(split[0]);
            int endCol = Integer.parseInt(split[1]);
            int cnt = bfs(new Node(startRow, startCol), new Node(endRow, endCol), l);
            System.out.println(cnt);
        }
        br.close();
    }

    public static int bfs(Node start,
                          Node end,
                          int l) {
        int[][] moves = {
                {-2, -1},
                {-1, -2},
                {-1, 2},
                {-2, 1},
                {1, -2},
                {2, -1},
                {2, 1},
                {1, 2}
        };
        int[][] visited = new int[l][l];
        for (int i = 0; i < l; i++) {
            Arrays.fill(visited[i], -1);
        }
        Queue<Node> queue = new ArrayDeque<>();
        queue.add(start);
        visited[start.row][start.col] = 0;
        while (!queue.isEmpty()) {
            Node now = queue.poll();
            if(now.row == end.row && now.col == end.col) return visited[now.row][now.col];
            for(int[] move : moves) {
                int nextRow = now.row + move[0];
                int nextCol = now.col + move[1];
                if(nextRow<0 || nextRow>=l || nextCol<0 || nextCol>=l) continue;
                if(visited[nextRow][nextCol] > -1) continue;
                queue.add(new Node(nextRow, nextCol));
                visited[nextRow][nextCol] = visited[now.row][now.col] + 1;
            }
        }
        return -1;
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
