package algo_middle_2.datastructure2;

import java.io.*;
import java.util.Comparator;
import java.util.PriorityQueue;

public class BOJ1655 {
    static class Compare implements Comparator<Integer> {
        public int compare(Integer one, Integer two) {
            return two.compareTo(one);
        }
    }

    public static void main(String args[]) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        Compare cmp = new Compare();
        PriorityQueue<Integer> l = new PriorityQueue<Integer>(1, cmp);
        PriorityQueue<Integer> r = new PriorityQueue<>();
        int n = Integer.parseInt(br.readLine());
        while (n-- > 0) {
            int x = Integer.parseInt(br.readLine());

            if (l.isEmpty() || r.isEmpty()) {
                l.add(x);
            } else {
                if (x <= l.peek()) {
                    l.add(x);
                } else if (x >= r.peek()) {
                    r.add(x);
                } else {
                    l.add(x);
                }
            }
            while (!(l.size() == r.size() || l.size() == r.size() + 1)) {
                if (l.size() > r.size()) {
                    r.add(l.peek());
                    l.poll();
                } else {
                    l.add(r.peek());
                    r.poll();
                }
            }

            bw.write(l.peek() + "\n");
        }
        bw.flush();
    }
}
