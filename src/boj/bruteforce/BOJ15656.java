package boj.bruteforce;

import java.util.*;

// https://www.acmicpc.net/problem/15656
public class BOJ15656 {

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
        makePermutationsWithRepetition(result, original, new ArrayList<>(), m);
        for (List<Integer> list : result) {
            for (int i = 0; i < list.size(); i++) {
                builder.append(list.get(i)).append(" ");
            }
            builder.append("\n");
        }
        System.out.println(builder);
        scanner.close();
    }


    // 중복 순열 생성
    public static void makePermutationsWithRepetition(List<List<Integer>> result,
                                                      List<Integer> original,
                                                      List<Integer> current,
                                                      int size) {
        if (current.size() == size) {
            result.add(new ArrayList<>(current));
            return;
        }

        for (int i = 0; i < original.size(); i++) {
            current.add(original.get(i));
            makePermutationsWithRepetition(result, original, current, size);
            current.remove(current.size() - 1); // 백 트래킹
        }
    }
}
