package algo_middle_2.geometryalgorithm;

import java.util.Scanner;

class Point {
    long x, y;

    Point(long x, long y) {
        this.x = x;
        this.y = y;
    }
}

class Line {
    Point first, second;

    Line(Point first, Point second) {
        this.first = first;
        this.second = second;
    }
}

public class BOJ17386 {
    static int ccw(Point p1, Point p2, Point p3) {
        long temp = p1.x * p2.y + p2.x * p3.y + p3.x * p1.y;
        temp -= p1.y * p2.x + p2.y * p3.x + p3.y * p1.x;
        if (temp > 0) return 1;
        if (temp < 0) return -1;
        return 0;
    }

    static boolean cross(Line l1, Line l2) {
        int l1l2 = ccw(l1.first, l1.second, l2.first) * ccw(l1.first, l1.second, l2.second);
        int l2l1 = ccw(l2.first, l2.second, l1.first) * ccw(l2.first, l2.second, l1.second);
        return l1l2 < 0 && l2l1 < 0;
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
        Line l1 = new Line(new Point(x1, y1), new Point(x2, y2));
        Line l2 = new Line(new Point(x3, y3), new Point(x4, y4));
        System.out.println(cross(l1, l2) ? 1 : 0);
    }
}
