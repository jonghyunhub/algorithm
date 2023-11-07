package algo_middle_2.dp2.BOJ12865;

import java.util.Scanner;

public class BOJ12865B {
    public static void main(String args[]) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int k = sc.nextInt();
        int[] w = new int[n+1];
        int[] v = new int[n+1];
        for (int i=1; i<=n; i++) {
            w[i] = sc.nextInt();
            v[i] = sc.nextInt();
        }
        int[] d = new int[k+1];
        for (int i=1; i<=n; i++) {
            for (int j=k; j>=1; j--) {
                if (j-w[i] >= 0) {
                    d[j] = Math.max(d[j], d[j-w[i]]+v[i]);
                }
            }
        }
        System.out.println(d[k]);
    }
}
