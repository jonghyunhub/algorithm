package boj.bruteforce;

import java.util.*;

// https://www.acmicpc.net/problem/15657
public class BOJ15657 {
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
        final List<List<Integer>> result = new ArrayList<>();
        makePermutations(result, original, new ArrayList<>(), 0, m);
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
                                        int now,
                                        int size) {
        if (current.size() == size) {
            result.add(new ArrayList<>(current));
            return;
        }

        for (int i = now; i < original.size(); i++) {
            current.add(original.get(i));
            makePermutations(result, original, current, i, size);
            current.remove(current.size() - 1); // 백 트래킹
        }
    }
}
