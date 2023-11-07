package algo_middle_1.greedy.BOJ1202;

import java.util.Arrays;
import java.util.Map;
import java.util.Scanner;
import java.util.TreeMap;

class JewelA implements Comparable<JewelA> {
    int m, v;

    JewelA(int m, int v) {
        this.m = m;
        this.v = v;
    }

    public int compareTo(JewelA that) {
        if (this.v > that.v) {
            return -1;
        } else if (this.v == that.v) {
            return 0;
        } else {
            return 1;
        }
    }
}

public class BOJ1202A {

    public static void main(String args[]) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int k = sc.nextInt();
        JewelA[] a = new JewelA[n];
        for (int i = 0; i < n; i++) {
            a[i] = new JewelA(sc.nextInt(), sc.nextInt());
        }
        Arrays.sort(a);
        TreeMap<Integer, Integer> d = new TreeMap<>();
        for (int i = 0; i < k; i++) {
            int c = sc.nextInt();
            Integer val = d.get(c);
            if (val == null) {
                val = 0;
            }
            val += 1;
            d.put(c, val);
        }
        long ans = 0;
        for (int i = 0; i < n; i++) {
            Map.Entry<Integer, Integer> it = d.ceilingEntry(a[i].m);
            if (it != null) {
                ans += a[i].v;
                int c = (int) it.getKey();
                Integer val = it.getValue() - 1;
                if (val == 0) {
                    d.remove(c);
                } else {
                    d.put(c, val);
                }
            }
        }
        System.out.println(ans);
    }
}
