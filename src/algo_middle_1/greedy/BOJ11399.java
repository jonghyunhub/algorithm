package algo_middle_1.greedy;

import java.util.Arrays;
import java.util.Scanner;

public class BOJ11399 {
    public static void main(String args[]) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int[] a = new int[n];
        for (int i = 0; i < n; i++) {
            a[i] = sc.nextInt();
        }
        int sum = 0;
        int ans = 0;
        Arrays.sort(a);
        for (int i = 0; i < n; i++) {
            sum += a[i];
            ans += sum;
        }
        System.out.println(ans);
    }
}
