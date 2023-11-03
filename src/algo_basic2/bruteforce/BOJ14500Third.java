package algo_basic2.bruteforce;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Scanner;

public class BOJ14500Third {
    static String[][] blocks = {
            {"1111"},
            {"11",
                    "11"},
            {"10",
                    "10",
                    "11"},
            {"10",
                    "11",
                    "01"},
            {"111",
                    "010"}
    };

    static String[] mirror(String[] b) {
        String[] ans = new String[b.length];
        for (int i = 0; i < b.length; i++) {
            ans[i] = new StringBuilder(b[i]).reverse().toString();
        }
        return ans;
    }

    static String[] rotate(String[] b) {
        String[] ans = new String[b[0].length()];
        for (int j = 0; j < b[0].length(); j++) {
            StringBuilder sb = new StringBuilder();
            for (int i = (int) b.length - 1; i >= 0; i--) {
                sb.append(b[i].charAt(j));
            }
            ans[j] = sb.toString();
        }
        return ans;
    }

    static int calc(int[][] a, String[] b, int x, int y) {
        int n = a.length;
        int m = a[0].length;
        int sum = 0;
        for (int i = 0; i < b.length; i++) {
            for (int j = 0; j < b[0].length(); j++) {
                if (b[i].charAt(j) == '0') continue;
                int nx = x + i;
                int ny = y + j;
                if (0 <= nx && nx < n && 0 <= ny && ny < m) {
                    sum += a[nx][ny];
                } else {
                    return -1;
                }
            }
        }
        return sum;
    }

    public static void main(String[] args) throws IOException {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        String[] line = bf.readLine().split(" ");
        Scanner sc = new Scanner(System.in);
        int n = Integer.parseInt(line[0]);
        int m = Integer.parseInt(line[1]);
        int[][] a = new int[n][m];
        for (int i = 0; i < n; i++) {
            line = bf.readLine().split(" ");
            for (int j = 0; j < m; j++) {
                a[i][j] = Integer.parseInt(line[j]);
            }
        }
        int ans = 0;
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                for (String[] block : blocks) {
                    String[] b = new String[block.length];
                    System.arraycopy(block, 0, b, 0, block.length);
                    for (int mir = 0; mir < 2; mir++) {
                        for (int rot = 0; rot < 4; rot++) {
                            int cur = calc(a, b, i, j);
                            if (cur != -1 && ans < cur) {
                                ans = cur;
                            }
                            b = rotate(b);
                        }
                        b = mirror(b);
                    }
                }
            }
        }
        System.out.println(ans);
    }
}
