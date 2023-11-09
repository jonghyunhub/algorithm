package prepare_coding_test_basic.bruteforce_N_and_M.BOJ18290;

import java.util.Scanner;

public class BOJ18290A {
    static int[][] a = new int[10][10];
    static boolean[][] c = new boolean[10][10];
    static int n, m, k;
    static int ans = -2147483647;
    static int[] dx = {0, 0, 1, -1};
    static int[] dy = {1, -1, 0, 0};

    static void go(int cnt, int sum) {
        if (cnt == k) {
            if (ans < sum) ans = sum;
            return;
        }
        for (int x = 0; x < n; x++) {
            for (int y = 0; y < m; y++) {
                if (c[x][y]) continue;
                boolean ok = true;
                for (int i = 0; i < 4; i++) {
                    int nx = x + dx[i];
                    int ny = y + dy[i];
                    if (0 <= nx && nx < n && 0 <= ny && ny < m) {
                        if (c[nx][ny]) ok = false;
                    }
                }
                if (ok) {
                    c[x][y] = true;
                    go(cnt + 1, sum + a[x][y]);
                    c[x][y] = false;
                }
            }
        }
    }

    public static void main(String args[]) {
        Scanner sc = new Scanner(System.in);
        n = sc.nextInt();
        m = sc.nextInt();
        k = sc.nextInt();
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                a[i][j] = sc.nextInt();
            }
        }
        go(0, 0);
        System.out.println(ans);
    }
}
