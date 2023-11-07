package algo_middle_2.math2.BOJ11444;

import java.util.HashMap;
import java.util.Scanner;

public class BOJ11444B {
    final static long mod = 1000000007L;
    static HashMap<Long, Long> d = new HashMap<>();

    static long fibo(long n) {
        if (n <= 0) {
            return 0;
        } else if (n == 1) {
            return 1;
        } else if (n == 2) {
            return 1;
        } else if (d.containsKey(n)) {
            return d.get(n);
        } else {
            if (n % 2 == 1) {
                long m = (n + 1) / 2;
                long t1 = fibo(m);
                long t2 = fibo(m - 1);
                long t3 = t1 * t1 + t2 * t2;
                t3 %= mod;
                d.put(n, t3);
                return t3;
            } else {
                long m = n / 2;
                long t1 = fibo(m - 1);
                long t2 = fibo(m);
                long t3 = (2L * t1 + t2) * t2;
                t3 %= mod;
                d.put(n, t3);
                return t3;
            }
        }
    }

    public static void main(String args[]) {
        Scanner sc = new Scanner(System.in);
        long n = sc.nextLong();
        System.out.println(fibo(n));
    }
}
