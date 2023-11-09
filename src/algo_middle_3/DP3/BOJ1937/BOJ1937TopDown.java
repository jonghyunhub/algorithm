package algo_middle_3.DP3.BOJ1937;

import java.util.Scanner;

public class BOJ1937TopDown {
    static int n;
    static int[][] a = new int[500][500];
    static int[][] d = new int[500][500];
    static int[] dx = {0, 0, 1, -1};
    static int[] dy = {1, -1, 0, 0};

    static int go(int i, int j) {
        if (d[i][j] != 0) {
            return d[i][j];
        }
        d[i][j] = 1;
        for (int k = 0; k < 4; k++) {
            int x = i + dx[k];
            int y = j + dy[k];
            if (x < 0 || x >= n || y < 0 || y >= n) continue;
            if (a[i][j] < a[x][y]) {
                d[i][j] = Math.max(d[i][j], go(x, y) + 1);
            }
        }
        return d[i][j];
    }

    public static void main(String args[]) {
        Scanner sc = new Scanner(System.in);
        n = sc.nextInt();
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                a[i][j] = sc.nextInt();
            }
        }
        int ans = 0;
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                ans = Math.max(ans, go(i, j));
            }
        }
        System.out.println(ans);
    }
}
