package prepare_coding_test_problem.bruteforce2.BOJ16953;

import java.util.Scanner;

public class BOJ16953B {
    public static void main(String args[]) {
        Scanner sc = new Scanner(System.in);
        int a = sc.nextInt();
        int b = sc.nextInt();
        for (int i = 1; a <= b; i++) {
            if (a == b) {
                System.out.println(i);
                System.exit(0);
            }
            if (b % 10 == 1) {
                b = (b - 1) / 10;
            } else if (b % 2 == 0) {
                b = b / 2;
            } else {
                break;
            }
        }
        System.out.println(-1);
    }
}
