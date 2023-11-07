package algo_middle_2.bruteforce.BOJ16968;

import java.util.Scanner;

public class BOJ16968Combination {
    public static void main(String args[]) {
        Scanner sc = new Scanner(System.in);
        String s = sc.next();
        int ans = 1;
        for (int i = 0; i < s.length(); i++) {
            int cnt = (s.charAt(i) == 'c' ? 26 : 10);
            if (i > 0 && s.charAt(i) == s.charAt(i - 1)) {
                cnt -= 1;
            }
            ans = ans * cnt;
        }
        System.out.println(ans);
    }
}
