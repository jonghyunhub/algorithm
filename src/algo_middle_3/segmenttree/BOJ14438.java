package algo_middle_3.segmenttree;

import java.io.*;

public class BOJ14438 {
    static void init(int[] a, int[] tree, int node, int start, int end) {
        if (start == end) {
            tree[node] = a[start];
        } else {
            init(a, tree, node * 2, start, (start + end) / 2);
            init(a, tree, node * 2 + 1, (start + end) / 2 + 1, end);
            tree[node] = Math.min(tree[node * 2], tree[node * 2 + 1]);
        }
    }

    static int query(int[] tree, int node, int start, int end, int i, int j) {
        if (i > end || j < start) return -1;
        if (i <= start && end <= j) return tree[node];
        int m1 = query(tree, node * 2, start, (start + end) / 2, i, j);
        int m2 = query(tree, node * 2 + 1, (start + end) / 2 + 1, end, i, j);
        if (m1 == -1) return m2;
        else if (m2 == -1) return m1;
        else return Math.min(m1, m2);
    }

    static void update(int[] tree, int node, int start, int end, int index, int value) {
        if (index < start || end < index) return;
        if (start == end) {
            tree[node] = value;
            return;
        }
        update(tree, node * 2, start, (start + end) / 2, index, value);
        update(tree, node * 2 + 1, (start + end) / 2 + 1, end, index, value);
        tree[node] = Math.min(tree[node * 2], tree[node * 2 + 1]);
    }

    public static void main(String[] args) throws IOException {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(bf.readLine());
        String[] line = bf.readLine().split(" ");
        int[] a = new int[n + 1];
        for (int i = 1; i <= n; i++) {
            a[i] = Integer.parseInt(line[i - 1]);
        }
        int h = (int) Math.ceil(Math.log(n) / Math.log(2));
        int tree_size = (1 << (h + 1));
        int[] tree = new int[tree_size];
        init(a, tree, 1, 1, n);
        int m = Integer.parseInt(bf.readLine());
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        while (m-- > 0) {
            line = bf.readLine().split(" ");
            int what = Integer.parseInt(line[0]);
            if (what == 1) {
                int index = Integer.parseInt(line[1]);
                int value = Integer.parseInt(line[2]);
                update(tree, 1, 1, n, index, value);
            } else {
                int start = Integer.parseInt(line[1]);
                int end = Integer.parseInt(line[2]);
                bw.write(query(tree, 1, 1, n, start, end) + "\n");
            }
        }
        bw.flush();
    }
}
