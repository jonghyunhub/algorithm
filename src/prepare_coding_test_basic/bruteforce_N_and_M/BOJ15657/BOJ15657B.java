package prepare_coding_test_basic.bruteforce_N_and_M.BOJ15657;

import java.util.Arrays;
import java.util.Scanner;

public class BOJ15657B {
    static int[] cnt = new int[10];
    static int[] num = new int[10];

    static StringBuilder go(int index, int selected, int n, int m) {
        if (selected == m) {
            StringBuilder sb = new StringBuilder();
            for (int i = 0; i < n; i++) {
                for (int j = 1; j <= cnt[i]; j++) {
                    sb.append(num[i]);
                    sb.append(" ");
                }
            }
            sb.append("\n");
            return sb;
        }
        StringBuilder ans = new StringBuilder();
        if (index >= n) return ans;
        for (int i = m - selected; i >= 1; i--) {
            cnt[index] = i;
            ans.append(go(index + 1, selected + i, n, m));
        }
        cnt[index] = 0;
        ans.append(go(index + 1, selected, n, m));
        return ans;
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int m = sc.nextInt();
        for (int i = 0; i < n; i++) {
            num[i] = sc.nextInt();
        }
        Arrays.sort(num, 0, n);
        System.out.print(go(0, 0, n, m));
    }
}
