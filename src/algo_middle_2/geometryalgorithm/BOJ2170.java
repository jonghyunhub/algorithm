package algo_middle_2.geometryalgorithm;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;

class PairE implements Comparable<PairE> {
    int first, second;

    PairE(int first, int second) {
        this.first = first;
        this.second = second;
    }

    public int compareTo(PairE that) {
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

public class BOJ2170 {


    public static void main(String[] args) throws IOException {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(bf.readLine());
        ArrayList<PairE> a = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            String[] line = bf.readLine().split(" ");
            int x = Integer.parseInt(line[0]);
            int y = Integer.parseInt(line[1]);
            a.add(new PairE(x, 1));
            a.add(new PairE(y, -1));
        }
        Collections.sort(a);
        int ans = 0;
        int cnt = 0;
        int last = 0;
        for (PairE p : a) {
            if (p.second == 1 && cnt == 0) {
                last = p.first;
            }
            cnt += p.second;
            if (p.second == -1 && cnt == 0) {
                ans += p.first - last;
            }
        }
        System.out.println(ans);
    }
}
