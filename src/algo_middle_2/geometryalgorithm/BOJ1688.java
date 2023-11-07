package algo_middle_2.geometryalgorithm;

import java.util.Scanner;

class PointB {
    long x, y;

    PointB(long x, long y) {
        this.x = x;
        this.y = y;
    }
}

class LineB {
    PointB first, second;

    LineB(PointB first, PointB second) {
        this.first = first;
        this.second = second;
    }
}

public class BOJ1688 {
    static int ccw(PointB p1, PointB p2, PointB p3) {
        long temp = p1.x * p2.y + p2.x * p3.y + p3.x * p1.y;
        temp -= p1.y * p2.x + p2.y * p3.x + p3.y * p1.x;
        if (temp > 0) return 1;
        if (temp < 0) return -1;
        return 0;
    }

    static boolean cross(LineB l1, LineB l2) {
        int l1l2 = ccw(l1.first, l1.second, l2.first) * ccw(l1.first, l1.second, l2.second);
        int l2l1 = ccw(l2.first, l2.second, l1.first) * ccw(l2.first, l2.second, l1.second);
        return l1l2 < 0 && l2l1 < 0;
    }

    static int go(PointB p1, int n, PointB[] a) {
        for (int i = 0; i < n; i++) {
            int t1 = ccw(a[i], a[i + 1], p1);
            if (t1 == 0) {
                long minx = Math.min(a[i].x, a[i + 1].x);
                long maxx = Math.max(a[i].x, a[i + 1].x);
                long miny = Math.min(a[i].y, a[i + 1].y);
                long maxy = Math.max(a[i].y, a[i + 1].y);
                if (minx <= p1.x && p1.x <= maxx) {
                    if (miny <= p1.y && p1.y <= maxy) {
                        return 1;
                    }
                }
            }
        }
        PointB p0 = new PointB(1, 2147483647);
        int cnt = 0;
        for (int i = 0; i < n; i++) {
            LineB l = new LineB(p0, p1);
            cnt += cross(l, new LineB(a[i], a[i + 1])) ? 1 : 0;
        }
        return cnt % 2;
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        PointB[] a = new PointB[n + 1];
        for (int i = 0; i < n; i++) {
            int x = sc.nextInt();
            int y = sc.nextInt();
            a[i] = new PointB(x, y);
        }
        a[n] = a[0];
        int m = 3;
        while (m-- > 0) {
            int x = sc.nextInt();
            int y = sc.nextInt();
            PointB p1 = new PointB(x, y);
            System.out.println(go(p1, n, a));
        }
    }
}
