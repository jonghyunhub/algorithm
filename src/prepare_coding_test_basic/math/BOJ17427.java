package prepare_coding_test_basic.math;

import java.util.Scanner;

public class BOJ17427 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        long ans = 0;
        for (int i = 1; i <= n; i++) {
            ans += (n / i) * i;
        }
        System.out.println(ans);
    }
}
