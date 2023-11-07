package algo_middle_2.datastructure2;

import java.util.Scanner;
import java.util.Stack;

public class BOJ6549 {
    public static void main(String args[]) {
        Scanner sc = new Scanner(System.in);
        while (true) {
            int n = sc.nextInt();
            if (n == 0) break;
            long[] a = new long[n];
            for (int i = 0; i < n; i++) {
                a[i] = sc.nextLong();
            }
            Stack<Integer> s = new Stack<>();
            long ans = 0;
            for (int i = 0; i < n; i++) {
                int left = i;
                while (!s.isEmpty() && a[s.peek()] > a[i]) {
                    long height = a[s.pop()];
                    long width = i;
                    if (!s.isEmpty()) {
                        width = (i - s.peek() - 1);
                    }
                    if (ans < (long) width * height) {
                        ans = (long) width * height;
                    }
                }
                s.push(i);
            }
            while (!s.isEmpty()) {
                long height = a[s.pop()];
                long width = n;
                if (!s.isEmpty()) {
                    width = n - s.peek() - 1;
                }
                if (ans < (long) width * height) {
                    ans = (long) width * height;
                }
            }
            System.out.println(ans);
        }
    }
}
