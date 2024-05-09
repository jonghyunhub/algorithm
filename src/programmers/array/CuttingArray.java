package programmers.array;

// https://school.programmers.co.kr/learn/courses/30/lessons/87390
public class CuttingArray {

    public static void main(String[] args) {
        CuttingArray cuttingArray = new CuttingArray();
        int n = 3;
        long left = 2, right = 5;
        int[] result = cuttingArray.solution(n, left, right);
    }

    public int[] solution(int n, long left, long right) {
        int[][] init = initInput(n);
        int[] arr = makeSingleDimensionList(n, init);
        return cutArr(left, right, arr);
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
