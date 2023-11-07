package algo_middle_2.bruteforce;

import java.util.Scanner;

public class BOJ9944 {
    static final int[] dx = {0, 0, 1, -1};
    static final int[] dy = {1, -1, 0, 0};
    static char[][] a;
    static int n, m;

    static boolean ok(int x, int y) {
        return 0 <= x && x < n && 0 <= y && y < m;
    }

    static int go(int x, int y, int cnt) {
        int ans = -1;
        if (cnt == 0) {
            return 0;
        }
        for (int k = 0; k < 4; k++) {
            int nx = x + dx[k];
            int ny = y + dy[k];
            while (ok(nx, ny) && a[nx][ny] == '.') {
                a[nx][ny] = '#';
                cnt -= 1;
                nx += dx[k];
                ny += dy[k];
            }
            nx -= dx[k];
            ny -= dy[k];
            if (!(x == nx && y == ny)) {
                int temp = go(nx, ny, cnt);
                if (temp != -1) {
                    if (ans == -1 || ans > temp + 1) {
                        ans = temp + 1;
                    }
                }
            }
            while (!(x == nx && y == ny)) {
                a[nx][ny] = '.';
                cnt += 1;
                nx -= dx[k];
                ny -= dy[k];
            }
        }
        return ans;
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int tc = 1;
        while (sc.hasNextInt()) {
            n = sc.nextInt();
            m = sc.nextInt();
            a = new char[n][m];
            int cnt = 0;
            for (int i = 0; i < n; i++) {
                a[i] = sc.next().toCharArray();
                for (int j = 0; j < m; j++) {
                    if (a[i][j] == '.') {
                        cnt += 1;
                    }
                }
            }
            int ans = -1;
            for (int i = 0; i < n; i++) {
                for (int j = 0; j < m; j++) {
                    if (a[i][j] == '.') {
                        a[i][j] = '#';
                        int temp = go(i, j, cnt - 1);
                        if (temp != -1) {
                            if (ans == -1 || ans > temp) {
                                ans = temp;
                            }
                        }
                        a[i][j] = '.';
                    }
                }
            }
            System.out.printf("Case %d: %d\n", tc, ans);
            tc += 1;
        }
    }
}
