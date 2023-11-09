package algo_middle_3.fenwicktree;

import java.util.Scanner;

public class BOJ11659 {
    public static void main(String args[]) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int m = sc.nextInt();
        int[] a = new int[n + 1];
        int[] s = new int[n + 1];
        for (int i = 1; i <= n; i++) {
            a[i] = sc.nextInt();
            s[i] = s[i - 1] + a[i];
        }
        while (m-- > 0) {
            int x = sc.nextInt();
            int y = sc.nextInt();
            System.out.println(s[y] - s[x - 1]);
        }
    }
}
