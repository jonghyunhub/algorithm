package algo_middle_3.tree2.BOJ11438;

import java.io.*;
import java.util.ArrayList;

public class BOJ11438B {
    static final int MAX = 100111;
    static ArrayList<Integer>[] a = new ArrayList[MAX];
    static int[][] p = new int[MAX][18];
    static int[] tin = new int[MAX];
    static int[] tout = new int[MAX];
    static int timer = 0;
    static int l = 0;

    static void dfs(int v, int parent) {
        tin[v] = ++timer;
        p[v][0] = parent;
        for (int i = 1; i <= l; i++) {
            p[v][i] = p[p[v][i - 1]][i - 1];
        }
        for (int to : a[v]) {
            if (to != parent) {
                dfs(to, v);
            }
        }
        tout[v] = ++timer;
    }

    static boolean upper(int u, int v) {
        return (tin[u] <= tin[v] && tout[u] >= tout[v]);
    }

    static int lca(int u, int v) {
        if (upper(u, v)) return u;
        if (upper(v, u)) return v;
        for (int i = l; i >= 0; i--) {
            if (!upper(p[u][i], v)) {
                u = p[u][i];
            }
        }
        return p[u][0];
    }

    public static void main(String[] args) throws IOException {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(bf.readLine());
        for (int i = 1; i <= n; i++) {
            a[i] = new ArrayList<Integer>();
        }
        for (int i = 0; i < n - 1; i++) {
            String[] line = bf.readLine().split(" ");
            int x = Integer.parseInt(line[0]);
            int y = Integer.parseInt(line[1]);
            a[x].add(y);
            a[y].add(x);
        }
        for (l = 1; (1 << l) <= n; l++) ;
        l -= 1;
        dfs(1, 1);
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        int m = Integer.parseInt(bf.readLine());
        while (m-- > 0) {
            String[] line = bf.readLine().split(" ");
            int x = Integer.parseInt(line[0]);
            int y = Integer.parseInt(line[1]);
            bw.write(lca(x, y) + "\n");
        }
        bw.flush();
    }
}
