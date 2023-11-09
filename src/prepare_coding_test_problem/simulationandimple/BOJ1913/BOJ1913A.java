package prepare_coding_test_problem.simulationandimple.BOJ1913;

import java.io.BufferedWriter;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.util.Scanner;

public class BOJ1913A {
    static final int[] dx = {0, 1, 0, -1};
    static final int[] dy = {1, 0, -1, 0};

    public static void main(String[] args) throws IOException {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int m = sc.nextInt();
        int[][] a = new int[n][n];
        int x = (n - 1) / 2;
        int y = (n - 1) / 2;
        a[x][y] = 1;
        int num = 2;
        for (int size = 3; size <= n; size += 2) {
            x += dx[3];
            y += dy[3];
            a[x][y] = num++;
            for (int k = 0; k < 4; k++) {
                int loop = size - 1;
                if (k == 0) loop -= 1;
                for (int i = 0; i < loop; i++) {
                    x += dx[k];
                    y += dy[k];
                    a[x][y] = num++;
                }
            }
        }
        x = 0;
        y = 0;
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                bw.write(a[i][j] + " ");
                if (a[i][j] == m) {
                    x = i + 1;
                    y = j + 1;
                }
            }
            bw.write("\n");
        }
        bw.flush();
        System.out.println(x + " " + y);
    }
}
