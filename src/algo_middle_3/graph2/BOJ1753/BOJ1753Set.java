package algo_middle_3.graph2.BOJ1753;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.TreeSet;

class EdgeE implements Comparable<EdgeE> {
    int to, cost;

    EdgeE(int to, int cost) {
        this.to = to;
        this.cost = cost;
    }

    public int compareTo(EdgeE that) {
        if (this.cost < that.cost) {
            return -1;
        } else if (this.cost == that.cost) {
            if (this.to < that.to) return -1;
            else if (this.to > that.to) return 1;
            else return 0;
        } else {
            return 1;
        }
    }
}

public class BOJ1753Set {
    static final int inf = 1000000000;

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        List<EdgeE>[] a = (List<EdgeE>[]) new List[n + 1];
        for (int i = 1; i <= n; i++) {
            a[i] = new ArrayList<EdgeE>();
        }
        int m = sc.nextInt();
        int start = sc.nextInt();
        for (int i = 0; i < m; i++) {
            int x = sc.nextInt();
            int y = sc.nextInt();
            int z = sc.nextInt();
            a[x].add(new EdgeE(y, z));
        }
        int[] dist = new int[n + 1];
        boolean[] check = new boolean[n + 1];
        for (int i = 1; i <= n; i++) {
            dist[i] = inf;
            check[i] = false;
        }
        dist[start] = 0;
        TreeSet<EdgeE> s = new TreeSet<>();
        s.add(new EdgeE(start, 0));
        while (!s.isEmpty()) {
            EdgeE e = s.pollFirst();
            s.remove(e);
            int x = e.to;
            if (check[x]) continue;
            check[x] = true;
            for (EdgeE y : a[x]) {
                if (dist[y.to] > dist[x] + y.cost) {
                    if (dist[y.to] != inf) {
                        s.remove(new EdgeE(y.to, dist[y.to]));
                    }
                    dist[y.to] = dist[x] + y.cost;
                    s.add(new EdgeE(y.to, dist[y.to]));
                }
            }
        }
        for (int i = 1; i <= n; i++) {
            if (dist[i] == inf) {
                System.out.println("INF");
            } else {
                System.out.println(dist[i]);
            }
        }
    }
}
