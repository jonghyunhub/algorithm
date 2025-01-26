package boj.bruteforce;

import java.util.*;

// https://www.acmicpc.net/problem/15652
public class BOJ15652 {
    public static void main(String[] args) {
        final Scanner scanner = new Scanner(System.in);
        final int n = scanner.nextInt();
        final int m = scanner.nextInt();
        final List<Integer> original = new ArrayList<>();
        final List<List<Integer>> result = new ArrayList<>();
        for (int i = 1; i <= n; i++) original.add(i);
        makeCombinations(result, original, new ArrayList<>(), 0, m);
        for (List<Integer> list : result) {
            for (Integer i : list) {
                System.out.print(i + " ");
            }
            System.out.println();
        }
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
