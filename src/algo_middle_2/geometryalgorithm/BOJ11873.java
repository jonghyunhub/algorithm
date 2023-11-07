package algo_middle_2.geometryalgorithm;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Stack;

public class BOJ11873 {
    static int largest(int[] a) {
        int n = a.length;
        Stack<Integer> s = new Stack<>();
        int ans = 0;
        for (int i=0; i<n; i++) {
            int left = i;
            while(!s.isEmpty() && a[s.peek()] > a[i]) {
                int height = a[s.peek()];
                s.pop();
                int width = i;
                if (!s.isEmpty()) {
                    width = (i - s.peek() - 1);
                }
                if (ans < width*height) {
                    ans = width*height;
                }
            }
            s.push(i);
        }
        while(!s.isEmpty()) {
            int height = a[s.peek()];
            s.pop();
            int width = n;
            if (!s.isEmpty()) {
                width = n-s.peek()-1;
            }
            if (ans < width*height) {
                ans = width*height;
            }
        }
        return ans;
    }
    public static void main(String[] args) throws IOException {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        while (true) {
            String[] line = bf.readLine().split(" ");
            int n = Integer.parseInt(line[0]);
            int m = Integer.parseInt(line[1]);
            if (n == 0 && m == 0) break;
            int[][] d = new int[n][m];
            for (int i=0; i<n; i++) {
                line = bf.readLine().split(" ");
                for (int j=0; j<m; j++) {
                    d[i][j] = Integer.parseInt(line[j]);
                }
            }
            int ans = 0;
            int[] a = new int[m];
            for (int i=0; i<n; i++) {
                for (int j=0; j<m; j++) {
                    if (d[i][j] == 0) {
                        a[j] = 0;
                    } else {
                        a[j] += 1;
                    }
                }
                int cur = largest(a);
                if (ans < cur) ans = cur;
            }
            System.out.println(ans);
        }
    }
}
