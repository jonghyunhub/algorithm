package algo_middle_1.greedy;

import java.util.Scanner;

class PairZ {
    boolean first;
    int second;

    PairZ(boolean first, int second) {
        this.first = first;
        this.second = second;
    }
}

public class BOJ2138 {
    static void change(int[] a, int index) {
        for (int i = index - 1; i <= index + 1; i++) {
            if (0 <= i && i < a.length) {
                a[i] = 1 - a[i];
            }
        }
    }

    static PairZ go(int[] a, int[] goal) {
        int n = a.length;
        int ans = 0;
        for (int i = 0; i < n - 1; i++) {
            if (a[i] != goal[i]) {
                change(a, i + 1);
                ans += 1;
            }
        }
        boolean ok = true;
        for (int i = 0; i < n; i++) {
            if (a[i] != goal[i]) {
                ok = false;
            }
        }
        if (ok) {
            return new PairZ(true, ans);
        } else {
            return new PairZ(false, ans);
        }
    }

    public static void main(String args[]) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int[] a = new int[n];
        int[] goal = new int[n];
        String s = sc.next();
        for (int i = 0; i < n; i++) {
            a[i] = s.charAt(i) - '0';
        }
        s = sc.next();
        for (int i = 0; i < n; i++) {
            goal[i] = s.charAt(i) - '0';
        }
        int[] b = new int[a.length];
        System.arraycopy(a, 0, b, 0, a.length);
        PairZ p1 = go(b, goal);
        change(a, 0);
        PairZ p2 = go(a, goal);
        if (p2.first) {
            p2.second += 1;
        }
        if (p1.first && p2.first) {
            System.out.printf("%d\n", Math.min(p1.second, p2.second));
        } else if (p1.first) {
            System.out.printf("%d\n", p1.second);
        } else if (p2.first) {
            System.out.printf("%d\n", p2.second);
        } else {
            System.out.printf("-1\n");
        }
    }
}
