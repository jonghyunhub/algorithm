package prepare_coding_test_basic.BFS.BOJ1261;

import java.util.ArrayDeque;
import java.util.Scanner;

class PairA {
    int x, y;

    PairA(int x, int y) {
        this.x = x;
        this.y = y;
    }
}

public class BOJ1261Deck {
    public static void main(String args[]) {
        Scanner sc = new Scanner(System.in);
        int m = sc.nextInt();
        int n = sc.nextInt();
        sc.nextLine();
        int[][] a = new int[n][m];
        for (int i = 0; i < n; i++) {
            String s = sc.nextLine();
            for (int j = 0; j < m; j++) {
                a[i][j] = s.charAt(j) - '0';
            }
        }
        int[] dx = {0, 0, 1, -1};
        int[] dy = {1, -1, 0, 0};
        int[][] d = new int[n][m];
        ArrayDeque<PairA> deque = new ArrayDeque<PairA>();
        deque.add(new PairA(0, 0));
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                d[i][j] = -1;
            }
        }
        d[0][0] = 0;
        while (!deque.isEmpty()) {
            PairA p = deque.poll();
            int x = p.x;
            int y = p.y;
            for (int k = 0; k < 4; k++) {
                int nx = x + dx[k];
                int ny = y + dy[k];
                if (0 <= nx && nx < n && 0 <= ny && ny < m) {
                    if (d[nx][ny] == -1) {
                        if (a[nx][ny] == 0) {
                            d[nx][ny] = d[x][y];
                            deque.addFirst(new PairA(nx, ny));
                        } else {
                            d[nx][ny] = d[x][y] + 1;
                            deque.addLast(new PairA(nx, ny));
                        }
                    }
                }
            }
        }
        System.out.println(d[n - 1][m - 1]);
    }
}
