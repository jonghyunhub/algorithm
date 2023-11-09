package algo_middle_3.graph2.BOJ2252;

import java.util.ArrayList;
import java.util.Scanner;

public class BOJ2252DFS {
    static ArrayList<Integer>[] a;
    static boolean[] check;

    static void go(int x) {
        check[x] = true;
        for (int y : a[x]) {
            if (check[y] == false) {
                go(y);
            }
        }
        System.out.print(x + " ");
    }

    public static void main(String args[]) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int m = sc.nextInt();
        a = new ArrayList[n + 1];
        check = new boolean[n + 1];
        for (int i = 1; i <= n; i++) {
            a[i] = new ArrayList<>();
        }
        for (int i = 0; i < m; i++) {
            int x = sc.nextInt();
            int y = sc.nextInt();
            a[y].add(x);
        }
        for (int i = 1; i <= n; i++) {
            if (check[i] == false) {
                go(i);
            }
        }
        System.out.println();
    }
}
