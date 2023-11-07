package algo_middle_2.math2;

import java.util.Scanner;

public class BOJ2747 {
    public static void main(String args[]) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int[] d = new int[50];
        d[0] = 0;
        d[1] = 1;
        for (int i = 2; i <= 45; i++) {
            d[i] = d[i - 1] + d[i - 2];
        }
        System.out.println(d[n]);
    }
}
