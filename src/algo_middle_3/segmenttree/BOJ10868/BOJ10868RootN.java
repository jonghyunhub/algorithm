package algo_middle_3.segmenttree.BOJ10868;

import java.io.*;

public class BOJ10868RootN {
    static int[] a = new int[100000];
    static int[] group = new int[317];

    public static void main(String[] args) throws IOException {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        String[] line = bf.readLine().split(" ");
        int n = Integer.parseInt(line[0]);
        int m = Integer.parseInt(line[1]);
        for (int i = 0; i < n; i++) {
            a[i] = Integer.parseInt(bf.readLine());
        }

        int r = 1;
        for (int i = 1; i * i <= n; i++) {
            r = i;
        }

        for (int i = 0; i < n; i++) {
            if (i % r == 0) {
                group[i / r] = a[i];
            } else {
                group[i / r] = Math.min(group[i / r], a[i]);
            }
        }
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        while (m-- > 0) {
            line = bf.readLine().split(" ");
            int start = Integer.parseInt(line[0]) - 1;
            int end = Integer.parseInt(line[1]) - 1;
            int ans = a[start];
            int g1 = start / r;
            int g2 = end / r;
            if (g1 == g2) {
                for (int i = start; i <= end; i++) {
                    ans = Math.min(ans, a[i]);
                }
            } else {
                while (true) {
                    ans = Math.min(ans, a[start]);
                    start += 1;
                    if (start % r == 0) {
                        break;
                    }
                }

                while (true) {
                    ans = Math.min(ans, a[end]);
                    end -= 1;
                    if (end % r == r - 1) {
                        break;
                    }
                }
                for (int i = start / r; i <= end / r; i++) {
                    ans = Math.min(ans, group[i]);
                }
            }
            bw.write(ans + "\n");
        }
        bw.flush();
    }
}
