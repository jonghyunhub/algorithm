package algo_middle_2.math2.BOJ17436;

import java.util.Scanner;

public class BOJ17436A {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        long m = sc.nextLong();
        int[] a = new int[n];
        for (int i = 0; i < n; i++) {
            a[i] = sc.nextInt();
        }
        long ans = 0;
        for (int i = 1; i < (1 << n); i++) {
            int cnt = 0;
            long p = 1;
            for (int j = 0; j < n; j++) {
                if ((i & (1 << j)) > 0) {
                    p *= a[j];
                    cnt += 1;
                    if (p > m) {
                        cnt = -1;
                        break;
                    }
                }
            }
            if (cnt == -1) continue;
            if (cnt % 2 == 0) {
                ans -= (m / p);
            } else {
                ans += (m / p);
            }
        }
        System.out.println(ans);
    }
}
