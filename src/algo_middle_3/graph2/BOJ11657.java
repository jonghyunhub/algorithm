package algo_middle_3.graph2;

import java.util.ArrayList;
import java.util.Scanner;

class EdgeB {
    int from, to, cost;

    EdgeB(int from, int to, int cost) {
        this.from = from;
        this.to = to;
        this.cost = cost;
    }

}

public class BOJ11657 {
    static final long inf = 100000000;

    public static void main(String args[]) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int m = sc.nextInt();
        ArrayList<EdgeB> a = new ArrayList<EdgeB>();
        for (int i = 0; i < m; i++) {
            int from = sc.nextInt();
            int to = sc.nextInt();
            int cost = sc.nextInt();
            a.add(new EdgeB(from, to, cost));
        }
        long[] dist = new long[n + 1];
        for (int i = 1; i <= n; i++) {
            dist[i] = inf;
        }
        dist[1] = 0;
        boolean negative_cycle = false;
        for (int i = 1; i <= n; i++) {
            for (EdgeB e : a) {
                int x = e.from;
                int y = e.to;
                int z = e.cost;
                if (dist[x] != inf && dist[y] > dist[x] + z) {
                    dist[y] = dist[x] + z;
                    if (i == n) {
                        negative_cycle = true;
                    }
                }
            }
        }
        if (negative_cycle) {
            System.out.println("-1");
        } else {
            for (int i = 2; i <= n; i++) {
                if (dist[i] == inf) dist[i] = -1;
                System.out.println(dist[i]);
            }
        }
    }
}
