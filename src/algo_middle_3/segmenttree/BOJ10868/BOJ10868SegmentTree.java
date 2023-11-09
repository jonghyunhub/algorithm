package algo_middle_3.segmenttree.BOJ10868;

import java.io.*;

public class BOJ10868SegmentTree {
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

    public static void main(String[] args) throws IOException {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        String[] line = bf.readLine().split(" ");
        int n = Integer.parseInt(line[0]);
        int m = Integer.parseInt(line[1]);
        int[] a = new int[n];
        for (int i = 0; i < n; i++) {
            a[i] = Integer.parseInt(bf.readLine());
        }
        int h = (int) Math.ceil(Math.log(n) / Math.log(2));
        int tree_size = (1 << (h + 1));
        int[] tree = new int[tree_size];
        init(a, tree, 1, 0, n - 1);
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        while (m-- > 0) {
            line = bf.readLine().split(" ");
            int start = Integer.parseInt(line[0]) - 1;
            int end = Integer.parseInt(line[1]) - 1;
            bw.write(query(tree, 1, 0, n - 1, start, end) + "\n");
        }
        bw.flush();
    }
}
