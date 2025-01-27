package boj.bruteforce;

import java.util.*;

// https://www.acmicpc.net/problem/15655
public class BOJ15655 {
    // 입력받은 숫자로 조합 생성
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
        makeCombinations(result, original, new ArrayList<>(), 0, m);
        for (List<Integer> list : result) {
            for (int i = 0; i < list.size(); i++) {
                builder.append(list.get(i)).append(" ");
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
            makeCombinations(result, original, current, i + 1, size - 1);
            current.remove(current.size() - 1); // 백 트래킹
        }
    }
}
