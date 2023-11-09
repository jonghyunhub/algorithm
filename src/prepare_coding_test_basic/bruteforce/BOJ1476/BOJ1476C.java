package prepare_coding_test_basic.bruteforce.BOJ1476;

import java.util.Scanner;

public class BOJ1476C {
    public static void main(String args[]) {
        Scanner sc = new Scanner(System.in);
        int e = sc.nextInt();
        int s = sc.nextInt();
        int m = sc.nextInt();
        System.out.println((6916 * e + 4845 * s + 4200 * m - 1) % (15 * 28 * 19) + 1);
    }
}
