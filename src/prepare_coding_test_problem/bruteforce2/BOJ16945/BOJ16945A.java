package prepare_coding_test_problem.bruteforce2.BOJ16945;

import java.util.Scanner;

public class BOJ16945A {
    final static int n = 3;

    static boolean next_permutation(int[] a) {
        int i = a.length - 1;
        while (i > 0 && a[i - 1] >= a[i]) {
            i -= 1;
        }

        if (i <= 0) {
            return false;
        }

        int j = a.length - 1;
        while (a[j] <= a[i - 1]) {
            j -= 1;
        }

        int temp = a[i - 1];
        a[i - 1] = a[j];
        a[j] = temp;

        j = a.length - 1;
        while (i < j) {
            temp = a[i];
            a[i] = a[j];
            a[j] = temp;
            i += 1;
            j -= 1;
        }
        return true;
    }

    static boolean is_magic(int[] d) {
        int magic = d[0 * n + 0] + d[0 * n + 1] + d[0 * n + 2];
        for (int i = 0; i < n; i++) {
            int sum = 0;
            for (int j = 0; j < n; j++) {
                sum += d[i * n + j];
            }
            if (magic != sum) return false;
        }
        for (int j = 0; j < n; j++) {
            int sum = 0;
            for (int i = 0; i < n; i++) {
                sum += d[i * n + j];
            }
            if (magic != sum) return false;
        }
        if (magic != d[0 * n + 0] + d[1 * n + 1] + d[2 * n + 2]) return false;
        if (magic != d[0 * n + 2] + d[1 * n + 1] + d[2 * n + 0]) return false;
        return true;
    }

    static int diff(int[] a, int[] d) {
        int ans = 0;
        for (int i = 0; i < n * n; i++) {
            int temp = a[i] - d[i];
            if (temp < 0) temp = -temp;
            ans += temp;
        }
        return ans;
    }

    public static void main(String args[]) {
        Scanner sc = new Scanner(System.in);
        int[] a = new int[n * n];
        for (int i = 0; i < n * n; i++) {
            a[i] = sc.nextInt();
        }
        int[] d = new int[n * n];
        for (int i = 0; i < n * n; i++) {
            d[i] = i + 1;
        }
        int ans = -1;
        do {
            if (is_magic(d)) {
                int cost = diff(a, d);
                if (ans == -1 || ans > cost) {
                    ans = cost;
                }
            }
        } while (next_permutation(d));
        System.out.println(ans);
    }
}
