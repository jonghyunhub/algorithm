package programmers.backtracking;

import java.util.ArrayList;
import java.util.List;

public class PartialSum {
    public static void main(String[] args) {
        int n = 4;
        int[][] solution = solution(n);
        for (int[] ints : solution) {
            for (int anInt : ints) {
                System.out.print(anInt + " ");
            }
            System.out.println();
        }
    }

    public static int[][] solution(int n) {
        final List<List<Integer>> answer = new ArrayList<>();

        calBackTrack(1, 6, 0, new ArrayList<>(), answer);

        return answer.stream().map(list ->
                        list.stream().mapToInt(i -> i).toArray())
                .toArray(int[][]::new);
    }


    private static void calBackTrack(int start, int n, int sum, List<Integer> list, List<List<Integer>> answer) {
        if (sum == 6) {
            answer.add(list);
            return;
        }

        for (int i = start; i <= n; i++) {
            if (sum + i > 6) {
                break;
            }
            ArrayList<Integer> newList = new ArrayList<>(list);
            newList.add(i);
            calBackTrack(i + 1, n, sum + i, newList, answer);
        }
    }
}
