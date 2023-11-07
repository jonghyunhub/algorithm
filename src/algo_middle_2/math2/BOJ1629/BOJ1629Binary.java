package algo_middle_2.math2.BOJ1629;

import java.util.Scanner;

public class BOJ1629Binary {
    static long go(long a, long b, long c) {
        long ans = 1;
        while (b > 0) {
            if ((b & 1) == 1) {
                ans = (ans * a) % c;
            }
            a = (a * a) % c;
            b = b / 2;
        }
        return ans;
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        long a = sc.nextLong();
        long b = sc.nextLong();
        long c = sc.nextLong();
        System.out.println(go(a, b, c));
    }
}
