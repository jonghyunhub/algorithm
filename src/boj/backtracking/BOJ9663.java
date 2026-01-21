package boj.backtracking;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;


// https://www.acmicpc.net/problem/9663
public class BOJ9663 {

    static int answer = 0;

    // Queen : 상하좌우, 대각선 겹치면 안됨
    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(reader.readLine());
        List<Node> tmp = new ArrayList<>();
        for (int col = 0; col < n; col++) {
            Node start = new Node(0, col);
            tmp.add(start);
            backTracking(n, tmp, start);
            tmp.remove(tmp.size() - 1);
        }
        System.out.println(answer);
    }

    /**
     * 아이디어 : row 를 증가시키면서 Queen 하나씩 배치
     * 왼쪽 위 -> 오른쪽 아래 순으로 탐색
     * 1. 같은 col인 Node가 있으면 X
     * 2. 대각선 왼쪽 위 Node 가 있으면 X -> (row -k , col - k) = (row,col)
     * 3. 대각선 오른쪽 위 Node 가 있으면 X -> (row -k , col + k) = (row,col)
     */
    static public void backTracking(int n,
                                    List<Node> tmp,
                                    Node now) {
        if (now.row == n - 1) {
            answer++;
            return;
        }

        for (int col = 0; col < n; col++) {
            Node next = new Node(now.row + 1, col);
            if (!canAddQueen(tmp, next)) continue;
            tmp.add(next);
            backTracking(n, tmp, next);
            tmp.remove(tmp.size() - 1);
        }

    }


    static public boolean canAddQueen(List<Node> tmp, Node now) {
        for (Node node : tmp) {
            if (node.row == now.row) {
                return false;
            }

            if (node.col == now.col) {
                return false;
            }

            if (Math.abs(node.col - now.col) == Math.abs(node.row - now.row)) {
                return false;
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