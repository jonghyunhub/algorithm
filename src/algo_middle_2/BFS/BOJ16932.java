package algo_middle_2.BFS;

import java.util.HashSet;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

public class BOJ16932 {
    static int n, m;
    static int[][] a;
    static int[][] group;
    static int[] group_size;
    static int groupn;
    final static int[] dx = {0, 0, 1, -1};
    final static int[] dy = {1, -1, 0, 0};

    static void bfs(int sx, int sy) {
        groupn += 1;
        Queue<Integer> q = new LinkedList<>();
        q.add(sx);
        q.add(sy);
        group[sx][sy] = groupn;
        int cnt = 1;
        while (!q.isEmpty()) {
            int x = q.remove();
            int y = q.remove();
            for (int k = 0; k < 4; k++) {
                int nx = x + dx[k];
                int ny = y + dy[k];
                if (0 <= nx && nx < n && 0 <= ny && ny < m) {
                    if (group[nx][ny] == 0 && a[nx][ny] == 1) {
                        group[nx][ny] = groupn;
                        q.add(nx);
                        q.add(ny);
                        cnt += 1;
                    }
                }
            }
        }
        group_size[groupn] = cnt;
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        n = sc.nextInt();
        m = sc.nextInt();
        a = new int[n][m];
        group = new int[n][m];
        group_size = new int[n * m];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                a[i][j] = sc.nextInt();
            }
        }
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                if (a[i][j] == 1 && group[i][j] == 0) {
                    bfs(i, j);
                }
            }
        }
        int ans = 0;
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                if (a[i][j] == 0) {
                    HashSet<Integer> near = new HashSet<>();
                    for (int k = 0; k < 4; k++) {
                        int nx = i + dx[k];
                        int ny = j + dy[k];
                        if (0 <= nx && nx < n && 0 <= ny && ny < m) {
                            if (a[nx][ny] == 1) {
                                near.add(group[nx][ny]);
                            }
                        }
                    }
                    int sum = 1;
                    for (int neighbor : near) {
                        sum += group_size[neighbor];
                    }
                    if (ans < sum) ans = sum;
                }
            }
        }
        System.out.println(ans);
    }
}
