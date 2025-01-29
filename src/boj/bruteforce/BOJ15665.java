package boj.bruteforce;

import java.util.*;
import java.util.stream.Collectors;

// https://www.acmicpc.net/problem/15665
public class BOJ15665 {
    public static void main(String[] args) {
        final Scanner scanner = new Scanner(System.in);
        final int n = scanner.nextInt();
        final int m = scanner.nextInt();
        final List<Integer> original = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            original.add(scanner.nextInt());
        }
        List<List<Integer>> result = new ArrayList<>();
        original.sort(Comparator.naturalOrder()); // 오름차순 정렬
        makePermutations(result, original, new ArrayList<>(), m);
        result = result.stream().distinct().collect(Collectors.toList());
        StringBuilder builder = new StringBuilder();
        for (List<Integer> list : result) {
            for (int i = 0; i < list.size(); i++) {
                builder.append(list.get(i)).append(" ");
            }
            builder.append("\n");
        }
        System.out.println(builder);
        scanner.close();
    }

    public static void makePermutations(List<List<Integer>> result,
                                        List<Integer> original,
                                        List<Integer> current,
                                         int size) {
        if (current.size() == size) {
            result.add(new ArrayList<>(current));
            return;
        }

        for (int i = 0; i < original.size(); i++) {
            current.add(original.get(i));
            makePermutations(result, original, current, size);
            current.remove(current.size() - 1);
        }
    }
}
