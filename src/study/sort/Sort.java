package study.sort;

import java.util.Arrays;

public class Sort {
    public static void main(String[] args) {
        int[] data = {1, 9, 3, 2};
        selectSort(data);
        System.out.println(Arrays.toString(data));
    }

    /**
     * [버블 정렬]
     * 시간복잡도 : O(N^2)
     */
    public static void bubbleSort(int[] data) {
        for (int i = 0; i < data.length; i++) {
            for (int j = 0; j < data.length - 1 - i; j++) {
                if (data[j] > data[j + 1]) {
                    int tmp = data[j];
                    data[j] = data[j + 1];
                    data[j + 1] = tmp;
                }
            }
        }
    }

    /**
     * [선택 정렬]
     * 시간복잡도  : O(N^2)
     */
    public static void selectSort(int[] data) {
        for (int i = 0; i < data.length - 1; i++) {
            int minIndex = i;  // 최솟값의 위치를 저장

            // 최솟값 찾기
            for (int j = i + 1; j < data.length; j++) {
                if (data[j] < data[minIndex]) {
                    minIndex = j;
                }
            }

            // 찾은 최솟값과 한 번만 교환
            if (minIndex != i) {
                int tmp = data[i];
                data[i] = data[minIndex];
                data[minIndex] = tmp;
            }
        }
    }


}
