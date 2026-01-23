package boj.backtracking;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;


// https://www.acmicpc.net/problem/9663
public class BOJ9663 {

    static int answer = 0;

    // Queen : 상하좌우, 대각선 겹치면 안됨
    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(reader.readLine());
        int[] columns = new int[n];
        Arrays.fill(columns, -1);
        backTracking(0, n, columns);
        System.out.println(answer);
    }

    /**
     * 아이디어 : row 를 증가시키면서 Queen 하나씩 배치
     * 왼쪽 위 -> 오른쪽 아래 순으로 탐색
     * 1. 같은 col인 Node가 있으면 X
     * 2. 대각선 왼쪽 위 Node 가 있으면 X -> (row -k , col - k) = (row,col)
     * 3. 대각선 오른쪽 위 Node 가 있으면 X -> (row -k , col + k) = (row,col)
     * columns[0] = 1 => row = 0 , col = 1 에 퀸 위치
     */
    static public void backTracking(int row ,int n, int[] columns) {
        if(row >= n) {
            answer++;
            return;
        }

        for(int col = 0; col < n; col++) {
            if(!canMakeQueen(row, col, columns)) continue;
            columns[row] = col;
            backTracking(row + 1, n, columns);
            columns[row] = -1;
        }
    }

    static public boolean canMakeQueen(int nowRow, int nowCol, int[] columns){
        for(int row=0; row<columns.length; row++) {
            if(columns[row] == -1) continue;
            if(row == nowRow || columns[row] == nowCol) {
                return false;
            }
            if(Math.abs(nowRow - row) == Math.abs(nowCol - columns[row])) {
                return false;
            }
        }

        return true;
    }

}