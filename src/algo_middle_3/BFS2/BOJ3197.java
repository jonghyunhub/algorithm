package algo_middle_3.BFS2;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

class PairB {
    int first;
    int second;

    PairB(int first, int second) {
        this.first = first;
        this.second = second;
    }
}

public class BOJ3197 {
    static int[] dx = {1, -1, 0, 0};
    static int[] dy = {0, 0, 1, -1};

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int m = sc.nextInt();
        int sx, sy, ex, ey;
        sx = sy = ex = ey = -1;
        Queue<PairB> swan, nswan, water, nwater;
        swan = new LinkedList<PairB>();
        nswan = new LinkedList<PairB>();
        water = new LinkedList<PairB>();
        nwater = new LinkedList<PairB>();
        char[][] a = new char[n][m];
        boolean[][] wcheck = new boolean[n][m];
        boolean[][] scheck = new boolean[n][m];
        for (int i = 0; i < n; i++) {
            a[i] = sc.next().toCharArray();
            for (int j = 0; j < m; j++) {
                if (a[i][j] == 'L') {
                    if (sx == -1) {
                        sx = i;
                        sy = j;
                    } else {
                        ex = i;
                        ey = j;
                    }
                    a[i][j] = '.';
                }
                if (a[i][j] == '.') {
                    water.add(new PairB(i, j));
                    wcheck[i][j] = true;
                }
            }
        }
        swan.add(new PairB(sx, sy));
        scheck[sx][sy] = true;
        for (int i = 0; ; i++) {
            while (!water.isEmpty()) {
                PairB p = water.remove();
                int x = p.first;
                int y = p.second;
                a[x][y] = '.';
                for (int k = 0; k < 4; k++) {
                    int nx = x + dx[k];
                    int ny = y + dy[k];
                    if (nx < 0 || nx >= n || ny < 0 || ny >= m) continue;
                    if (wcheck[nx][ny]) continue;
                    if (a[nx][ny] == '.') {
                        water.add(new PairB(nx, ny));
                        wcheck[nx][ny] = true;
                    } else {
                        nwater.add(new PairB(nx, ny));
                        wcheck[nx][ny] = true;
                    }
                }
            }
            while (!swan.isEmpty()) {
                PairB p = swan.remove();
                int x = p.first;
                int y = p.second;
                for (int k = 0; k < 4; k++) {
                    int nx = x + dx[k];
                    int ny = y + dy[k];
                    if (nx < 0 || nx >= n || ny < 0 || ny >= m) continue;
                    if (scheck[nx][ny]) continue;
                    if (a[nx][ny] == '.') {
                        swan.add(new PairB(nx, ny));
                        scheck[nx][ny] = true;
                    } else {
                        nswan.add(new PairB(nx, ny));
                        scheck[nx][ny] = true;
                    }
                }
            }
            if (scheck[ex][ey]) {
                System.out.println(i);
                break;
            }
            water = nwater;
            swan = nswan;
            nwater = new LinkedList<PairB>();
            nswan = new LinkedList<PairB>();
        }
    }
}
