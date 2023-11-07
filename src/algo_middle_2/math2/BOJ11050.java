package algo_middle_2.math2;

import java.util.Scanner;

public class BOJ11050 {
    public static void main(String args[]) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int m = sc.nextInt();
        int ans = 1;
        for (int i = 1; i <= n; i++) {
            ans *= i;
        }
        for (int i = 1; i <= m; i++) {
            ans /= i;
        }
        for (int i = 1; i <= n - m; i++) {
            ans /= i;
        }
        System.out.println(ans);
    }
}
