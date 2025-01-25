package boj.bruteforce;

import java.util.*;

// https://www.acmicpc.net/problem/15650
public class BOJ15650 {

    /**
     * N : 1~N 까지의 수열
     * M : 각각의 수열중 선택하는 조합의 원소 갯수
     * N,M을 입력받아서 오름차순의 모든 조합을 생성해야한다.
     * @param args
     */
    public static void main(String[] args) {
        final Scanner scanner = new Scanner(System.in);
        final int n = scanner.nextInt();
        final int m = scanner.nextInt();
        final List<Integer> original = new ArrayList<>();
        for (int i = 1; i <= n; i++)
            original.add(i);
        final List<List<Integer>> answer = new ArrayList<>();
        makeCombinations(answer, original, new ArrayList<>(), 0, m);
        for (List<Integer> list : answer) {
            for (int i = 0; i < list.size(); i++) {
                System.out.print(list.get(i));
                if(i == list.size()-1) System.out.println();
                else System.out.print(" ");
            }
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
            makeCombinations(result, original, current, i + 1, size - 1);
            current.remove(current.size() - 1);
        }

    }
}
