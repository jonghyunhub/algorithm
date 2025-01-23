package boj.bruteforce;

import java.util.*;

// https://www.acmicpc.net/problem/15649
public class BOJ15649 {
    public static void main(String[] args) {
        final Scanner scanner = new Scanner(System.in);
        final int n = scanner.nextInt();
        final int m = scanner.nextInt();
        final List<Integer> original = new ArrayList<>();
        for (int i = 1; i <= n; i++) {
            original.add(i);
        }
        final List<List<Integer>> answer = new ArrayList<>();
        final boolean[] used = new boolean[original.size()];
        for (int i = 0; i < original.size(); i++) {
            used[i] = false;
        }
        makePermutations(answer, original, new ArrayList<>(), used, m);
        for (List<Integer> list : answer) {
            for (int i=0; i<list.size(); i++)
                System.out.print(list.get(i) + " ");
            System.out.println();
        }
        scanner.close();
    }

    /**
     * size 길이를 가지는 순열의 집합을 생성한다
     *
     * @param result   : 생성한 순열의 집합 리스트
     * @param original : 원본 리스트
     * @param current  : 현재 순열의 리스트
     * @param used     : 현재 사용한 원소 체크하는 배열
     * @param size     : 생성해야하는 순열의 크기
     */
    public static void makePermutations(List<List<Integer>> result,
                                        List<Integer> original,
                                        List<Integer> current,
                                        boolean[] used, int size) {
        if (current.size() == size) {
            final List<Integer> copy = new ArrayList<>(current);
            result.add(copy);
            return;
        }

        for (int i = 0; i < original.size(); i++) {
            if (used[i]) continue; // 이미 사용한 숫자는 스킵

            current.add(original.get(i));
            used[i] = true;
            makePermutations(result, original, current, used, size);

            // 백트래킹
            current.remove(current.size() - 1);
            used[i] = false;
        }
    }
}
