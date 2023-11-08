package prepare_coding_test_problem.BFS.BOJ17071;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

public class BOJ17071A {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int k = sc.nextInt();
        if (n == k) {
            System.out.println(0);
            System.exit(0);
        }
        int[] dist = new int[500001];
        Arrays.fill(dist, -1);
        Queue<Integer> q = new LinkedList<>();
        q.add(n);
        dist[n] = 0;
        for (int t = 1; ; t++) {
            k += t;
            if (k > 500000) break;
            Queue<Integer> nq = new LinkedList<>();
            while (!q.isEmpty()) {
                int x = q.remove();
                for (int y : new int[]{x + 1, x - 1, 2 * x}) {
                    if (0 <= y && y <= 500000) {
                        if (dist[y] != t) {
                            nq.add(y);
                            dist[y] = t;
                        }
                    }
                }
            }
            if (dist[k] == t) {
                System.out.println(t);
                System.exit(0);
            }
            q = nq;
        }
        System.out.println(-1);
    }
}
