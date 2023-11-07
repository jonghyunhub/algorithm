package algo_middle_2.datastructure2;

import java.util.Scanner;

public class BOJ2606 {
    static int[] parent = new int[1000001];

    static int find(int x) {
        if (x == parent[x]) {
            return x;
        } else {
            return parent[x] = find(parent[x]);
        }
    }

    static void union(int x, int y) {
        x = find(x);
        y = find(y);
        if (x != y) {
            parent[y] = x;
        }
    }

    public static void main(String args[]) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int m = sc.nextInt();
        for (int i = 1; i <= n; i++) {
            parent[i] = i;
        }
        while (m-- > 0) {
            int x = sc.nextInt();
            int y = sc.nextInt();
            union(x, y);
        }
        int ans = 0;
        for (int i = 2; i <= n; i++) {
            if (find(1) == find(i)) {
                ans += 1;
            }
        }
        System.out.println(ans);
    }
}
