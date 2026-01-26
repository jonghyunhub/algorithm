package leetcode;

import java.util.*;

// https://leetcode.com/problems/rotting-oranges/
public class RottingOranges {
    public static void main(String[] args) {
        RottingOranges rottingOranges = new RottingOranges();
        int[][] grid1 = {{2, 1, 1}, {1, 1, 0}, {0, 1, 1}};
        int[][] grid2 = {{0,2}};
        int[][] grid3 = {{0,2,2}};
        int answer = rottingOranges.orangesRotting(grid2);
        System.out.println(answer);
    }

    // multi bfs 문제 - 시작점이 여러개 있을수도 있음
    public int orangesRotting(int[][] grid) {
        int[][] moves = {{-1,0}, {1,0}, {0,-1}, {0,1}};
        int[][] visited = new int[grid.length][grid[0].length];
        for (int i = 0; i < visited.length; i++) {
            Arrays.fill(visited[i], -1);
        }
        bfs(grid, visited, moves);

        int answer = 0;
        for(int i=0; i<grid.length; i++){
            for(int j=0; j<grid[0].length; j++){
                if(grid[i][j] == 1) return -1;
                answer = Math.max(answer, visited[i][j]);
            }
        }
        return answer;
    }

    void bfs(int[][] grid, int[][] visited, int[][] moves){
        Queue<Node> queue = new ArrayDeque<>();
        for(int i=0; i<grid.length; i++){
            for(int j=0; j<grid[0].length; j++){
                if(grid[i][j] == 2) {
                    queue.add(new Node(i,j));
                    visited[i][j] = 0;
                }
            }
        }

        while(!queue.isEmpty()){
            Node now = queue.poll();
            for(int[] move : moves){
                int nextRow = now.row + move[0];
                int nextCol = now.col + move[1];
                if(nextRow < 0 || nextRow >= grid.length) continue;
                if(nextCol < 0 || nextCol >= grid[0].length) continue;
                if(grid[nextRow][nextCol] != 1) continue;
                if(visited[nextRow][nextCol] > 0) continue;

                visited[nextRow][nextCol] = visited[now.row][now.col] + 1;
                grid[nextRow][nextCol] = 2;
                queue.add(new Node(nextRow, nextCol));
            }
        }
    }

    class Node {
        int row;
        int col;

        Node(int row, int col){
            this.row = row;
            this.col = col;
        }
    }


//    // multi bfs 문제 - 시작점이 여러개 있을수도 있음
//    public int orangesRotting(int[][] grid) {
//        int[][] move = {{-1, 0}, {1, 0}, {0, -1}, {0, 1}};
//        int[][] visited = new int[grid.length][grid[0].length];
//        for (int[] arr : visited) {
//            Arrays.fill(arr, -1);
//        }
//        Queue<Node> queue = new ArrayDeque<>();
//
//        for(int i = 0; i < grid.length; i++){
//            for(int j = 0; j < grid[0].length; j++){
//                if(grid[i][j] == 2){
//                    queue.add(new Node(i, j));
//                    visited[i][j] = 0; // 시작점들은 모두 시간이 0
//                }
//            }
//        }
//
//        bfs(grid, visited, move, queue);
//
//        for (int i = 0; i < grid.length; i++) {
//            for (int j = 0; j < grid[0].length; j++) {
//                if (grid[i][j] == 1) return -1;
//            }
//        }
//
//        int time = 0;
//        for (int i = 0; i < grid.length; i++) {
//            for (int j = 0; j < grid[0].length; j++) {
//                time = Math.max(time, visited[i][j]);
//            }
//        }
//        return time;
//    }
//
//    public void bfs(int[][] grid, int[][] visited, int[][] moves, Queue<Node> queue) {
//        while (!queue.isEmpty()) {
//            Node now = queue.poll();
//            for (int[] move : moves) {
//                int nextRow = now.row + move[0];
//                int nextCol = now.col + move[1];
//                if (nextRow < 0 || nextRow >= grid.length || nextCol < 0 || nextCol >= grid[0].length) continue;
//                if (visited[nextRow][nextCol] > -1) continue;
//                if (grid[nextRow][nextCol] == 0) continue;
//                // 다음으로 지울 오렌지가 있으면 큐에 삽입
//                Node nextNode = new Node(nextRow, nextCol);
//                if (grid[now.row][now.col] == 2 && grid[nextRow][nextCol] == 1) {
//                    queue.add(nextNode);
//                    grid[nextRow][nextCol] = 2;
//                    visited[nextRow][nextCol] = visited[now.row][now.col] + 1;
//                }
//            }
//        }
//    }
//
//    public static class Node {
//        int row;
//        int col;
//
//        Node(int row, int col) {
//            this.row = row;
//            this.col = col;
//        }
//    }

}
