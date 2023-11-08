package prepare_coding_test_problem.bruteforce1.BOJ16938;

import java.util.Scanner;

public class BOJ16938A {
    static int n, l, r, x;
    static int[] a = new int[15];
    static boolean[] c = new boolean[15];

    static int go(int index) {
        if (index == n) {
            int cnt = 0;
            int sum = 0;
            int hard = -1;
            int easy = -1;
            for (int i = 0; i < n; i++) {
                if (c[i] == false) continue;
                sum += a[i];
                cnt += 1;
                if (hard == -1 || hard < a[i]) hard = a[i];
                if (easy == -1 || easy > a[i]) easy = a[i];
            }
            if (cnt >= 2 && l <= sum && sum <= r && hard - easy >= x) return 1;
            else return 0;
        }
        c[index] = true;
        int cnt1 = go(index + 1);
        c[index] = false;
        int cnt2 = go(index + 1);
        return cnt1 + cnt2;
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        n = sc.nextInt();
        l = sc.nextInt();
        r = sc.nextInt();
        x = sc.nextInt();
        for (int i = 0; i < n; i++) {
            a[i] = sc.nextInt();
        }
        System.out.println(go(0));
    }
}
