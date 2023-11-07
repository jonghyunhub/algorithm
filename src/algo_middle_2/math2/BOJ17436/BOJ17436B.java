package algo_middle_2.math2.BOJ17436;

import java.util.Scanner;

public class BOJ17436B {
    static int n;
    static int[] a;
    static long m;

    static long go(int index, long num) {
        if (index >= n) return 0;
        long ans = 0;
        if (num * a[index] > m) return 0;
        ans += m / (num * a[index]);
        ans += go(index + 1, num);
        ans -= go(index + 1, num * a[index]);
        return ans;
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        n = sc.nextInt();
        m = sc.nextLong();
        a = new int[n];
        for (int i = 0; i < n; i++) {
            a[i] = sc.nextInt();
        }
        System.out.println(go(0, 1));
    }
}
