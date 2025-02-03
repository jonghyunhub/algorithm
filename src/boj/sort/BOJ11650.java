package boj.sort;

import java.util.*;
import java.io.*;

// https://www.acmicpc.net/problem/11650
public class BOJ11650 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());
        List<List<Integer>> list = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            List<Integer> inner = new ArrayList<>();
            inner.add(Integer.parseInt(st.nextToken()));
            inner.add(Integer.parseInt(st.nextToken()));
            list.add(inner);
        }

        Collections.sort(list,
                Comparator.comparing((List<Integer> l) -> l.get(0))
                        .thenComparing(l -> l.get(1)));

        StringBuilder builder = new StringBuilder();
        for (int i = 0; i < n; i++) {
            List<Integer> inner = list.get(i);
            builder.append(inner.get(0))
                    .append(" ")
                    .append(inner.get(1))
                    .append("\n");
        }
        System.out.println(builder);
    }
}
