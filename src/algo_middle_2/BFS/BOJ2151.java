package algo_middle_2.BFS;

import java.util.*;

class PairC {
    int x, y;

    PairC(int x, int y) {
        this.x = x;
        this.y = y;
    }
}

public class BOJ2151 {
    static final int[] dx = {0, 0, 1, -1};
    static final int[] dy = {1, -1, 0, 0};

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        String[] s = new String[n];
        int[][] b = new int[n][n];
        ArrayList<PairC> v = new ArrayList<>();
        int start = -1, end = -1;
        for (int i = 0; i < n; i++) {
            s[i] = sc.next();
            for (int j = 0; j < n; j++) {
                char ch = s[i].charAt(j);
                if (ch == '#') {
                    if (start == -1) {
                        start = v.size();
                    } else {
                        end = v.size();
                    }
                    v.add(new PairC(i, j));
                    b[i][j] = v.size() - 1;
                } else if (ch == '!') {
                    v.add(new PairC(i, j));
                    b[i][j] = v.size() - 1;
                }
            }
        }
        int m = v.size();
        boolean[][] a = new boolean[m][m];
        for (int i = 0; i < v.size(); i++) {
            for (int k = 0; k < 4; k++) {
                int x = v.get(i).x + dx[k];
                int y = v.get(i).y + dy[k];
                while (0 <= x && x < n && 0 <= y && y < n) {
                    char ch = s[x].charAt(y);
                    if (ch == '*') break;
                    if (ch == '!' || ch == '#') {
                        a[i][b[x][y]] = true;
                    }
                    x += dx[k];
                    y += dy[k];
                }
            }
        }
        Queue<Integer> q = new LinkedList<>();
        int[] dist = new int[m];
        Arrays.fill(dist, -1);
        q.add(start);
        dist[start] = 0;
        while (!q.isEmpty()) {
            int now = q.remove();
            for (int i = 0; i < m; i++) {
                if (a[now][i] && dist[i] == -1) {
                    dist[i] = dist[now] + 1;
                    q.add(i);
                }
            }
        }
        System.out.println(dist[end] - 1);
    }
}
