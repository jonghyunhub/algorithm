package algo_middle_2.geometryalgorithm;

import java.util.Scanner;

public class BOJ2166 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        long[] x = new long[n + 1];
        long[] y = new long[n + 1];
        for (int i = 0; i < n; i++) {
            x[i] = sc.nextLong();
            y[i] = sc.nextLong();
        }
        x[n] = x[0];
        y[n] = y[0];
        long ans = 0;
        for (int i = 0; i < n; i++) {
            ans += x[i] * y[i + 1];
            ans -= y[i] * x[i + 1];
        }
        if (ans < 0) ans = -ans;
        System.out.print((ans / 2) + ".");
        if (ans % 2 == 0) {
            System.out.println("0");
        } else {
            System.out.println("5");
        }
    }
}
