package algo_middle_3.DP3;

import java.util.Arrays;
import java.util.Scanner;

public class BOJ2494 {
    static int[] a = new int[10000];
    static int[] b = new int[10000];
    static int[][] d = new int[10000][10];
    static int[][] how = new int[10000][10];
    static int[][] to = new int[10000][10];
    static int[][] cost = new int[10000][10];
    static int n;

    static int go(int index, int turn) {
        if (index == n) {
            return 0;
        }
        int ans = d[index][turn];
        if (ans != -1) {
            return ans;
        }
        int cur = (a[index] + turn) % 10;
        // left
        int cost_l = (b[index] + 10 - cur) % 10;
        int left = go(index + 1, (turn + cost_l) % 10) + cost_l;
        int cost_r = (cur + 10 - b[index]) % 10;
        int right = go(index + 1, turn) + cost_r;
        if (left < right) {
            ans = left;
            how[index][turn] = 1;
            to[index][turn] = (turn + cost_l) % 10;
            cost[index][turn] = cost_l;
        } else {
            ans = right;
            how[index][turn] = 2;
            to[index][turn] = turn;
            cost[index][turn] = cost_r;
        }
        d[index][turn] = ans;
        return ans;
    }

    static void print(int index, int turn) {
        if (index == n) {
            return;
        }
        if (cost[index][turn] == 0) {
            print(index + 1, turn);
        } else {
            System.out.print((index + 1) + " ");
            if (how[index][turn] == 2) {
                System.out.print("-");
            }
            System.out.println(cost[index][turn]);
            print(index + 1, to[index][turn]);
        }
    }

    public static void main(String args[]) {
        Scanner sc = new Scanner(System.in);
        n = sc.nextInt();
        sc.nextLine();
        String s = sc.nextLine();
        for (int i = 0; i < n; i++) {
            a[i] = s.charAt(i) - '0';
        }
        s = sc.nextLine();
        for (int i = 0; i < n; i++) {
            b[i] = s.charAt(i) - '0';
        }
        for (int i = 0; i < n; i++) {
            Arrays.fill(d[i], -1);
        }
        System.out.println(go(0, 0));
        print(0, 0);
    }
}
