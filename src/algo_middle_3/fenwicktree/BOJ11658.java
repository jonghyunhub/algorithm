package algo_middle_3.fenwicktree;

import java.util.Scanner;

public class BOJ11658 {
    static int n, m;
    static int[][] a = new int[1025][1025];
    static int[][] tree = new int[1025][1025];

    static void update(int x, int y, int val) {
        for (int i = x; i <= n; i += i & -i) {
            for (int j = y; j <= n; j += j & -j) {
                tree[i][j] += val;
            }
        }
    }

    static int sum(int x, int y) {
        int ans = 0;
        for (int i = x; i > 0; i -= i & -i) {
            for (int j = y; j > 0; j -= j & -j) {
                ans += tree[i][j];
            }
        }
        return ans;
    }

    public static void main(String args[]) {
        Scanner sc = new Scanner(System.in);
        n = sc.nextInt();
        m = sc.nextInt();
        for (int i = 1; i <= n; i++) {
            for (int j = 1; j <= n; j++) {
                a[i][j] = sc.nextInt();
                update(i, j, a[i][j]);
            }
        }
        while (m-- > 0) {
            int what = sc.nextInt();
            if (what == 0) {
                int x = sc.nextInt();
                int y = sc.nextInt();
                int val = sc.nextInt();
                update(x, y, val - a[x][y]);
                a[x][y] = val;
            } else {
                int x1 = sc.nextInt();
                int y1 = sc.nextInt();
                int x2 = sc.nextInt();
                int y2 = sc.nextInt();
                System.out.println(sum(x2, y2) - sum(x1 - 1, y2) - sum(x2, y1 - 1) + sum(x1 - 1, y1 - 1));
            }
        }
    }
}
