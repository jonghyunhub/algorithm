package algo_middle_3.fenwicktree.BOJ2042;

import java.util.Scanner;

public class BOJ2042B {
    static long sum(long[] tree, int x) {
        long ans = 0;
        for (int i = x; i > 0; i -= i & -i) {
            ans += tree[i];
        }
        return ans;
    }

    static void update(long[] tree, int x, long diff) {
        for (int i = x; i < tree.length; i += i & -i) {
            tree[i] += diff;
        }
    }

    public static void main(String args[]) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int m = sc.nextInt();
        int k = sc.nextInt();
        long[] a = new long[n + 1];
        long[] tree = new long[n + 1];
        for (int i = 1; i <= n; i++) {
            a[i] = sc.nextLong();
            update(tree, i, a[i]);
        }
        m += k;
        while (m-- > 0) {
            int t1 = sc.nextInt();
            if (t1 == 1) {
                int t2 = sc.nextInt();
                long t3 = sc.nextLong();
                long diff = t3 - a[t2];
                a[t2] = t3;
                update(tree, t2, diff);
            } else {
                int t2 = sc.nextInt();
                int t3 = sc.nextInt();
                System.out.println(sum(tree, t3) - sum(tree, t2 - 1));
            }
        }
    }
}
