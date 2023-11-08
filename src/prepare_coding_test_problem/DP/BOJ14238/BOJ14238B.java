package prepare_coding_test_problem.DP.BOJ14238;

import java.util.Scanner;

public class BOJ14238B {
    static int[][][][][] d = new int[51][51][51][3][3];

    static int go(int a, int b, int c, int p1, int p2) {
        int ans = d[a][b][c][p1][p2];
        if (a + b + c == 0) {
            d[a][b][c][p1][p2] = 1;
            return d[a][b][c][p1][p2];
        }
        if (ans != -1) return ans;
        if (a > 0 && go(a - 1, b, c, 0, p1) == 1) {
            d[a][b][c][p1][p2] = 1;
            return d[a][b][c][p1][p2];
        }
        if (b > 0 && p1 != 1) {
            if (go(a, b - 1, c, 1, p1) == 1) {
                d[a][b][c][p1][p2] = 1;
                return d[a][b][c][p1][p2];
            }
        }
        if (c > 0 && p1 != 2 && p2 != 2) {
            if (go(a, b, c - 1, 2, p1) == 1) {
                d[a][b][c][p1][p2] = 1;
                return d[a][b][c][p1][p2];
            }
        }
        d[a][b][c][p1][p2] = 0;
        return d[a][b][c][p1][p2];
    }

    static String back(int a, int b, int c, int p1, int p2) {
        if (a + b + c == 0) {
            return "";
        }
        if (a > 0 && go(a - 1, b, c, 0, p1) == 1) {
            return "A" + back(a - 1, b, c, 0, p1);
        }
        if (b > 0 && p1 != 1) {
            if (go(a, b - 1, c, 1, p1) == 1) {
                return "B" + back(a, b - 1, c, 1, p1);
            }
        }
        if (c > 0 && p1 != 2 && p2 != 2) {
            if (go(a, b, c - 1, 2, p1) == 1) {
                return "C" + back(a, b, c - 1, 2, p1);
            }
        }
        return "";
    }

    public static void main(String args[]) {
        Scanner sc = new Scanner(System.in);
        String s = sc.nextLine();
        int n = s.length();
        int[] limit = {0, 0, 0};
        for (int i = 0; i < n; i++) {
            limit[s.charAt(i) - 'A'] += 1;
        }
        for (int i = 0; i < 51; i++) {
            for (int j = 0; j < 51; j++) {
                for (int k = 0; k < 51; k++) {
                    for (int l = 0; l < 3; l++) {
                        Arrays.fill(d[i][j][k][l], -1);
                    }
                }
            }
        }
        int ans = go(limit[0], limit[1], limit[2], 0, 0);
        if (ans == 0) {
            System.out.println(-1);
        } else {
            System.out.println(back(limit[0], limit[1], limit[2], 0, 0));
        }
    }
}
