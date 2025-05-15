package boj.graph;

import java.io.*;
import java.util.*;
import java.util.stream.Collectors;

public class BOJ2667 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());
        int[][] map  = new int[n][n];
        for (int i = 0; i < n; i++) {
            String[] split = br.readLine().split("");
            for (int j = 0; j < n; j++) {
                map[i][j] = Integer.parseInt(split[j]);
            }
        }
        List<Integer> houseCnt = new ArrayList<>();
        boolean[][] visited  = new boolean[n][n];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if(visited[i][j]) continue; // 이미 처리한 곳은 skip
                if(map[i][j] == 0) continue; // 집이 아니면 skip
                houseCnt.add(dfs(map,i,j, visited));
            }
        }

        List<Integer> sortedHouseCnt = houseCnt.stream().sorted().collect(Collectors.toList());
        System.out.println(sortedHouseCnt.size());
        for(int count:sortedHouseCnt){
            System.out.println(count);
        }

    }

    public static int dfs(int[][] map,
                           int startRow,
                           int startCol,
                           boolean[][] visited) {
        final Stack<Node> stack = new Stack<>();
        Node start = new Node(startRow, startCol);
        stack.push(start);
        int cnt = 0;
        int[][] moves = {{0, 1}, {1, 0}, {0, -1}, {-1, 0}};
        while (!stack.isEmpty()) {
            Node now = stack.pop();
            if(visited[now.row][now.col]) continue;
            if(map[now.row][now.col] == 0) continue;
            visited[now.row][now.col] = true;
            cnt++;
            for(int[] move : moves){
                int nextRow = now.row + move[0];
                int nextCol = now.col + move[1];
                if(nextRow <0 || nextRow >= map.length || nextCol <0 || nextCol >= map[0].length) continue;
                stack.push(new Node(nextRow, nextCol));
            }
        }


        return cnt;
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
