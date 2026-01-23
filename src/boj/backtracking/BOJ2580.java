package boj.backtracking;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

// https://www.acmicpc.net/problem/2580
public class BOJ2580 {

    static int[][] answer = new int[9][9];


    public static void main(String[] args) throws IOException {
        int[][] arr = new int[9][9];
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        for (int i = 0; i < 9; i++) {
            String[] split = reader.readLine().split(" ");
            for (int j = 0; j < split.length; j++) {
                arr[i][j] = Integer.parseInt(split[j]);
            }
        }

        List<Node> nodes = new ArrayList<>();
        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                if (arr[i][j] == 0) {
                    nodes.add(new Node(i, j));
                }
            }
        }

        backTracking(nodes, arr, 0);


        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                System.out.print(answer[i][j]);
                if (j < 8) {
                    System.out.print(" ");
                }
            }
            if (i < 8) {
                System.out.println();
            }
        }
    }

    public static void backTracking(List<Node> nodes, int[][] arr, int idx) {
        if (idx >= nodes.size()) {
            for(int i = 0; i < 9; i++) {
                for (int j = 0; j < 9; j++) {
                    answer[i][j] = arr[i][j];
                }
            }
            return;
        }

        for (int val = 1; val <= 9; val++) {
            Node node = nodes.get(idx);
            if(!canMakeSudoku(arr, node.row, node.col, val)) continue;
            arr[node.row][node.col] = val;
            backTracking(nodes, arr, idx + 1);
            arr[node.row][node.col] = 0;
        }
    }

    public static boolean canMakeSudoku(int[][] arr, int row, int col, int val) {
        for (int i = 0; i < 9; i++) {
            if (arr[row][i] == val) return false;
            if (arr[i][col] == val) return false;
        }

        // row / 3 + i, col/ 3 + j
        int startRow = (row / 3) * 3;
        int startCol = (col / 3) * 3;

        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                if (arr[startRow + i][startCol + j] == val) return false;
            }
        }

        return true;
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
