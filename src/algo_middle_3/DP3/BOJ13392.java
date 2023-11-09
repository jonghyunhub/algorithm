package algo_middle_3.DP3;

import java.util.Scanner;

public class BOJ13392 {
    public static int n;
    public static String a, b;
    public static int[][] d;

    // d[i][j] = i, j
    public static int go(int index, int turn) {
        if (index == n) {
            return 0;
        }
        if (d[index][turn] != -1) return d[index][turn];
        // A[index] -> B[index];
        //   from   ->    to
        int from = a.charAt(index) - '0';
        from = (from + turn) % 10;
        int to = b.charAt(index) - '0';
        // left from -> to, to-from
        int left = to - from;
        if (left < 0) left += 10;
        d[index][turn] = left + go(index + 1, (turn + left) % 10);
        // right from -> to, from - to
        int right = from - to;
        if (right < 0) right += 10;
        int temp = right + go(index + 1, turn);
        if (d[index][turn] > temp) {
            d[index][turn] = temp;
        }
        return d[index][turn];
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        n = sc.nextInt();
        sc.nextLine();
        a = sc.nextLine();
        b = sc.nextLine();
        d = new int[n][10];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < 10; j++) {
                d[i][j] = -1;
            }
        }
        System.out.println(go(0, 0));
    }
}
