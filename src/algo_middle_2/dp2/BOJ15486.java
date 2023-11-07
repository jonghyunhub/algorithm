package algo_middle_2.dp2;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class BOJ15486 {
    public static void main(String[] args) throws IOException {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(bf.readLine());
        int[] t = new int[n];
        int[] p = new int[n];
        int[] d = new int[n + 50];
        for (int i = 0; i < n; i++) {
            String[] temp = bf.readLine().split(" ");
            t[i] = Integer.parseInt(temp[0]);
            p[i] = Integer.parseInt(temp[1]);
        }
        for (int i = 0; i < n; i++) {
            d[i + t[i]] = Math.max(d[i + t[i]], d[i] + p[i]);
            d[i + 1] = Math.max(d[i + 1], d[i]);
        }
        System.out.println(d[n]);
    }
}
