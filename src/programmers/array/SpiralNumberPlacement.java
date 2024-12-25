package programmers.array;

// https://school.programmers.co.kr/learn/courses/30/lessons/181832
public class SpiralNumberPlacement {

    public static void main(String[] args) {
        SpiralNumberPlacement spiralNumberPlacement = new SpiralNumberPlacement();
        int n = 5;
        int[][] result = spiralNumberPlacement.solution(n);
        for (int i = 0; i < result.length; i++) {
            for (int j = 0; j < result[i].length; j++) {
                System.out.print(result[i][j] + " ");
            }
            System.out.println();
        }
    }


    public int[][] solution(int n) {
        int[][] answer = new int[n][n];
        final Coordinate now = new Coordinate(0, 0);
        int dirControl = 0; // 방향조절 변수
        int i = 1;
        answer[now.row][now.col] = i;
        while (true) {
            if (i == n * n) break;
            switch (dirControl % 4) {
                // 가로로 우측 이동 => col 증가
                case 0:
                    while (now.col + 1 < answer.length && answer[now.row][now.col + 1] == 0) {
                        answer[now.row][++now.col] = ++i;
                    }
                    dirControl++;
                    break;
                // 세로로 아래로 이동 => row 증가
                case 1:
                    while (now.row + 1 < answer.length && answer[now.row + 1][now.col] == 0) {
                        answer[++now.row][now.col] = ++i;
                    }
                    dirControl++;
                    break;
                // 가로로 좌측 이동 => col 감소
                case 2:
                    while (now.col - 1 >= 0 && answer[now.row][now.col - 1] == 0) {
                        answer[now.row][--now.col] = ++i;
                    }
                    dirControl++;
                    break;
                // 세로로 위로 이동 => row 감소
                case 3:
                    while (now.row - 1 >= 0 && answer[now.row - 1][now.col] == 0) {
                        answer[--now.row][now.col] = ++i;
                    }
                    dirControl++;
                    break;
            }
        }
        return answer;
    }

    static class Coordinate {
        int row;
        int col;

        public Coordinate(int row, int col) {
            this.row = row;
            this.col = col;
        }
    }
}
