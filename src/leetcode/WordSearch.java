package leetcode;

import java.util.Arrays;

// https://leetcode.com/problems/word-search/
public class WordSearch {
    public static void main(String[] args) {
        char[][] board1 = {{'A', 'B', 'C', 'E'}, {'S', 'F', 'C', 'S'}, {'A', 'D', 'E', 'E'}};
        String word1 = "ABCCED";
        WordSearch wordSearch = new WordSearch();
        boolean exist = wordSearch.exist(board1, word1);
        System.out.println(exist);
    }

    public boolean exist(char[][] board, String word) {
        int[][] move = {{-1, 0}, {0, 1}, {1, 0}, {0, -1}};
        for(int i = 0; i < board.length; i++) {
            for(int j = 0; j < board[0].length; j++) {
                if(board[i][j] != word.charAt(0)) continue;
                int[][] visited = new int[board.length][board[0].length];
                for (int[] arr : visited) {
                    Arrays.fill(arr, -1);
                }
                visited[i][j] = 0;
                boolean bfs = bfs(board, word, move, new Node(i, j), visited);
                if (bfs) {
                    return true;
                }
            }
        }
        return false;
    }

    public boolean bfs(
            char[][] board,
            String word,
            int[][] move,
            Node now,
            int[][] visited
    ) {
        int idxInWord = visited[now.row][now.col];
        if(idxInWord >= word.length()) return false;
        if(word.charAt(idxInWord) != board[now.row][now.col]) {
            return false;
        }
        if(idxInWord == word.length() - 1) return true;

        for(int i = 0; i < move.length; i++){
            int nextRow = now.row + move[i][0];
            int nextCol = now.col + move[i][1];
            if(nextRow <0 || nextRow >= board.length) continue;
            if(nextCol <0 || nextCol >= board[0].length) continue;
            if(visited[nextRow][nextCol] > -1) continue;
            Node nextNode = new Node(nextRow, nextCol);
            visited[nextRow][nextCol] = visited[now.row][now.col]+1;
            if(bfs(board, word, move, nextNode, visited)) return true;
            visited[nextRow][nextCol] = -1;
        }

        return false;
    }

    static class Node{
        int row;
        int col;

        Node(int row, int col) {
            this.row = row;
            this.col = col;
        }
    }
}
