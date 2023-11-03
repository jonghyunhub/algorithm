package algo_basic1.math;

import java.util.Scanner;

public class BOJ1934 {
    public static int gcd(int x, int y) {
        if (y == 0) {
            return x;
        } else {
            return gcd(y, x % y);
        }
    }

    public static void main(String args[]) {
        Scanner sc = new Scanner(System.in);
        int t = sc.nextInt();
        while (t-- > 0) {
            int a = sc.nextInt();
            int b = sc.nextInt();
            int g = gcd(a, b);
            int l = a * b / g;
            System.out.println(l);
        }
    }
}
