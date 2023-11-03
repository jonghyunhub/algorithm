package algo_basic1.math;

import java.util.Scanner;

public class BOJ1676 {
    public static void main(String args[]) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int ans = 0;
        for (int i = 5; i <= n; i *= 5) {
            ans += n / i;
        }
        System.out.println(ans);
    }
}
