package prepare_coding_test_problem.simulationandimple.BOJ20057;

import java.util.ArrayList;
import java.util.Scanner;

class Wind {
    int dx, dy, ratio;

    Wind(int dx, int dy, int ratio) {
        this.dx = dx;
        this.dy = dy;
        this.ratio = ratio;
    }
}

public class BOJ20057 {
    // left, down, right, up
    static final int[] dx = {0, 1, 0, -1};
    static final int[] dy = {-1, 0, 1, 0};
    static ArrayList<Wind>[] dd = new ArrayList[4];

    static ArrayList<Wind> rotate(ArrayList<Wind> xx) {
        ArrayList<Wind> ans = new ArrayList<>();
        for (Wind t : xx) {
            int nx = -t.dy;
            int ny = t.dx;
            ans.add(new Wind(nx, ny, t.ratio));
        }
        return ans;
    }

    static void make_dd() {
        ArrayList<Wind> rlwns = new ArrayList<>();
        rlwns.add(new Wind(0, -2, 5));
        rlwns.add(new Wind(-1, -1, 10));
        rlwns.add(new Wind(1, -1, 10));
        rlwns.add(new Wind(-1, 0, 7));
        rlwns.add(new Wind(-2, 0, 2));
        rlwns.add(new Wind(1, 0, 7));
        rlwns.add(new Wind(2, 0, 2));
        rlwns.add(new Wind(-1, 1, 1));
        rlwns.add(new Wind(1, 1, 1));
        dd[0] = rlwns;
        ArrayList<Wind> x = rlwns;
        for (int i = 1; i < 4; i++) {
            x = rotate(x);
            dd[i] = x;
        }
    }

    static int go(int[][] a, int nx, int ny, int cur) {
        int n = a.length;
        if (0 <= nx && nx < n && 0 <= ny && ny < n) {
            a[nx][ny] += cur;
        } else {
            return cur;
        }
        return 0;
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        make_dd();
        int n = sc.nextInt();
        int[][] a = new int[n][n];
        boolean[][] c = new boolean[n][n];
        int[][] order = new int[n][n]; // for debugging
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                a[i][j] = sc.nextInt();
            }
        }
        int x = (n - 1) / 2;
        int y = (n - 1) / 2;
        int z = 0;
        order[x][y] = 1;
        c[x][y] = true;
        int cnt = 2;
        int ans = 0;
        while (true) {
            int nx = x + dx[z];
            int ny = y + dy[z];
            order[nx][ny] = cnt++;
            c[nx][ny] = true;
            // (x, y) -> (nx, ny)
            int sand = a[nx][ny];
            int used = 0;
            a[nx][ny] = 0;
            x = nx;
            y = ny;
            for (Wind t : dd[z]) {
                nx = x + t.dx;
                ny = y + t.dy;
                int cur = sand * t.ratio / 100;
                used += cur;
                ans += go(a, nx, ny, cur);
            }
            sand -= used;
            nx = x + dx[z];
            ny = y + dy[z];
            ans += go(a, nx, ny, sand);
            if (x == 0 && y == 0) break;
            // next
            int nz = (z + 1) % 4;
            nx = x + dx[nz];
            ny = y + dy[nz];
            if (c[nx][ny] == false) {
                z = nz;
            }
        }
        System.out.println(ans);
    }
}
