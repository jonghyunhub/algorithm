package algo_middle_3.graph2;

import java.util.Scanner;
import java.util.Vector;

class EdgeC {
    int from, to, cost;

    EdgeC(int from, int to, int cost) {
        this.from = from;
        this.to = to;
        this.cost = cost;
    }

}

public class BOJ1865 {
    static final int inf = 100000000;

    public static void main(String args[]) {
        Scanner sc = new Scanner(System.in);
        int t = sc.nextInt();
        while (t-- > 0) {
            int n = sc.nextInt();
            int m = sc.nextInt();
            int w = sc.nextInt();
            Vector<EdgeC> a = new Vector<EdgeC>();
            for (int i = 0; i < m; i++) {
                int x = sc.nextInt();
                int y = sc.nextInt();
                int z = sc.nextInt();
                a.add(new EdgeC(x, y, z));
                a.add(new EdgeC(y, x, z));
            }
            for (int i = 2 * m; i < 2 * m + w; i++) {
                int x = sc.nextInt();
                int y = sc.nextInt();
                int z = sc.nextInt();
                a.add(new EdgeC(x, y, -z));
            }
            int[] d = new int[n + 1];
            for (int i = 1; i <= n; i++) {
                d[i] = 0;
            }
            m = 2 * m + w;
            boolean ok = false;
            for (int i = 1; i <= n; i++) {
                for (EdgeC e : a) {
                    int x = e.from;
                    int y = e.to;
                    int z = e.cost;
                    if (d[x] != inf && d[y] > d[x] + z) {
                        d[y] = d[x] + z;
                        if (i == n) {
                            ok = true;
                        }
                    }
                }
            }
            if (ok) {
                System.out.println("YES");
            } else {
                System.out.println("NO");
            }
        }
    }
}
