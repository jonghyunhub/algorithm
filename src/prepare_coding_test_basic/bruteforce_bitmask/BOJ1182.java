package prepare_coding_test_basic.bruteforce_bitmask;

import java.util.Scanner;

public class BOJ1182 {
    public static void main(String args[]) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int s = sc.nextInt();
        int[] a = new int[n];
        for (int i = 0; i < n; i++) {
            a[i] = sc.nextInt();
        }
        int ans = 0;
        for (int i = 1; i < (1 << n); i++) {
            int sum = 0;
            for (int k = 0; k < n; k++) {
                if ((i & (1 << k)) != 0) {
                    sum += a[k];
                }
            }
            if (sum == s) {
                ans += 1;
            }
        }
        System.out.println(ans);
    }
}
