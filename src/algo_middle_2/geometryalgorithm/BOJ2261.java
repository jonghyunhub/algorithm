package algo_middle_2.geometryalgorithm;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Comparator;
import java.util.TreeSet;

class PointD {
    int x, y;

    PointD(int x, int y) {
        this.x = x;
        this.y = y;
    }
}

class PointComparatorX implements Comparator<PointD> {
    public int compare(PointD u, PointD v) {
        if (u.x < v.x) {
            return -1;
        } else if (u.x == v.x) {
            if (u.y < v.y) {
                return -1;
            } else if (u.y == v.y) {
                return 0;
            } else {
                return 1;
            }
        } else {
            return 1;
        }
    }
}

class PointComparatorY implements Comparator<PointD> {
    public int compare(PointD u, PointD v) {
        if (u.y < v.y) {
            return -1;
        } else if (u.y == v.y) {
            if (u.x < v.x) {
                return -1;
            } else if (u.x == v.x) {
                return 0;
            } else {
                return 1;
            }
        } else {
            return 1;
        }
    }
}

public class BOJ2261 {

    static int dist(PointD p1, PointD p2) {
        return (p1.x - p2.x) * (p1.x - p2.x) + (p1.y - p2.y) * (p1.y - p2.y);
    }

    public static void main(String[] args) throws IOException {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(bf.readLine());
        PointD[] a = new PointD[n];
        for (int i = 0; i < n; i++) {
            String[] line = bf.readLine().split(" ");
            int x = Integer.parseInt(line[0]);
            int y = Integer.parseInt(line[1]);
            a[i] = new PointD(x, y);
        }
        Arrays.sort(a, new PointComparatorX());
        PointComparatorY cmp = new PointComparatorY();
        TreeSet<PointD> candidate = new TreeSet<>(cmp);
        candidate.add(a[0]);
        candidate.add(a[1]);
        int ans = dist(a[0], a[1]);
        int start = 0;
        for (int i = 2; i < n; i++) {
            PointD now = a[i];
            while (start < i) {
                PointD p = a[start];
                int x = now.x - p.x;
                if (x * x > ans) {
                    candidate.remove(p);
                    start += 1;
                } else {
                    break;
                }
            }
            int diff = (int) Math.sqrt((double) ans) + 1;
            PointD lower_point = new PointD(-100000, now.y - diff);
            PointD upper_point = new PointD(100000, now.y + diff);
            PointD p = candidate.ceiling(lower_point);
            while (p != null && cmp.compare(p, upper_point) != 1) {
                int d = dist(now, p);
                if (d < ans) {
                    ans = d;
                }
                p = candidate.higher(p);
            }
            candidate.add(now);
        }
        System.out.println(ans);
    }
}
