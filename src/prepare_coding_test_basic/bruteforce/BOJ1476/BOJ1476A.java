package prepare_coding_test_basic.bruteforce.BOJ1476;

import java.util.Scanner;

public class BOJ1476A {
    public static void main(String args[]) {
        Scanner sc = new Scanner(System.in);
        int E = sc.nextInt();
        int S = sc.nextInt();
        int M = sc.nextInt();
        int e = 1, s = 1, m = 1;
        for (int i = 1; ; i++) {
            if (e == E && s == S && m == M) {
                System.out.println(i);
                break;
            }
            e += 1;
            s += 1;
            m += 1;
            if (e == 16) {
                e = 1;
            }
            if (s == 29) {
                s = 1;
            }
            if (m == 20) {
                m = 1;
            }
        }
    }
}
