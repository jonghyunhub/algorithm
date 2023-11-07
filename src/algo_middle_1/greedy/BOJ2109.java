package algo_middle_1.greedy;

import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.Scanner;

class Lecture implements Comparable<Lecture> {
    int p, d;

    Lecture(int p, int d) {
        this.p = p;
        this.d = d;
    }

    public int compareTo(Lecture that) {
        if (this.d < that.d) {
            return 1;
        } else if (this.d == that.d) {
            if (this.p < that.p) {
                return -1;
            } else if (this.p == that.p) {
                return 0;
            } else {
                return 1;
            }
        } else {
            return -1;
        }
    }
}

public class BOJ2109 {

    public static void main(String args[]) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        Lecture[] a = new Lecture[n];
        for (int i = 0; i < n; i++) {
            a[i] = new Lecture(sc.nextInt(), sc.nextInt());
        }
        Arrays.sort(a);
        int k = 0;
        PriorityQueue<Integer> q = new PriorityQueue<>();
        int ans = 0;
        for (int i = 10000; i >= 1; i--) {
            while (k < n && a[k].d == i) {
                q.offer(-a[k].p);
                k += 1;
            }
            if (!q.isEmpty()) {
                ans += -q.poll();
            }
        }
        System.out.println(ans);
    }
}
