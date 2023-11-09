package algo_middle_3.DP3.BOJ12911;

import java.util.ArrayList;
import java.util.Scanner;

public class BOJ12911 {
    public static final long mod = 1000000007L;

    public static void main(String args[]) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int k = sc.nextInt();
        ArrayList<Integer>[] divisors = (ArrayList<Integer>[]) new ArrayList[k + 1];
        for (int i = 1; i <= k; i++) {
            divisors[i] = new ArrayList<Integer>();
        }
        for (int i = 1; i <= k; i++) {
            for (int j = i * 2; j <= k; j += i) {
                divisors[j].add(i);
            }
        }
        long[] sum = new long[n + 1];
        long[][] d = new long[n + 1][k + 1];
        for (int j = 1; j <= k; j++) {
            d[1][j] = 1;
        }
        sum[1] = k;
        for (int i = 2; i <= n; i++) {
            for (int j = 1; j <= k; j++) {
                d[i][j] = sum[i - 1];
                for (int div : divisors[j]) {
                    d[i][j] -= d[i - 1][div];
                    d[i][j] %= mod;
                    d[i][j] += mod;
                    d[i][j] %= mod;
                }
                sum[i] += d[i][j];
                sum[i] %= mod;
            }
        }
        System.out.println(sum[n]);
    }
}
