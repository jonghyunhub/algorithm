package algo_middle_2.math2;

import java.util.Scanner;

public class BOJ2748 {
    public static void main(String args[]) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        long[] d = new long[100];
        d[0] = 0;
        d[1] = 1;
        for (int i = 2; i < 100; i++) {
            d[i] = d[i - 1] + d[i - 2];
        }
        System.out.println(d[n]);
    }
}
