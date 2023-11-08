package prepare_coding_test_problem.bruteforce2.BOJ16953;

import java.util.Scanner;

public class BOJ16953A {
    static int go(long a, long b) {
        if (a == b) return 1;
        if (a > b) return -1;
        int t1 = go(a*2, b);
        int t2 = go(a*10+1, b);
        if (t1 == -1 && t2 == -1) return -1;
        if (t1 == -1) return t2+1;
        if (t2 == -1) return t1+1;
        return Math.min(t1,t2)+1;
    }
    public static void main(String args[]) {
        Scanner sc = new Scanner(System.in);
        long a = sc.nextLong();
        long b = sc.nextLong();
        System.out.println(go(a, b));
    }
}
