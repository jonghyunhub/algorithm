package algo_middle_3.tree2.BOJ11438;

import java.io.*;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;

public class BOJ11438A {
    public static void main(String[] args) throws IOException {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(bf.readLine());
        ArrayList<Integer>[] a = new ArrayList[n + 1];
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
        int[] depth = new int[n + 1];
        boolean[] check = new boolean[n + 1];
        int[] parent = new int[n + 1];
        Queue<Integer> q = new LinkedList<Integer>();
        check[1] = true;
        depth[1] = 0;
        parent[1] = 0;
        q.add(1);
        while (!q.isEmpty()) {
            int x = q.remove();
            for (int y : a[x]) {
                if (check[y] == false) {
                    depth[y] = depth[x] + 1;
                    check[y] = true;
                    parent[y] = x;
                    q.add(y);
                }
            }
        }
        int log = 1;
        while ((1 << log) <= n) {
            log += 1;
        }
        log -= 1;
        int[][] p = new int[n + 1][log + 1];
        for (int i = 1; i <= n; i++) {
            p[i][0] = parent[i];
        }
        for (int j = 1; (1 << j) < n; j++) {
            for (int i = 1; i <= n; i++) {
                if (p[i][j - 1] != 0) {
                    p[i][j] = p[p[i][j - 1]][j - 1];
                }
            }
        }
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        int m = Integer.parseInt(bf.readLine());
        while (m-- > 0) {
            String[] line = bf.readLine().split(" ");
            int x = Integer.parseInt(line[0]);
            int y = Integer.parseInt(line[1]);
            if (depth[x] < depth[y]) {
                int temp = x;
                x = y;
                y = temp;
            }
            log = 1;
            while ((1 << log) <= depth[x]) {
                log += 1;
            }
            log -= 1;
            for (int i = log; i >= 0; i--) {
                if (depth[x] - (1 << i) >= depth[y]) {
                    x = p[x][i];
                }
            }
            if (x == y) {
                bw.write(x + "\n");
            } else {
                for (int i = log; i >= 0; i--) {
                    if (p[x][i] != 0 && p[x][i] != p[y][i]) {
                        x = p[x][i];
                        y = p[y][i];
                    }
                }
                bw.write(parent[x] + "\n");
            }
        }
        bw.flush();
    }
}
