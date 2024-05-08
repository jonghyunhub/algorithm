package programmers.array;

import java.util.Arrays;

public class ModifyProcession {
    public static void main(String[] args) {
        ModifyProcession modifyProcession = new ModifyProcession();
        int[][] a = {{1, 2}, {2, 3}};
        int[][] b = {{3, 4}, {5, 6}};
        int[][] solution = modifyProcession.solution(a, b);
        System.out.println(Arrays.toString(solution));
    }

    public int[][] solution(int[][] arr1, int[][] arr2) {
        // arr1 : [n x m] 행렬, arr2 : [m x k] 행렬, answer : [n x k] 행렬
        int n = arr1.length; // arr1 의 행 갯수(arr1.length)
        int m = arr2.length; // arr1 의 열 갯수(arr1[0].length) = arr2 의 행 갯수(arr2.length)
        int k = arr2[0].length; // arr2 의 행 갯수(arr2[0].length
        int[][] answer = new int[n][k];

        // answer[i][j] = a[i][0] * b[0][j] + a[i][1] * b[1][j] + ... + a[i][m] * b[m][j]
        for (int i = 0; i < n; i++)
            for (int j = 0; j < k; j++)
                for (int l = 0; l < m; l++)
                    answer[i][j] += arr1[i][l] * arr2[l][j];

        return answer;
    }
}
