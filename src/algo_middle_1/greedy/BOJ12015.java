package algo_middle_1.greedy;

import java.util.Scanner;

public class BOJ12015 {
    static int lower_bound(int[] a, int n, int key) {
        int left = 0;
        int right = n;
        while (left < right) {
            int mid = (left + right) / 2;
            if (key <= a[mid]) {
                right = mid;
            } else {
                left = mid + 1;
            }
        }
        return left;
    }

    public static void main(String args[]) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int[] a = new int[n];
        int len = 0;
        for (int i = 0; i < n; i++) {
            int num = sc.nextInt();
            int p = lower_bound(a, len, num);
            a[p] = num;
            if (len == p) len += 1;
        }
        System.out.println(len);
    }
}
