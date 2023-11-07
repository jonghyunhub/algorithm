package algo_middle_1.dividedconquest;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class BOJ10815 {
    public static boolean binary_search(int[] a, int num) {
        int n = a.length;
        int l = 0;
        int r = n - 1;
        while (l <= r) {
            int mid = (l + r) / 2;
            if (a[mid] == num) {
                return true;
            } else if (a[mid] > num) {
                r = mid - 1;
            } else {
                l = mid + 1;
            }
        }
        return false;
    }

    public static void main(String args[]) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.valueOf(br.readLine());
        String[] line = br.readLine().split(" ");
        int[] a = new int[n];
        for (int i = 0; i < n; i++) {
            a[i] = Integer.valueOf(line[i]);
        }
        Arrays.sort(a);
        int m = Integer.valueOf(br.readLine());
        line = br.readLine().split(" ");
        StringBuilder ans = new StringBuilder();
        for (int i = 0; i < m; i++) {
            int num = Integer.valueOf(line[i]);
            boolean res = binary_search(a, num);
            if (res) {
                ans.append("1 ");
            } else {
                ans.append("0 ");
            }

        }
        System.out.println(ans);
    }
}
