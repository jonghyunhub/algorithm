package algo_middle_2.stringalgorithm1.BOJ16916;

import java.util.Scanner;

public class BOJ16916A {
    static int mod = 127;
    static int base = 256;

    static int h(String s) {
        int ans = 0;
        for (char ch : s.toCharArray()) {
            ans = (ans * base + ch) % mod;
        }
        return ans;
    }

    static int match(String s, String p) {
        int n = s.length();
        int m = p.length();
        if (n < m) return 0;
        int hash_p = h(p);
        int hash_s = h(s.substring(0, m));
        int first = 1;
        for (int i = 0; i < m - 1; i++) {
            first = (first * base) % mod;
        }
        for (int i = 0; i <= n - m; i++) {
            if (hash_p == hash_s) {
                if (s.substring(i, i + m).equals(p)) return 1;
            }
            if (i + m < n) {
                hash_s = hash_s - (s.charAt(i) * first) % mod;
                hash_s = (hash_s + mod) % mod;
                hash_s = ((hash_s * base) % mod + s.charAt(i + m)) % mod;
            }
        }
        return 0;
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String s = sc.next();
        String p = sc.next();
        System.out.println(match(s, p));
    }
}
