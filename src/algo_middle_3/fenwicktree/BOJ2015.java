package algo_middle_3.fenwicktree;

import java.util.HashMap;
import java.util.Scanner;

public class BOJ2015 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int m = sc.nextInt();
        int[] a = new int[n + 1];
        for (int i = 1; i <= n; i++) {
            a[i] = sc.nextInt();
        }
        int[] s = new int[n + 1];
        for (int i = 1; i <= n; i++) {
            s[i] = s[i - 1] + a[i];
        }
        HashMap<Integer, Integer> cnt = new HashMap<>();
        cnt.put(0, 1); // s[0] = 0
        long ans = 0;
        for (int i = 1; i <= n; i++) {
            // a[j] + ... +a[i] == s[i] - s[j-1]
            // s[i] - s[j-1] == m
            // s[j-1] = s[i] - m (j-1 < i)
            ans += cnt.getOrDefault(s[i] - m, 0);
            cnt.put(s[i], cnt.getOrDefault(s[i], 0) + 1);
        }
        System.out.println(ans);
    }
}
