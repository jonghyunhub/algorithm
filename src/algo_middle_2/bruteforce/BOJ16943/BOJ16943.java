package algo_middle_2.bruteforce.BOJ16943;

import java.util.Arrays;
import java.util.Scanner;

public class BOJ16943 {
    static boolean next_permutation(char[] a) {
        int i = a.length-1;
        while (i > 0 && a[i-1] >= a[i]) {
            i -= 1;
        }

        if (i <= 0) {
            return false;
        }

        int j = a.length-1;
        while (a[j] <= a[i-1]) {
            j -= 1;
        }

        char temp = a[i-1];
        a[i-1] = a[j];
        a[j] = temp;

        j = a.length-1;
        while (i < j) {
            temp = a[i];
            a[i] = a[j];
            a[j] = temp;
            i += 1;
            j -= 1;
        }
        return true;
    }
    static int stoi(char[] a) {
        int ans = 0;
        for (char ch : a) {
            ans = ans * 10 + (ch - '0');
        }
        return ans;
    }
    public static void main(String args[]) {
        Scanner sc = new Scanner(System.in);
        char[] a = sc.next().toCharArray();
        int b = sc.nextInt();
        int ans = -1;
        Arrays.sort(a);
        do {
            int c = stoi(a);
            if (a[0] != '0' && c < b) {
                if (ans == -1 || ans < c) {
                    ans = c;
                }
            }
        } while (next_permutation(a));
        System.out.println(ans);
    }
}
