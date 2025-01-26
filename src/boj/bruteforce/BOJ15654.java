package boj.bruteforce;

import java.util.*;

// https://www.acmicpc.net/problem/15654
public class BOJ15654 {

    public static void main(String[] args) {
        final Scanner scanner = new Scanner(System.in);
        final int n = scanner.nextInt();
        final int m = scanner.nextInt();
        final StringBuilder builder = new StringBuilder();
        final List<List<Integer>> result = new ArrayList<>();
        final List<Integer> original = new ArrayList<>();
        for (int i = 0; i < n; i++){
            int num = scanner.nextInt();
            original.add(num);
        }
        Collections.sort(original); // 오름차순 정렬
        final boolean[] used = new boolean[n];
        for (int i = 0; i < n; i++) used[i] = false;
        makePermutations(result, original, new ArrayList<>(), used, m);
        for (List<Integer> list : result) {
            for (Integer i : list) {
                builder.append(i).append(" ");
            }
            builder.append("\n");
        }
        System.out.println(builder);
        scanner.close();
    }

    public static void makePermutations(List<List<Integer>> result,
                                        List<Integer> original,
                                        List<Integer> current,
                                        boolean[] used, int size) {
        if (current.size() >= size) {
            result.add(new ArrayList<>(current));
            return;
        }

        for (int i = 0; i < original.size(); i++) {
            if(used[i]) continue;
            current.add(original.get(i));
            used[i] = true;

            makePermutations(result, original, current, used, size);

            current.remove(current.size() - 1);
            used[i] = false;
        }
    }


}
