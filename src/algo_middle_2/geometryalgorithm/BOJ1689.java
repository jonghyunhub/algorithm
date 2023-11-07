package algo_middle_2.geometryalgorithm;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;

class Pair implements Comparable<Pair> {
    int first, second;

    Pair(int first, int second) {
        this.first = first;
        this.second = second;
    }

    public int compareTo(Pair that) {
        if (this.first < that.first) {
            return -1;
        } else if (this.first == that.first) {
            if (this.second < that.second) {
                return -1;
            } else if (this.second == that.second) {
                return 0;
            } else {
                return 1;
            }
        } else {
            return 1;
        }
    }
}

public class BOJ1689 {

    public static void main(String[] args) throws IOException {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(bf.readLine());
        ArrayList<Pair> a = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            String[] line = bf.readLine().split(" ");
            int x = Integer.parseInt(line[0]);
            int y = Integer.parseInt(line[1]);
            a.add(new Pair(x, 1));
            a.add(new Pair(y, -1));
        }
        Collections.sort(a);
        int ans = 0;
        int cnt = 0;
        for (Pair p : a) {
            cnt += p.second;
            if (cnt > ans) ans = cnt;
        }
        System.out.println(ans);
    }
}
