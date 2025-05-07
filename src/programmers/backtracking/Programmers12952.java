package programmers.backtracking;

import java.util.Arrays;

// https://school.programmers.co.kr/learn/courses/30/lessons/12952
public class Programmers12952 {
    public static void main(String[] args) {
        Programmers12952 programmers12952 = new Programmers12952();
        int n1 = 4;
        int solution = programmers12952.solution(n1);
        System.out.println(solution);
    }

    public int solution(int n) {
        int answer = 0;
        int[][] map = new int[n][n];
        for (int i = 0; i < n; i++)
            Arrays.fill(map[i], 0); // 0은 비어있음, 1은 들어있음
        for (int col = 0; col < map[0].length; col++) {
            answer += search(0, col, map);
        }
        return answer;
    }

    public int search(int row, int col, int[][] map) {
        if (row == map.length - 1) return 1; // 마지막 행 도달

        int cnt = 0;
        map[row][col] = 1;
        for (int nextCol = 0; nextCol < map[0].length; nextCol++) {
            if (isSafe(row+1, nextCol, map))
                cnt += search(row + 1, nextCol, map);
        }
        map[row][col] = 0; // 백트래킹
        return cnt;
    }

    public boolean isSafe(int row, int col, int[][] map) {
        // 같은 열 확인
        for(int i=0; i<map.length; i++){
            if(map[row][i] == 1) return false;
        }

        // 같은 행 확인
        for(int i=0; i<map[0].length; i++){
            if(map[i][col] == 1) return false;
        }

        // 왼쪽 위 대각선 확인
        for (int i = row, j = col; i >= 0 && j >= 0; i--, j--) {
            if (map[i][j] == 1) {
                return false;
            }
        }

        // 오른쪽 위 대각선 확인
        for (int i = row, j = col; i >= 0 && j < map.length; i--, j++) {
            if (map[i][j] == 1) {
                return false;
            }
        }


        return true;
    }
}
