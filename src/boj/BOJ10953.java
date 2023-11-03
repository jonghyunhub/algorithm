package boj;

import java.util.Scanner;
import java.util.StringTokenizer;

public class BOJ10953 {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        StringTokenizer st;
        String str;

        int T = scanner.nextInt();
        while (T-- > 0) {
            str = scanner.next();
            st = new StringTokenizer(str, ",");

            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            System.out.println(a + b);
        }
        scanner.close();
    }
}
