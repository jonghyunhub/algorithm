package algo_middle_2.BFS;

import java.util.Scanner;

public class BOJ16956 {
    static int[] dx = {0, 0, 1, -1};
    static int[] dy = {1, -1, 0, 0};

    public static void main(String args[]) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int m = sc.nextInt();
        String[] a = new String[n];
        for (int i = 0; i < n; i++) {
            a[i] = sc.next();
        }
        boolean ok = true;
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                if (a[i].charAt(j) == 'S') {
                    for (int k = 0; k < 4; k++) {
                        int x = i + dx[k];
                        int y = j + dy[k];
                        if (0 <= x && x < n && 0 <= y && y < m) {
                            if (a[x].charAt(y) == 'W') {
                                ok = false;
                            }
                        }
                    }
                }
            }
        }
        if (!ok) {
            System.out.println(0);
        } else {
            System.out.println(1);
            for (int i = 0; i < n; i++) {
                for (int j = 0; j < m; j++) {
                    if (a[i].charAt(j) == '.') {
                        System.out.print('D');
                    } else {
                        System.out.print(a[i].charAt(j));
                    }
                }
                System.out.println();
            }
        }
    }
}
