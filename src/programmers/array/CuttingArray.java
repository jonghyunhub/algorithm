package programmers.array;

import java.util.ArrayList;
import java.util.List;

// https://school.programmers.co.kr/learn/courses/30/lessons/87390
public class CuttingArray {

    public static void main(String[] args) {
        CuttingArray cuttingArray = new CuttingArray();
        int n = 3;
        long left = 2, right = 5;
        int[] result = cuttingArray.solution2(n, left, right);
    }


    // 실제 배열 구현 풀이 -> 문제 인자가 너무 많아서 메모리 초과 일어남
    // 1 ≤ n ≤ 10^7
    // 0 ≤ left ≤ right < n^2
    // right - left < 105
    public int[] solution1(int n, long left, long right) {
        int[][] init = initInput(n);
        int[] arr = makeSingleDimensionList(n, init);
        return cutArr(left, right, arr);
    }


    /**
     * 수식 풀이
     * @param n
     * @param left : left = p1 * n + q1,  arr[p1][q1]
     * @param right : right = p2 * n + q2, arr[p2][q2]
     * @return
     */
    public int[] solution2(int n, long left, long right) {
        List<Long> answer = new ArrayList<>();
        for(long idx = left; idx <= right; idx++) {
            long p = idx / n + 1;
            long q = idx % n + 1;
            answer.add(Math.max(p, q));
        }
        return answer.stream().mapToInt(Long::intValue).toArray();
    }


    private int[][] initInput(int n) {
        int[][] input = new int[n + 1][n + 1];
        for (int i = 1; i <= n; i++)
            for (int j = 1; j <= n; j++) {
                input[i][j] = i;
                input[j][i] = i;
            }
        return input;
    }

    private int[] makeSingleDimensionList(int n, int[][] input) {
        int[] arr = new int[n * n];
        for (int i = 1; i <= n; i++)
            for (int j = 1; j <= n; j++) {
                arr[(i - 1) * n + (j - 1)] = input[i][j];
            }
        return arr;
    }

    private static int[] cutArr(long left, long right, int[] arr) {
        int[] answer = new int[(int) (right - left + 1)];
        for (long idx = left; idx <= right; idx++) {
            answer[(int) (idx - left)] = arr[(int) idx];
        }
        return answer;
    }
}
