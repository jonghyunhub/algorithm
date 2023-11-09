package algo_middle_3.segmenttree.BOJ10868;

import java.io.*;

public class BOJ10868DP {
    static int[] a = new int[100000];
    static int[][] d = new int[100000][17];

    public static void main(String[] args) throws IOException {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        String[] line = bf.readLine().split(" ");
        int n = Integer.parseInt(line[0]);
        int m = Integer.parseInt(line[1]);
        for (int i = 0; i < n; i++) {
            a[i] = Integer.parseInt(bf.readLine());
            d[i][0] = a[i];
        }
        for (int j = 1; j < 17; j++) {
            for (int i = 0; i < n; i++) {
                if (i + (1 << j) - 1 < n) {
                    d[i][j] = Math.min(d[i][j - 1], d[i + (1 << (j - 1))][j - 1]);
                } else {
                    break;
                }
            }
        }
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        while (m-- > 0) {
            line = bf.readLine().split(" ");
            int start = Integer.parseInt(line[0]) - 1;
            int end = Integer.parseInt(line[1]) - 1;
            int ans = a[start];
            int k = 16;
            while (start <= end && k >= 0) {
                if (start + (1 << k) - 1 <= end) {
                    ans = Math.min(ans, d[start][k]);
                    start += (1 << k);
                }
                k -= 1;
            }
            bw.write(ans + "\n");
        }
        bw.flush();
    }
}
