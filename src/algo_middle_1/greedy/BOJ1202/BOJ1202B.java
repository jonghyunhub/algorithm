package algo_middle_1.greedy.BOJ1202;

import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.Scanner;

class Jewel implements Comparable<Jewel> {
    public int m;
    public int v;
    public int w;

    Jewel(int m, int v, int w) {
        this.m = m;
        this.v = v;
        this.w = w;
    }

    public int compareTo(Jewel that) {
        if (this.m < that.m) {
            return -1;
        } else if (this.m == that.m) {
            if (this.w < that.w) return -1;
            else if (this.w == that.w) return 0;
            else return 1;
        } else {
            return 1;
        }
    }
}

public class BOJ1202B {

    public static void main(String args[]) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int k = sc.nextInt();
        Jewel[] a = new Jewel[n + k];
        for (int i = 0; i < n; i++) {
            int m = sc.nextInt();
            int v = sc.nextInt();
            a[i] = new Jewel(m, v, 0);
        }
        for (int i = 0; i < k; i++) {
            int m = sc.nextInt();
            a[n + i] = new Jewel(m, 0, 1);
        }
        Arrays.sort(a);
        PriorityQueue<Integer> q = new PriorityQueue<Integer>();
        long ans = 0;
        for (Jewel p : a) {
            if (p.w == 0) {
                q.offer(-p.v);
            } else {
                if (!q.isEmpty()) {
                    ans += (long) -q.poll();
                }
            }
        }
        System.out.println(ans);
    }
}
