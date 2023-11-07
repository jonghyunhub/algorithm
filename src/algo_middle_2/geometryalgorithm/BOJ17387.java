package algo_middle_2.geometryalgorithm;

import java.util.Scanner;

class PointA {
    long x, y;

    PointA(long x, long y) {
        this.x = x;
        this.y = y;
    }

    public int compareTo(PointA that) {
        if (this.x < that.x) {
            return -1;
        } else if (this.x == that.x) {
            if (this.y < that.y) return -1;
            if (this.y > that.y) return 1;
            return 0;
        } else {
            return 1;
        }
    }
}

class LineA {
    PointA first, second;

    LineA(PointA first, PointA second) {
        this.first = first;
        this.second = second;
    }
}

public class BOJ17387 {
    static int ccw(PointA p1, PointA p2, PointA p3) {
        long temp = p1.x * p2.y + p2.x * p3.y + p3.x * p1.y;
        temp -= p1.y * p2.x + p2.y * p3.x + p3.y * p1.x;
        if (temp > 0) return 1;
        if (temp < 0) return -1;
        return 0;
    }

    static boolean cross(LineA l1, LineA l2) {
        int l1l2 = ccw(l1.first, l1.second, l2.first) * ccw(l1.first, l1.second, l2.second);
        int l2l1 = ccw(l2.first, l2.second, l1.first) * ccw(l2.first, l2.second, l1.second);
        if (l1l2 == 0 && l2l1 == 0) {
            if (l1.first.compareTo(l1.second) == 1) {
                PointA temp = l1.first;
                l1.first = l1.second;
                l1.second = temp;
            }
            if (l2.first.compareTo(l2.second) == 1) {
                PointA temp = l2.first;
                l2.first = l2.second;
                l2.second = temp;
            }
            return l2.first.compareTo(l1.second) != 1 && l1.first.compareTo(l2.second) != 1;
        }
        return l1l2 <= 0 && l2l1 <= 0;
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        long x1 = sc.nextLong();
        long y1 = sc.nextLong();
        long x2 = sc.nextLong();
        long y2 = sc.nextLong();
        long x3 = sc.nextLong();
        long y3 = sc.nextLong();
        long x4 = sc.nextLong();
        long y4 = sc.nextLong();
        LineA l1 = new LineA(new PointA(x1, y1), new PointA(x2, y2));
        LineA l2 = new LineA(new PointA(x3, y3), new PointA(x4, y4));
        System.out.println(cross(l1, l2) ? 1 : 0);
    }
}
