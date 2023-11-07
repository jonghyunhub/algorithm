package algo_middle_2.math2;

import java.util.Scanner;

public class BOJ2749 {
    public static void main(String args[]) {
        Scanner sc = new Scanner(System.in);
        long[] d = new long[1500000];
        d[0] = 0;
        d[1] = 1;
        for (int i = 2; i < 1500000; i++) {
            d[i] = d[i - 1] + d[i - 2];
            d[i] %= 1000000;
        }
        long n = sc.nextLong();
        System.out.println(d[(int) (n % 1500000)]);
    }
}
