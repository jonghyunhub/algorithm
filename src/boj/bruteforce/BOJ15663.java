package boj.bruteforce;

import java.util.*;
import java.util.stream.Collectors;

// https://www.acmicpc.net/problem/15663
public class BOJ15663 {

    public static void main(String[] args) {
        final Scanner scanner = new Scanner(System.in);
        final int n = scanner.nextInt();
        final int m = scanner.nextInt();
        final List<Integer> original = new ArrayList<>();
        final StringBuilder builder = new StringBuilder();
        for (int i = 0; i < n; i++) {
            original.add(scanner.nextInt());
        }
        Collections.sort(original);
        List<List<Integer>> result = new ArrayList<>();
        final boolean[] used = new boolean[n];
        makePermutations(result, original, new ArrayList<>(),used, m);
        result = result.stream().distinct().collect(Collectors.toList());
        for (List<Integer> list : result) {
            for (int i = 0; i < list.size(); i++) {
                builder.append(list.get(i));
                if(i != list.size()-1) builder.append(" ");
            }
            builder.append("\n");
        }
        System.out.print(builder);
        scanner.close();
    }

    public static void makePermutations(List<List<Integer>> result,
                                        List<Integer> original,
                                        List<Integer> current,
                                        boolean[] used,
                                        int size) {
        if (current.size() == size) {
            result.add(new ArrayList<>(current));
            return;
        }

        for (int i = 0; i < original.size(); i++) {
            if(used[i]) continue;
            current.add(original.get(i));
            used[i] = true;
            makePermutations(result, original, current,used, size);
            current.remove(current.size() - 1); // 백 트래킹
            used[i] = false;
        }
    }
}
