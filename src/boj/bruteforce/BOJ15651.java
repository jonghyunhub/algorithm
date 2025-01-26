package boj.bruteforce;

import java.util.*;

// https://www.acmicpc.net/problem/15651
public class BOJ15651 {

    public static void main(String[] args) {
        final Scanner scanner = new Scanner(System.in);
        final StringBuilder builder = new StringBuilder();
        final int n = scanner.nextInt();
        final int m = scanner.nextInt();
        final List<Integer> original = new ArrayList<>();
        final List<List<Integer>> result = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            original.add(i + 1);
        }
        makeCombinations(result, original, new ArrayList<>(), 0, m);
        for (List<Integer> list : result) {
            for (int i = 0; i < list.size(); i++) {
                builder.append(list.get(i) + " ");
            }
            builder.append("\n");
        }
        System.out.println(builder);
        scanner.close();
    }


    public static void makeCombinations(List<List<Integer>> result,
                                        List<Integer> original,
                                        List<Integer> current,
                                        int now, int size) {
        if (size <= 0) {
            result.add(new ArrayList<>(current));
            return;
        }

        for (int i = now; i < original.size(); i++) {
            current.add(original.get(i));
            makeCombinations(result, original, current, i, size-1);
            current.remove(current.size() - 1);
        }
    }
}
