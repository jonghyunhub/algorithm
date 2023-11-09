package algo_middle_3.DP3.BOJ1520;

import java.util.Arrays;
import java.util.Scanner;

class Cell implements Comparable<Cell> {
    int row, col, val;

    Cell(int row, int col, int val) {
        this.row = row;
        this.col = col;
        this.val = val;
    }

    public int compareTo(Cell that) {
        if (this.val < that.val) {
            return -1;
        } else if (this.val == that.val) {
            return 0;
        } else {
            return 1;
        }
    }
}

public class BOJ1520BottomUp {
    static int[] dx = {0, 0, 1, -1};
    static int[] dy = {1, -1, 0, 0};

    public static void main(String args[]) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int m = sc.nextInt();
        int[][] a = new int[n][m];
        int[][] d = new int[n][m];
        Cell[] v = new Cell[n * m];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                a[i][j] = sc.nextInt();
                v[i * m + j] = new Cell(i, j, a[i][j]);
            }
        }
        Arrays.sort(v);
        d[n - 1][m - 1] = 1;
        for (Cell c : v) {
            int x = c.row;
            int y = c.col;
            for (int k = 0; k < 4; k++) {
                int nx = x + dx[k];
                int ny = y + dy[k];
                if (0 <= nx && nx < n && 0 <= ny && ny < m) {
                    if (a[nx][ny] < a[x][y]) {
                        d[x][y] += d[nx][ny];
                    }
                }
            }
        }
        System.out.println(d[0][0]);
    }
}
