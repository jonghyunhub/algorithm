package programmers.dp;

import java.util.Scanner;

public class LIS {

    public static void main(String[] args) {
        int[] arr1 = {1, 4, 2, 3, 1, 5, 7, 3};
        int[] arr2 = {3,2,1};
        int[] arr3 = {1, 2, 3};
        int[] arr4 = {1, 1, 1};
        int[] arr5 = {10, 20, 10, 30, 20, 50};
        final LIS lis = new LIS();

        Scanner scanner = new Scanner(System.in);
        int n = scanner.nextInt();

        int[] arr = new int[n];
        for (int i = 0; i < n; i++) {
            arr[i] = scanner.nextInt(); // 두 번째 줄의 숫자들 읽기
        }

        scanner.close();
        System.out.print(lis.solution(arr));
    }

    /**
     * [todo]
     * 1. 입력 배열과 똑같은 크기의 LIS 베열을 만든다.
     * 2. "각 원소에 해당하는 LIS 값"을 LIS 배열에 저장한다.
     * - 예시 arr :  [1,4,2,3,1,5,7,3]
     * - 예시 LIS :  [1,2,2,3,3,4,5,5]
     * 3. LIS의 점화식을 구한다.
     * - LIS[1] = 1
     * - LIS[n] = MAX(LIS[k]) + 1 (단, 0 <  k < n, arr[k] < arr[n]) = LIS[n-1] + 1 (단, arr[n-1] < arr[n])
     * - LIS[n] = MAX(LIS[k]) (단, 0 < k < n, arr[k] >= arr[n]) = LIS[n-1] (단, arr[n-1] >= arr[n])
     * 4. LIS 배열의 최대값을 반환한다. => LIS 배열의 마지막 원소
     * <p>
     * [성능 분석]
     * - 시간 복잡도 : O(N)
     *
     * @param arr
     * @return
     */
    public int solution(int[] arr) {
        int[] LIS = new int[arr.length];
        for (int i = 0; i < arr.length; i++) {
            if (i == 0) {
                LIS[i] = 1;
                continue;
            }

            if (arr[i - 1] < arr[i]) {
                LIS[i] = LIS[i - 1] + 1;
                continue;
            }

            LIS[i] = LIS[i - 1];
        }

        return LIS[arr.length - 1];
    }
}
